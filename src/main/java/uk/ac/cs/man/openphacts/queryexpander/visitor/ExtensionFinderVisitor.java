package uk.ac.cs.man.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import org.openrdf.query.algebra.Extension;
import org.openrdf.query.algebra.ExtensionElem;
import org.openrdf.query.algebra.Join;
import org.openrdf.query.algebra.LeftJoin;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpansionException;

public class ExtensionFinderVisitor extends QueryModelVisitorBase<QueryExpansionException>{

    private boolean extensionFound;
    
    private ExtensionFinderVisitor(){
        extensionFound = false;
    }
    
    @Override
    public void meet(Extension e) throws QueryExpansionException {
        extensionFound = true;
    }
    
    @Override
    public void meet(Join join) throws QueryExpansionException {
        //No need to look father as now in statements
    }

    @Override
    public void meet(LeftJoin join) throws QueryExpansionException {
        //No need to look father as now in statements
    }
    
    public static boolean hasExtension(TupleExpr tupleExpr) throws QueryExpansionException{
        ExtensionFinderVisitor listener = new ExtensionFinderVisitor();
        tupleExpr.visit(listener);
        return listener.extensionFound;
    }
}
