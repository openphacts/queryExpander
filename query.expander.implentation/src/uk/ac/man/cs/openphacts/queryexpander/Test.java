/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander;

import java.util.Set;
import org.openrdf.model.URI;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.sparql.SPARQLParser;
import uk.ac.man.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.visitor.ExpansionStategy;
import uk.ac.man.cs.openphacts.queryexpander.visitor.URIExtractorVisitor;
import uk.ac.man.cs.openphacts.queryexpander.visitor.UnionExpansionVisitor;

/**
 *
 * @author Christian
 */
public class Test {
   public static void main(String[] args) throws Exception{
       String query = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "SELECT ?predicate ?object WHERE {\n" +
                //"   { \n" +
                //"      foo:subj1 ?predicate ?object . \n" +
                //"   } UNION { \n" +
                //"     GRAPH ?g1 {" +
                "        foo:subj1 owl:sameAs ?caff .\n" +
                "        foo:subj2 foo:pred2s ?object2 .\n" +
                "        ?caff ?predicate ?object . \n" +
                //"     }\n" +
                //"   }\n" +
                " }";
        SPARQLParser parser = new SPARQLParser();
        ParsedQuery parsedQuery = parser.parseQuery(query, null);
        TupleExpr tupleExpr = parsedQuery.getTupleExpr();
        System.out.println(tupleExpr);
        DummyIMSMapper imsMapper = new DummyIMSMapper();
        imsMapper.addMapping("http://www.foo.com/subj1", "http://www.bar.com/1");
        imsMapper.addMapping("http://www.foo.com/subj2", "http://www.bar.com/2");
        String newQuery = UnionExpansionVisitor.convertToQueryString(tupleExpr, null, imsMapper, 
                ExpansionStategy.UNION_STATEMENT);
        System.out.println(newQuery);
        parsedQuery = parser.parseQuery(newQuery, null);
    }
}
