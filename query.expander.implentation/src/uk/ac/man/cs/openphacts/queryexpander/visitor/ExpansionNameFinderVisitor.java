package uk.ac.man.cs.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import org.openrdf.query.algebra.ExtensionElem;
import org.openrdf.query.algebra.Join;
import org.openrdf.query.algebra.LeftJoin;
import org.openrdf.query.algebra.Projection;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;

public class ExpansionNameFinderVisitor extends QueryModelVisitorBase<QueryExpansionException>{

    private ArrayList<String> foundNames = new ArrayList<String>();
    
    private ExpansionNameFinderVisitor(){
    }
    
    @Override
    public void meet(ExtensionElem ee) throws QueryExpansionException {
        foundNames.add(ee.getName());
    }
    
    @Override
    public void meet(Join join) throws QueryExpansionException {
        //No need to look father as now in statements
    }

    @Override
    public void meet(LeftJoin join) throws QueryExpansionException {
        //No need to look father as now in statements
    }
    
    @Override
    public void meet(Projection prjctn) throws QueryExpansionException {
        //in subquery so stop
    }

    private ArrayList<String> getNamesFound(){
        return foundNames;
    }
    
    public static ArrayList<String> getNamesFound(TupleExpr tupleExpr) throws QueryExpansionException{
        ExpansionNameFinderVisitor listener = new ExpansionNameFinderVisitor();
        tupleExpr.visit(listener);
        return listener.getNamesFound();
    }
}
