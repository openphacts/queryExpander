package uk.ac.manchester.cs.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.Dataset;
import org.openrdf.query.algebra.Compare;
import org.openrdf.query.algebra.Compare.CompareOp;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.ValueConstant;
import org.openrdf.query.algebra.ValueExpr;
import org.openrdf.query.algebra.Var;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.IMSMapper;

/**
 * Extends the QueryWriterModelVisitor to insert replacement URI received from an IMSMapper.
 * 
 * @author Christian
 */
public class QueryReplaceAndWriteVisitor extends QueryWriterModelVisitor{
    
    //FULL_EXPAND currently untested with the Option nesting.
    //private static final boolean DO_FULL_EXPAND = false;
       
    //Maps the Values used for a URI with the list of URIs that this value could represent.
    private Map<String,List<URI>> mappings = new  HashMap<String,List<URI>>(); 
    
    //Service that provides the URI mappings.
    private IMSMapper mapper;
    
    //Counter to ensure that all the temporary variables have unique names.
    private int variableCounter =  0;
    
    private List<String> parameters;
    private URI inputURI;
    
    private final String lensUri;
    
    /**
     * Sets up the visitor for writing the query.
     * 
     * @param dataSet dataSets listed in the original Queries FROM clause.
     * @param mapper The service that will offers replacement URIs for each (none predicate) URI found in the query.
     * @param contexts List of Contexts retrieved using the ContextListerVisitor.
     */
    QueryReplaceAndWriteVisitor (Dataset dataset, List<String> parameters, URI inputURI, IMSMapper mapper, String lensUri){
        super(dataset);
        this.mapper = mapper;  
        this.parameters = parameters;
        this.inputURI = inputURI;
        this.lensUri = lensUri;
    }
    
    @Override
    public QueryWriterModelVisitor clone(){
        return new QueryReplaceAndWriteVisitor(originalDataSet, parameters, inputURI, mapper, lensUri);
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
    private List<URI> getMappings(URI uri) throws QueryExpanderException{
        if (context == null){
            return mapper.getMatchesForURI(uri, lensUri);            
        } else {
            if (context.hasValue()){
                List<URI> results = mapper.getSpecificMatchesForURI(uri, context.getValue().stringValue(), lensUri);
                return results;
            } else {
                return mapper.getMatchesForURI(uri, lensUri);   
            }
        }
    }
    
    private List<URI> getMappings(ValueExpr uriArg) throws QueryExpanderException {
        Value value;
        if (uriArg instanceof ValueConstant){
            ValueConstant vc = (ValueConstant)uriArg;
            value = vc.getValue();
        } else if (uriArg instanceof Var){
            Var var = (Var)uriArg;
            if (var.hasValue()){
               value = var.getValue();
            } else {
                throw new QueryExpanderException ("Expected a URI but found : " + uriArg);        
            }
        } else {
            throw new QueryExpanderException ("Expected a URI but found : " + uriArg);
        }
        if (value instanceof URI){
            return getMappings((URI)value);
        } else {
            throw new QueryExpanderException ("Expected a URI but found : " + uriArg);            
        }
    }

    @Override                 
     /**
     * Close the context (GRAPH clause) and any optional clauses opened inside the graph anding a filter if required.
     * <p>
     * This method has three steps.
     * <ol>
     * <li> Close any optionals opened inside the Graph Clause. </li>
     * <li> Add any required URI filters </li>
     * <li> Close the context. </li>
     * </ol>    
     * <p>
     * Subclasses with overwrite this method to add behavior such as adding URi replacement filters.
     */
    void closeContext (String caller){
        if (SHOW_DEBUG_IN_QUERY) {
            queryString.append(" # IN closeContext " + unionsInGraph);
            newLine();
        }
        closeInsideContext(caller);
        //Add any required URI filters
        //if there are non no filters need to be added.
        if (!(mappings.isEmpty())){
            for (String variableName:mappings.keySet()){
                List<URI> uriList = mappings.get(variableName);
                if (uriList.isEmpty()){
                    addUnbound(variableName);
                } else {
                    addFilter(variableName, uriList);
                }
            }
            //Clear the mappings so they are no closed again.
            mappings = new  HashMap<String,List<URI>>(); 
        }
        closeTheContext(caller);
    }

    private void addUnbound(String variableName){
        newLine();
        queryString.append("FILtER (");
        //queryString.append("!(bound(");
        //queryString.append(variableName);
        //queryString.append(")) || ");
        queryString.append(variableName);
        queryString.append(" = \"No Mappings Found\")");
    }

    private void addFilter(String variableName, List<URI> uriList){
        newLine();
        queryString.append("FILTeR (");
        queryString.append(variableName);
        queryString.append(" = <");
        queryString.append(uriList.get(0));
        queryString.append(">");
        for (int i = 1; i < uriList.size(); i++){
            queryString.append(" || ");
            queryString.append(variableName);
            queryString.append(" = <");
            queryString.append(uriList.get(i));
            queryString.append(">");
        }
        queryString.append(")");
    }

    private void mapParameter(String variableName) throws QueryExpanderException{
        //If the name is not in the parameter list nothing needs to be done
        if (!(parameters.contains(variableName))) return;
        //if there is already a paramter mapping nothing needs to be done
        if (mappings.containsKey(variableName)) return;
        List<URI> list = getMappings(inputURI);
//        if (list.isEmpty()){
//            list.add(inputURI);
//        }
       //Store the list for adding the filter.
        mappings.put(variableName, list);
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
        meet(var);         
        if (!var.isAnonymous()){
            String name = "?" + var.getName();
            if (parameters.contains(name)){
                mapParameter(name);
            }
       }
    }

    /**
     * Writes a compare where one of the values is known to be a URI which may need replacing.
     * <p>
     * <ol>
     * <li> Write the non URI arguement. </li>
     * <li> Writes the operator. </li>
     * <li> Gets the List of replacement URIs (if any) </li>
     * <ol>
     * <li> If the List is null or empty: Just writes the list </li>
     * <li> If the list has one URI: Just write that URI inclduing the &lt; and &gt; </li>
     * <li> If the list has more than one URI: Expands the filter to include each of the mapped URIs
     *     Seperated by AND or OR as appropriate. </li>
     * </ol></ol>
     * @param compareOp The comparison operator. Note: Only Equals and  Not equals make sence.
     * @param normalArg The none URI arguement.
     * @param uriArg The URI arguement
     * @throws QueryExpanderException If uriArg is not a URI, compareOp is not "=" or "!=" or a mapping exception.
     */
    private void writeCompareWithURI(CompareOp compareOp, ValueExpr normalArg, ValueExpr uriArg) throws QueryExpanderException {
        queryString.append("(");
        normalArg.visit(this);
        queryString.append(" ");
        queryString.append(compareOp.getSymbol());
        List<URI> uriList = this.getMappings(uriArg);
        if (uriList == null || uriList.isEmpty()){
            uriArg.visit(this);
        } else {
            queryString.append(" <");
            queryString.append(uriList.get(0));
            queryString.append(">");
            for (int i = 1; i< uriList.size(); i++){
                switch (compareOp){
                    case EQ: 
                        queryString.append(" || ");        
                        break;                    
                    case NE: 
                        queryString.append(" && ");        
                        break;
                    default:  //LT, LE, GE, GT do not make sense applied to a URI: 
                        throw new QueryExpanderException ("Did not expect " + compareOp + " in a Compare with URIs");
                }
                normalArg.visit(this);
                queryString.append(" ");
                queryString.append(compareOp.getSymbol());
                queryString.append(" <");
                queryString.append(uriList.get(i));
                queryString.append(">");
            }
        }
        queryString.append(")");
   }

    /**
     * Write a compare that does not include a URi by writing the three parts. 
     */
    private void writeCompareWithoutURI (CompareOp operator, ValueExpr leftArg,  ValueExpr rightArg) 
            throws QueryExpanderException{
        queryString.append("(");
        leftArg.visit(this);    
        queryString.append(" ");
        queryString.append(operator.getSymbol());
        queryString.append(" ");
        rightArg.visit(this);
        queryString.append(")");
    }
    
    /**
     * Determines if a ValueExpr contains a URI.
     * @param valueExpr Any non null ValueExpr
     * @return True if and only if the expresssion holds a URI
     */
    private boolean isURI(ValueExpr valueExpr){
        //ystem.out.println(valueExpr);
        if (valueExpr instanceof ValueConstant){
            ValueConstant vc = (ValueConstant)valueExpr;
            Value value = vc.getValue();
            return value instanceof URI;
        } else if (valueExpr instanceof Var){
            Var var = (Var)valueExpr;
            if (var.hasValue()){
                Value value = var.getValue();
                return value instanceof URI;
            //} else {
                //ystem.out.println(" no value");
            }
        //} else {
            //ystem.out.println (" not a Var or ValueConstant");
        }
        return false;
    }
    
    @Override
    /**
     * Checks if the compare incluses a URi and calls the appropriate method writeCompareWithURI or writeCompareWithoutURI.
     * @param cmpr 
     * @throws QueryExpanderException 
     */
    public void meet(Compare cmpr) throws QueryExpanderException {
        //ystem.out.println(cmpr);
        if (isURI(cmpr.getRightArg())){
            if (isURI(cmpr.getLeftArg())) {
                throw new QueryExpanderException ("Unexpected compare with two URIs; " + cmpr);
            } else {
                writeCompareWithURI(cmpr.getOperator(), cmpr.getLeftArg(), cmpr.getRightArg());
            }
        } else {
            if (isURI(cmpr.getLeftArg())) {
                writeCompareWithURI( cmpr.getOperator(), cmpr.getRightArg(), cmpr.getLeftArg());
            } else {
                writeCompareWithoutURI(cmpr.getOperator(), cmpr.getLeftArg(), cmpr.getRightArg());
            } 
        }
    }

    @Override
    /**
     * Checks if the Variable is a URI and if so writes the mapped list otherwise just writes the variable
     * @param decribeVariable Variable which may be a URI
     * @throws QueryExpanderException 
     */
    void writeDescribeVariable(ValueExpr decribeVariable) throws QueryExpanderException{
        if (isURI(decribeVariable)){
            //See if there are any mapped URIs
            List<URI> mappedURIs = getMappings(decribeVariable);
            if (mappedURIs == null){
                //OK no mapped URIs so just fall back to the normal behavior.
                queryString.append(extractName(decribeVariable));
            } else {
                //Write the mapped URIs
                for (URI uri:mappedURIs){
                    queryString.append(getUriString(uri));
                }
            }
        } else {
            //OK not a URI so just fall back to the normal bahaviour
            queryString.append(extractName(decribeVariable));
        }
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

    @Override
    protected String writeSubQuery(TupleExpr tupleExpr) throws QueryExpanderException{
        QueryReplaceAndWriteVisitor writer = 
                new QueryReplaceAndWriteVisitor(originalDataSet, parameters, inputURI, mapper, lensUri);
        tupleExpr.visit(writer);
        return writer.getQuery();
    }
    
    public static String convertToQueryString(TupleExpr tupleExpr, Dataset dataSet, List<String> placeholders, 
            URI replacementVariable, IMSMapper mapper, List<String> requiredAttributes, String lensUri) throws QueryExpanderException{
        QueryReplaceAndWriteVisitor writer = 
                new QueryReplaceAndWriteVisitor(dataSet, placeholders, replacementVariable, mapper, lensUri);
        tupleExpr.visit(writer);
        return writer.getQuery();
    }
}
