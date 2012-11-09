/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander;

import org.apache.log4j.Logger;
import org.bridgedb.utils.ConfigReader;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.sparql.SPARQLParser;

/**
 *
 * @author Christian
 */
public class Test {
    static final Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) throws Exception{
       ConfigReader.logToConsole();
       String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "SELECT ?name ?mbox\n" +
                "WHERE  { \n" +
                "  OPTIONAL {" +
                "    GRAPH ?g1 {" +
                "    OPTIONAL {?x foaf:name  ?name .}\n" +
                "    OPTIONAL { ?x  foaf:mbox  ?mbox }\n" +
                "    Filter (?x = \"hi\") \n" +
                "  } \n" +
                "   }    }";
       SPARQLParser parser = new SPARQLParser();
       ParsedQuery parsedQuery = parser.parseQuery(query, null);
       TupleExpr tupleExpr = parsedQuery.getTupleExpr();
       logger.info(tupleExpr);
   }
}
