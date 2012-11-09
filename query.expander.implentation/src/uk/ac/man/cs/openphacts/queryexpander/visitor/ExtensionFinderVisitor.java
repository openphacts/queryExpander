package uk.ac.man.cs.openphacts.queryexpander.visitor;

import java.util.ArrayList;
import org.openrdf.query.algebra.Extension;
import org.openrdf.query.algebra.ExtensionElem;
import org.openrdf.query.algebra.Join;
import org.openrdf.query.algebra.LeftJoin;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;

public class ExtensionFinderVisitor extends QueryModelVisitorBase<QueryExpanderException>{

    private boolean extensionFound;
    
    private ExtensionFinderVisitor(){
        extensionFound = false;
    }
    
    @Override
    public void meet(Extension e) throws QueryExpanderException {
        extensionFound = true;
    }
    
    @Override
    public void meet(Join join) throws QueryExpanderException {
        //No need to look father as now in statements
    }

    @Override
    public void meet(LeftJoin join) throws QueryExpanderException {
        //No need to look father as now in statements
    }
    
    public static boolean hasExtension(TupleExpr tupleExpr) throws QueryExpanderException{
        ExtensionFinderVisitor listener = new ExtensionFinderVisitor();
        tupleExpr.visit(listener);
        return listener.extensionFound;
    }
}
