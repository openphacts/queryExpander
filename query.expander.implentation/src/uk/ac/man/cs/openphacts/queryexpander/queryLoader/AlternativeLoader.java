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
        loadWithGraph();
        loadLifeScience2WithGraph();
        loadLifeScience2WithGraphAndFilter();
        loadLifeScience2WithGraphAnd2Filters();
        loadAlasdair1();
    }

   private void loadSmall() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltMyexpample";
        queryCase.name = "Alternative Query small";
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
                "           FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "       }\n" +
                "		OPTIONAL { \n" +
                "           ?replacedURI2 example:pred2 ?obj2 \n" +
                "           FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "       }\n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { \n" +
                "           ?replacedURI3 example:pred3 ?obj3 \n" +
                "           FiLTER (?replacedURI3 = bar:1 || ?replacedURI3 = foo:subj1) \n" +
                "       }\n" +
                "		?replacedURI4 example:pred4 ?obj4.\n" +
                "       FiLTER (?replacedURI4 = bar:1 || ?replacedURI4 = foo:subj1) \n" +
                "       ?replacedURI5 example:pred5 ?obj5.\n" +
                "       FiLTER (?replacedURI5 = bar:1 || ?replacedURI5 = foo:subj1) \n" +
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
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { ?replacedURI2 example:pred3 ?obj3 }\n" +
                "		?replacedURI2 example:pred4 ?obj4.\n" +
                "       ?replacedURI2 example:pred5 ?obj5.\n" +
                "       FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
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
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "}\n";
        queryCase.unionAll = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "    {\n" +
                "	    GRAPH <http://www.example.org/graph1> {\n" +
                "		    OPTIONAL { bar:1 example:pred1 ?obj1 }\n" +
                "		    OPTIONAL { bar:1 example:pred2 ?obj2 }\n" +
                "	    }\n" +
                "	    GRAPH <http://www.example.org/graph2> {\n" +
                "		    OPTIONAL { bar:1 example:pred3 ?obj3 }\n" +
                "		    bar:1 example:pred4 ?obj4;\n" +
                "                     example:pred5 ?obj5.\n" +
                "	    }\n" +
                "    } UNION {\n" +
                "	    GRAPH <http://www.example.org/graph1> {\n" +
                "		    OPTIONAL { foo:subj1 example:pred1 ?obj1 }\n" +
                "		    OPTIONAL { foo:subj1 example:pred2 ?obj2 }\n" +
                "	    }\n" +
                "	    GRAPH <http://www.example.org/graph2> {\n" +
                "		    OPTIONAL { foo:subj1 example:pred3 ?obj3 }\n" +
                "		    foo:subj1 example:pred4 ?obj4;\n" +
                "                     example:pred5 ?obj5.\n" +
                "	    }\n" +
                "    }\n" +
                "}\n";
        queryCase.unionStatement = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "    GRAPH <http://www.example.org/graph1> {\n" +
                "	    OPTIONAL { \n" +
                "           {\n" +
                "               bar:1 example:pred1 ?obj1 \n" +
                "           } UNION {\n" +
                "               foo:subj1 example:pred1 ?obj1 \n" +
                "           }\n" +
                "	    }\n" +
                "		OPTIONAL { \n" +
                "           {\n" +
                "		        bar:1 example:pred2 ?obj2 \n" +
                "           } UNION {\n" +
                "		        foo:subj1 example:pred2 ?obj2 \n" +
                "           }\n" +
                "	    }\n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { \n" +
                "           {\n" +
                "		        bar:1 example:pred3 ?obj3 \n" +
                "           } UNION {\n" +
                "		        foo:subj1 example:pred3 ?obj3 \n" +
                "           }\n" +
                "	    }\n" +
                "       {\n" +
                "		    bar:1 example:pred4 ?obj4.\n" +
                "       } UNION {\n" +
                "		    foo:subj1 example:pred4 ?obj4.\n" +
                "	    }\n" +
                "       {\n" +
                "           bar:1 example:pred5 ?obj5.\n" +
                "       } UNION {\n" +
                "           foo:subj1 example:pred5 ?obj5.\n" +
                "	    }\n" +
                "	}\n" +
                "}\n";
        queryCase.unionGraph = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "    {\n" +
                "	    GRAPH <http://www.example.org/graph1> {\n" +
                "		    OPTIONAL { bar:1 example:pred1 ?obj1 }\n" +
                "		    OPTIONAL { bar:1 example:pred2 ?obj2 }\n" +
                "	    }\n" +
                "    } UNION {\n" +
                "	    GRAPH <http://www.example.org/graph1> {\n" +
                "		    OPTIONAL { foo:subj1 example:pred1 ?obj1 }\n" +
                "		    OPTIONAL { foo:subj1 example:pred2 ?obj2 }\n" +
                "	    }\n" +
                "    }\n" +
                "    {\n" +
                "	    GRAPH <http://www.example.org/graph2> {\n" +
                "		    OPTIONAL { bar:1 example:pred3 ?obj3 }\n" +
                "		    bar:1 example:pred4 ?obj4;\n" +
                "                     example:pred5 ?obj5.\n" +
                "	    }\n" +
                "    } UNION {\n" +
                "	    GRAPH <http://www.example.org/graph2> {\n" +
                "		    OPTIONAL { foo:subj1 example:pred3 ?obj3 }\n" +
                "		    foo:subj1 example:pred4 ?obj4;\n" +
                "                     example:pred5 ?obj5.\n" +
                "	    }\n" +
                "    }\n" +
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
                "    { \n" +
                "        foo:subj1 ?predicate ?object . \n" +
                "    } UNION { \n" +
                "        foo:subj1 owl:sameAs ?caff .\n" +
                "        ?caff ?predicate ?object . \n" +
                "    } \n" +
                "}";
        queryCase.filterStatement = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { ?replacedURI2 owl:sameAs ?caff .\n" +
                "   FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "     ?caff ?predicate ?object . } }";
        queryCase.replaceQuery = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { ?replacedURI2 owl:sameAs ?caff .\n" +
                "   FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "     ?caff ?predicate ?object . } }";
        queryCase.filterAll = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . }\n" +
                "  UNION\n" +
                "   { ?replacedURI1 owl:sameAs ?caff .\n" +
                "     ?caff ?predicate ?object . } " +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "}";
        queryCase.unionAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            bar:1 owl:sameAs ?caff .\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    } UNION {\n" +
                "        { \n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            foo:subj1 owl:sameAs ?caff .\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    }\n" +
                "}";
        queryCase.unionStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION { \n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        } \n" +
                "    } UNION { \n" +
                "        { \n" +
                "            bar:1 owl:sameAs ?caff .\n" +
                "        } UNION { \n" +
                "            foo:subj1 owl:sameAs ?caff .\n" +
                "        } \n" +
                "        ?caff ?predicate ?object . \n" +
                "    } \n" +
                "}";
        queryCase.unionGraph = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION { \n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        } \n" +
                "    } UNION { \n" +
                "        { \n" +
                "            bar:1 owl:sameAs ?caff .\n" +
                "        } UNION { \n" +
                "            foo:subj1 owl:sameAs ?caff .\n" +
                "        } \n" +
                "        ?caff ?predicate ?object . \n" +
                "    } \n" +
                "}";
        queries.put(queryCase.key, queryCase);
   }

   private void loadWithGraph() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltGraph";
        queryCase.name = "Alternative Query  with graph";
        queryCase.originalQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    GRAPH ?g1 {" +
                "        foo:subj1 owl:sameAs ?caff .\n" +
                "        foo:subj1 foo:pred2s ?object2 .\n" +
                "        ?caff ?predicate ?object . \n" +
                "    }\n" +
                " }";
        queryCase.filterStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   GRAPH ?g1 {" +
                "       ?replacedURI1 owl:sameAs ?caff .\n" +
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "       ?replacedURI2 foo:pred2s ?object2 .\n" +
                "       FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "       ?caff ?predicate ?object . \n" +
                "    }\n" +
                " }";
        queryCase.replaceQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI1 owl:sameAs ?caff .\n" +
                "        ?replacedURI1 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                " }";
        queryCase.filterAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI1 owl:sameAs ?caff .\n" +
                "        ?replacedURI1 foo:pred2s ?object2 .\n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                " }";
        queryCase.unionAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "            GRAPH ?g1 {" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "    } UNION {\n" +
                "            GRAPH ?g1 {" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "    }\n" +
                " }";
        queryCase.unionStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "        GRAPH ?g1 {" +
                "            { \n" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "            } UNION {\n" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "            }\n" +
                "            { \n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "            } UNION {\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "            }\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                " }";
        queryCase.unionGraph = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "            GRAPH ?g1 {" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "    } UNION {\n" +
                "            GRAPH ?g1 {" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "    }\n" +
                " }";
        queries.put(queryCase.key, queryCase);
   }

   private void loadLifeScience2WithGraph() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltLS2Graph";
        queryCase.name = "Alternative Query Life Science 2 with graph";
        queryCase.originalQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        foo:subj1 ?predicate ?object . \n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            foo:subj1 owl:sameAs ?caff .\n" +
                "            foo:subj1 foo:pred2s ?object2 .\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.filterStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "      FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { \n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "        ?replacedURI3 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI3 = bar:1 || ?replacedURI3 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.replaceQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { ?replacedURI1 ?predicate ?object . \n" +
                "      FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) }\n" +
                "  UNION\n" +
                "   { \n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        ?replacedURI2 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
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
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                " }";
        queryCase.unionAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            GRAPH ?g1 {" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    } UNION {\n" +
                "        { \n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            GRAPH ?g1 {" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.unionStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        }\n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            { \n" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "            } UNION {\n" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "            }\n" +
                "            { \n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "            } UNION {\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "            }\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.unionGraph = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        }\n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            { \n" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            } UNION {\n" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                " }";
        queries.put(queryCase.key, queryCase);
   }

   private void loadLifeScience2WithGraphAndFilter() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltLS2GraphFilter";
        queryCase.name = "Alternative Query Life Science 2 with graph and filter";
        queryCase.originalQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        FILTER (?object > 25) . \n" +
                "        foo:subj1 ?predicate ?object . \n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            foo:subj1 owl:sameAs ?caff .\n" +
                "            foo:subj1 foo:pred2s ?object2 .\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.filterStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { " +
                "       FILTER (?object > 25) . \n" +
                "       ?replacedURI1 ?predicate ?object . \n" +
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) " +
                "   } UNION { \n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "        ?replacedURI3 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI3 = bar:1 || ?replacedURI3 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.replaceQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { " +
                "       FILTER (?object > 25) . \n" +
                "       ?replacedURI1 ?predicate ?object . \n" +
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "   } UNION {\n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        ?replacedURI2 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.filterAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { " +
                "       FILTER (?object > 25) . \n" +
                "       ?replacedURI1 ?predicate ?object . \n" +
                "   } UNION { \n" +
                "     GRAPH ?g1 {" +
                "        ?replacedURI1 owl:sameAs ?caff .\n" +
                "        ?replacedURI1 foo:pred2s ?object2 .\n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                " }";
        queryCase.unionAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            FILTER (?object > 25) . \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            GRAPH ?g1 {" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    } UNION {\n" +
                "        { \n" +
                "            FILTER (?object > 25) . \n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            GRAPH ?g1 {" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.unionStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        FILTER (?object > 25) . \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        }\n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            { \n" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "            } UNION {\n" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "            }\n" +
                "            { \n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "            } UNION {\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "            }\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.unionGraph = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        FILTER (?object > 25) . \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        }\n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            { \n" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            } UNION {\n" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                " }";
        queries.put(queryCase.key, queryCase);
   }

   private void loadLifeScience2WithGraphAnd2Filters() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltLS2Graph2Filter";
        queryCase.name = "Alternative Query Life Science 2 with graph and 2 filters";
        queryCase.originalQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        FILTER (?object > 25) . \n" +
                "        foo:subj1 ?predicate ?object . \n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            FILTER (?caff < 100) " +
                "            foo:subj1 owl:sameAs ?caff .\n" +
                "            foo:subj1 foo:pred2s ?object2 .\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.filterStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { " +
                "       FILTER (?object > 25) . \n" +
                "       ?replacedURI1 ?predicate ?object . \n" +
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) " +
                "   } UNION { \n" +
                "     FILTER (?caff < 100) " +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "        ?replacedURI3 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI3 = bar:1 || ?replacedURI3 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.replaceQuery = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { " +
                "       FILTER (?object > 25) . \n" +
                "       ?replacedURI1 ?predicate ?object . \n" +
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                "   } UNION {\n" +
                "     FILTER (?caff < 100) " +
                "     GRAPH ?g1 {" +
                "        ?replacedURI2 owl:sameAs ?caff .\n" +
                "        ?replacedURI2 foo:pred2s ?object2 .\n" +
                "        FiLTER (?replacedURI2 = bar:1 || ?replacedURI2 = foo:subj1) \n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                " }";
        queryCase.filterAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "   { " +
                "       FILTER (?object > 25) . \n" +
                "       ?replacedURI1 ?predicate ?object . \n" +
                "   } UNION { \n" +
                "     FILTER (?caff < 100) " +
                "     GRAPH ?g1 {" +
                "        ?replacedURI1 owl:sameAs ?caff .\n" +
                "        ?replacedURI1 foo:pred2s ?object2 .\n" +
                "        ?caff ?predicate ?object . \n" +
                "     }\n" +
                "   }\n" +
                "   FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = foo:subj1) \n" +
                " }";
        queryCase.unionAll = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        { \n" +
                "            FILTER (?object > 25) . \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "           FILTER (?caff < 100) " +
                "           GRAPH ?g1 {" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    } UNION {\n" +
                "        { \n" +
                "            FILTER (?object > 25) . \n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            FILTER (?caff < 100) " +
                "            GRAPH ?g1 {" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.unionStatement = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        FILTER (?object > 25) . \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        }\n" +
                "    } UNION {\n" +
                "        FILTER (?caff < 100) " +
                "        GRAPH ?g1 {" +
                "            { \n" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "            } UNION {\n" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "            }\n" +
                "            { \n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "            } UNION {\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "            }\n" +
                "            ?caff ?predicate ?object . \n" +
                "        }\n" +
                "    }\n" +
                " }";
        queryCase.unionGraph = "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT ?predicate ?object WHERE {\n" +
                "    { \n" +
                "        FILTER (?object > 25) . \n" +
                "        { \n" +
                "            bar:1 ?predicate ?object . \n" +
                "        } UNION {\n" +
                "            foo:subj1 ?predicate ?object . \n" +
                "        }\n" +
                "    } UNION {\n" +
                "        GRAPH ?g1 {" +
                "            FILTER (?caff < 100) " +
                "            { \n" +
                "                bar:1 owl:sameAs ?caff .\n" +
                "                bar:1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            } UNION {\n" +
                "                foo:subj1 owl:sameAs ?caff .\n" +
                "                foo:subj1 foo:pred2s ?object2 .\n" +
                "                ?caff ?predicate ?object . \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                " }";
        queries.put(queryCase.key, queryCase);
   }

    private void loadAlasdair1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltAlasdair1";
        queryCase.name = "Alternative Alasdair 1";
        queryCase.originalQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n" +"PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#> \n" +
                "PREFIX sio: <http://semanticscience.org/resource/> \n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX chemspider: <http://rdf.chemspider.com/#> \n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
                "SELECT DISTINCT ?target_name ?compound_name ?csid_uri ?smiles ?inchi ?inchi_key ?molweight ?num_ro5_violations ?std_type ?relation ?std_value ?std_unit ?target_organism \n" +
                "WHERE { \n" +
                "        GRAPH <http://www.conceptwiki.org> { \n" +
                "                <http://www.conceptwiki.org/concept/d915a79d-e556-4614-983d-e1f7830066d7> skos:prefLabel ?target_name . \n" +
                "                ?compound_cw skos:exactMatch ?csid_uri ; \n" +
                "                         skos:prefLabel ?compound_name . \n" +
                "        } \n" +
                "        GRAPH <http://data.kasabi.com/dataset/chembl-rdf> { \n" +
                "                <http://www.conceptwiki.org/concept/d915a79d-e556-4614-983d-e1f7830066d7> chembl:organism ?target_organism .  \n" +
                "                ?assay_uri chembl:hasTarget <http://www.conceptwiki.org/concept/d915a79d-e556-4614-983d-e1f7830066d7> . \n" +
                "                ?activity_uri chembl:onAssay ?assay_uri ; \n" +
                "                        chembl:type ?std_type ; \n" +
                "                        chembl:relation ?relation ; \n" +
                "                        chembl:standardValue ?std_value ; \n" +
                "                        chembl:standardUnits ?std_unit ; \n" +
                "                        chembl:forMolecule ?compound_uri . \n" +
                "                ?compound_uri owl:equivalentClass ?equiv_uri .  \n" +
                "                ?equiv_uri skos:exactMatch ?csid_uri . \n" +
                "                OPTIONAL { ?compound_uri sio:CHEMINF_000200 _:node1 .  \n" +
                "                        _:node1 a sio:CHEMINF_000314 ; \n" +
                "                                sio:SIO_000300 ?num_ro5_violations } \n" +
                "                OPTIONAL { ?compound_uri sio:CHEMINF_000200 _:node2 .  \n" +
                "                        _:node2 a sio:CHEMINF_000198 ; \n" +
                "                                sio:SIO_000300 ?molweight } \n" +
                "        } \n" +
                "        GRAPH <http://www.chemspider.com> { \n" +
                "                ?csid_uri chemspider:inchi ?inchi; \n" +
                "                        chemspider:inchikey ?inchi_key; \n" +
                "                        chemspider:smiles ?smiles . \n" +
                "        } \n" +
                "}";
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
