/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class AlternativeLoader extends QueryCaseLoader{

    public AlternativeLoader(){
        loadSmall();
        loadLifeScience2();
        loadLifeScience2WithGraph();
    }

   private void loadSmall() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltMyexpample";
        queryCase.name = "Alternative Query my example";
        queryCase.originalQuery = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "	GRAPH <http://www.example.org/graph1> {\n" +
                "		OPTIONAL { foo:subj1 example:pred1 ?obj1 }\n" +
                "		OPTIONAL { foo:subj1 example:pred2 ?obj2 }\n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { foo:subj1 example:pred3 ?obj3 }\n" +
                "		foo:subj1 example:pred4 ?obj4;\n" +
                "                 example:pred5 ?obj5.\n" +
                "	}\n" +
                "}\n";
        queryCase.filterStatement = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "	GRAPH <http://www.example.org/graph1> {\n" +
                "		OPTIONAL { \n" +
                "           ?replacedURI1 example:pred1 ?obj1 \n" +
                "           FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "                   ?replacedURI1 = foo:subj1) \n" +
                "       }\n" +
                "		OPTIONAL { " +
                "           ?replacedURI2 example:pred2 ?obj2 \n" +
                "           FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = bar:2 ||\n" +
                "                  ?replacedURI2 = foo:subj1) \n" +
                "       }\n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { " +
                "           ?replacedURI3 example:pred3 ?obj3 \n" +
                "           FiLTER (?replacedURI3 = bar:1 || ?replacedURI3 = bar:2 ||\n" +
                "                   ?replacedURI3 = foo:subj1) \n" +
                "       }\n" +
                "		?replacedURI4 example:pred4 ?obj4.\n" +
                "       FiLTER (?replacedURI4 = bar:1 || ?replacedURI4 = bar:2 ||\n" +
                "               ?replacedURI4 = foo:subj1) \n" +
                "       ?replacedURI5 example:pred5 ?obj5.\n" +
                "       FiLTER (?replacedURI5 = bar:1 || ?replacedURI5 = bar:2 ||\n" +
                "               ?replacedURI5 = foo:subj1) \n" +
                "	}\n" +
                "}\n";
        queryCase.replaceQuery = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "	GRAPH <http://www.example.org/graph1> {\n" +
                "		OPTIONAL { ?replacedURI1 example:pred1 ?obj1 }\n" +
                "		OPTIONAL { ?replacedURI1 example:pred2 ?obj2 }\n" +
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "               ?replacedURI1 = foo:subj1) \n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { ?replacedURI2 example:pred3 ?obj3 }\n" +
                "		?replacedURI2 example:pred4 ?obj4.\n" +
                "       ?replacedURI2 example:pred5 ?obj5.\n" +
                "       FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = bar:2 ||\n" +
                "               ?replacedURI2 = foo:subj1) \n" +
                "	}\n" +
                "}\n";
        queryCase.filterAll = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "	GRAPH <http://www.example.org/graph1> {\n" +
                "		OPTIONAL { ?replacedURI1 example:pred1 ?obj1 }\n" +
                "		OPTIONAL { ?replacedURI1 example:pred2 ?obj2 }\n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { ?replacedURI1 example:pred3 ?obj3 }\n" +
                "		?replacedURI1 example:pred4 ?obj4.\n" +
                "       ?replacedURI1 example:pred5 ?obj5.\n" +
                "	}\n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "           ?replacedURI1 = foo:subj1) \n" +
                "}\n";
        queries.put(queryCase.key, queryCase);
   }

   private void loadLifeScience2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltLS2";
        queryCase.name = "Alternative Query Life Science 2";
        queryCase.originalQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { foo:subj1 ?predicate ?object . }\n" +
                "  UNION\n" +
                "   { foo:subj1 owl:sameAs ?caff .\n" +
                "     ?caff ?predicate ?object . } }";
        queryCase.filterStatement = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "           ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { ?replacedURI2 owl:sameAs ?caff .\n" +
                "   FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = bar:2 ||\n" +
                "           ?replacedURI2 = foo:subj1) \n" +
                "     ?caff ?predicate ?object . } }";
        queryCase.replaceQuery = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "           ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { ?replacedURI2 owl:sameAs ?caff .\n" +
                "   FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = bar:2 ||\n" +
                "           ?replacedURI2 = foo:subj1) \n" +
                "     ?caff ?predicate ?object . } }";
        queryCase.filterAll = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . }\n" +
                "  UNION\n" +
                "   { ?replacedURI1 owl:sameAs ?caff .\n" +
                "     ?caff ?predicate ?object . } " +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "           ?replacedURI1 = foo:subj1) \n" +
                "}";
        queries.put(queryCase.key, queryCase);
   }

   private void loadLifeScience2WithGraph() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltLS2Graph";
        queryCase.name = "Alternative Query Life Science 2 with graph";
        queryCase.originalQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { foo:subj1 ?predicate ?object . }\n" +
                "  UNION\n" +
                "   { \n" +
                "     GRAPH ?g1 {" +
                "        foo:subj1 owl:sameAs ?caff .\n" +
                "        foo:subj1 foo:pred2s ?object2 .\n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.filterStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "      FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "           ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { \n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = bar:2 ||\n" +
                "              ?replacedURI2 = foo:subj1) \n" +
                "        ?replacedURI3 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI3 = bar:1 || ?replacedURI3 = bar:2 ||\n" +
                "              ?replacedURI3 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.replaceQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "      FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "           ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { \n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        ?replacedURI2 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = bar:2 ||\n" +
                "              ?replacedURI2 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.filterAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . }\n" +
                "  UNION\n" +
                "   { \n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI1 owl:sameAs ?caff .\n" +
                "        ?replacedURI1 foo:pred2s ?object2 .\n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "           ?replacedURI1 = foo:subj1) \n" +
                " }";
        queries.put(queryCase.key, queryCase);
   }

   private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Alt";
        queryCase.name = "Alternative Query";
        queryCase.originalQuery = "";
        queryCase.filterStatement = "";
        queryCase.replaceQuery = "";
        queryCase.filterAll = "";
        queries.put(queryCase.key, queryCase);
    }

 
}
