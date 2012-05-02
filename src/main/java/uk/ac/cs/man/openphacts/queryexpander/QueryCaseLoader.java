/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *     //http://www.w3.org/TR/2012/WD-sparql11-query-20120105/

 * @author Christian
 */
public class QueryCaseLoader {

    private static class QueryCase {
        private String name;
        private String originalQuery;
        private String key;
        private String noReplaceQuery;
        
        public QueryCase() {
        }
    }
    
    private Map<String, QueryCase> queries = new LinkedHashMap<String, QueryCase>();
    
    public QueryCaseLoader(){
        loadSparql2_1();
        loadSparql2_2();
        loadSparql2_3_1a();
        loadSparql2_3_1b();
        loadSparql2_3_2();
        loadSparql2_3_3();
        loadSparql2_4();
        loadSparql2_5a();  //CONCAT(?G, " ", ?S) AS ?name
        loadSparql2_5b(); //BIND(CONCAT(?G, " ", ?S) AS ?name)
        loadSparql3_1a();
        loadSparql3_1b();
        loadSparql3_2();
        loadSparql4_2a();
        loadSparql4_2b();
        loadSparql4_2c();
        loadSparql5_2a();
        loadSparql5_2b();
        loadSparql5_2_2a();
        loadSparql5_2_2b();
        loadSparql5_2_2c();
        loadSparql5_2_3a();
        loadSparql5_2_3b();
        loadSparql5_2_3c();
        loadSparql6_1();
        loadSparql6_2();
        loadSparql6_3();
        loadSparql7a();
        loadSparql7b();
        loadSparql7c();
        loadSparql8_1_1();
        loadSparql8_1_2();
        loadSparql8_2();
        loadSparql8_3_1a();
        loadSparql8_3_1b();
        loadSparql8_3_2a();
        loadSparql8_3_2b();
        loadSparql8_3_3a();
        loadSparql8_3_3b();
        loadSparql9_2a();
        loadSparql9_2b();
        loadSparql9_2c();
        loadSparql9_2d();
        loadSparql9_2e();
        loadSparql9_2f();
        loadSparql9_2g();
        loadSparql9_2h();
        loadSparql9_2i();
        loadSparql9_2j();
        loadSparql9_2k();
        loadSparql9_2l();
        loadSparql9_2m();
        loadSparql9_2n();
        loadSparql9_2o();
        loadSparql9_2p1();
        loadSparql9_2p2();
        loadSparql9_2q();
        loadSparql9_3a();
        loadSparql9_3b();
        loadSparql9_3c();
        loadSparql9_3d();
        loadSparql10_1a1();
        loadSparql10_1a2();
        loadSparql10_1a3();
        loadSparql10_1a4();
        loadSparql10_1b();
        loadSparql10_2a();
        loadSparql10_2b();
        loadSparql11_1a1();
        loadSparql11_1a2();
        loadSparql11_1a3();
        loadSparql11_1a4();
        loadSparql11_1a5();
    }
    
    private void loadSparql2_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_1";
        queryCase.name = "Sparql Specification section 2.1 Writing a Simple Query";
        queryCase.originalQuery =
              "SELECT ?title \n"
            + "WHERE { \n"
            + "    <http://example.org/book/book1> <http://purl.org/dc/elements/1.1/title> ?title .\n"
            + "}";
        queries.put(queryCase.key, queryCase);
     }

    private void loadSparql2_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_2";
        queryCase.name = "Sparql Specification section 2.2 Multiple Matches";
        queryCase.originalQuery = 
              "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
            + "SELECT ?name ?mbox \n"
            + "WHERE \n"
            + "  { ?x foaf:name ?name .\n"
            + "    ?x foaf:mbox ?mbox \n"
            + "}";
        queries.put(queryCase.key, queryCase);
    }

   private void loadSparql2_3_1a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_1a";
        queryCase.name = "Sparql Specification section 2.3.1 Matching RDF Literals. 1st Query";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p \"cat\" }";
        queries.put(queryCase.key, queryCase);
     }
    
   private void loadSparql2_3_1b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_1b";
        queryCase.name = "Sparql Specification section 2.3.1 Matching RDF Literals 2nd query";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p \"cat\"@en }";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadSparql2_3_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_2";
        queryCase.name = "Sparql Specification section 2.3.2 Matching Literals with Numeric Types";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p 42 }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql2_3_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_3";
        queryCase.name = "Sparql Specification section 2.3.3 Matching Literals with Arbitrary Datatypes";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p \"abc\"^^<http://example.org/datatype#specialDatatype> }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql2_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_4";
        queryCase.name = "Sparql Specification section 2.4 Blank Node Labels in Query Results";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?x ?name \n"
                + "WHERE  { ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql2_5a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_5a";
        queryCase.name = "Sparql Specification section 2.5 Creating Values with Expressions 1st Query";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ( CONCAT(?G, \" \", ?S) AS ?name ) \n"
                + "WHERE  { ?P foaf:givenName ?G ; foaf:surname ?S }\n";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql2_5b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_5b";
        queryCase.name = "Sparql Specification section 2.5 Creating Values with Expressions 2ndt Query";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?name \n"
                + "WHERE  { \n"
                + "   ?P foaf:givenName ?G ; \n"
                + "      foaf:surname ?S \n"
                + "   BIND(CONCAT(?G, \" \", ?S) AS ?name)\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql2_6() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_6";
        queryCase.name = "Sparql Specification section 2.6 Building RDF Graphs";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "PREFIX org:    <http://example.com/ns#> \n"
                + " CONSTRUCT { ?x foaf:name ?name }\n"
                + "WHERE  { ?x org:employeeName ?name }\n";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql3_1a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql3_1a";
        queryCase.name = "Sparql Specification section 3.1 Restricting the Value of Strings 1st Query";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/> \n"
                + "SELECT  ?title \n"
                + "WHERE   { ?x dc:title ?title \n"
                + "          FILTER regex(?title, \"^SPARQL\") \n"
        + "        }";                             
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql3_1b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql3_1b";
        queryCase.name = "Sparql Specification section 3.1 Restricting the Value of Strings 2nd Query";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/> \n"
                + "SELECT  ?title \n"
                + "WHERE   { ?x dc:title ?title \n"
                + "          FILTER regex(?title, \"web\", \"i\" )  \n"
        + "        }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql3_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql3_2";
        queryCase.name = "Sparql Specification section 3.2 Restricting Numeric Values";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "WHERE   { ?x ns:price ?price .\n"
                + "          FILTER (?price < 30.5)\n"
                + "          ?x dc:title ?title . }\n";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql4_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql4_2a";
        queryCase.name = "Sparql Specification section 4.2 Syntax for Triple Patterns 1st query\n";
        queryCase.originalQuery = "PREFIX  dc: <http://purl.org/dc/elements/1.1/> \n"
                + "SELECT  ?title \n"
                + "WHERE   { <http://example.org/book/book1> dc:title ?title }\n";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql4_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql4_2b";
        queryCase.name = "Sparql Specification section 4.2 Syntax for Triple Patterns 2nd query\n";
        queryCase.originalQuery = "PREFIX  dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  : <http://example.org/book/>\n"
                + "SELECT  $title\n"
                + "WHERE   { :book1  dc:title  $title }\n";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql4_2c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql4_2c";
        queryCase.name = "Sparql Specification section 4.2 Syntax for Triple Patterns 3rd query";
        queryCase.originalQuery = "BASE    <http://example.org/book/>\n"
                + "PREFIX  dc: <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT  $title\n"
                + "WHERE   { <book1>  dc:title  ?title }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2a";
        queryCase.name = "Sparql Specification section 5.2 Group Graph Patterns 1st query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + "WHERE  {\n"
                + "          ?x foaf:name ?name .\n"
                + "          ?x foaf:mbox ?mbox .\n"
                + "       }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2b";
        queryCase.name = "Sparql Specification section section 5.2 Group Graph Patterns 2nd query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + "WHERE  { { ?x foaf:name ?name . }\n"
                + "         { ?x foaf:mbox ?mbox . }\n"
                + "       }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2_2a";
        queryCase.name = "Sparql Specification section 5.2.2 Scope of Filters 1st query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {  ?x foaf:name ?name .\n"
                + "    ?x foaf:mbox ?mbox .\n"
                + "    FILTER regex(?name, \"Smith\")"
                + " }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2_2b";
        queryCase.name = "Sparql Specification section 5.2.2 Scope of Filters 2nd query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {  FILTER regex(?name, \"Smith\")\n"
                + "    ?x foaf:name ?name .\n"
                + "    ?x foaf:mbox ?mbox .\n"
                + " }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2_2c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2_2c";
        queryCase.name = "Sparql Specification section 5.2.2 Scope of Filters 3rd query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {  ?x foaf:name ?name .\n"
                + "    FILTER regex(?name, \"Smith\")\n"
                + "    ?x foaf:mbox ?mbox .\n"
                + " }\n";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2_3a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2_3a";
        queryCase.name = "Sparql Specification section";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {\n"
                + "    ?x foaf:name ?name .\n"
                + "    ?x foaf:mbox ?mbox .\n"
                + "  }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2_3b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2_3b";
        queryCase.name = "Sparql Specification section";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {\n"
                + "    ?x foaf:name ?name . FILTER regex(?name, \"Smith\")\n"
                + "    ?x foaf:mbox ?mbox .\n"
                + "  }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql5_2_3c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql5_2_3c";
        queryCase.name = "Sparql Specification section";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {    ?x foaf:name ?name .\n"
                + "    {}\n"
                + "    ?x foaf:mbox ?mbox .\n"
                + "  }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql6_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql6_1";
        queryCase.name = "Sparql Specification section 6.1 Optional Pattern Matching";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + "WHERE  { ?x foaf:name  ?name .\n"
                + "         OPTIONAL { ?x  foaf:mbox  ?mbox }\n"
                + "       }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql6_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql6_2";
        queryCase.name = "Sparql Specification section 6.2 Constraints in Optional Pattern Matching";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "WHERE   { ?x dc:title ?title .\n"
                + "          OPTIONAL { ?x ns:price ?price . FILTER (?price < 30) }\n"
                + "        }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql6_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql6_3";
        queryCase.name = "Sparql Specification section 6.3 Multiple Optional Graph Patterns";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?hpage\n"
                + "WHERE  { ?x foaf:name  ?name .\n"
                + "         OPTIONAL { ?x foaf:mbox ?mbox } .\n"
                + "         OPTIONAL { ?x foaf:homepage ?hpage }\n"
                + "       }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql7a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql7a";
        queryCase.name = "Sparql Specification section 7 Matching Alternatives 1st query";
        queryCase.originalQuery = "PREFIX dc10:  <http://purl.org/dc/elements/1.0/>\n"
                + "PREFIX dc11:  <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?title\n"
                + "WHERE  { { ?book dc10:title  ?title } UNION { ?book dc11:title  ?title } }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql7b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql7b";
        queryCase.name = "Sparql Specification section 7 Matching Alternatives 2nd query";
        queryCase.originalQuery = "PREFIX dc10:  <http://purl.org/dc/elements/1.0/>\n"
                + "PREFIX dc11:  <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?x ?y\n"
                + "WHERE  { { ?book dc10:title ?x } UNION { ?book dc11:title  ?y } }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql7c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql7a";
        queryCase.name = "Sparql Specification section 7 Matching Alternatives 3rd query";
        queryCase.originalQuery = "PREFIX dc10:  <http://purl.org/dc/elements/1.0/>\n"
                + "PREFIX dc11:  <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?title ?author\n"
                + "WHERE  { { ?book dc10:title ?title .  ?book dc10:creator ?author }\n"
                + "         UNION\n"
                + "         { ?book dc11:title ?title .  ?book dc11:creator ?author }\n"
                + "       }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_1_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_1_1";
        queryCase.name = "Sparql Specification section 8.1.1 Testing For the Absence of a Pattern";
        queryCase.originalQuery = "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX  foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?person\n"
                + "WHERE {\n"
                + "    ?person rdf:type  foaf:Person .\n"
                + "    FILTER NOT EXISTS { ?person foaf:name ?name }\n"
                + "}     ";   
        //Parser moves filters up which cases numbering of aanonymous variables to be different.
        queryCase.noReplaceQuery = "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX  foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?person\n"
                + "WHERE {\n"
                + "    FILTER NOT EXISTS { ?person foaf:name ?name }\n"
                + "    ?person rdf:type  foaf:Person .\n"
                + "}     ";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_1_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_1_2";
        queryCase.name = "Sparql Specification section 8.1.2 Testing For the Presence of a Pattern";
        queryCase.originalQuery = "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX  foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?person\n"
                + "WHERE {\n"
                + "    ?person rdf:type  foaf:Person .\n"
                + "    FILTER EXISTS { ?person foaf:name ?name }\n"
                + "}";                
        //Parser moves filters up which cases numbering of aanonymous variables to be different.
        queryCase.noReplaceQuery = "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX  foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?person\n"
                + "WHERE {\n"
                + "    FILTER EXISTS { ?person foaf:name ?name }\n"
                + "    ?person rdf:type  foaf:Person .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_2";
        queryCase.name = "Sparql Specification section 8.2 Removing Possible Solutions";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT DISTINCT ?s\n"
                + "WHERE {\n"
                + "   ?s ?p ?o .\n"
                + "   MINUS {\n"
                + "      ?s foaf:givenName \"Bob\" .\n"
                + "   }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_3_1a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_3_1a";
        queryCase.name = "Sparql Specification section 8.3.1 Example: Sharing of variables 1st query";
        queryCase.originalQuery = "SELECT *\n"
                + "{ \n"
                + "  ?s ?p ?o\n"
                + "  FILTER NOT EXISTS { ?x ?y ?z }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_3_1b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_3_1b";
        queryCase.name = "Sparql Specification section 8.3.1 Example: Sharing of variables 2nd query";
        queryCase.originalQuery = "SELECT *\n"
                + "{ \n"
                + "   ?s ?p ?o \n"
                + "   MINUS \n"
                + "     { ?x ?y ?z }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_3_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_3_2a";
        queryCase.name = "Sparql Specification section 8.3.2 Example: Fixed pattern 1st query";
        queryCase.originalQuery = "PREFIX : <http://example/>\n"
                + "SELECT * \n"
                + "{ \n"
                + "  ?s ?p ?o \n"
                + "  FILTER NOT EXISTS { :a :b :c }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_3_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_3_2b";
        queryCase.name = "Sparql Specification section 8.3.2 Example: Fixed pattern 2nd query";
        queryCase.originalQuery = "PREFIX : <http://example/>\n"
                + "SELECT * \n"
                + "{ \n"
                + "  ?s ?p ?o \n"
                + "  MINUS { :a :b :c }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_3_3a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_3_3a";
        queryCase.name = "Sparql Specification section 8.3.3 Example: Inner FILTERs 1st Query";
        queryCase.originalQuery = "PREFIX : <http://example.com/>\n"
                + "SELECT * WHERE {\n"
                + "        ?x :p ?n\n"
                + "        FILTER NOT EXISTS {\n"
                + "                ?x :q ?m .\n"
                + "                FILTER(?n = ?m)\n"
                + "        }\n"
                + "}";                
        //Parser moves filters up which cases numbering of aanonymous variables to be different.
        queryCase.originalQuery = "PREFIX : <http://example.com/>\n"
                + "SELECT * WHERE {\n"
                + "        FILTER NOT EXISTS {\n"
                + "                FILTER(?n = ?m)\n"
                + "                ?x :q ?m .\n"
                + "        }\n"
                + "        ?x :p ?n\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql8_3_3b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql8_3_3b";
        queryCase.name = "Sparql Specification section 8.3.3 Example: Inner FILTERs 2nd query";
        queryCase.originalQuery = "PREFIX : <http://example/>\n"
                + "SELECT * WHERE {\n"
                + "        ?x :p ?n\n"
                + "        MINUS {\n"
                + "                ?x :q ?m .\n"
                + "                FILTER(?n = ?m)\n"
                + "        }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2a";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 1st subquery";
        queryCase.originalQuery = "PREFIX : <http://example.com/> \n"
                + "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc:   <http://purl.org/dc/elements/1.1/> \n"
                + "PREFIX rdfs:     <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT *\n"
                + "WHERE { :book1 dc:title|rdfs:label ?displayString }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2b";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 2nd subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE {\n"
                + "  ?x foaf:mbox <mailto:alice@example> .\n"
                + "  ?x foaf:knows/foaf:name ?name .\n"
                + "}";       
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2c";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 3rd subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE { \n"
                + "  ?x foaf:mbox <mailto:alice@example> .\n"
                + "  ?x foaf:knows/foaf:knows/foaf:name ?name .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2d() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2d";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 4th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE {\n"
                + "  ?x foaf:mbox <mailto:alice@example> .\n"
                + "  ?x foaf:knows{2}/foaf:name ?name .\n"
                + "\n}";     
        //Removed the {2} as the anonymous name is different
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE {\n"
                + "  ?x foaf:mbox <mailto:alice@example> .\n"
                + "  ?x foaf:knows/foaf:knows/foaf:name ?name .\n"
                + "\n}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2e() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2e";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 5th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT *\n"
                + "WHERE { ?x  foaf:mbox <mailto:alice@example> .\n"
                + "   ?x  foaf:knows [ foaf:knows [ foaf:name ?name ]]. \n"
                + "}";                
        //top is semantic sugar for below 
        //Parser also changes the order to what is below.
        //I think they are the same.
        queryCase.noReplaceQuery = "SELECT  ?x ?name\n"
                + "WHERE {\n"
                + "       ?x <http://xmlns.com/foaf/0.1/mbox> <mailto:alice@example>. \n"
                + "       _:_b <http://xmlns.com/foaf/0.1/name> ?name. \n"
                + "       _:_a <http://xmlns.com/foaf/0.1/knows> _:_b .\n"
                + "       ?x <http://xmlns.com/foaf/0.1/knows> _:_a . \n"
                + "}";
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2f() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2f";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 6th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE {\n"
                + "  ?x  foaf:mbox <mailto:alice@example> .\n"
                + "  ?x  foaf:knows ?a1 .\n"
                + "  ?a1 foaf:knows ?a2 .\n"
                + "  ?a2 foaf:name ?name .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2g() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2g";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 7th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE {\n"
                + "  ?x foaf:mbox <mailto:alice@example> .\n"
                + "  ?x foaf:knows/foaf:knows ?y .\n"
                + "  FILTER ( ?x != ?y )\n"
                + "  ?y foaf:name ?name \n"
                + "}";                
         queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2h() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2h";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 8th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE { ?x foaf:mbox <mailto:alice@example> }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2i() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2i";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 9th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE { <mailto:alice@example> ^foaf:mbox ?x }";  
        //Parser uninvets and this changes the const numbering
        queryCase.noReplaceQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE { ?x foaf:mbox <mailto:alice@example> }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2j() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2j";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 10th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE {\n"
                + "  ?x foaf:knows/^foaf:knows ?y .  \n"
                + "  FILTER(?x != ?y)\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2k() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2k";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 11th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT * \n"
                + "WHERE {\n"
                + "  ?x foaf:mbox <mailto:alice@example> .\n"
                + "  ?x foaf:knows+/foaf:name ?name .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2l() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2l";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 12th subquery";
        //doc has "?ancestor (ex:motherOf|ex:fatherOf)+ <#me>"
        queryCase.originalQuery = "PREFIX ex:   <http://www.example.com/>\n"
                + "SELECT * \n"
                + "WHERE { ?ancestor (ex:motherOf|ex:fatherOf)+ <http://example/me> }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2m() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2m";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 13th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX rdfs:     <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT * \n"
                + "WHERE { <http://example/thing> rdf:type/rdfs:subClassOf* ?type }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2n() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2n";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 14th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX  rdfs:     <http://www.w3.org/2000/01/rdf-schema#> \n"
                + "SELECT * \n"
                + "WHERE { ?x rdf:type/rdfs:subClassOf* ?type }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2o() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2o";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 15th subquery";
        queryCase.originalQuery = "PREFIX : <http://example.com/> \n"
                + "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX rdfs:     <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT * \n"
                + "WHERE { ?x ?p ?v . ?p rdfs:subPropertyOf* :property }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2p1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2p1";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 16th subquery (simplified)";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "SELECT * \n"
                + "WHERE { ?x !rdf:type ?y }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2p2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2p2";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 16th subquery";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "SELECT * \n"
                + "WHERE { ?x !(rdf:type|^rdf:type) ?y }";      
        //This is done as a UNION so expander can not tell.
        //The order of inverse first is done by orignal expander
        queryCase.noReplaceQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "SELECT ?x ?y \n"
                + "WHERE { \n"
                + "     { ?y !rdf:type ?x } \n"
                + "    UNION \n"
                + "     { ?x !rdf:type ?y } \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_2q() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_2q";
        queryCase.name = "Sparql Specification section 9.2 Property Paths Examples 17th subquery";
        queryCase.originalQuery = "PREFIX : <http://example.com/>\n"
                + "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX  rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "SELECT * \n"
                + "WHERE { :list rdf:rest*/rdf:first ?element }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_3a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_3a";
        queryCase.name = "Sparql Specification section 9.3 Cycles and Duplicates 1st query";
        queryCase.originalQuery = "PREFIX :   <http://example/>\n"
                + "SELECT * \n"
                + "{  ?s :p/:q ?o . }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_3b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_3b";
        queryCase.name = "Sparql Specification section 9.3 Cycles and Duplicates 2nd query";
        queryCase.originalQuery = "PREFIX :   <http://example/>\n"
                + "SELECT * \n"
                + "{  ?s :p ?_a .\n"
                + "   ?_a :q ?o . }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_3c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_3c";
        queryCase.name = "Sparql Specification section 9.3 Cycles and Duplicates 3rd query";
        queryCase.originalQuery = "PREFIX :   <http://example/>\n"
                + "SELECT * \n"
                + "{ :x :p+ ?o }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql9_3d() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql9_3d";
        queryCase.name = "Sparql Specification section 9.3 Cycles and Duplicates 4th query";
        queryCase.originalQuery = "PREFIX :   <http://example/>\n"
                + "SELECT * \n"
                + "{ :x :p* ?o }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql10_1a1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql10_1a1";
        queryCase.name = "Sparql Specification section 10.1 BIND: Assigning to Variables 1st query very simplified";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?price \n"
                + "{  BIND (?p * ?discount AS ?price)\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql10_1a2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql10_1a2";
        queryCase.name = "Sparql Specification section 10.1 BIND: Assigning to Variables 1st query simplified";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?price\n"
                + "{  BIND (?p*(1-?discount) AS ?price)\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql10_1a3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql10_1a3";
        queryCase.name = "Sparql Specification section 10.1 BIND: Assigning to Variables 1st query no filter";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "{  ?x ns:price ?p .\n"
                + "   ?x ns:discount ?discount\n"
                + "   BIND (?p*(1-?discount) AS ?price)\n"
                + "   ?x dc:title ?title . \n"
                + "}";                
        //Parser moves bind up which causes numbering of aanonymous variables to be different.
        //This is different to what the sparql docs say should happen!
        queryCase.noReplaceQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "WHERE { BIND (( ?p*(\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>- ?discount)) as ?price)  . \n"
                + " ?x<http://example.org/ns#price> ?p. \n"
                + " ?x<http://example.org/ns#discount> ?discount. \n"
                + " ?x<http://purl.org/dc/elements/1.1/title> ?title. \n"
                + " } ";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql10_1a4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql10_1a4";
        queryCase.name = "Sparql Specification section 10.1 BIND: Assigning to Variables 1st query";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "{  ?x ns:price ?p .\n"
                + "   ?x ns:discount ?discount\n"
                + "   BIND (?p*(1-?discount) AS ?price)\n"
                + "   FILTER(?price < 20)\n"
                + "   ?x dc:title ?title . \n"
                + "}";                
        //Parser moves filter and bind up which cases numbering of aanonymous variables to be different.
        //Note the fullstop needed after ?discount here not needed above!
        queryCase.noReplaceQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "{  \n"
                + "   FILTER(?price < 20)\n"
                + "   BIND (?p*(1-?discount) AS ?price)\n"
                + "   ?x ns:price ?p .\n"
                + "   ?x ns:discount ?discount .\n"
                + "   ?x dc:title ?title . \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql10_1b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql10_1b";
        queryCase.name = "Sparql Specification section 10.1 BIND: Assigning to Variables 2nd query";
        //Parser moves filter and bind up which cases numbering of aanonymous variables to be different.
        //Note the fullstop needed after ?discount here not needed above!
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "{  { ?x ns:price ?p .\n"
                + "     ?x ns:discount ?discount\n"
                + "     BIND (?p*(1-?discount) AS ?price)\n"
                + "   }\n"
                + "   {?x dc:title ?title . }\n"
                + "   FILTER(?price < 20)\n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title ?price\n"
                + "{  \n"
                + "   FILTER(?price < 20)\n"
                + "   BIND (?p*(1-?discount) AS ?price)\n"
                + "   ?x ns:price ?p .\n"
                + "   ?x ns:discount ?discount .\n"
                + "   ?x dc:title ?title . \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql10_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql10_2a";
        queryCase.name = "Sparql Specification section 10.2 BINDINGS 1st query";
        queryCase.originalQuery = "PREFIX dc:   <http://purl.org/dc/elements/1.1/> \n"
                + "PREFIX :     <http://example.org/book/> \n"
                + "PREFIX ns:   <http://example.org/ns#> \n"
                + "SELECT ?book ?title ?price\n"
                + "{\n"
                + "   ?book dc:title ?title ;\n"
                + "         ns:price ?price .\n"
                + "}\n"
                + "BINDINGS ?book {\n"
                + " (:book1)\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql10_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql10_2b";
        queryCase.name = "Sparql Specification section 10.2 BINDINGS 2nd query";
        queryCase.originalQuery = "PREFIX dc:   <http://purl.org/dc/elements/1.1/> \n"
                + "PREFIX :     <http://example.org/book/> \n"
                + "PREFIX ns:   <http://example.org/ns#> \n"
                + "SELECT ?book ?title ?price\n"
                + "{\n"
                + "   ?book dc:title ?title ;\n"
                + "         ns:price ?price .\n"
                + "}\n"
                + "BINDINGS ?book ?title {\n"
                + " (UNDEF \"SPARQL Tutorial\")\n"
                + " (:book2 UNDEF)\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql11_1a1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_1a";
        queryCase.name = "Sparql Specification section 11.1 Aggregate Example simplified";
        queryCase.originalQuery = "PREFIX : <http://books.example/>\n"
                + "SELECT (SUM(?lprice) AS ?totalPrice)\n"
                + "WHERE {\n"
                + "  ?org :affiliates ?auth .\n"
                + "  ?auth :writesBook ?book .\n"
                + "  ?book :price ?lprice .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadSparql11_1a2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_1a2";
        queryCase.name = "Sparql Specification section 11.1 Aggregate Example No Having";
        queryCase.originalQuery = "PREFIX : <http://books.example/>\n"
                + "SELECT (SUM(?lprice) AS ?totalPrice)\n"
                + "WHERE {\n"
                + "  ?org :affiliates ?auth .\n"
                + "  ?auth :writesBook ?book .\n"
                + "  ?book :price ?lprice .\n"
                + "}\n"
                + "GROUP BY ?org";                
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadSparql11_1a3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_1a3";
        queryCase.name = "Sparql Specification section 11.1 Aggregate Example";
        queryCase.originalQuery = "PREFIX : <http://books.example/>\n"
                + "SELECT (SUM(?lprice) AS ?totalPrice)\n"
                + "WHERE {\n"
                + "  FILTER (?lprice > 10)\n"
                + "  ?org :affiliates ?auth .\n"
                + "  ?auth :writesBook ?book .\n"
                + "  ?book :price ?lprice .\n"
                + "}\n"
                + "GROUP BY ?org\n"
               // + "HAVING (?lprice > 10)"
                + "";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql11_1a4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_1a4";
        queryCase.name = "Sparql Specification section 11.1 Aggregate Example simple having";
        queryCase.originalQuery = "PREFIX : <http://books.example/>\n"
                + "SELECT (SUM(?lprice) AS ?totalPrice)\n"
                + "WHERE {\n"
                + "  ?org :affiliates ?auth .\n"
                + "  ?auth :writesBook ?book .\n"
                + "  ?book :price ?lprice .\n"
                + "}\n"
                + "GROUP BY ?org\n"
                + "HAVING (?lprice > 10)";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql11_1a5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_1a5";
        queryCase.name = "Sparql Specification section 11.1 Aggregate Example simple having";
        queryCase.originalQuery = "PREFIX : <http://books.example/>\n"
                + "SELECT (SUM(?lprice) AS ?totalPrice)\n"
                + "WHERE {\n"
                + "  ?org :affiliates ?auth .\n"
                + "  ?auth :writesBook ?book .\n"
                + "  ?book :price ?lprice .\n"
                + "}\n"
                + "GROUP BY ?org\n"
                + "HAVING (SUM(?lprice) > SUM(?book))";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparq() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql";
        queryCase.name = "Sparql Specification section ";
        queryCase.originalQuery = "";                
        queries.put(queryCase.key, queryCase);
   }
   
   public Set<String> keySet(){
       return queries.keySet();
   }
   
    String getQueryName(String key) {
       return queries.get(key).name;
    }

   public String getOriginalQuery(String key){
       return queries.get(key).originalQuery;
   }

   public String getNoReplaceQuery(String key){
       if (queries.get(key).noReplaceQuery != null){
           return queries.get(key).noReplaceQuery;
       }
       return queries.get(key).originalQuery;
   }
}



