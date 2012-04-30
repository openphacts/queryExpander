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
 *
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
    }
    
    //http://www.w3.org/TR/2012/WD-sparql11-query-20120105/
    private void loadSparql2_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_1";
        queryCase.name = "Sparql Specification section 2.1 Writing a Simple Query";
        queryCase.originalQuery =
              "SELECT ?title \n"
            + "WHERE { \n"
            + "    <http://example.org/book/book1> <http://purl.org/dc/elements/1.1/title> ?title .\n"
            + "}";
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
    }

   private void loadSparql2_3_1a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_1a";
        queryCase.name = "Sparql Specification section 2.3.1 Matching RDF Literals. 1st Query";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p \"cat\" }";
        queries.put(queryCase.name, queryCase);
     }
    
   private void loadSparql2_3_1b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_1b";
        queryCase.name = "Sparql Specification section 2.3.1 Matching RDF Literals 2nd query";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p \"cat\"@en }";
        queries.put(queryCase.name, queryCase);
   }
   
   private void loadSparql2_3_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_2";
        queryCase.name = "Sparql Specification section 2.3.2 Matching Literals with Numeric Types";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p 42 }";                
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql2_3_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_3_3";
        queryCase.name = "Sparql Specification section 2.3.3 Matching Literals with Arbitrary Datatypes";
        queryCase.originalQuery = "SELECT ?v WHERE { ?v ?p \"abc\"^^<http://example.org/datatype#specialDatatype> }";                
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql2_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_4";
        queryCase.name = "Sparql Specification section 2.4 Blank Node Labels in Query Results";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?x ?name \n"
                + "WHERE  { ?x foaf:name ?name }";                
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql2_5a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_5a";
        queryCase.name = "Sparql Specification section 2.5 Creating Values with Expressions 1st Query";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ( CONCAT(?G, \" \", ?S) AS ?name ) \n"
                + "WHERE  { ?P foaf:givenName ?G ; foaf:surname ?S }\n";                
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql2_6() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_6";
        queryCase.name = "Sparql Specification section 2.6 Building RDF Graphs";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "PREFIX org:    <http://example.com/ns#> \n"
                + " CONSTRUCT { ?x foaf:name ?name }\n"
                + "WHERE  { ?x org:employeeName ?name }\n";                
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql4_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql4_2a";
        queryCase.name = "Sparql Specification section 4.2 Syntax for Triple Patterns 1st query\n";
        queryCase.originalQuery = "PREFIX  dc: <http://purl.org/dc/elements/1.1/> \n"
                + "SELECT  ?title \n"
                + "WHERE   { <http://example.org/book/book1> dc:title ?title }\n";                
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql4_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql4_2b";
        queryCase.name = "Sparql Specification section 4.2 Syntax for Triple Patterns 2nd query\n";
        queryCase.originalQuery = "PREFIX  dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  : <http://example.org/book/>\n"
                + "SELECT  $title\n"
                + "WHERE   { :book1  dc:title  $title }\n";                
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql4_2c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql4_2c";
        queryCase.name = "Sparql Specification section 4.2 Syntax for Triple Patterns 3rd query";
        queryCase.originalQuery = "BASE    <http://example.org/book/>\n"
                + "PREFIX  dc: <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT  $title\n"
                + "WHERE   { <book1>  dc:title  ?title }";                
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql7a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql7a";
        queryCase.name = "Sparql Specification section 7 Matching Alternatives 1st query";
        queryCase.originalQuery = "PREFIX dc10:  <http://purl.org/dc/elements/1.0/>\n"
                + "PREFIX dc11:  <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?title\n"
                + "WHERE  { { ?book dc10:title  ?title } UNION { ?book dc11:title  ?title } }";                
        queries.put(queryCase.name, queryCase);
   }

   private void loadSparql7b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql7b";
        queryCase.name = "Sparql Specification section 7 Matching Alternatives 2nd query";
        queryCase.originalQuery = "PREFIX dc10:  <http://purl.org/dc/elements/1.0/>\n"
                + "PREFIX dc11:  <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?x ?y\n"
                + "WHERE  { { ?book dc10:title ?x } UNION { ?book dc11:title  ?y } }";                
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        //Parser moves filters up whuch case numbering of aanonymous variables to be different.
        queryCase.noReplaceQuery = "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX  foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?person\n"
                + "WHERE {\n"
                + "    FILTER NOT EXISTS { ?person foaf:name ?name }\n"
                + "    ?person rdf:type  foaf:Person .\n"
                + "}     ";                
        queries.put(queryCase.name, queryCase);
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
        //Parser moves filters up whuch case numbering of aanonymous variables to be different.
        queryCase.noReplaceQuery = "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX  foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT ?person\n"
                + "WHERE {\n"
                + "    FILTER EXISTS { ?person foaf:name ?name }\n"
                + "    ?person rdf:type  foaf:Person .\n"
                + "}";                
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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
        queries.put(queryCase.name, queryCase);
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



