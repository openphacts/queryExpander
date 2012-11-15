package uk.ac.man.cs.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.BindingSet;
import org.openrdf.query.Dataset;
import org.openrdf.query.algebra.Add;
import org.openrdf.query.algebra.And;
import org.openrdf.query.algebra.ArbitraryLengthPath;
import org.openrdf.query.algebra.Avg;
import org.openrdf.query.algebra.BNodeGenerator;
import org.openrdf.query.algebra.BindingSetAssignment;
import org.openrdf.query.algebra.Bound;
import org.openrdf.query.algebra.Clear;
import org.openrdf.query.algebra.Coalesce;
import org.openrdf.query.algebra.Compare;
import org.openrdf.query.algebra.CompareAll;
import org.openrdf.query.algebra.CompareAny;
import org.openrdf.query.algebra.Copy;
import org.openrdf.query.algebra.Count;
import org.openrdf.query.algebra.Create;
import org.openrdf.query.algebra.Datatype;
import org.openrdf.query.algebra.DeleteData;
import org.openrdf.query.algebra.Difference;
import org.openrdf.query.algebra.Distinct;
import org.openrdf.query.algebra.EmptySet;
import org.openrdf.query.algebra.Exists;
import org.openrdf.query.algebra.Extension;
import org.openrdf.query.algebra.ExtensionElem;
import org.openrdf.query.algebra.Filter;
import org.openrdf.query.algebra.FunctionCall;
import org.openrdf.query.algebra.Group;
import org.openrdf.query.algebra.GroupConcat;
import org.openrdf.query.algebra.GroupElem;
import org.openrdf.query.algebra.IRIFunction;
import org.openrdf.query.algebra.If;
import org.openrdf.query.algebra.In;
import org.openrdf.query.algebra.InsertData;
import org.openrdf.query.algebra.Intersection;
import org.openrdf.query.algebra.IsBNode;
import org.openrdf.query.algebra.IsLiteral;
import org.openrdf.query.algebra.IsNumeric;
import org.openrdf.query.algebra.IsResource;
import org.openrdf.query.algebra.IsURI;
import org.openrdf.query.algebra.Join;
import org.openrdf.query.algebra.Label;
import org.openrdf.query.algebra.Lang;
import org.openrdf.query.algebra.LangMatches;
import org.openrdf.query.algebra.LeftJoin;
import org.openrdf.query.algebra.Like;
import org.openrdf.query.algebra.Load;
import org.openrdf.query.algebra.LocalName;
import org.openrdf.query.algebra.MathExpr;
import org.openrdf.query.algebra.Max;
import org.openrdf.query.algebra.Min;
import org.openrdf.query.algebra.Modify;
import org.openrdf.query.algebra.Move;
import org.openrdf.query.algebra.MultiProjection;
import org.openrdf.query.algebra.Namespace;
import org.openrdf.query.algebra.Not;
import org.openrdf.query.algebra.Or;
import org.openrdf.query.algebra.Order;
import org.openrdf.query.algebra.OrderElem;
import org.openrdf.query.algebra.Projection;
import org.openrdf.query.algebra.ProjectionElem;
import org.openrdf.query.algebra.ProjectionElemList;
import org.openrdf.query.algebra.QueryModelNode;
import org.openrdf.query.algebra.QueryModelVisitor;
import org.openrdf.query.algebra.QueryRoot;
import org.openrdf.query.algebra.Reduced;
import org.openrdf.query.algebra.Regex;
import org.openrdf.query.algebra.SameTerm;
import org.openrdf.query.algebra.Sample;
import org.openrdf.query.algebra.Service;
import org.openrdf.query.algebra.SingletonSet;
import org.openrdf.query.algebra.Slice;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.Str;
import org.openrdf.query.algebra.Sum;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Union;
import org.openrdf.query.algebra.ValueConstant;
import org.openrdf.query.algebra.ValueExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.ZeroLengthPath;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.man.cs.openphacts.queryexpander.QueryType;

/**
 *
 * @author Christian
 */
public class QueryWriterModelVisitor implements QueryModelVisitor<QueryExpanderException>{
    
    //Builder to write the query to bit by bit
    StringBuilder queryString = new StringBuilder();
   
    //Used to add the FROM clause
    Dataset originalDataSet;
    
    //The current context (GRAPH clause) the writer is in. (or null if not in a context.
    Var context = null;
        
    //Flag that the writing of an Optional has been delayed until a Graph clause is added
    boolean swapGraphAndOptional = false;
    
    //List of the number of options clauses pushed under the graph clause
    int optionInGraph = 0;
    
    //Count of the number of { to start a union delayed until the context is started
    int delayedUnionOpenings = 0;
    
    //Keep track of the number of uions started inside the graph so these can be closed before the graph (and filters)
    int unionsInGraph = 0;
    
    //Keep track of the unions closed early to avoid double closing 
    int unionsClosedEarly = 0;

    //List of the Contexts (including null) for all the Statements not yet met.
    ArrayList<Var> contexts;
    
    //This is the name of all the functions found to go in the select clause.
    ArrayList<String> namesInExtensions;
    
    //This names temp variables to the functions that should go in their place 
    Map<String, String> extensionMappings;
    
    private boolean whereOpen = false;
    
    private boolean inConstruct = false;
    
    private boolean inSelect = false;
    
    private String propertyPath = null;
    
    final boolean SHOW_DEBUG_IN_QUERY = true;
    
    //private int nextAnon = 1;
    
    //private Map<String,String> anonMapper;
    
    /**
     * Sets up the visitor for writing the query.
     * 
     * @param dataSet dataSets listed in the original Queries FROM clause.
     * @param contexts List of Contexts retrieved using the ContextListerVisitor.
     */
    QueryWriterModelVisitor(Dataset dataSet){
        originalDataSet = dataSet;
    }

    @Override
    public QueryWriterModelVisitor clone(){
        return new QueryWriterModelVisitor(originalDataSet);
    }
    
    @Override
    public void meet(Add add) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(ArbitraryLengthPath alp) throws QueryExpanderException {
        //Limited implenation expanded as discovered so far
        TupleExpr path = alp.getPathExpression();
        if (path instanceof StatementPattern){
            meetArbitraryLengthPath(alp, (StatementPattern)path);
        } else if (path instanceof Union){
            meetArbitraryLengthPath(alp, (Union)path);
        } else {
            throw new UnsupportedOperationException
                    ("ArbitraryLengthPath with none StatementPattern or Union path not yet supported");
        }
    }

    private void meetArbitraryLengthPath(ArbitraryLengthPath alp, StatementPattern sp) throws QueryExpanderException{
        beforeStatmentPattern(sp);
        Var object = sp.getObjectVar();
        writeStatementPart(sp.getSubjectVar());
        sp.getPredicateVar().visit(this);
        if (object.isAnonymous() && !(object.hasValue())){
            queryString.append("+/");
            propertyPath = object.getName();  
        } else {
            queryString.append("*");
            sp.getObjectVar().visit(this);
        }
        afterStatmentPattern(sp);
    }
    
    private void meetArbitraryLengthPath(ArbitraryLengthPath alp, Union union) throws QueryExpanderException{
        TupleExpr leftExpr = union.getLeftArg();
        if (!(leftExpr instanceof StatementPattern)){
            throw new UnsupportedOperationException
                ("ArbitraryLengthPath with Union only supported if left is a StatementPattern not.");                            
        }
        StatementPattern leftStatement = (StatementPattern)leftExpr;
        TupleExpr rightExpr = union.getRightArg();
        if (!(rightExpr instanceof StatementPattern)){
            throw new UnsupportedOperationException
                ("ArbitraryLengthPath with Union only supported if right is a StatementPattern");                            
        }
        StatementPattern rightStatement = (StatementPattern)rightExpr;
        if (leftStatement.getSubjectVar().hasValue()){
            if (!leftStatement.getSubjectVar().getValue().equals(rightStatement.getSubjectVar().getValue())){
                throw new UnsupportedOperationException
                    ("ArbitraryLengthPath with Union only supported if Subjects have the same value.");                                    
            }
        } else if (!(leftStatement.getSubjectVar().equals(rightStatement.getSubjectVar()))){
            throw new UnsupportedOperationException
                ("ArbitraryLengthPath with Union only supported if Subjects are the same");                                  
        }
        if (leftStatement.getObjectVar().hasValue()){
            if (!leftStatement.getObjectVar().getValue().equals(rightStatement.getObjectVar().getValue())){
                throw new UnsupportedOperationException
                    ("ArbitraryLengthPath with Union only supported if Objects have the same value.");                                    
            }
        } else if (!(leftStatement.equals(rightStatement.getObjectVar()))){
            throw new UnsupportedOperationException
                ("ArbitraryLengthPath with Union only supported if Subjects are the same");                             
        }
        beforeStatmentPattern(leftStatement);
        beforeStatmentPattern(rightStatement);
        writeStatementPart(leftStatement.getSubjectVar());
        queryString.append("(");
        leftStatement.getPredicateVar().visit(this);
        queryString.append("|");
        rightStatement.getPredicateVar().visit(this);
        queryString.append(")+ ");
        writeStatementPart(leftStatement.getObjectVar());
        afterStatmentPattern(leftStatement);
        afterStatmentPattern(rightStatement);
    }

    @Override
    public void meet(And and) throws QueryExpanderException {
        queryString.append("(");
        and.getLeftArg().visit(this);
        queryString.append(" && ");
        and.getRightArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(Avg avg) throws QueryExpanderException {
        queryString.append("AVG(");
        avg.getArg().visit(this);
        queryString.append(") ");
    }

    /**
     * Replaces an annonomous variable with a named variable.
     * <p>
     * The reason for this is that the semantic sugar ; method of writing statements 
     *    allows the same annonous variable to be used more than once.
     * @param name 
     * /
    void writeAnon(String name){
        //-anon-1
        String numberPart = name.substring(6);
        short anonNumber = Short.parseShort(numberPart); 
        short anonBig = (short) (anonNumber / 26);
        short anonSmall = (short) (anonNumber % 26);
        char[] ending = Character.toChars(65 + anonSmall);
        queryString.append("_:");
        queryString.append(ending);
        if (anonBig > 0) {
            ending = Character.toChars(64 + anonBig);
            queryString.append(ending);        
        }
    }*/
    
    /*void writeAnon(String name){
        if (anonMapper == null){
            anonMapper = new HashMap<String, String>();
        } 
        String subValue = anonMapper.get(name);
        if (subValue == null){
            subValue = "_:_" + this.nextAnon;
            nextAnon++;
            anonMapper.put(name, subValue);
        }
        queryString.append(subValue);
    }*/
    
    private boolean writeInnerName(String name){
        if (name.length() < 30) return false;
        if (!(name.contains("-"))) return false;
        String actualName = name.substring(0, name.indexOf('-'));
        String tail = name.substring(name.indexOf('-'));
        if (tail.matches("\\-\\w+\\-\\w+\\-\\w+\\-\\w+\\-\\w+")){
            queryString.append(" ?");
            queryString.append(actualName);
            queryString.append(" ");
            return true;
        }
        return false;
    }
    
    void writeAnon(String name){
        if (writeMappedExtension(name)) return;
        if (propertyPath != null){
            propertyPath = null;
        } else if (name.startsWith("-anon-") || name.startsWith("nps-x-")){
            String numberPart = name.substring(6);
            queryString.append(" _:_");
            queryString.append(numberPart);
        } else if(writeInnerName(name)){
            //do nothing already written
        } else {
            propertyPath = name;
            queryString.append("/");
        }
    }

    private boolean writeMappedExtension(String name){
        if (extensionMappings != null){
            //ystem.out.println("extensionMappings = " + extensionMappings);
            String replace = extensionMappings.get(name);
            if (replace != null){
               queryString.append(replace); 
               return true;
            }
        }        
        return false;
    }
    
     @Override
    public void meet(BindingSetAssignment bsa) throws QueryExpanderException {
        queryString.append("BINDINGS");
        for(String name:bsa.getBindingNames()){
            queryString.append(" ?");
            queryString.append(name);
        }
        queryString.append("{");
        if (SHOW_DEBUG_IN_QUERY) queryString.append("#meet(BindingSetAssignment bsa)");
        newLine();
        for (BindingSet bindingSet:bsa.getBindingSets()){
            queryString.append(" (");
            for(String name:bsa.getBindingNames()){
                addValue(bindingSet.getValue(name));
            }
            queryString.append(") ");
        }
        queryString.append(" }");
        newLine();
    }

   @Override
    public void meet(BNodeGenerator bng) throws QueryExpanderException {
        queryString.append(" BNODE() ");
    }

    @Override
    public void meet(Bound bound) throws QueryExpanderException {
        queryString.append("BOUND(");
        bound.getArg().visit(this);
        queryString.append(") ");        
    }

    @Override
    public void meet(Clear clear) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Coalesce clsc) throws QueryExpanderException {
        queryString.append("COALESCE(");
        List<ValueExpr> children = clsc.getArguments();
        children.get(0).visit(this);
        for (int i = 1; i< children.size(); i++){
            queryString.append(" , ");
            children.get(i).visit(this);
        }
        queryString.append(") ");     
    }

    @Override
    public void meet(Compare cmpr) throws QueryExpanderException {
        queryString.append("(");
        cmpr.getLeftArg().visit(this);
        queryString.append(" ");
        queryString.append(cmpr.getOperator().getSymbol());
        queryString.append(" ");
        cmpr.getRightArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(CompareAll ca) throws QueryExpanderException {
        throw new QueryExpanderException("CompareAl not supported yet.");
    }

    @Override
    public void meet(CompareAny ca) throws QueryExpanderException {
        throw new QueryExpanderException("CompareAny not supported yet.");
    }

    @Override
    public void meet(Copy copy) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Count count) throws QueryExpanderException {
        queryString.append("COUNT(");
        count.getArg().visit(this);
        queryString.append(") ");
    }

    @Override
    public void meet(Create create) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Datatype dtp) throws QueryExpanderException {
        queryString.append(" DATATYPE(");
        dtp.getArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(DeleteData dd) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Difference dfrnc) throws QueryExpanderException {
        dfrnc.getLeftArg().visit(this);
        queryString.append(" MINUS {");
        dfrnc.getRightArg().visit(this);
        queryString.append(" } ");
        if (SHOW_DEBUG_IN_QUERY) queryString.append(" #Difference");
        newLine();
    }

    @Override
    public void meet(Distinct dstnct) throws QueryExpanderException {
        TupleExpr tupleExpr = dstnct.getArg();
        if (tupleExpr instanceof Projection){
            if  (writeAsSubQuery(dstnct)){
                //all Done.
            } else {
                meet ((Projection)tupleExpr, " DISTINCT");
            }
        } else {
            throw new QueryExpanderException("Distinct only supported followed by Projection.");
        }
    }

    @Override
    public void meet(EmptySet es) throws QueryExpanderException {
        throw new QueryExpanderException("EmptySet not supported yet.");
    }

    @Override
    public void meet(Exists exists) throws QueryExpanderException {
        queryString.append(" EXISTS {");
        exists.getSubQuery().visit(this);
        queryString.append(" } ");
        if (SHOW_DEBUG_IN_QUERY) queryString.append("#EXITS");
        newLine();
    }

    @Override
    public void meet(Extension extnsn) throws QueryExpanderException {
        //I assume that this is also part of the ProjectionElemList so not required again.
        //extnsn.getElements();
        List<ExtensionElem> extensionElems = extnsn.getElements();
        for (ExtensionElem extensionElem: extensionElems){
            extensionElem.visit(this);
        }
        extnsn.getArg().visit(this);
    }

    @Override
    public void meet(ExtensionElem ee) throws QueryExpanderException {
        String name = ee.getName();
        if (name.startsWith("-") || name.startsWith("_")){
            //will be writen by writeanon so not here
            return;
        }
        if (whereOpen){
            queryString.append(" BIND");
        }
        queryString.append(" (");
        ee.getExpr().visit(this);
        queryString.append(" as ?");
        queryString.append(ee.getName());        
        queryString.append(") ");
        if (whereOpen){
            queryString.append(" . ");
        }
    }

    @Override
    public void meet(Filter filter) throws QueryExpanderException {
        writeWhereIfRequired(filter, "filter");
        newLine();
        if (writeNotPredicate(filter)) return;
        if (writeHaving(filter)) return;
        queryString.append("FILTER (");
        filter.getCondition().visit(this);
        queryString.append(" )");
        //Many Arguements add the brackets but false does not
        filter.getArg().visit(this);

    }
    
    /**
     * This captures where the filter is a having
     * @param filter
     * @return 
     */
    private boolean writeHaving(Filter filter) throws QueryExpanderException{
        //May prove to be incomplete
        if (this.whereOpen) return false;
        //Must add to the mappings as normally lookahead stops when it hit
        extensionMappings.putAll(ExtensionMapperVisitor.getMappings(filter.getArg()));
        TupleExpr arg = filter.getArg();
        arg.visit(this);
        newLine();
        queryString.append("HAVING ");
        this.closeWhereIfRequired();
        filter.getCondition().visit(this);
        return true;
    }

    /**
     * This captures the case where the predicate path of a negated predicate is used.
     * 
     * For example { ?x !rdf:type ?y }
     */
    private boolean writeNotPredicate(Filter filter) throws QueryExpanderException{
        ValueExpr conditionExpr = filter.getCondition();
        if (!(conditionExpr instanceof Compare)) return false;
        Compare compare = (Compare)conditionExpr;
        if (!(compare.getOperator().getSymbol().equals("!="))) return false;
        TupleExpr agrExpression = filter.getArg();
        if (!(agrExpression instanceof StatementPattern)) return false;
        StatementPattern statementPattern = (StatementPattern)agrExpression;
        if (! (compare.getLeftArg() instanceof Var)) return false;
        Var left = (Var)compare.getLeftArg();
        if (!left.isAnonymous()) return false;
        if (!(left.equals(statementPattern.getPredicateVar()))) return false;
        beforeStatmentPattern(statementPattern);
        writeStatementPart(statementPattern.getSubjectVar());
        queryString.append(" !");
        compare.getRightArg().visit(this);
        writeStatementPart(statementPattern.getObjectVar()); 
        afterStatmentPattern(statementPattern);
        return true;
    }
    
    @Override
    public void meet(FunctionCall fc) throws QueryExpanderException {
        String uriString = fc.getURI();
        try {
            URIImpl uri = new URIImpl(uriString);
            queryString.append("<");
            queryString.append(uri);
            queryString.append(">");
        } catch (IllegalArgumentException ex){
            //Not a uriString so must be pure
            queryString.append(uriString);                
        }
        queryString.append("(");
        List<ValueExpr> args = fc.getArgs();
        if (!args.isEmpty()) {
           args.get(0).visit(this);
        }
        for (int i = 1; i < args.size(); i++){
            queryString.append(" ,");
            args.get(i).visit(this);
        }
        queryString.append(")");
    }

    @Override
    public void meet(Group group) throws QueryExpanderException {
        group.getArg().visit(this);
        closeWhereIfRequired();
        Set<String> groupings = group.getGroupBindingNames();
        if (groupings.size() > 0){
            newLine();
            queryString.append("GROUP BY ");
            for (String grouping:groupings){
                if (!writeMappedExtension(grouping)) {
                   queryString.append(" ?");
                   queryString.append(grouping);
                }
            }
            queryString.append(" ");
        }
    }

    @Override
    public void meet(GroupConcat gc) throws QueryExpanderException {
        queryString.append("GROUP_CONCAT(");
        if (gc.isDistinct()) queryString.append(" DISTINCT ");
        gc.getArg().visit(this);
        if (gc.getSeparator() != null){
            queryString.append(" ; SEPARATOR=");
            gc.getSeparator().visit(this);
        }
        queryString.append(") ");
    }

    @Override
    public void meet(GroupElem ge) throws QueryExpanderException {
        throw new QueryExpanderException("GroupElem not supported yet.");
    }

    @Override
    public void meet(If i) throws QueryExpanderException {
        queryString.append("IF ( ");
        i.getCondition().visit(this);
        queryString.append(" , ");
        i.getResult().visit(this);
        queryString.append(" , ");
        i.getAlternative().visit(this);
        queryString.append(") ");        
    }

    @Override
    public void meet(In in) throws QueryExpanderException {
        //So far in all examples the parse replaces the in with alteratives
        throw new QueryExpanderException("In not supported yet.");
    }

    @Override
    public void meet(InsertData id) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Intersection i) throws QueryExpanderException {
        writeWhereIfNotInWhere(i, "Intersection");
        if (SHOW_DEBUG_IN_QUERY){
            queryString.append("#Intersection"); 
            newLine();
        }
        i.getLeftArg().visit(this);
        i.getRightArg().visit(this);
    }

    @Override
    public void meet(IRIFunction irif) throws QueryExpanderException {
        queryString.append(" IRI(");
        irif.getArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(IsBNode ibn) throws QueryExpanderException {
        queryString.append(" ISBLANK(");
        ibn.getArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(IsLiteral il) throws QueryExpanderException {
        queryString.append(" ISLITERAL(");
        il.getArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(IsNumeric in) throws QueryExpanderException {
        queryString.append(" ISNUMERIC(");
        in.getArg().visit(this);
        queryString.append(")");    }

    @Override
    public void meet(IsResource ir) throws QueryExpanderException {
        throw new QueryExpanderException("IsResource not supported yet.");
    }

    @Override
    public void meet(IsURI isuri) throws QueryExpanderException {
        queryString.append(" ISIRI(");
        isuri.getArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(Join join) throws QueryExpanderException {
        writeWhereIfRequired(join, "join");
        if (join.getLeftArg() instanceof BindingSetAssignment){
            join.getRightArg().visit(this);
            closeWhereIfRequired();
            join.getLeftArg().visit(this);
        } else {
            join.getLeftArg().visit(this);
            join.getRightArg().visit(this);
        }
    }

    private void writeWhereIfNotInWhere(TupleExpr tupleExpr, String caller) throws QueryExpanderException {
        if (!whereOpen){
            writeWhere(caller);
        }
    }
    
    private void writeWhereIfRequired(TupleExpr tupleExpr, String caller) throws QueryExpanderException {
        if (!whereOpen){
           if (!ExtensionFinderVisitor.hasExtension(tupleExpr)){
                writeWhere(caller);
            }
        }
    }

    private void writeWhere(String caller) throws QueryExpanderException {
        newLine();
        queryString.append("WHERE {");
        whereOpen= true;
        if (SHOW_DEBUG_IN_QUERY){
            queryString.append("#openWhere in " + caller); 
            newLine();
        }
    }
    
    @Override
    public void meet(Label label) throws QueryExpanderException {
        throw new QueryExpanderException("Label not supported yet.");
    }

    @Override
    public void meet(Lang lang) throws QueryExpanderException {
        queryString.append(" LANG(");
        lang.getArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(LangMatches lm) throws QueryExpanderException {
        queryString.append(" LANGMATCHES(");
        lm.getLeftArg().visit(this);
        queryString.append(" ,");
        lm.getRightArg().visit(this);
        queryString.append(")");
    }

    //@Override
    /**
     * 
     * @param lj
     * @throws QueryExpanderException 
     */
    public void meet(LeftJoin lj) throws QueryExpanderException {
        
        writeWhereIfRequired(lj, "left join");
        //The leftArg is the stuff outside of the optional.
        //May be a SingletonSet in which case nothing is written
        lj.getLeftArg().visit(this);

        //If context is null no GRAPH clause is open 
        if (context == null){
            //If contexts is empty is just subqueries so no swap required 
            if (!contexts.isEmpty() && statementsInNextGraph() > statementsInExpression(lj.getRightArg())){
                //There are statements in the graph which will be written after the optional is closed
                //For example this happens if there is mmore than one Optional clause in a single graph.
                //So the wrting of the Optional is delayed until the GRAPH clause is added.
                swapGraphAndOptional = true;
            } else {
                //No need to delay so write the OPTIONAL clause
                newLine();
                queryString.append("OPTIONAL { ");
                if (SHOW_DEBUG_IN_QUERY) queryString.append("#left join ");
            }
            //Write the Optional part
            lj.getRightArg().visit(this);
            //Write any filters in the OPTIONAL clause
            //This is Filters in the original query not uriString replacement filters.
            if (lj.hasCondition()){
                newLine();
                queryString.append("    FILTER ");
                lj.getCondition().visit(this);
            }
            //Close the Optional
            newLine();
            queryString.append(" } ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#OPTIONAL leftJoin");
            newLine();
        } else {
            //Already in a Context (GRAPH clause)
            //For example because there are non optional statements, or more than one optional clause.
            //So open the optional
            newLine();
            queryString.append("OPTIONAL { ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#leftJoin");
           //Record that we opened the optional in side the graph so graph closes it first
            optionInGraph++;
            //Write the Optional part
            lj.getRightArg().visit(this);
            //Write any filters in the OPTIONAL clause
            //This is Filters in the original query not uriString replacement filters.
            if (lj.hasCondition()){
                newLine();
                queryString.append("    FILTER ");
                lj.getCondition().visit(this);
            }
        }
        //If there is an optional open close it.
        if (optionInGraph > 0){
            newLine();
            queryString.append(" } "); 
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#OPTIONAL by optionInGraph");
            //This may be an optional inside another optional so only close one.
            optionInGraph--;
            newLine();
        }
    }
    
    /**
     * Preforms a look ahead to see how many statements will be in the next Graph clause.
     * <p>
     * In practice it uses the list of contexts passed in the constructor to do the look ahead.
     * This was done as the next statements may be in a completely different branch of the tree.
     * @return Number of statements in the Graph Clause
     */
    private int statementsInNextGraph(){
        //Starts at the 0 element of the contexts list as meet(Statement) removes context that have been met.
        //If you are not in a grahp just return 0
        if (contexts.get(0) == null){
            return 0;
        }
        //Use the list of count how many are the same.
        int i;
        for (i = 0; i < contexts.size(); i++){
            if (!(contexts.get(0).equals(contexts.get(i)))){
                //Found one different so return the count
                return i;
            }
        }
        //End of the list so return the count.
        return i;
    }

    /**
     * Counts the number of statements in this query or sub query.
     * @param tupleExpr query or sub query
     * @return Number of statements met.
     * @throws QueryExpanderException Unlikely but just in case.
     */
    private int statementsInExpression(TupleExpr tupleExpr) throws QueryExpanderException{
        //Could be done with a lister that counts instead of lists but this reuses code.
        ArrayList<Var> localContexts = ContextListerVisitor.getContexts(tupleExpr);
        return localContexts.size();
    }
    
    @Override
    public void meet(Like like) throws QueryExpanderException {
        throw new QueryExpanderException("Like not supported yet.");
    }

    @Override
    public void meet(Load load) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(LocalName ln) throws QueryExpanderException {
        throw new QueryExpanderException("LocalName not supported yet.");
    }

    @Override
    public void meet(MathExpr me) throws QueryExpanderException {
        queryString.append("(");
        me.getLeftArg().visit(this);
        //queryString.append(" ");
        queryString.append(me.getOperator().getSymbol());
        //queryString.append(" ");
        me.getRightArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(Max max) throws QueryExpanderException {
        queryString.append("MAX(");
        max.getArg().visit(this);
        queryString.append(") ");
    }

    @Override
    public void meet(Min min) throws QueryExpanderException {
        queryString.append("MIN(");
        min.getArg().visit(this);
        queryString.append(") ");
    }

    @Override
    public void meet(Modify modify) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(Move move) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meet(MultiProjection mp) throws QueryExpanderException {
        throw new QueryExpanderException("MultiProjection not supported yet.");
    }

    @Override
    public void meet(Namespace nmspc) throws QueryExpanderException {
        throw new QueryExpanderException("Namespace not supported yet.");
    }

    @Override
    public void meet(Not not) throws QueryExpanderException {
        ValueExpr inner = not.getArg();
        if (inner instanceof Exists){
            queryString.append(" NOT"); //ouch no space after the not. Lexer can not handle double whitespace after a not!
            inner.visit(this);
        } else {
            queryString.append(" (!");  
            inner.visit(this);
            queryString.append(")");
        }
    }

    @Override
    public void meet(Or or) throws QueryExpanderException {
        queryString.append("(");
        or.getLeftArg().visit(this);
        queryString.append(" || ");
        or.getRightArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(Order order) throws QueryExpanderException {
        order.getArg().visit(this);
        closeWhereIfRequired();
        newLine();
        queryString.append("ORDER BY ");
        List<OrderElem> orderElems = order.getElements();
        for (OrderElem orderElem: orderElems){
            meet(orderElem);
        }
    }

    @Override
    public void meet(OrderElem oe) throws QueryExpanderException {
        if (oe.isAscending()){
            queryString.append("ASC(");    
        } else {
            queryString.append("DESC(");                
        }
        oe.getExpr().visit(this);
        queryString.append(") ");    
    }

    @Override
    public void meet(Projection prjctn) throws QueryExpanderException {
        if (this.inConstruct && !this.whereOpen) {
            writeWhere("prjctn");
        }
        if (writeAsSubQuery(prjctn)){
            //AllDone
        } else {
            switch (workoutQueryType(prjctn.getProjectionElemList())){
                case CONSTRUCT: 
                    writeConstruct(prjctn);
                    break;
                case SELECT:    
                    meet (prjctn, "");
                    break;
                default: 
                    throw new QueryExpanderException("Unexpected QueryType");
            }
        }
    }

    private void writeConstruct(Projection prjctn) throws QueryExpanderException{
        queryString.append("CONSTRUCT {");
        HashMap<String, ValueExpr> mappedExstensionElements = mapExensionElements(prjctn.getArg());
        meet (prjctn.getProjectionElemList(), mappedExstensionElements);
        queryString.append("} ");
        if (SHOW_DEBUG_IN_QUERY) queryString.append("#writeConstruct 1");
        newLine();
        contexts = ContextListerVisitor.getContexts(prjctn.getArg());
        prjctn.getArg().visit(this);
        closeWhereIfRequired();
    }
    
/*                switch (workoutQueryType(prjctn.getProjectionElemList())){
                case CONSTRUCT:

    /**
     * Used by sub classes to add expaned list if required.
     * 
     * Currently not used but left in if required again.
     */
    void addExpanded(Projection prjctn) throws QueryExpanderException{
    }
    
    public void meet(Projection prjctn, String modifier) throws QueryExpanderException {
        boolean extraCurly = false;
        if (this.inConstruct){
            queryString.append("{ ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("# meet(Projection prjctn, String modifier)");
            newLine();
            extraCurly = true;
        }
        queryString.append("SELECT ");
        queryString.append(modifier);
        this.inSelect = true;
        addExpanded(prjctn);
        contexts = ContextListerVisitor.getContexts(prjctn.getArg());
        //This gets the names that represent functions in the select statemenet.
        this.namesInExtensions = ExpansionNameFinderVisitor.getNamesFound(prjctn.getArg());
        if (this.namesInExtensions.isEmpty()){
            this.extensionMappings =  new HashMap<String,String>();
        } else {
            //This mapes to sub functions.
            this.extensionMappings = ExtensionMapperVisitor.getMappings(prjctn.getArg());
        }
        prjctn.getProjectionElemList().visit(this);
        newLine();
        printDataset();
        if (SHOW_DEBUG_IN_QUERY) {
            queryString.append("#Projection with modifier before where");
            queryString.append("" + this.whereOpen);
            newLine();
        }

        writeWhereIfRequired(prjctn, "meet Projection with modifier");
        prjctn.getArg().visit(this);
        closeWhereIfRequired();
        if (extraCurly){
            queryString.append("} ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#project inContruct");
            newLine();
        }
        this.inSelect = false;
     }

    /**
     * Add the Where's } unless it has already been done.
     * 
     * As the Order statement is added after the Where's }, the meet(Order) method has to close it.
     * In which case another } would cause an invalid query
     * @param expr 
     */
    private void closeProjectionUnlessOrderHasX(TupleExpr expr){
        if (expr instanceof Order) return;
        if (expr instanceof Extension){
            Extension extnsn = (Extension) expr;
            if (extnsn.getArg() instanceof Order) return;
        }
        newLine();
        queryString.append(" } ");
        if (SHOW_DEBUG_IN_QUERY) queryString.append("# WHERE");
        newLine();
    }
    
    private void closeWhereIfRequired(){
        if (whereOpen){
            newLine();
            queryString.append(" } ");
        if (SHOW_DEBUG_IN_QUERY) queryString.append("# WHERE");
            newLine();
            whereOpen = false;
        }
    }
    
    private void printDataset(){
        if (originalDataSet == null) return;
        queryString.append(originalDataSet);
    }
    
    @Override
    public void meet(ProjectionElemList pel) throws QueryExpanderException {
        List<ProjectionElem> elements = pel.getElements();
        for (ProjectionElem element:elements){
            element.visit(this);
        }
        
    }

    /**
     * Lookahead function to see what query type this ProjectionElemList.
     * <p>
     * Note: This method is built from a few test cases and is probably far from complete.
     * It will err on the side of SELECT.
     * 
     * @param pel A ProjectionElemList
     * @return CONSTRUCT or DESCRIBE if it fits the pattenr seen so far. Otherwise SELECT
     */
    private QueryType workoutQueryType(ProjectionElemList pel){
        List<ProjectionElem> elements = pel.getElements();
        if (elements.size() != 3) return QueryType.SELECT;
        if (!(elements.get(0).getTargetName().equals("subject"))) return QueryType.SELECT;
        if (!(elements.get(1).getTargetName().equals("predicate"))) return QueryType.SELECT;
        if (!(elements.get(2).getTargetName().equals("object"))) return QueryType.SELECT;
        if (!(elements.get(0).getSourceName().equals("-descr-subj"))) return QueryType.CONSTRUCT;
        if (!(elements.get(1).getSourceName().equals("-descr-pred"))) return QueryType.CONSTRUCT;
        if (!(elements.get(2).getSourceName().equals("-descr-obj"))) return QueryType.CONSTRUCT;
        return QueryType.DESCRIBE;
    }
    
    /**
     * Used by the Reduce (Construction query) to add looked ahead ExtensionElems
     *
     * @param pel
     * @param mappedExstensionElements
     * @throws QueryExpanderException 
     */
    private void meet(List<ProjectionElemList> pels, HashMap<String, ValueExpr> mappedExstensionElements) 
            throws QueryExpanderException {
        for (ProjectionElemList pel:pels){
            newLine();
            meet(pel, mappedExstensionElements);
            queryString.append(" . ");
        }
    }

    /**
     * Used by the Reduce (Construction query) to add looked ahead ExtensionElems
     *
     * @param pel
     * @param mappedExstensionElements
     * @throws QueryExpanderException 
     */
    private void meet(ProjectionElemList pel, HashMap<String, ValueExpr> mappedExstensionElements) 
            throws QueryExpanderException {
        List<ProjectionElem> elements = pel.getElements();
        for (ProjectionElem element:elements){
            meet(element, mappedExstensionElements);
            queryString.append(" ");
        }
    }

    @Override
    public void meet(ProjectionElem pe) throws QueryExpanderException {
        String sourceName = pe.getSourceName();
        //if (requiredAttributes != null){
        //    if (!(requiredAttributes.contains(sourceName))){
        //         eliminatedAttributes.add(sourceName);
        //        //requiredAttributes are written by so not written here..
        //    }
        //} else {
         if (!namesInExtensions.contains(sourceName)){
            queryString.append(" ?");
            queryString.append(sourceName);
        }
        //}
    }

    /**
     * Used by the Reduce (Construction query) to add looked ahead ExtensionElems
     * 
     * @param pe
     * @param mappedExstensionElements
     * @throws QueryExpanderException 
     */
    private void meet(ProjectionElem pe, HashMap<String, ValueExpr> mappedExstensionElements) 
            throws QueryExpanderException {
        String name = pe.getSourceName();
        ValueExpr mapped = mappedExstensionElements.get(name);
        if (mapped == null){
            queryString.append(" ?");
            queryString.append(pe.getSourceName());
        } else if (mapped instanceof BNodeGenerator) {
            writeAnon(name);
        } else {
            mapped.visit(this);
        }
    }

    @Override
    public void meet(QueryRoot qr) throws QueryExpanderException {
        throw new QueryExpanderException("QueryRoot not supported yet.");
    }

    //REDUCE is found both in CONSTRUCT AND SELECT QUERIES
    @Override
    public void meet(Reduced rdcd) throws QueryExpanderException {
        TupleExpr arg = rdcd.getArg();
        if (arg instanceof Projection){
            Projection prjctn = (Projection)arg;
            TupleExpr prjctnArg = prjctn.getArg();
            switch (workoutQueryType(prjctn.getProjectionElemList())){
                case CONSTRUCT:
                    this.inConstruct = true;
                    writeConstruct(prjctn);
                    break;
                case DESCRIBE:
                    if (prjctnArg instanceof Filter){
                        writeDescribe((Filter)prjctnArg);
                    } else {
                        throw new QueryExpanderException ("Reduced assumed to be a Describe but "
                                + "Projection element is not a Filter");
                    }
                    break;
                case SELECT:    
                    meet (prjctn, " REDUCED");
                    break;
                default: 
                    throw new QueryExpanderException("Unexpected QueryType");
            }
        }  else if (arg instanceof MultiProjection){
            //Assuming it must be construct
            MultiProjection mp = (MultiProjection)arg;
            TupleExpr prjctnArg = mp.getArg();
            queryString.append("CONSTRUCT {");
            HashMap<String, ValueExpr> mappedExstensionElements = mapExensionElements(prjctnArg);   
            contexts = ContextListerVisitor.getContexts(mp);
            meet (mp.getProjections(), mappedExstensionElements);
            queryString.append("} ");
            this.inConstruct = true;
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#Reduced MultiProjection1");
            newLine();
            if (SHOW_DEBUG_IN_QUERY) {
                queryString.append("#" + this.whereOpen);
                newLine();
            }
            mp.getArg().visit(this);
            closeWhereIfRequired();
        } else {
            throw new QueryExpanderException("Reduced with non Projection/ MultiProjection child not supported yet.");
        }
    }

    //Look ahead function to match names ProjectionElem to ExtensionElem
    //Used by reduce
    private HashMap<String, ValueExpr> mapExensionElements(TupleExpr tupleExpr) throws QueryExpanderException{
        HashMap<String, ValueExpr> mappedExstensionElements = new HashMap<String, ValueExpr>();
        if (tupleExpr instanceof Extension){
            Extension extnsn = (Extension) tupleExpr;
            List<ExtensionElem> ees =  extnsn.getElements();
            for (ExtensionElem ee:ees) {
                mappedExstensionElements.put(ee.getName(), ee.getExpr());
            }
        }
        return  mappedExstensionElements;
    }
    
    /**
     * Writes a Describe query based on a Filter that has been dettermined to be a Describe.
     * 
     * @param filter Filter tree representing a describe query
     * @throws QueryExpanderException 
     */
    private void writeDescribe(Filter filter) throws QueryExpanderException {
        contexts = ContextListerVisitor.getContexts(filter);
        queryString.append ("DESCRIBE ");
        ValueExpr condition = filter.getCondition();
        findandWriteDescribeVariable(condition);
        //Write the where bit of the decribe query
        TupleExpr arg = filter.getArg();
        if (arg instanceof StatementPattern){
            //Do nothing as only statement patter is the automatically added -descr-subj -descr-pred -descr-obj
        } else {
            filter.getArg().visit(this);
            queryString.append (" } ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append ("#writeDescribe");
            newLine();
        }
    }

    /**
     * Writes the describe Variable.
     * <p>
     * Subclasses may replace a uriString with mapped URIs
     * @param decribeVariable
     * @throws QueryExpanderException 
     */
    void writeDescribeVariable(ValueExpr decribeVariable) throws QueryExpanderException{
        queryString.append(extractName(decribeVariable));
    }
    
    /**
     * Looks through the condition for the describe variable and calls writeDescribeVariable(ValueExpr).
     * <p>
     * In a Describe query there is the statement ?-descr-subj ?-descr-pred ?-descr-obj.
     * Then there are filter conditions SameTerm(?-descr-subj, describeVariable) 
     *     and sameTerm(?-descr-obj, describeVariable) 
     * <p> 
     * This method looks for the SameTerm(?-descr-subj, describeVariable) then extracts the describeVariable
     * and calls writeDescribeVariable(ValueExpr) to do the actual writting. (So that sub classes can replace URIs)
     * @param condition
     * @throws QueryExpanderException 
     */
    private void findandWriteDescribeVariable(ValueExpr condition) throws QueryExpanderException {
        if (condition instanceof Or){
           Or or = (Or)condition;
           findandWriteDescribeVariable(or.getLeftArg());
           findandWriteDescribeVariable(or.getRightArg());
        } else if (condition instanceof SameTerm) {
           SameTerm term = (SameTerm)condition;
           String leftName = extractName(term.getLeftArg());
           if (" ?-descr-subj".equals(leftName)){
               writeDescribeVariable(term.getRightArg());
           } else {
               //ystem.out.println(leftName);
           }
        } else {
            throw new QueryExpanderException ("Expected Or when extracting DescribeVariable");
        }
    }
    
    //currently unit test fails.
    String extractName(ValueExpr expr) throws QueryExpanderException{
        if (expr instanceof Var){
            Var var = (Var)expr;
            String name = var.getName();
            if (var != null){
                return " ?" + name;
            } else {
                throw new QueryExpanderException ("Expected null name when extracting a name");
            }
        } if (expr instanceof ValueConstant){
            Value value = ((ValueConstant)expr).getValue();
            if (value instanceof URI){
                return getUriString((URI) value);
               
            } else {
                return value.stringValue();
            }
        } else {
            throw new QueryExpanderException ("Expected Var when extracting a name");
        }
    }

    @Override
    public void meet(Regex regex) throws QueryExpanderException {
        queryString.append("regex(");
        regex.getArg().visit(this);
        queryString.append(",");
        regex.getPatternArg().visit(this);
        ValueExpr flag = regex.getFlagsArg();
        if (flag != null){
            queryString.append(",");
            flag.visit(this);
        }
        queryString.append(")");
    }

    @Override
    public void meet(Sample sample) throws QueryExpanderException {
        queryString.append("SAMPLE(");
        sample.getArg().visit(this);
        queryString.append(") ");
    }

    @Override
    public void meet(SameTerm st) throws QueryExpanderException {
        queryString.append(" SAMETERM(");
        st.getLeftArg().visit(this);
        queryString.append(" ,");
        st.getRightArg().visit(this);
        queryString.append(")");
    }

    /**
     * Gets the String for a uriString.
     * 
     * Designed to be overwritten by a method that can get mapped uris.
     * 
     * @param uri
     * @return 
     */
    String getUriString(URI uri){
         return (" <" + uri.stringValue() + "> "); 
    }
    
    @Override
    public void meet(Service srvc) throws QueryExpanderException {
        writeWhereIfRequired(srvc, "service");
        //ystem.out.println(srvc);
        //ystem.out.println("BaseURI:" +srvc.getBaseURI());
        //ystem.out.println("prefix:" + srvc.getPrefixDeclarations());
        //ystem.out.println("serviceEXpr: " + srvc.getServiceExpr());
        //ystem.out.println("serviceref " + srvc.getServiceRef());
        //ystem.out.println("var: " + srvc.getServiceVars());
        //ystem.out.println("arg: " + srvc.getArg());
        newLine();
        queryString.append("SERVICE ");
        srvc.getServiceRef().visit(this);
        newLine();
        queryString.append("{ "); 
        if (SHOW_DEBUG_IN_QUERY) {
            queryString.append("# meet(Projection prjctn, String modifier)");
            newLine();
        }
        srvc.getArg().visit(this);
        newLine();
        queryString.append("} ");
        if (SHOW_DEBUG_IN_QUERY) queryString.append("#Service");
    }

    @Override
    public void meet(SingletonSet ss) throws QueryExpanderException {
        writeWhereIfRequired(ss,"SingkletonSet");
        newLine();
        //queryString.append("{} ");
        //Expected no children but just to be sure.
        ss.visitChildren(this);
    }

    /**
     * Write the var.
     * 
     * Sub classes will do fancy things here.
     * 
     * @param var Var to be written.
     * @throws QueryExpanderException 
     */
    void writeStatementPart(Var var) throws QueryExpanderException{
        meet(var);
    }

    @Override
    public void meet(Slice slice) throws QueryExpanderException {
        if (isAsk(slice)){
            queryString.append("ASK ");
            contexts = ContextListerVisitor.getContexts(slice);
            slice.getArg().visit(this);
            queryString.append("} ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#Slice ASK");
            newLine();
        } else {
            if (writeAsSubQuery(slice)){
                //All Done
            } else {
                slice.getArg().visit(this);
                if (slice.hasLimit()){
                    newLine();
                    queryString.append("LIMIT ");
                    queryString.append(slice.getLimit());     
                }
                if (slice.hasOffset()){
                    newLine();
                    queryString.append("OFFSET ");
                    queryString.append(slice.getOffset());     
                }
            }
        }
    }

     private boolean isAsk(Slice slice){
        if (!(slice.hasLimit())) return false;
        if (slice.getLimit() > 1) return false;
        TupleExpr arg = slice.getArg();
        if (arg instanceof Reduced){
            arg = ((Reduced)arg).getArg();
        } else if (arg instanceof Distinct){
            arg = ((Distinct)arg).getArg();
        } 
        if (arg instanceof Projection){
            return false;
        }
        return true;
    }
    
    //Stetement Pattern split inb three as meetArbitraryLengthPath method process StatementPatterns
    //And these also need to handle context.
    @Override
    public void meet(StatementPattern sp) throws QueryExpanderException  {
        beforeStatmentPattern(sp);
        
        writeStatementPattern(sp);
        afterStatmentPattern(sp);
    }
    
    private void beforeStatmentPattern(StatementPattern sp) throws QueryExpanderException{
        writeWhereIfRequired(sp,"StatementPattern");
        //Double check that then statement has the expected context 
        if (contexts.get(0) == null){
            if (sp.getContextVar() != null) {
                throw new QueryExpanderException ("Expected null context in statement: " + sp);
            }
        } else {
            if (!(contexts.get(0).equals(sp.getContextVar()))) {
               throw new QueryExpanderException ("Expected context  " + contexts.get(0) + " in statement: " + sp); 
            }
        }       
        //Remove the context from the list, so it only has future contexts in it.
        contexts.remove(sp.getContextVar());       
        
        openNewContextIfRequired(sp);         

        //Add an optional pushed down if required.
        if (swapGraphAndOptional) {
            newLine();
            queryString.append("OPTIONAL { ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#meet(StatementPattern sp) OPTIONAL");
            swapGraphAndOptional = false;
        }
        while (delayedUnionOpenings > 0){
            queryString.append("{ ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#meet(StatementPattern sp) UNION " + unionsInGraph);
            newLine();           
            delayedUnionOpenings--;
            unionsInGraph++;
        }
    }
    
    private void writeStatementPattern(StatementPattern sp) throws QueryExpanderException {
        //No need to write the describe pattern the parser will do that
        if (isDescribePattern(sp)) return;
        //    //No need to write a pattern for eliminated elements.
        //if (canEliminate(sp)) return; 
        if (propertyPath == null) newLine(); 
        if (propertyPath != null && propertyPath.equals(sp.getObjectVar().getName())){
            //sp.getObjectVar().isAnonymous()){
            queryString.append("^");
            propertyPath = null;
            sp.getPredicateVar().visit(this);
            //Inverted so write the subject now.
            writeStatementPart(sp.getSubjectVar());  
        } else {
            //write the actual normal statement.
            writeStatementPart(sp.getSubjectVar());
            sp.getPredicateVar().visit(this);
            writeStatementPart(sp.getObjectVar());
        }
        if (propertyPath == null){
            queryString.append(". ");
        }
    }

    private void afterStatmentPattern(StatementPattern sp) throws QueryExpanderException{
        //Now use the look ahead provided by the context list. 
        if (contexts.isEmpty()){
            //Last Statement so close and flush filters
            closeContext("context empty");
        } else if (context == null){
            //Not in a context so flush replacement filters        
           closeContext("context null"); 
        } else if (context.equals(contexts.get(0))){
            //staying in context so keep it open
        } else {
            //New context coming so close the context
            closeContext(" new context coming");
        }    
    }
    
    /**
     * Close the context (GRAPH clause) and any optional clauses opened inside the graph.
     * 
     * If this method is called while not in a context no action is taken.
     * <p>
     * Subclasses with overwrite this method to add behavior such as adding URi replacement filters.
     */
    void closeContext(String caller){
       //Only do something is inside a context
       if (context != null){
            closeInsideContext(caller);
            closeTheContext(caller);
        }
    }

    final void closeInsideContext(String caller){
        //If any optional clauses where opened inside the graph these need to be closed first.
        //Not strictly required here but keeps it similar to overwritten methods.
        while (optionInGraph > 0){
            newLine();
            queryString.append(" } ");   
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#OPTIONAL from close context optionInGraph " + caller);;
            //reduce the count so it is not closed again.
            optionInGraph--;
            newLine();
        }   
        unionsClosedEarly = unionsInGraph;
        while (unionsInGraph > 0){
            queryString.append(" } ");   
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#Union from close context unionsInGraph " + caller);;
            //reduce the count so it is not closed again.
            unionsInGraph--;
            newLine();            
        }
    }
    
    /**
     * Close the context (GRAPH clause) and any optional clauses opened inside the graph.
     * 
     * If this method is called while not in a context no action is taken.
     * <p>
     * Subclasses with overwrite this method to add behavior such as adding URi replacement filters.
     */
    final void closeTheContext(String caller){
       //Only do something is inside a context
       if (context != null){
            newLine();
            queryString.append(" } ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("# close Context " + caller);
            newLine();
            //Set context to null so it is not closed again.
            context = null;
        }
    }

    /**
     * Identifies if this Statement is the one added by a design query. 
     * <p>
     * This method was built based on a few example queries so may be incomplete.
     * 
     * @param sp Statement to check for the design signature. 
     * @return True if and only if the Statement appears to be one added by a Design query.
     */
    boolean isDescribePattern(StatementPattern sp){
        if (!(sp.getSubjectVar().isAnonymous())) return false;
        if (!(sp.getPredicateVar().isAnonymous())) return false;
        if (!(sp.getObjectVar().isAnonymous())) return false;
        if (!("-descr-subj".equals(sp.getSubjectVar().getName()))) return false;
        if (!("-descr-pred".equals(sp.getPredicateVar().getName()))) return false;
        if (!("-descr-obj".equals(sp.getObjectVar().getName()))) return false;
        return true;        
    }
    
    /**
     * Checks if a new context GRAPH) needs to be opend and does so if required.
     * 
     * @param sp Statement about to be written.
     * @throws QueryExpanderException Not expected but just in case.
     */
    private void openNewContextIfRequired(StatementPattern sp) throws QueryExpanderException {
        //Check not already in a context
        if (context == null) {
           //Check statement is part of a context 
           if (sp.getContextVar() != null) {
                context = sp.getContextVar();
                newLine();
                queryString.append(" GRAPH ");
                context.visit(this);
                queryString.append(" {");   
            }
        }
    }

    @Override
    public void meet(Str str) throws QueryExpanderException {
        queryString.append(" STR(");
        str.getArg().visit(this);
        queryString.append(")");
    }

    @Override
    public void meet(Sum sum) throws QueryExpanderException {
        queryString.append("SUM(");
        sum.getArg().visit(this);
        queryString.append(") ");
    }
    
    
    @Override
    public void meet(Union union) throws QueryExpanderException {
        writeWhereIfRequired(union, "union");
        //If context is null no GRAPH clause is open 
        if (context == null){
            ArrayList<Var> unionContexts = ContextListerVisitor.getContexts(union);
            HashSet<Var> contextSet = new HashSet(unionContexts);
            if (contextSet.size() == 1 && unionContexts.get(0)!= null){
                delayedUnionOpenings++;
            } else {
                queryString.append("{");
                if (SHOW_DEBUG_IN_QUERY) {
                    queryString.append("# meet(Union union)");
                    newLine();
                } 
            }
        } else {
            queryString.append("{");
            if (SHOW_DEBUG_IN_QUERY) {
                queryString.append("# meet(Union union)");
                newLine();
            } 
        }
        union.getLeftArg().visit(this);
        newLine();
        queryString.append("} UNION {");
        union.getRightArg().visit(this);
        newLine();
        if (unionsClosedEarly <= 0){
            queryString.append("} ");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#Union");
            newLine();
            if (unionsInGraph > 0){
                unionsInGraph--;
            }
        } else {
            unionsClosedEarly--;
        }
    }
    
    @Override
    public void meet(ValueConstant vc) throws QueryExpanderException {
        Value value = vc.getValue();
        addValue(value);
    }

    @Override
    public void meet(Var var) throws QueryExpanderException {
        if (var.hasValue()){
            Value value = var.getValue();
            addValue(value);
        } else if (var.isAnonymous()){
            writeAnon(var.getName());
        } else {
            queryString.append(" ?");
            queryString.append(var.getName());
            queryString.append(" ");
        }
    }

    @Override
    public void meet(ZeroLengthPath zlp) throws QueryExpanderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void meetOther(QueryModelNode qmn) throws QueryExpanderException {
        throw new QueryExpanderException("meetOther not supported yet.");
    }
    
    private void addValue(Value value){
        if (value == null){
            queryString.append("UNDEF ");
        } else if (value instanceof URI){
            queryString.append("<");
            queryString.append(value.stringValue());
            queryString.append(">"); 
        } else {
            String stringValue = value.toString();
            //Replace each single slash with a double slash
            stringValue = stringValue.replaceAll("\\\\", "\\\\\\\\");
            queryString.append(" ");
            queryString.append(stringValue);
        }
        queryString.append(" ");
    }
    
    void newLine(){
        queryString.append("\n");
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

    private boolean writeAsSubQuery(TupleExpr tupleExpr) throws QueryExpanderException{
        if (this.whereOpen || this.inSelect || this.inConstruct){
            newLine();
            if (!this.whereOpen){
                queryString.append("WHERE ");
            }
            //Second brackets is because antonis says Virtuso wants that.
            queryString.append("{ { ");
            if (SHOW_DEBUG_IN_QUERY) {
                queryString.append("# writeAsSubQuery");
                newLine();
            }
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#open subquery");
            newLine();
            queryString.append(writeSubQuery(tupleExpr));
            newLine();
            queryString.append("} }");
            if (SHOW_DEBUG_IN_QUERY) queryString.append("#closesubquery");
            newLine(); 
            return true;
        } else {
            if (SHOW_DEBUG_IN_QUERY) {
                queryString.append("# not subQuery");
                newLine();
            }
            return false;
        }
    }
    
   /*
    * This method calls the specific visitpor again
     * It will be overrided by other sub classes.
    */
    protected String writeSubQuery(TupleExpr tupleExpr) 
            throws QueryExpanderException{
        QueryWriterModelVisitor writer = new QueryWriterModelVisitor(originalDataSet);
 
        tupleExpr.visit(writer);
        return writer.getQuery();
    }

                                
}
