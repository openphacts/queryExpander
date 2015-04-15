package uk.ac.manchester.cs.openphacts.queryexpander.queryLoader;

/**
 *     //http://www.w3.org/TR/2012/WD-sparql11-query-20120105/

 * @author Christian
 */
public class SparqlLoader extends QueryCaseLoader{

    public SparqlLoader(){
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
        loadSparql11_2();
        loadSparql11_3();
        loadSparql11_4();
        loadSparql11_5();
        loadSparql12a1();
        loadSparql12a2();
        loadSparql13_2_1();
        loadSparql13_2_3();
        loadSparql13_3_1();
        loadSparql13_3_2();
        loadSparql13_3_3();
        loadSparql13_3_4();
        loadFederatedSparql2_1();
        loadFederatedSparql2_2();
        loadFederatedSparql2_3();
        loadFederatedSparql2_4a();
        loadFederatedSparql2_4b();
        loadFederatedSparql2_4c();
        loadFederatedSparql2_4d();
        loadFederatedSparql4();
        loadSparql15_1a();
        loadSparql15_1b();
        loadSparql15_1c();
        loadSparql15_2();
        loadSparql15_3();
        loadSparql15_3_1();
        loadSparql15_3_2();
        loadSparql15_4();
        loadSparql15_5();
        loadSparql16_1_1();
        loadSparql16_1_2a();
        loadSparql16_1_2b();
        loadSparql16_2();
        loadSparql16_2_1();
        loadSparql16_2_2a();
        loadSparql16_2_2b();
        loadSparql16_2_3();
        loadSparql16_2_4a();
        loadSparql16_2_4b();
        loadSparql16_3a();
        loadSparql16_3b();
        loadSparql16_4_1();
        loadSparql16_4_2a();
        loadSparql16_4_2b();
        loadSparql16_4_2c();
        loadSparql16_4_3();
        loadSparql17a();
        loadSparql17b();
        loadSparql17_3();
        loadSparql11_4_1_1a();
        loadSparql11_4_1_1b();
        loadSparql17_4_1_2();
        loadSparql17_4_1_3();
        loadSparql17_4_1_7a();
        loadSparql17_4_1_7b();
        loadSparql17_4_1_8a();
        loadSparql17_4_1_8b();
        loadSparql17_4_1_9();
        loadSparql17_4_1_10a();
        loadSparql17_4_1_10b();
        loadSparql17_4_2_1();
        loadSparql17_4_2_2();
        loadSparql17_4_2_3();
        loadSparql17_4_2_4();
        loadSparql17_4_2_5();
        loadSparql17_4_2_6();
        loadSparql17_4_2_7();
        loadSparql17_4_2_8();
        loadSparql17_4_2_9();
        loadSparql17_4_2_10();
        loadSparql17_4_2_11();
        loadSparql17_4_3_2();
        loadSparql17_4_3_3();
        loadSparql17_4_3_4();
        loadSparql17_4_3_5();
        loadSparql17_4_3_6();
        loadSparql17_4_3_7();
        loadSparql17_4_3_8();
        loadSparql17_4_3_9();
        loadSparql17_4_3_10();
        loadSparql17_4_3_11();
        loadSparql17_4_3_12();
        loadSparql17_4_3_13a();
        loadSparql17_4_3_13b();
        loadSparql17_4_3_14();
        loadSparql17_4_3_15();
        loadSparql17_4_4_1();
        loadSparql17_4_4_2();
        loadSparql17_4_4_3();
        loadSparql17_4_4_4();
        loadSparql17_4_4_5();
        loadSparql17_4_5_1();
        loadSparql17_4_5_2();
        loadSparql17_4_5_3();
        loadSparql17_4_5_4();
        loadSparql17_4_5_5();
        loadSparql17_4_5_6();
        loadSparql17_4_5_7();
        loadSparql17_4_5_8();
        loadSparql17_4_5_9();        
        loadSparql17_4_6_1();
        loadSparql17_4_6_2();
        loadSparql17_4_6_3();
        loadSparql17_4_6_4();
        loadSparql17_4_6_5();
        loadSparql17_6a();
        loadSparql17_6b();
        loadSparql18_2_3a();
        loadSparql18_2_3b();
        loadSparql18_2_3c();
        loadSparql18_2_3d();
        loadSparql18_2_3e();
        loadSparql18_2_3f();
        loadSparql18_2_3g();
        loadSparql18_2_3h();
        loadSparql18_2_3i();
        loadSparql18_2_3j();
        loadSparql18_2_3k();
        loadSparql18_2_3l();
        loadSparql18_2_3m1();
        loadSparql18_2_3m2();
        loadSparql18_2_4_1();
        loadSparql18_4_1_8();
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
        queryCase.noReplaceQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "SELECT (CONCAT(?G, \" \", ?S) AS ?name) \n"
                + "WHERE  { \n"
                + "   ?P foaf:givenName ?G ; \n"
                + "      foaf:surname ?S \n"
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
        queryCase.name = "Sparql Specification section 5.2.3 Group Graph Pattern Examples 1st query";
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
        queryCase.name = "Sparql Specification section 5.2.3 Group Graph Pattern Examples 2nd query";
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
        queryCase.name = "Sparql Specification section  5.2.3 Group Graph Pattern Examples 3rd query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {    ?x foaf:name ?name .\n"
                + "    {}\n"
                + "    ?x foaf:mbox ?mbox .\n"
                + "  }";                
        queryCase.noReplaceQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?x \n"
                + "WHERE {    ?x foaf:name ?name .\n"
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
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  (?p * ?discount AS ?price) \n"
                + "{\n"
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
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  (?p*(1-?discount) AS ?price)\n"
                + "{ \n"
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

   private void loadSparql11_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_2";
        queryCase.name = "Sparql Specification section 11.2 GROUP BY";
        queryCase.originalQuery = "PREFIX : <http://books.example/>\n"
                + "SELECT (AVG(?y) AS ?avg)\n"
                + "WHERE {\n"
                + "  ?a :x ?x ;\n"
                + "     :y ?y .}\n\n"
                + "GROUP BY ?x";                
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadSparql11_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_3";
        queryCase.name = "Sparql Specification section 11.3 HAVING";
        queryCase.originalQuery = "PREFIX : <http://data.example/>\n"
                + "SELECT (AVG(?size) AS ?asize)\n"
                + "WHERE {\n"
                + "  ?x :size ?size\n"
                + "}\n"
                + "GROUP BY ?x\n"
                + "HAVING(AVG(?size) > 10)";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql11_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_4";
        queryCase.name = "Sparql Specification section 11.4 Aggregate Projection Restrictions";
        queryCase.originalQuery = "PREFIX : <http://example.com/data/#>\n"
                + "SELECT ?x (MIN(?y) * 2 AS ?min)\n"
                + "WHERE {\n"
                + "  ?x :p ?y .\n"
                + "  ?x :q ?z .\n"
                + "} GROUP BY ?x (STR(?z))";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql11_5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_5";
        queryCase.name = "Sparql Specification section 11.5 Aggregate Example (with errors)";
        queryCase.originalQuery = "PREFIX : <http://example.com/data/#>\n"
                + "SELECT ?g (AVG(?p) AS ?avg) ((MIN(?p) + MAX(?p)) / 2 AS ?c)\n"
                + "WHERE {\n"
                + "  ?g :p ?p .\n"
                + "}\n"
                + "GROUP BY ?g";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql12a1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql12a1";
        queryCase.name = "Sparql Specification section 12 Subqueries  inner query";
        //removed double prefix
        queryCase.originalQuery = "PREFIX : <http://people.example/>\n"
                + "    SELECT ?y (MIN(?name) AS ?minName)\n"
                + "    WHERE {\n"
                + "      ?y :name ?name .\n"
                + "    } GROUP BY ?y";
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql12a2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql12a2";
        queryCase.name = "Sparql Specification section 12 Subqueries";
        //removed double prefix
        queryCase.originalQuery = "PREFIX : <http://people.example/>\n"
                + "SELECT ?y ?minName\n"
                + "WHERE {\n"
                + "  :alice :knows ?y .\n"
                + "  {\n"
                + "    SELECT ?y (MIN(?pizza) AS ?minName)\n"
                + "    WHERE {\n"
                + "      ?y :name ?pizza .\n"
                + "    } GROUP BY ?y\n"
                + "  }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql13_2_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql13_2_1";
        queryCase.name = "Sparql Specification section 13.2.1 Specifying the Default Graph";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT  ?name\n"
                + "FROM    <http://example.org/foaf/aliceFoaf>\n"
                + "WHERE   { ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql13_2_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql13_2_3";
        queryCase.name = "Sparql Specification section 13.2.3 Combining FROM and FROM NAMED";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?who ?g ?mbox\n"
                + "FROM <http://example.org/dft.ttl>\n"
                + "FROM NAMED <http://example.org/alice>\n"
                + "FROM NAMED <http://example.org/bob>\n"
                + "WHERE\n"
                + "{\n"
                + "   ?g dc:publisher ?who .\n"
                + "   GRAPH ?g { ?x foaf:mbox ?mbox }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql13_3_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql13_3_1";
        queryCase.name = "Sparql Specification section 13.3.1 Accessing Graph Names";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?src ?bobNick\n"
                + "FROM NAMED <http://example.org/foaf/aliceFoaf>\n"
                + "FROM NAMED <http://example.org/foaf/bobFoaf>\n"
                + "WHERE\n"
                + "  {\n"
                + "    GRAPH ?src\n"
                + "    { ?x foaf:mbox <mailto:bob@work.example> .\n"
                + "      ?x foaf:nick ?bobNick\n"
                + "    }\n"
                + "  }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql13_3_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql13_3_2";
        queryCase.name = "Sparql Specification section 13.3.2 Restricting by Graph IRI";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX data: <http://example.org/foaf/>\n"
                + "SELECT ?nick\n"
                + "FROM NAMED <http://example.org/foaf/aliceFoaf>\n"
                + "FROM NAMED <http://example.org/foaf/bobFoaf>\n"
                + "WHERE\n"
                + "  {\n"
                + "     GRAPH data:bobFoaf {\n"
                + "         ?x foaf:mbox <mailto:bob@work.example> .\n"
                + "         ?x foaf:nick ?nick }\n"
                + "  }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql13_3_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql13_3_3";
        queryCase.name = "Sparql Specification section ";
        queryCase.originalQuery = "PREFIX  data:  <http://example.org/foaf/>\n"
                + "PREFIX  foaf:  <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX  rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT ?mbox ?nick ?ppd\n"
                + "FROM NAMED <http://example.org/foaf/aliceFoaf>\n"
                + "FROM NAMED <http://example.org/foaf/bobFoaf>\n"
                + "WHERE\n"
                + "{\n"
                + "  GRAPH data:aliceFoaf\n"
                + "  {\n"
                + "    ?alice foaf:mbox <mailto:alice@work.example> ;\n"
                + "           foaf:knows ?whom .\n"
                + "    ?whom  foaf:mbox ?mbox ;\n"
                + "           rdfs:seeAlso ?ppd .\n"
                + "    ?ppd  a foaf:PersonalProfileDocument .\n"
                + "  } .\n"
                + "  GRAPH ?ppd\n"
                + "  {\n"
                + "      ?w foaf:mbox ?mbox ;\n"
                + "         foaf:nick ?nick\n"
                + "  }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadSparql13_3_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql13_3_4";
        queryCase.name = "Sparql Specification section 13.3.4 Named and Default Graphs";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc:   <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?name ?mbox ?date\n"
                + "WHERE\n"
                + "  {  ?g dc:publisher ?name ;\n"
                + "        dc:date ?date .\n"
                + "    GRAPH ?g\n"
                + "      { ?person foaf:name ?name ; foaf:mbox ?mbox }\n"
                + "  }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadFederatedSparql2_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql2_1";
        queryCase.name = "Federated Sparql Specification section 2.1 Simple query to a remote SPARQL endpoint";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + "FROM <http://example.org/myfoaf.rdf>\n"
                + "WHERE\n"
                + "{\n"
                + "  <http://example.org/myfoaf/I> foaf:knows ?person .\n"
                + "  SERVICE <http://people.example.org/sparql> { \n"
                + "    ?person foaf:name ?name . } \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadFederatedSparql2_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql2_2";
        queryCase.name = "Federated Sparql Specification section 2.2 SPARQL query with OPTIONAL to two remote SPARQL endpoints";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?person ?interest ?known\n"
                + "WHERE\n"
                + "{\n"
                + "  SERVICE <http://people.example.org/sparql> { \n"
                + "    ?person foaf:name ?name .  \n"
                + "    OPTIONAL { \n"
                + "      ?person foaf:interest ?interest .\n"
                + "      SERVICE <http://people2.example.org/sparql> { \n"
                + "        ?person foaf:knows ?known . } }\n"
                + "  }    \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   /**
    * Warning the SILENT keyword does not appear to be supported by this OpenRDF
    */
   private void loadFederatedSparql2_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql2_3";
        queryCase.name = "Federated Sparql Specification section 2.3 Service Execution Failure";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + "WHERE\n"
                + "{\n"
                + "  SERVICE SILENT <http://people.example.org/sparql> { \n"
                + "    <http://example.org/people15> foaf:name ?name . }\n"
                + "}";       
        queryCase.note = "Warning the SILENT keyword does not appear to be supported by this OpenRDF";
        queries.put(queryCase.key, queryCase);
   }

   private void loadFederatedSparql2_4a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql2_4a";
        queryCase.name = 
                "Federated Sparql Specification section 2.4 Interplay of SERVICE and BINDINGS (Informative) 1st query ";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?s\n"
                + "{\n"
                + "  ?s a foaf:Person .\n"
                + "  SERVICE <http://example.org/sparql> {?s foaf:knows ?o }\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadFederatedSparql2_4b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql2_4b";
        queryCase.name = 
                "Federated Sparql Specification section 2.4 Interplay of SERVICE and BINDINGS (Informative) 2nd query";
        queryCase.originalQuery = "PREFIX : <http://example.org/>\n"
                + "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?s\n"
                + "{\n"
                + "  ?s a foaf:Person\n"
                + "} ";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadFederatedSparql2_4c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql2-4c";
        queryCase.name = "Federated Sparql Specification section 2.4 Interplay of SERVICE and BINDINGS (Informative)";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX : <http://example.org/>\n"
                + "SELECT * {?s foaf:knows ?o } BINDINGS ?s { (:a) (:b) }";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadFederatedSparql2_4d() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql2_4d";
        queryCase.name = "Federated Sparql Specification section 2.4 Interplay of SERVICE and BINDINGS (Informative)";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?s ?o\n"
                + "{\n"
                + "  ?s a foaf:Person\n"
                + "  SERVICE <http://example.org/sparql> {?s foaf:knows ?o }\n"
                + "} ";                
        queries.put(queryCase.key, queryCase);
   }

   private void loadFederatedSparql4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "FederatedSparql4";
        queryCase.name = "Federated Sparql Specification section ";
        queryCase.originalQuery = "PREFIX  void: <http://rdfs.org/ns/void#>\n"
                + "PREFIX  dc:   <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  doap: <http://usefulinc.com/ns/doap#> \n"
                + "SELECT ?service ?projectName\n"
                + "WHERE {\n"
                + "  # Find the service with subject \"remote\".\n"
                + "  ?p dc:subject ?projectSubject ;\n"
                + "     void:sparqlEndpoint ?service .\n"
                + "     FILTER regex(?projectSubject, \"remote\")\n"
                + "  # Query that service projects.\n"
                + "  SERVICE ?service {\n"
                + "     ?project  doap:name ?projectName . } \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_1a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_1a";
        queryCase.name = "Sparql Specification section 15.1 ORDER BY 1st query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + "WHERE { ?x foaf:name ?name }\n"
                + "ORDER BY ?name";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_1b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_1b";
        queryCase.name = "Sparql Specification section 15.1 ORDER BY 2nd query";
        queryCase.originalQuery = "PREFIX     :    <http://example.org/ns#>\n"
                + "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + "WHERE { ?x foaf:name ?name ; :empId ?emp }\n"
                + "ORDER BY DESC(?emp)";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_1c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_1c";
        queryCase.name = "Sparql Specification section 15.1 ORDER BY 3rd query";
        queryCase.originalQuery = "PREFIX     :    <http://example.org/ns#>\n"
                + "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + "WHERE { ?x foaf:name ?name ; :empId ?emp }\n"
                + "ORDER BY ?name DESC(?emp)";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_2";
        queryCase.name = "Sparql Specification section 15.2 Projection";
        queryCase.originalQuery = "PREFIX foaf:       <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + "WHERE\n"
                + " { ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_3";
        queryCase.name = "Sparql Specification section 15.3 Duplicate Solutions";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name WHERE { ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_3_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_3_1";
        queryCase.name = "Sparql Specification section 15.3.1 DISTINCT";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT DISTINCT ?name WHERE { ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_3_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_3_2";
        queryCase.name = "Sparql Specification section 15.3.2 REDUCED";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT REDUCED ?name WHERE { ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_4";
        queryCase.name = "Sparql Specification section 15.4 OFFSET";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT  ?name\n"
                + "WHERE   { ?x foaf:name ?name }\n"
                + "ORDER BY ?name\n"
                + "LIMIT   5\n"
                + "OFFSET  10";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql15_5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql15_5";
        queryCase.name = "Sparql Specification section 15.5 LIMIT";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + "WHERE { ?x foaf:name ?name }\n"
                + "LIMIT 20";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_1_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_1_1";
        queryCase.name = "Sparql Specification section 16.1.1 Projection";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?nameX ?nameY ?nickY\n"
                + "WHERE\n"
                + "  { ?x foaf:knows ?y ;\n"
                + "       foaf:name ?nameX .\n"
                + "    ?y foaf:name ?nameY .\n"
                + "    OPTIONAL { ?y foaf:nick ?nickY }\n"
                + "  }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_1_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_1_2a";
        queryCase.name = "Sparql Specification section 16.1.2 SELECT Expressions 1st query";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title (?p*(1-?discount) AS ?price)\n"
                + "{ ?x ns:price ?p .\n"
                + "  ?x dc:title ?title . \n"
                + "  ?x ns:discount ?discount \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_1_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_1_2b";
        queryCase.name = "Sparql Specification section 16.1.2 SELECT Expressions 2nd query";
        queryCase.originalQuery = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX  ns:  <http://example.org/ns#>\n"
                + "SELECT  ?title (?p AS ?fullPrice) (?fullPrice*(1-?discount) AS ?customerPrice)\n"
                + "{ ?x ns:price ?p .\n"
                + "   ?x dc:title ?title . \n"
                + "   ?x ns:discount ?discount \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_2";
        queryCase.name = "Sparql Specification section 16.2 CONSTRUCT";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#>\n"
                + "CONSTRUCT   { <http://example.org/person#Alice> vcard:FN ?name }\n"
                + "WHERE       { ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_2_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_2_1";
        queryCase.name = "Sparql Specification section 16.2.1 Templates with Blank Nodes";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#>\n"
                + "CONSTRUCT { ?x  vcard:N _:v .\n"
                + "            _:v vcard:givenName ?gname .\n"
                + "            _:v vcard:familyName ?fname }\n"
                + "WHERE\n"
                + " {\n"
                + "    { ?x foaf:firstname ?gname } UNION  { ?x foaf:givenname   ?gname } .\n"
                + "    { ?x foaf:surname   ?fname } UNION  { ?x foaf:family_name ?fname } .\n"
                + " }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_2_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_2_2a";
        queryCase.name = "Sparql Specification section 16.2.2 Accessing Graphs in the RDF Dataset 1st query";
        queryCase.originalQuery = "CONSTRUCT { ?s ?p ?o } WHERE { GRAPH <http://example.org/aGraph> { ?s ?p ?o } . }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_2_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_2_2b";
        queryCase.name = "Sparql Specification section 16.2.2 Accessing Graphs in the RDF Dataset 2nd query";
        queryCase.originalQuery = "PREFIX  dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX app: <http://example.org/ns#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "CONSTRUCT { ?s ?p ?o } WHERE\n"
                + " {\n"
                + "   GRAPH ?g { ?s ?p ?o } .\n"
                + "   ?g dc:publisher <http://www.w3.org/> .\n"
                + "   ?g dc:date ?date .\n"
                + "   FILTER ( app:customDate(?date) > \"2005-02-28T00:00:00Z\"^^xsd:dateTime ) .\n"
                + " }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_2_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_2_3";
        queryCase.name = "Sparql Specification section 16.2.3 Solution Modifiers and CONSTRUCT";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX site: <http://example.org/stats#>\n"
                + "CONSTRUCT { [] foaf:name ?name }\n"
                + "WHERE\n"
                + "{ [] foaf:name ?name ;\n"
                + "     site:hits ?hits .\n"
                + "}\n"
                + "ORDER BY desc(?hits)\n"
                + "LIMIT 2";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_2_4a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_2_4a";
        queryCase.name = "Sparql Specification section 16.2.4 CONSTRUCT WHERE 1st query";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "CONSTRUCT WHERE { ?x foaf:name ?name } ";   
        //Sparql docs say these are the same so why worry!
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "CONSTRUCT { ?x foaf:name ?name } \n"
                + "WHERE\n"
                + "{ ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_2_4b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_2_4b";
        queryCase.name = "Sparql Specification section 16.2.4 CONSTRUCT WHERE 2nd query";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "CONSTRUCT { ?x foaf:name ?name } \n"
                + "WHERE\n"
                + "{ ?x foaf:name ?name }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_3a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_3a";
        queryCase.name = "Sparql Specification section 16.3 ASK 1st query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "ASK  { ?x foaf:name  \"Alice\" }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_3b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_3b";
        queryCase.name = "Sparql Specification section 16.3 ASK 2nd query";
        queryCase.originalQuery = "PREFIX foaf:    <http://xmlns.com/foaf/0.1/>\n"
                + "ASK  { ?x foaf:name  \"Alice\" ;\n"
                + "          foaf:mbox  <mailto:alice@work.example> }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_4_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_4_1";
        queryCase.name = "Sparql Specification section 16.4.1 Explicit IRIs";
        queryCase.originalQuery = "DESCRIBE <http://example.org/>";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_4_2a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_4_2a";
        queryCase.name = "Sparql Specification section 16.4.2 Identifying Resources";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "DESCRIBE ?x\n"
                + "WHERE    { ?x foaf:mbox <mailto:alice@org> }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_4_2b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_4_2b";
        queryCase.name = "Sparql Specification section 16.4.2 Identifying Resources";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "DESCRIBE ?x\n"
                + "WHERE    { ?x foaf:name \"Alice\" }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_4_2c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_4_2c";
        queryCase.name = "Sparql Specification section 16.4.2 Identifying Resources";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "DESCRIBE ?x\n"
                + "WHERE    { ?x foaf:name \"Alice\" }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql16_4_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql16_4_3";
        queryCase.name = "Sparql Specification section 16.4.3 Descriptions of Resources";
        queryCase.originalQuery = "PREFIX ent:  <http://org.example.com/employees#>\n"
                + "DESCRIBE ?x WHERE { ?x ent:employeeId \"1234\" }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17a";
        queryCase.name = "Sparql Specification section 17 Expressions and Testing Values";
        queryCase.originalQuery = "PREFIX a:      <http://www.w3.org/2000/10/annotation-ns#>\n"
                + "PREFIX dc:     <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX xsd:    <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT ?annot\n"
                + "WHERE { ?annot  a:annotates  <http://www.w3.org/TR/rdf-sparql-query/> .\n"
                + "        ?annot  dc:date      ?date .\n"
                + "        FILTER ( ?date > \"2005-01-01T00:00:00Z\"^^xsd:dateTime ) }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17b";
        queryCase.name = "Sparql Specification section 17 Expressions and Testing Values 2n Filter";
        queryCase.originalQuery = "PREFIX a:      <http://www.w3.org/2000/10/annotation-ns#>\n"
                + "PREFIX dc:     <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX xsd:    <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT ?annot\n"
                + "WHERE { ?annot  a:annotates  <http://www.w3.org/TR/rdf-sparql-query/> .\n"
                + "        ?annot  dc:date      ?date .\n"
                + "        FILTER ( xsd:dateTime(?date) < xsd:dateTime(\"2005-01-01T00:00:00Z\") ) }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_3";
        queryCase.name = "Sparql Specification section 17.3 Operator Mapping";
        queryCase.note = "Query is purely to test all operations. Clear it will never return any data.";
        queryCase.originalQuery = "SELECT ?a "
                + "WHERE {"
                + "      FILTER (?a = 2)\n"
                + "      FILTER (?a != 2)\n"
                + "      FILTER (?a < 2)\n"
                + "      FILTER (?a > 2)\n"
                + "      FILTER (?a <= 2)\n"
                + "      FILTER (?a >= 2)\n"
                + "      FILTER (?a = 2 * 3)\n"
                + "      FILTER (?a = 2 / 3)\n"
                + "      FILTER (?a = 2 + 3)\n"
                + "      FILTER (?a = 2 - 3)\n"
                + "      FILTER ((?a = 2) || (?a = 3))\n"
                + "      FILTER ((?a = 2) && (?a = 3))\n"
                + "      FILTER (!(?a = 2))\n"
                + "}";   
        //Parser reverse the order
        queryCase.noReplaceQuery = "SELECT ?a "
                + "WHERE {"
                + "      FILTER (!(?a = 2))\n"
                + "      FILTER ((?a = 2) && (?a = 3))\n"
                + "      FILTER ((?a = 2) || (?a = 3))\n"
                + "      FILTER (?a = 2 - 3)\n"
                + "      FILTER (?a = 2 + 3)\n"
                + "      FILTER (?a = 2 / 3)\n"
                + "      FILTER (?a = 2 * 3)\n"
                + "      FILTER (?a >= 2)\n"
                + "      FILTER (?a <= 2)\n"
                + "      FILTER (?a > 2)\n"
                + "      FILTER (?a < 2)\n"
                + "      FILTER (?a != 2)\n"
                + "      FILTER (?a = 2)\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql11_4_1_1a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_4_1_1a";
        queryCase.name = "Sparql Specification section 17.4.1.1 bound 1st query";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc:   <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT ?givenName\n"
                + " WHERE { ?x foaf:givenName  ?givenName .\n"
                + "         OPTIONAL { ?x dc:date ?date } .\n"
                + "         FILTER ( bound(?date) ) }";    
        //Parser moves filters up
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc:   <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT ?givenName\n"
                + " WHERE { ?x foaf:givenName  ?givenName .\n"
                + "         FILTER ( bound(?date) ) "
                + "         OPTIONAL { ?x dc:date ?date } .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql11_4_1_1b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql11_4_1_1b";
        queryCase.name = "Sparql Specification section 17.4.1.1 bound 2nd query";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc:   <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?name\n"
                + " WHERE { ?x foaf:givenName  ?name .\n"
                + "         OPTIONAL { ?x dc:date ?date } .\n"
                + "         FILTER (!bound(?date)) "
                + "}";                
        //Parser moves filters up
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc:   <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?name\n"
                + " WHERE { ?x foaf:givenName  ?name .\n"
                + "         FILTER (!bound(?date)) "
                + "         OPTIONAL { ?x dc:date ?date } .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_2";
        queryCase.name = "Sparql Specification section 17.4.1.2 IF";
        queryCase.originalQuery = "SELECT \n"
                + "     ( IF(?x = 2, \"yes\", \"no\") AS ?x1 )\n"
                + "     ( IF(bound(?y), \"yes\", \"no\") AS ?x2 )\n"
                + "     ( IF(?x=2, \"yes\", 1/?z) AS ?x3 )\n"
                + "     ( IF(?x=1, \"yes\", 1/?z) AS ?x4 )\n"
                + "     ( IF(\"2\" > 1, \"yes\", \"no\") AS ?x5 )\n"
                + "WHERE { \n"
                + "} ";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_3";
        queryCase.name = "Sparql Specification section 17.4.1.3 COALESCE";
        queryCase.originalQuery = "SELECT \n"
                + "     ( COALESCE(?x, 1/0) AS ?x1 )\n"
                + "     ( COALESCE(1/0, ?x) AS ?x2 )\n"
                + "     ( COALESCE(5, ?x) AS ?x3 )\n"
                + "     ( COALESCE(?y, 3) AS ?x4 )\n"
                + "     ( COALESCE(?y) AS ?x5 )\n"
                + "WHERE { \n"
                + "} ";                
        queries.put(queryCase.key, queryCase);
   }

   //Sparql8_1_2 and Sparql8_1_1 do exists and not exists
  //Sparql17_3 includes and(&&) and or(||)
  
  private void loadSparql17_4_1_7a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_7a";
        queryCase.name = "Sparql Specification section 17.4.1.7 RDFterm-equal 1st query";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name1 ?name2\n"
                + "WHERE { ?x foaf:name  ?name1 ;\n"
                + "        foaf:mbox  ?mbox1 .\n"
                + "        ?y foaf:name  ?name2 ;\n"
                + "        foaf:mbox  ?mbox2 .\n"
                + "        FILTER (?mbox1 = ?mbox2 && ?name1 != ?name2)\n"
                + "      }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_7b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_7b";
        queryCase.name = "Sparql Specification section 17.4.1.7 RDFterm-equal 2nd query";
        queryCase.originalQuery = "PREFIX a:      <http://www.w3.org/2000/10/annotation-ns#>\n"
                + "PREFIX dc:     <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX xsd:    <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT ?annotates\n"
                + "WHERE { ?annot  a:annotates  ?annotates .\n"
                + "        ?annot  dc:date      ?date .\n"
                + "        FILTER ( ?date = xsd:dateTime(\"2005-01-01T00:00:00Z\") ) \n"
                + "      }";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_8a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_8a";
        queryCase.name = "Sparql Specification section 17.4.1.8 sameTerm 1st query";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name1 ?name2\n"
                + "WHERE { ?x foaf:name  ?name1 ;\n"
                + "        foaf:mbox  ?mbox1 .\n"
                + "         ?y foaf:name  ?name2 ;\n"
                + "         foaf:mbox  ?mbox2 .\n"
                + "         FILTER (sameTerm(?mbox1, ?mbox2) && !sameTerm(?name1, ?name2))\n"
                + "      } ";                
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name1 ?name2\n"
                + "WHERE { "
                + "     FILTER (sameTerm(?mbox1, ?mbox2) && !sameTerm(?name1, ?name2))\n"
                + "     ?x foaf:name  ?name1 ;\n"
                + "     foaf:mbox  ?mbox1 .\n"
                + "     ?y foaf:name  ?name2 ;\n"
                + "     foaf:mbox  ?mbox2 .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_8b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_8b";
        queryCase.name = "Sparql Specification section 17.4.1.8 sameTerm 2nd query";
        queryCase.originalQuery = "PREFIX  :      <http://example.org/WMterms#>\n"
                + "PREFIX  t:     <http://example.org/types#>\n"
                + "SELECT ?aLabel1 ?bLabel\n"
                + "WHERE { ?a  :label        ?aLabel .\n"
                + "        ?a  :weight       ?aWeight .\n"
                + "        ?a  :displacement ?aDisp .\n"
                + "        ?b  :label        ?bLabel .\n"
                + "        ?b  :weight       ?bWeight .\n"
                + "        ?b  :displacement ?bDisp .\n"
                + "        FILTER ( sameTerm(?aWeight, ?bWeight) && !sameTerm(?aDisp, ?bDisp)) \n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX  :      <http://example.org/WMterms#>\n"
                + "PREFIX  t:     <http://example.org/types#>\n"
                + "SELECT ?aLabel1 ?bLabel\n"
                + "WHERE { "
                + "     FILTER ( sameTerm(?aWeight, ?bWeight) && !sameTerm(?aDisp, ?bDisp)) \n"
                + "     ?a  :label        ?aLabel .\n"
                + "     ?a  :weight       ?aWeight .\n"
                + "     ?a  :displacement ?aDisp .\n"
                + "     ?b  :label        ?bLabel .\n"
                + "     ?b  :weight       ?bWeight .\n"
                + "     ?b  :displacement ?bDisp .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_9() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_9";
        queryCase.name = "Sparql Specification section 17.4.1.9 IN";
        queryCase.originalQuery = "SELECT ?x \n"
                + "WHERE {\n"
                + "   FILTER (2 IN (1, 2, 3))\n"
                + "   FILTER (2 IN ())\n"
                + "   FILTER (2 IN (<http://example/iri>, \"str\", 2.0))\n"
                + "   FILTER (2 IN (1/0, 2))\n"
                + "   FILTER (2 IN (2, 1/0))\n"
                + "   FILTER (2 IN (3, 1/0))\n"
                + "   ?x a ?y \n"
                + "}";                
        queryCase.noReplaceQuery = "SELECT ?x \n"
                + "WHERE {\n"
                + "   FILTER (2 IN (3, 1/0))\n"
                + "   FILTER (2 IN (2, 1/0))\n"
                + "   FILTER (2 IN (1/0, 2))\n"
                + "   FILTER (2 IN (<http://example/iri>, \"str\", 2.0))\n"
                + "   FILTER (2 IN ())\n"
                + "   FILTER (2 IN (1, 2, 3))\n"
                + "   ?x a ?y \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_10a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_10a";
        queryCase.name = "Sparql Specification section 17.4.1.10 NOT IN";
        queryCase.originalQuery = "SELECT ?x \n"
                + "WHERE {\n"
                + "   FILTER (2 NOT IN (1, 2, 3))\n"
                + "   FILTER (2 NOT IN ())\n"
                + "   FILTER (2 NOT IN (<http://example/iri>, \"str\", 2.0))\n"
                + "   FILTER (2 NOT IN (1/0, 2))\n"
                + "   FILTER (2 NOT IN (2, 1/0))\n"
                + "   FILTER (2 NOT IN (3, 1/0))\n"
                + "   ?x a ?y \n"
                + "}";                
        queryCase.noReplaceQuery = "SELECT ?x \n"
                + "WHERE {\n"
                + "   FILTER (2 NOT IN (3, 1/0))\n"
                + "   FILTER (2 NOT IN (2, 1/0))\n"
                + "   FILTER (2 NOT IN (1/0, 2))\n"
                + "   FILTER (2 NOT IN (<http://example/iri>, \"str\", 2.0))\n"
                + "   FILTER (2 NOT IN ())\n"
                + "   FILTER (2 NOT IN (1, 2, 3))\n"
                + "   ?x a ?y \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
   }

  private void loadSparql17_4_1_10b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_1_10b";
        queryCase.name = "Sparql Specification section 17.4.1.10 NOT IN usig !";
        queryCase.originalQuery = "SELECT ?x \n"
                + "WHERE {\n"
                + "   FILTER (!(2 IN (1, 2, 3)))\n"
                + "   FILTER (!(2 IN ()))\n"
                + "   FILTER (!(2 IN (<http://example/iri>, \"str\", 2.0)))\n"
                + "   FILTER (!(2 IN (1/0, 2)))\n"
                + "   FILTER (!(2 IN (2, 1/0)))\n"
                + "   FILTER (!(2 IN (3, 1/0)))\n"
                + "   ?x a ?y \n"
                + "}";                
        queryCase.noReplaceQuery = "SELECT ?x \n"
                + "WHERE {\n"
                + "   FILTER (!(2 IN (3, 1/0)))\n"
                + "   FILTER (!(2 IN (2, 1/0)))\n"
                + "   FILTER (!(2 IN (1/0, 2)))\n"
                + "   FILTER (!(2 IN (<http://example/iri>, \"str\", 2.0)))\n"
                + "   FILTER (!(2 IN ()))\n"
                + "   FILTER (!(2 IN (1, 2, 3)))\n"
                + "   ?x a ?y \n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_1";
        queryCase.name = "Sparql Specification section 17.4.2.1 isIRI";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + " WHERE { \n"
                + "         ?x foaf:name  ?name ;\n"
                + "            foaf:mbox  ?mbox .\n"
                + "         FILTER isIRI(?mbox) \n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + " WHERE { \n"
                + "         FILTER isIRI(?mbox) \n"
                + "         ?x foaf:name  ?name ;\n"
                + "            foaf:mbox  ?mbox .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_2";
        queryCase.name = "Sparql Specification section 17.4.2.2 isBlank";
        queryCase.originalQuery = "PREFIX a:      <http://www.w3.org/2000/10/annotation-ns#>\n"
                + "PREFIX dc:     <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?given ?family\n"
                + "WHERE { \n"
                + "  ?annot  a:annotates  <http://www.w3.org/TR/rdf-sparql-query/> .\n"
                + "  ?annot  dc:creator   ?c .\n"
                + "  OPTIONAL { ?c  foaf:given   ?given ; foaf:family  ?family } .\n"
                + "  FILTER isBlank(?c)\n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX a:      <http://www.w3.org/2000/10/annotation-ns#>\n"
                + "PREFIX dc:     <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?given ?family\n"
                + "WHERE { \n"
                + "  FILTER isBlank(?c)\n"
                + "  ?annot  a:annotates  <http://www.w3.org/TR/rdf-sparql-query/> .\n"
                + "  ?annot  dc:creator   ?c .\n"
                + "  OPTIONAL { ?c  foaf:given   ?given ; foaf:family  ?family } .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_3";
        queryCase.name = "Sparql Specification section 17.4.2.3 isLiteral";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + "WHERE { \n"
                + "        FILTER isLiteral(?mbox) \n"
                + "        ?x foaf:name  ?name ;\n"
                + "        foaf:mbox  ?mbox .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_4";
        queryCase.name = "Sparql Specification section 17.4.2.4 isNumeric";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT ?name \n"
                + "WHERE { "
                + "        ?x foaf:name  ?name ;\n"
                + "        FILTER (isNumeric(12)) \n"
                + "        FILTER (isNumeric(\"12\"))\n"
                + "        FILTER (isNumeric(\"12\"^^xsd:nonNegativeInteger))\n"
                + "        FILTER (isNumeric(\"1200\"^^xsd:byte))\n"
                + "        FILTER (isNumeric(<http://example/>))\n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT ?name \n"
                + "WHERE { "
                + "        FILTER (isNumeric(<http://example/>))\n"
                + "        FILTER (isNumeric(\"1200\"^^xsd:byte))\n"
                + "        FILTER (isNumeric(\"12\"^^xsd:nonNegativeInteger))\n"
                + "        FILTER (isNumeric(\"12\"))\n"
                + "        FILTER (isNumeric(12))\n"
                + "        ?x foaf:name  ?name ;\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_5";
        queryCase.name = "Sparql Specification section 17.4.2.5 str";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + " WHERE { \n"
                + "         ?x foaf:name  ?name ;\n"
                + "            foaf:mbox  ?mbox .\n"
                + "         FILTER regex(str(?mbox), \"@work\\\\.example$\") \n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + " WHERE { \n"
                + "         FILTER regex(str(?mbox), \"@work\\\\.example$\") \n"
                + "         ?x foaf:name  ?name ;\n"
                + "            foaf:mbox  ?mbox .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_6() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_6";
        queryCase.name = "Sparql Specification section 17.4.2.6 lang";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + " WHERE { \n"
                + "         ?x foaf:name  ?name ;\n"
                + "            foaf:mbox  ?mbox .\n"
                + "         FILTER ( lang(?name) = \"es\" ) \n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox\n"
                + " WHERE { \n"
                + "         FILTER ( lang(?name) = \"es\" ) \n"
                + "         ?x foaf:name  ?name ;\n"
                + "            foaf:mbox  ?mbox .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_7() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_7";
        queryCase.name = "Sparql Specification section 17.4.2.7 datatype";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX eg:   <http://biometrics.example/ns#>\n"
                + "SELECT ?name ?shoeSize\n"
                + " WHERE { \n"
                + "         ?x foaf:name  ?name ; eg:shoeSize  ?shoeSize .\n"
                + "         FILTER ( datatype(?shoeSize) = xsd:integer ) \n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX eg:   <http://biometrics.example/ns#>\n"
                + "SELECT ?name ?shoeSize\n"
                + " WHERE { \n"
                + "         FILTER ( datatype(?shoeSize) = xsd:integer ) \n"
                + "         ?x foaf:name  ?name ; eg:shoeSize  ?shoeSize .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_8() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_8";
        queryCase.name = "Sparql Specification section 17.4.2.8 IRI";
        queryCase.note = "Parse replaces URI function with IRI";
        queryCase.originalQuery = "SELECT "
                + " (IRI(?x) as ?iri1 )\n"
                + " (IRI(\"example\") as ?iri2 )\n"
                + " (IRI(<http:example.com/jumper>) as ?iri3 )\n"
                + " (URI(?x) as ?uri1 )\n"
                + " (URI(\"example\") as ?uri2 )\n"
                + " (URI(<http:example.com/jumper>) as ?uri3 )\n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_9() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_2_9";
        queryCase.name = "Sparql Specification section 17.4.2.9 BNODE";
        queryCase.note = "Parser treats all BNODE statements the same";
        queryCase.originalQuery = "select \n"
                + "   (BNODE() AS ?a ) \n"
                + "   (BNODE(\"john\") AS ?b ) \n"
                + "   (BNODE(?x) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_10() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_2_10";
        queryCase.name = "Sparql Specification section 17.4.2.10 STRDT";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (STRDT(\"123\", xsd:integer) AS ?a ) \n"
                + "   (STRDT(\"iiii\", <http://example/romanNumeral>) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_2_11() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_2_11";
        queryCase.name = "Sparql Specification section 17.4.2.11 STRLANG";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (STRLANG(\"chat\", \"en\") AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_2";
        queryCase.name = "Sparql Specification section 17.4.3.2 STRLEN";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (strlen(\"chat\") AS ?a ) \n"
                + "   (strlen(\"chat\"@en) AS ?b ) \n"
                + "   (strlen(\"chat\"^^xsd:string) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_3";
        queryCase.name = "Sparql Specification section 17.4.3.3 SUBSTR";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (substr(\"foobar\", 4) AS ?a ) \n"
                + "   (substr(\"foobar\"@en, 4) AS ?b ) \n"
                + "   (substr(\"foobar\"^^xsd:string, 4) AS ?c ) \n"
                + "   (substr(\"foobar\", 4, 1) AS ?d ) \n"
                + "   (substr(\"foobar\"@en, 4, 1) AS ?e ) \n"
                + "   (substr(\"foobar\"^^xsd:string, 4, 1) AS ?f ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_4";
        queryCase.name = "Sparql Specification section 17.4.3.4 UCASE";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (ucase(\"foo\") AS ?a ) \n"
                + "   (ucase(\"foo\"@en) AS ?b ) \n"
                + "   (ucase(\"foo\"^^xsd:string) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_5";
        queryCase.name = "Sparql Specification section 17.4.3.5 LCASE";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (lcase(\"BAR\") AS ?a ) \n"
                + "   (lcase(\"BAR\"@en) AS ?b ) \n"
                + "   (lcase(\"BAR\"^^xsd:string) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_6() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_6";
        queryCase.name = "Sparql Specification section 17.4.3.6 STRSTARTS";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (strStarts(\"foobar\", \"foo\") AS ?a ) \n"
                + "   (strStarts(\"foobar\"@en, \"foo\"@en) AS ?b ) \n"
                + "   (strStarts(\"foobar\"^^xsd:string, \"foo\"^^xsd:string) AS ?c ) \n"
                + "   (strStarts(\"foobar\"^^xsd:string, \"foo\") AS ?d ) \n"
                + "   (strStarts(\"foobar\", \"foo\"^^xsd:string) AS ?e ) \n"
                + "   (strStarts(\"foobar\"@en, \"foo\") AS ?f ) \n"
                + "   (strStarts(\"foobar\"@en, \"foo\"^^xsd:string) AS ?g ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_7() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_7";
        queryCase.name = "Sparql Specification section 17.4.3.7 STRENDS";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (strEnds(\"foobar\", \"foo\") AS ?a ) \n"
                + "   (strEnds(\"foobar\"@en, \"foo\"@en) AS ?b ) \n"
                + "   (strEnds(\"foobar\"^^xsd:string, \"foo\"^^xsd:string) AS ?c ) \n"
                + "   (strEnds(\"foobar\"^^xsd:string, \"foo\") AS ?d ) \n"
                + "   (strEnds(\"foobar\", \"foo\"^^xsd:string) AS ?e ) \n"
                + "   (strEnds(\"foobar\"@en, \"foo\") AS ?f ) \n"
                + "   (strEnds(\"foobar\"@en, \"foo\"^^xsd:string) AS ?g ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_8() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_8";
        queryCase.name = "Sparql Specification section 17.4.3.8 CONTAINS";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (contains(\"foobar\", \"foo\") AS ?a ) \n"
                + "   (contains(\"foobar\"@en, \"foo\"@en) AS ?b ) \n"
                + "   (contains(\"foobar\"^^xsd:string, \"foo\"^^xsd:string) AS ?c ) \n"
                + "   (contains(\"foobar\"^^xsd:string, \"foo\") AS ?d ) \n"
                + "   (contains(\"foobar\", \"foo\"^^xsd:string) AS ?e ) \n"
                + "   (contains(\"foobar\"@en, \"foo\") AS ?f ) \n"
                + "   (contains(\"foobar\"@en, \"foo\"^^xsd:string) AS ?g ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_9() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_9";
        queryCase.name = "Sparql Specification section 17.4.3.9 STRBEFORE";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (strbefore(\"abc\",\"b\") AS ?a ) \n"
                + "   (strbefore(\"abc\"@en,\"bc\") AS ?b ) \n"
                + "   (strbefore(\"abc\"@en,\"b\"@cy) AS ?c ) \n"
                + "   (strbefore(\"abc\"^^xsd:string,\"\") AS ?d ) \n"
                + "   (strbefore(\"abc\",\"xyz\") AS ?e ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_10() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_10";
        queryCase.name = "Sparql Specification section 17.4.3.10 STRAFTER";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (strafter(\"abc\",\"b\") AS ?a ) \n"
                + "   (strafter(\"abc\"@en,\"bc\") AS ?b ) \n"
                + "   (strafter(\"abc\"@en,\"b\"@cy) AS ?c ) \n"
                + "   (strafter(\"abc\"^^xsd:string,\"\") AS ?d ) \n"
                + "   (strafter(\"abc\",\"xyz\") AS ?e ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_11() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_11";
        queryCase.name = "Sparql Specification section 7.4.3.11 ENCODE_FOR_URI";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (encode_for_uri(\"Los Angeles\") AS ?a ) \n"
                + "   (encode_for_uri(\"Los Angeles\"@en) AS ?b ) \n"
                + "   (encode_for_uri(\"Los Angeles\"^^xsd:string) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_12() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_12";
        queryCase.name = "Sparql Specification section 17.4.3.12 CONCAT";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (concat(\"foo\", \"bar\") AS ?a ) \n"
                + "   (concat(\"foo\"@en, \"foo\"@en) AS ?b ) \n"
                + "   (concat(\"foo\"^^xsd:string, \"bar\"^^xsd:string) AS ?c ) \n"
                + "   (concat(\"foo\", \"bar\"^^xsd:string) AS ?d ) \n"
                + "   (concat(\"foo\"@en, \"foo\") AS ?e ) \n"
                + "   (concat(\"foo\"@en, \"bar\"^^xsd:string)AS ?f ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_13a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_3_13a";
        queryCase.name = "Sparql Specification section 17.4.3.13 langMatches 1st Query";
        queryCase.originalQuery = "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?title\n"
                + " WHERE { ?x dc:title  \"That Seventies Show\"@en ;\n"
                + "            dc:title  ?title .\n"
                + "         FILTER langMatches( lang(?title), \"FR\" ) }";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_13b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_3_13b";
        queryCase.name = "Sparql Specification section 17.4.3.13 langMatches 2nd query";
        queryCase.originalQuery = "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?title\n"
                + " WHERE { ?x dc:title  ?title .\n"
                + "         FILTER langMatches( lang(?title), \"*\" ) }";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_14() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_4_3_14";
        queryCase.name = "Sparql Specification section 17.4.3.14 REGEX";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name\n"
                + " WHERE { ?x foaf:name  ?name\n"
                + "         FILTER regex(?name, \"^ali\", \"i\") }";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_3_15() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_3_15";
        queryCase.name = "Sparql Specification section 17.4.3.15 REPLACE";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (replace(\"abcd\", \"b\", \"Z\") AS ?a ) \n"
                + "   (replace(\"abab\", \"B\", \"Z\",\"i\") AS ?b ) \n"
                + "   (replace(\"abab\", \"B.\", \"Z\",\"i\") AS ?c ) \n"
                 + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_4_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_4_1";
        queryCase.name = "Sparql Specification section 17.4.4.1 abs";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (abs(1) AS ?a ) \n"
                + "   (abs(-1.5) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_4_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_4_2";
        queryCase.name = "Sparql Specification section 17.4.4.2 round";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (round(2.4999) AS ?a ) \n"
                + "   (round(2.5) AS ?b ) \n"
                + "   (round(-2.5) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_4_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_4_3";
        queryCase.name = "Sparql Specification section 17.4.4.3 ceil";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (ceil(10.5) AS ?a ) \n"
                + "   (ceil(-10.5) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_4_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_4_4";
        queryCase.name = "Sparql Specification section 17.4.4.4 floor";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (floor(10.5) AS ?a ) \n"
                + "   (floor(-10.5) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_4_5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_4_5";
        queryCase.name = "Sparql Specification section 17.4.4.5 RAND";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (RAND ( ) AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_1";
        queryCase.name = "Sparql Specification section 17.4.5.1 now";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (NOW () AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_2";
        queryCase.name = "Sparql Specification section 17.4.5.2 year";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (year(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_3";
        queryCase.name = "Sparql Specification section 17.4.5.3 month";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (month(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_4";
        queryCase.name = "Sparql Specification section 17.4.5.4 day";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (day(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_5";
        queryCase.name = "Sparql Specification section 17.4.5.5 hours";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (hours(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_6() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_6";
        queryCase.name = "Sparql Specification section 17.4.5.6 minutes";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (minutes(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_7() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_7";
        queryCase.name = "Sparql Specification section 17.4.5.7 seconds";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (seconds(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_8() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_8";
        queryCase.name = "Sparql Specification section 17.4.5.8 timezone";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (timezone(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "   (timezone(\"2011-01-10T14:45:13.815Z\"^^xsd:dateTime) AS ?b ) \n"
                + "   (timezone(\"2011-01-10T14:45:13.815\"^^xsd:dateTime) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_5_9() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_5_9";
        queryCase.name = "Sparql Specification section 17.4.5.9 tz";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (tz(\"2011-01-10T14:45:13.815-05:00\"^^xsd:dateTime) AS ?a ) \n"
                + "   (tz(\"2011-01-10T14:45:13.815Z\"^^xsd:dateTime) AS ?b ) \n"
                + "   (tz(\"2011-01-10T14:45:13.815\"^^xsd:dateTime) AS ?c ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_6_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_6_1";
        queryCase.name = "Sparql Specification section 17.4.6.1 MD5";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (MD5(\"abc\") AS ?a ) \n"
                + "   (MD5(\"abc\"^^xsd:string) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_6_2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_6_2";
        queryCase.name = "Sparql Specification section 17.4.6.2 SHA1";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (SHA1(\"abc\") AS ?a ) \n"
                + "   (SHA1(\"abc\"^^xsd:string) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_6_3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_6_3";
        queryCase.name = "Sparql Specification section 17.4.6.3 SHA256";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (SHA256(\"abc\") AS ?a ) \n"
                + "   (SHA256(\"abc\"^^xsd:string) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_6_4() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_6_4";
        queryCase.name = "Sparql Specification section 17.4.6.4 SHA384";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (SHA384(\"abc\") AS ?a ) \n"
                + "   (SHA384(\"abc\"^^xsd:string) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_4_6_5() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "17_4_6_5";
        queryCase.name = "Sparql Specification section 17.4.6.5 SHA512";
        queryCase.originalQuery = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT \n"
                + "   (SHA512(\"abc\") AS ?a ) \n"
                + "   (SHA512(\"abc\"^^xsd:string) AS ?b ) \n"
                + "WHERE {"
                + "  ?x <http://xmlns.com/foaf/0.1/mbox>  ?mbox\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_6a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_6a";
        queryCase.name = "Sparql Specification section 17.6 Extensible Value Testing 1st query";
        queryCase.originalQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX func: <http://example.org/functions#>\n"
                + "SELECT ?name ?id\n"
                + "WHERE {\n"
                + "        ?x foaf:name  ?name ;\n"
                + "           func:empId   ?id .\n"
                + "        FILTER (func:even(?id)) \n"
                + "}";                
        queryCase.noReplaceQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX func: <http://example.org/functions#>\n"
                + "SELECT ?name ?id\n"
                + "WHERE {\n"
                + "        FILTER (func:even(?id)) \n"
                + "        ?x foaf:name  ?name ;\n"
                + "           func:empId   ?id .\n"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql17_6b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql17_6b";
        queryCase.name = "Sparql Specification section 17.6 Extensible Value Testing 2nd query";
        queryCase.originalQuery = "PREFIX aGeo: <http://example.org/geo#>\n"
                + "SELECT ?neighbor\n"
                + "WHERE { \n"
                + "        ?a aGeo:placeName \"Grenoble\" .\n"
                + "        ?a aGeo:location ?axLoc .\n"
                + "        ?a aGeo:location ?ayLoc .\n"
                + "        ?b aGeo:placeName ?neighbor .\n"
                + "        ?b aGeo:location ?bxLoc .\n"
                + "        ?b aGeo:location ?byLoc .\n"
                + "        FILTER ( aGeo:distance(?axLoc, ?ayLoc, ?bxLoc, ?byLoc) < 10 ) .\n"
                + "      }";                
        queryCase.noReplaceQuery = "PREFIX aGeo: <http://example.org/geo#>\n"
                + "SELECT ?neighbor\n"
                + "WHERE { \n"
                + "        FILTER ( aGeo:distance(?axLoc, ?ayLoc, ?bxLoc, ?byLoc) < 10 ) .\n"
                + "        ?a aGeo:placeName \"Grenoble\" .\n"
                + "        ?a aGeo:location ?axLoc .\n"
                + "        ?a aGeo:location ?ayLoc .\n"
                + "        ?b aGeo:placeName ?neighbor .\n"
                + "        ?b aGeo:location ?bxLoc .\n"
                + "        ?b aGeo:location ?byLoc .\n"
                + "      }";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3a() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3a";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 1st query";
        queryCase.originalQuery = "SELECT * WHERE \n"
                + "{ ?s ?p ?o } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3b() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3b";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 2nd query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p1 ?v1 ; :p2 ?v2 } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3c() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3c";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 3rd query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ { ?s :p1 ?v1 } UNION {?s :p2 ?v2 } } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3d() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3d";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 4th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ { ?s :p1 ?v1 } UNION {?s :p2 ?v2 } UNION {?s :p3 ?v3 } } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3e() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3e";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 5th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p1 ?v1 OPTIONAL {?s :p2 ?v2 } }";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3f() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3f";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 6th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p1 ?v1 OPTIONAL {?s :p2 ?v2 } OPTIONAL { ?s :p3 ?v3 } } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3g() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3g";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 7th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p1 ?v1 OPTIONAL {?s :p2 ?v2  OPTIONAL { ?s :p3 ?v3 } } } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3h() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3h";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 8th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p1 ?v1 OPTIONAL {?s :p2 ?v2 FILTER(?v1<3) } } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3i() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3i";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 9th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ {?s :p1 ?v1} UNION {?s :p2 ?v2} OPTIONAL {?s :p3 ?v3} } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3j() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3j";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 10th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p1 ?v1 FILTER (?v1 < 3 ) OPTIONAL {?s :p2 ?v2} }";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3k() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3k";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 11th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ \n"
                + "   ?s :p ?v . \n"
                + "   BIND (2*?v AS ?v2) \n"
                + "   ?s :p1 ?v2 \n"
                + "} ";                
        queryCase.noReplaceQuery = "PREFIX :       <http://example/>\n"
                + "SELECT ?s ?v ?v2 WHERE \n"
                + "{ \n"
                + "   BIND (2*?v AS ?v2) \n"
                + "   ?s :p ?v . \n"
                + "   ?s :p1 ?v2 \n"
                + "} ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3l() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3l";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 12th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p ?v . MINUS {?s :p1 ?v2 } } ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3m1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3m1";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 13th query simplified ";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT DISTINCT ?o {?o ?p ?z} ";                
        queries.put(queryCase.key, queryCase);
    }

    private void loadSparql18_2_3m2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_3m2";
        queryCase.name = "Sparql Specification section 18.2.3 Examples of Mapped Graph Patterns 13th query";
        queryCase.originalQuery = "PREFIX :       <http://example/>\n"
                + "SELECT * WHERE \n"
                + "{ ?s :p ?o . {SELECT DISTINCT ?o {?o ?p ?z} } } ";                
        queries.put(queryCase.key, queryCase);
    }

   private void loadSparql18_2_4_1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_2_4_1";
        queryCase.name = "Sparql Specification section 18.2.4.1 Grouping and Aggregation";
        queryCase.originalQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "SELECT (SUM(?val) AS ?sum) (COUNT(?a) AS ?count)\n"
                + "WHERE {\n"
                + "  ?a rdf:value ?val .\n"
                + "} GROUP BY ?a";                
        queries.put(queryCase.key, queryCase);
    }

   //18.2.5.1 ORDER BY see loadSparql15_1a
   //18.2.5.3 DISTINCT see loadSparql8_2()
   //18.2.5.4 REDUCED see loadSparql15_3_2()
   //18.2.5.5 OFFSET and LIMIT see loadSparql15_4()
   //18.4.1.2 Count see loadSparql18_2_4_1()
   //18.4.1.3 Sum see loadSparql11_1a2()
   //18.4.1.4 Avg see loadSparql11_3()
   //18.4.1.5 Min see loadSparql11_5()
   //18.4.1.6 Max see loadSparql11_5()
   //18.4.1.7 GroupConcat see loadSparql2_5a()
   
   private void loadSparql18_4_1_8() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql18_4_1_8";
        queryCase.name = "Sparql Specification section 18_4_1_8 Sample";
        queryCase.originalQuery = "SELECT (Sample(?s) as ?anS) "
                + "WHERE {"
                + "  ?s ?p ?o"
                + "}";                
        queries.put(queryCase.key, queryCase);
    }

   //not used
   private void loadSparql() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql";
        queryCase.name = "Sparql Specification section ";
        queryCase.originalQuery = "";                
        queries.put(queryCase.key, queryCase);
    }

}



