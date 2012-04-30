package uk.ac.cs.man.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import org.openrdf.query.algebra.ExtensionElem;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpansionException;

public class ExpansionNameFinderVisitor extends QueryModelVisitorBase<QueryExpansionException>{

    private ArrayList<String> foundNames = new ArrayList<String>();
    
    private ExpansionNameFinderVisitor(){
    }
    
    @Override
    public void meet(ExtensionElem ee) throws QueryExpansionException {
        foundNames.add(ee.getName());
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
