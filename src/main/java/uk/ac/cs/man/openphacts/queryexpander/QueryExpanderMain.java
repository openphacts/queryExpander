/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

import uk.ac.cs.man.openphacts.queryexpander.mapper.DummyIMSMapper;
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
public class QueryExpanderMain implements QueryExpander{

    static final SPARQLParser parser = new SPARQLParser();
    static final List<String> ALL_ATTRIBUTES = null;
    IMSMapper imsMapper = new DummyIMSMapper();
    
    @Override
     public String expand(String originalQuery) throws MalformedQueryException, QueryExpansionException {
        ParsedQuery parsedQuery = parser.parseQuery(originalQuery, null); 
        TupleExpr tupleExpr = parsedQuery.getTupleExpr();
        System.out.println(tupleExpr);
        Dataset dataset = parsedQuery.getDataset();
        System.out.println(dataset);
        List<String> requiredAttributes = null;
        String expandedQuery = 
                QueryExpandAndWriteVisitor.convertToQueryString(tupleExpr, dataset, imsMapper, requiredAttributes);
        System.out.println(expandedQuery);
        ParsedQuery parsedQuery1 =  parser.parseQuery(expandedQuery, null); 
        TupleExpr tupleExpr1 = parsedQuery1.getTupleExpr();
        System.out.println(tupleExpr1.equals(tupleExpr));
        return expandedQuery;
    }
    
    public static void main(String[] args) throws MalformedQueryException, QueryExpansionException {
        QueryExpanderMain impl = new QueryExpanderMain();
        impl.expand("PREFIX foaf:   <http://xmlns.com/foaf/0.1/>"
            + "SELECT ?name ?mbox "
            + "FROM    <http://example.org/foaf/aliceFoaf> "
            + "WHERE"
            + "  { ?x foaf:name ?name ."
            + "  GRAPH ?g {  ?x foaf:mbox ?mbox } }");
    }
 
}
