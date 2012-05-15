/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

import java.util.ArrayList;
import java.util.List;
import org.openrdf.query.Dataset;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.sparql.SPARQLParser;
import uk.ac.cs.man.openphacts.queryexpander.mapper.IMSMapper;
import uk.ac.cs.man.openphacts.queryexpander.visitor.QueryExpandAndWriteVisitor;

/**
 *
 * @author Christian
 */
public class QueryExpanderImpl implements QueryExpander{

    private static final SPARQLParser parser = new SPARQLParser();
    private static final List<String> ALL_ATTRIBUTES = null;
    
    IMSMapper imsMapper;
    
    public QueryExpanderImpl(IMSMapper imsMapper){
        this.imsMapper = imsMapper;
    }
    
    @Override
    public String expand(String originalQuery, boolean verbose) 
            throws MalformedQueryException, QueryExpansionException {
        if (verbose) System.out.println(originalQuery);
        ParsedQuery parsedQuery = parser.parseQuery(originalQuery, null); 
        TupleExpr tupleExpr = parsedQuery.getTupleExpr();
        if (verbose) System.out.println(tupleExpr);
        Dataset dataset = parsedQuery.getDataset();
        return QueryExpandAndWriteVisitor.convertToQueryString(tupleExpr, dataset, imsMapper, ALL_ATTRIBUTES);
    }
    
}
