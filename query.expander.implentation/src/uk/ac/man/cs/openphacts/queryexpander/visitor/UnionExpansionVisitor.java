/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.visitor;

import uk.ac.man.cs.openphacts.queryexpander.ExpansionStategy;
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
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.man.cs.openphacts.queryexpander.mapper.IMSMapper;

/**
 *
 * @author Christian
 */
public class UnionExpansionVisitor extends QueryWriterModelVisitor{

    private final IMSMapper mapper;
    private final ExpansionStategy expansionStategy;

    private int unionCount = 0;
    
    HashMap<URI,URI> currentMappings  = new HashMap<URI,URI>();
    boolean inUnion = false;

    UnionExpansionVisitor (Dataset dataset, IMSMapper mapper, ExpansionStategy expansionStategy){
        super(dataset);
        this.mapper = mapper;
        this.expansionStategy = expansionStategy;
    }

    @Override
    void meetProjectionArg(TupleExpr arg) throws QueryExpanderException {
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
    public void meet(Join join) throws QueryExpanderException {
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

    private void unionOrMeet(TupleExpr expr) throws QueryExpanderException{
        if (singleGraph(expr)){
            insertUnion(expr);
        } else {
            expr.visit(this);
        }
    }

    @Override
    void writeStatementPattern(StatementPattern sp) throws QueryExpanderException {
        if (expansionStategy == ExpansionStategy.UNION_STATEMENT){
             unionStatementPattern(sp);
        } else if (expansionStategy == ExpansionStategy.UNION_GRAPH && !inUnion ) {
             unionStatementPattern(sp);
        } else {
            super.writeStatementPattern(sp);
        }
    }

    private void unionStatementPattern(StatementPattern sp) throws QueryExpanderException {
        Var subject = sp.getSubjectVar();
        Var object = sp.getObjectVar();
        List<URI> subjectMaps = getMappings(subject, sp.getContextVar());
        if (subjectMaps != null){
            URI subjectURI = subjectMaps.get(0);
            if (subjectMaps.size() > 1){
                startUnion();
            }
            queryString.append("<" + subjectURI.stringValue() + "> ");
            sp.getPredicateVar().visit(this);
            sp.getObjectVar().visit(this);
            if (subjectMaps.size() <= 1){
                queryString.append(". ");
            }
            for (int i = 1; i < subjectMaps.size(); i++){
                subjectURI = subjectMaps.get(i);
                nextUnion(" if");
                queryString.append("<" + subjectURI.stringValue() + "> ");
                sp.getPredicateVar().visit(this);
                object.visit(this);
            }
            if (subjectMaps.size() > 1){
                closeUnion();
            }
            inUnion = false;
            if (SHOW_DEBUG_IN_QUERY) queryString.append("# close union in writeStatementPattern ");
        } else {
            List<URI> objectMaps = getMappings(object, sp.getContextVar());
            if (objectMaps != null){
                URI objectURI = objectMaps.get(0);
                if (objectMaps.size() > 1){
                    startUnion();
                }
                subject.visit(this);
                sp.getPredicateVar().visit(this);
                queryString.append("<" + objectURI.stringValue() + "> ");
                if (objectMaps.size() <= 1){
                    queryString.append(". ");
                 }
                for (int i = 1; i < objectMaps.size(); i++){
                    objectURI = objectMaps.get(i);
                    nextUnion("else");
                    queryString.append("<" + objectURI.stringValue() + "> ");
                    sp.getPredicateVar().visit(this);
                    sp.getObjectVar().visit(this);
                }
                if (objectMaps.size() > 1){
                    closeUnion();
                }
            } else {
                super.writeStatementPattern(sp);
            }
        }
    }

    private void startUnion(){
        newLine();
        queryString.append("{ ");
        newLine();
        unionCount = 1;
    }
    
    private void nextUnion(String caller){
        newLine();
        queryString.append("} UNION { ");
        if (SHOW_DEBUG_IN_QUERY) {
            queryString.append("# nextUnion ");
            queryString.append("# caller");
        }
        newLine();
        unionCount++;    
    }
    
    private void closeUnion(){
        newLine();
        queryString.append("} ");
        if (SHOW_DEBUG_IN_QUERY) queryString.append("# closeUnion ");
        unionCount = 0;
    }
    
    private boolean singleGraph (TupleExpr expr) throws QueryExpanderException{
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

    private List<URI> getMappings (Var var, Var localContext) throws QueryExpanderException{
        if (var.isAnonymous()){
            Value value = var.getValue();
            if (value instanceof URI){
                List<URI> maps = getMappings((URI)value, localContext);
                if (maps.size() > 0) {
                    return maps;
                }
            }
        }
        return null;
    }

    /**
     * Retreives the mappings from this URI is any from the service.
     * <p>
     * Where applicable this function will attempt to retreive context specific mappings.
     * Where there is no context for example the Statement is not inside a Graph claus,
     * or the context is a variable all mapped URIs are retreived.
     * 
     * @param uri The URI for which replacements are to be found.
     * @return A List of replacement URIs or NULL is not replacement are returned by the mapper.
     * @throws QueryExpanderException Some expection thrown by the mapping service.
     */
    private List<URI> getMappings(URI uri, Var localContext) throws QueryExpanderException{
        if (localContext == null || expansionStategy == ExpansionStategy.UNION_ALL){
            return mapper.getMatchesForURI(uri);            
        } else {
            if (localContext.hasValue()){
                List<URI> results = mapper.getSpecificMatchesForURI(uri, localContext.getValue().stringValue());
                return results;
            } else {
                return mapper.getMatchesForURI(uri);   
            }
        }
    }
    
    private void insertUnion(TupleExpr expr) throws QueryExpanderException{
        Set<URI> uris = URIExtractorVisitor.extactURI(expr);
        ArrayList<Var> localContexts = ContextListerVisitor.getContexts(expr);
        Var localContext = null;
        if (localContexts.size() >= 1){
            localContext = localContexts.get(0);
            for (int i = 1; i < localContexts.size(); i++){
                if (localContext != null && !localContext.equals(contexts.get(i))){
                    localContext = null;
                }
            }
        } 
        
        HashMap<URI,List<URI>> mappings = new HashMap<URI,List<URI>>();
        for (URI uri:uris){
            List<URI> uriMappings = getMappings(uri, localContext);
            if (uriMappings.size() > 0){
                mappings.put(uri, uriMappings);
            } else {
            }
        }
        if (mappings.isEmpty()){
            inUnion = true;
            expr.visit(this);
            inUnion = false;
        } else {
            ArrayList<Var> holderContexts = new ArrayList<Var>(contexts);
            writeWhereIfRequired(expr, "insertUnion!");
            doUnionAlternatives(expr, mappings.keySet(), mappings, holderContexts);
            newLine();
            inUnion = false;
            closeUnion();
            if (SHOW_DEBUG_IN_QUERY) queryString.append("# insertUnion");
        }
    }

    private void doUnionAlternatives(TupleExpr expr, Set<URI> keys, HashMap<URI,List<URI>> mappings,
            ArrayList<Var> holderContexts)
            throws QueryExpanderException{
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
                    nextUnion("alternatives");
                } else {
                    startUnion();
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
     * @throws QueryExpanderException
     */
    void writeStatementPart(Var var) throws QueryExpanderException{
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

    @Override
    void writeBNodeID(String name){
        String numberPart = name.substring(6);
        queryString.append(" _:_");
        queryString.append(numberPart);     
        queryString.append("_");
        queryString.append(unionCount);
        queryString.append(" ");
    }

    /**
     * Returns the query as a string.
     * <p>
     * Works if and only if the model was visited exactly once.
     * @return query as a String
     * @throws QueryExpanderException Declared as thrown to allow calling methods to catch it specifically.
     */
    private String getQuery() throws QueryExpanderException {
        return queryString.toString();
    }

    public static String convertToQueryString(TupleExpr tupleExpr, Dataset dataSet, 
            IMSMapper mapper, ExpansionStategy expansionStategy) throws QueryExpanderException{
        UnionExpansionVisitor writer = new UnionExpansionVisitor(dataSet, mapper, expansionStategy);
        tupleExpr.visit(writer);
        return writer.getQuery();
    }

}
