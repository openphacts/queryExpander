/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.Dataset;
import org.openrdf.query.algebra.Join;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;
import uk.ac.man.cs.openphacts.queryexpander.mapper.IMSMapper;

/**
 *
 * @author Christian
 */
public class UnionExpansionVisitor extends QueryWriterModelVisitor{

    private final IMSMapper mapper;
    private final ExpansionStategy expansionStategy;

    HashMap<URI,URI> currentMappings  = new HashMap<URI,URI>();
    boolean inUnion = false;

    UnionExpansionVisitor (Dataset dataset, IMSMapper mapper, ExpansionStategy expansionStategy){
        super(dataset);
        this.mapper = mapper;
        this.expansionStategy = expansionStategy;
    }

    @Override
    void meetProjectionArg(TupleExpr arg) throws QueryExpansionException {
        if (expansionStategy == ExpansionStategy.UNION_ALL){
            insertUnion(arg);
        } else if (expansionStategy == ExpansionStategy.UNION_GRAPH){
            if (singleGraph(arg)){
                insertUnion(arg);
            } else {
                arg.visit(this);
            }
        } else {
            arg.visit(this);
        }
    }

    @Override
    public void meet(Join join) throws QueryExpansionException {
        if (expansionStategy == ExpansionStategy.UNION_GRAPH && !inUnion) {
            if (singleGraph(join)){
                insertUnion(join);
            } else {
                writeWhereIfRequired(join, "join");
                unionOrMeet(join.getLeftArg());
                unionOrMeet(join.getRightArg());
            }
        } else {
            super.meet(join);
        }
    }

    private void unionOrMeet(TupleExpr expr) throws QueryExpansionException{
        if (singleGraph(expr)){
            insertUnion(expr);
        } else {
            expr.visit(this);
        }
    }

    @Override
    void writeStatementPattern(StatementPattern sp) throws QueryExpansionException {
        if (expansionStategy == ExpansionStategy.UNION_STATEMENT){
             unionStatementPattern(sp);
        } else if (expansionStategy == ExpansionStategy.UNION_GRAPH && !inUnion ) {
             unionStatementPattern(sp);
        } else {
            super.writeStatementPattern(sp);
        }
    }

    private void unionStatementPattern(StatementPattern sp) throws QueryExpansionException {
        Var subject = sp.getSubjectVar();
        Var object = sp.getObjectVar();
        List<URI> subjectMaps = getMappings(subject);
        if (subjectMaps != null){
            URI subjectURI = subjectMaps.get(0);
            newLine();
            queryString.append("{ ");
            newLine();
            queryString.append("<" + subjectURI.stringValue() + "> ");
            sp.getPredicateVar().visit(this);
            sp.getObjectVar().visit(this);
            for (int i = 1; i < subjectMaps.size(); i++){
                subjectURI = subjectMaps.get(i);
                newLine();
                queryString.append("} UNION { ");
                if (SHOW_DEBUG_IN_QUERY) {
                    queryString.append("# unionStatementPattern if");
                }
                newLine();
                queryString.append("<" + subjectURI.stringValue() + "> ");
                sp.getPredicateVar().visit(this);
                object.visit(this);
            }
            newLine();
            queryString.append("} ");
            inUnion = false;
            if (SHOW_DEBUG_IN_QUERY) queryString.append("# close union in writeStatementPattern ");
        } else {
            List<URI> objectMaps = getMappings(object);
            if (objectMaps != null){
                URI objectURI = objectMaps.get(0);
                newLine();
                queryString.append("{ ");
                newLine();
                subject.visit(this);
                queryString.append("<" + objectURI.stringValue() + "> ");
                sp.getPredicateVar().visit(this);
                for (int i = 1; i < subjectMaps.size(); i++){
                    objectURI = objectMaps.get(i);
                    newLine();
                    queryString.append("} UNION {");
                    if (SHOW_DEBUG_IN_QUERY) {
                        queryString.append("# unionStatementPattern else");
                    }
                    newLine();
                    queryString.append("<" + objectURI.stringValue() + "> ");
                    sp.getPredicateVar().visit(this);
                    sp.getObjectVar().visit(this);
                }
                newLine();
                queryString.append("} ");
                if (SHOW_DEBUG_IN_QUERY) queryString.append("# writeStatementPattern ");
            } else {
                super.writeStatementPattern(sp);
            }
        }
    }

    private boolean singleGraph (TupleExpr expr) throws QueryExpansionException{
        ArrayList<Var> list = ContextListerVisitor.getContexts(expr);
       //Let the statement code handle this
        if (list.size() < 2 ){
            return false;
        }
        //Not in a graph so let the statement code handle this.
        if (list.get(0) == null) {
            return false;
        }
        Var first = list.get(0);
        for (int i = 1; i< list.size(); i++){
            if (!(first.equals(list.get(i)))){
                return false;
            }
        }
        return true;
    }

    private List<URI> getMappings (Var var) throws QueryExpansionException{
        if (var.isAnonymous()){
            Value value = var.getValue();
            if (value instanceof URI){
                List<URI> maps = mapper.getMatchesForURI((URI)value);
                if (maps.size() > 1) {
                    return maps;
                }
            }
        }
        return null;
    }

    private void insertUnion(TupleExpr expr) throws QueryExpansionException{
        Set<URI> uris = URIExtractorVisitor.extactURI(expr);
        HashMap<URI,List<URI>> mappings = new HashMap<URI,List<URI>>();
        for (URI uri:uris){
            List<URI> uriMappings = mapper.getMatchesForURI(uri);
            if (uriMappings.size() > 1){
                mappings.put(uri, uriMappings);
            } else {
            }
        }
        if (mappings.isEmpty()){
            expr.visit(this);   
        } else {
            ArrayList<Var> holderContexts = new ArrayList<Var>(contexts);
            writeWhereIfRequired(expr, "insertUnion!");
            doUnionAlternatives(expr, mappings.keySet(), mappings, holderContexts);
            newLine();
            inUnion = false;
            queryString.append("} ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("# insertUnion");
        }
    }

    private void doUnionAlternatives(TupleExpr expr, Set<URI> keys, HashMap<URI,List<URI>> mappings,
            ArrayList<Var> holderContexts)
            throws QueryExpansionException{
        URI key = keys.iterator().next();
        for (URI uri: mappings.get(key)){
            currentMappings.put(key, uri);
            //ystem.out.println(key + " -> " + uri);
            if (keys.size() > 1 ){
                HashSet remainingKeys = new HashSet(keys);
                remainingKeys.remove(key);
                doUnionAlternatives(expr, remainingKeys, mappings, holderContexts);
            } else {
                newLine();
                if (inUnion) {
                    contexts = new ArrayList<Var>(holderContexts);
                    queryString.append("} UNION {");
                    if (SHOW_DEBUG_IN_QUERY) {
                        queryString.append("# doUnionAlternatives");
                        newLine();
                    }
                } else {
                    queryString.append("{");
                    inUnion = true;
                }
                expr.visit(this);
            }
        }
        currentMappings.remove(key);
    }

    @Override
    /**
     * Write the var, or for a URI the replacement.
     * <p>
     * Called by meet(StatementPattern sp) for the subject and the object.
     * <p>
     * Checks to see if the var is a URI.
     * If it is a URI getURIVariable is used to find a possible replacement.
     * If it is not a URI the normal write method is used.
     *
     * @param var Var to be written.
     * @throws QueryExpansionException
     */
    void writeStatementPart(Var var) throws QueryExpansionException{
        if (var.isAnonymous()){
            Value value = var.getValue();
            if (value instanceof URI){
                URI original =(URI)value;
                URI replace = currentMappings.get(original);
                if (replace == null) replace = original;
                queryString.append("<" + replace.stringValue() + "> ");
             } else {
                meet(var);
            }
        } else {
            meet(var);
        }
    }

    /**
     * Returns the query as a string.
     * <p>
     * Works if and only if the model was visited exactly once.
     * @return query as a String
     * @throws QueryExpansionException Declared as thrown to allow calling methods to catch it specifically.
     */
    private String getQuery() throws QueryExpansionException {
        return queryString.toString();
    }

    public static String convertToQueryString(TupleExpr tupleExpr, Dataset dataSet, 
            IMSMapper mapper, ExpansionStategy expansionStategy) throws QueryExpansionException{
        UnionExpansionVisitor writer = new UnionExpansionVisitor(dataSet, mapper, expansionStategy);
        tupleExpr.visit(writer);
        return writer.getQuery();
    }

}
