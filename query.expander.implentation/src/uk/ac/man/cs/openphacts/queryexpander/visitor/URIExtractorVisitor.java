/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.visitor;

import java.util.HashSet;
import java.util.Set;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;

/**
 *
 * @author Christian
 */
public class URIExtractorVisitor extends QueryModelVisitorBase<QueryExpansionException>{

    private HashSet<URI> uris = new HashSet<URI>();

    /** 
     * Block outside access
     * 
     */
    private URIExtractorVisitor(){}

    //We dont want predicates
    @Override
    public void meet(StatementPattern sp) throws QueryExpansionException  {
        sp.getSubjectVar().visit(this);
        //do not visit sp.getPredicateVar()
        sp.getObjectVar().visit(this);
    }

    @Override
    public void meet(Var var) throws QueryExpansionException {
        if (var.hasValue()){
            Value value = var.getValue();
            if (value instanceof URI){
                uris.add((URI)value);
            }
        }
    }

    public static Set<URI> extactURI(TupleExpr tupleExpr) throws QueryExpansionException{
        URIExtractorVisitor visitor = new URIExtractorVisitor();
        tupleExpr.visit(visitor);
        return visitor.uris;
    }

}
