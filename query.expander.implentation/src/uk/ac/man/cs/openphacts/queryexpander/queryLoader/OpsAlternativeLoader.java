/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class OpsAlternativeLoader extends QueryCaseLoader{

    public OpsAlternativeLoader(){
        loadSmall();
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
        queryCase.replaceQuery = "PREFIX example: <http://www.example.com/#>\n" +
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
                "		OPTIONAL { bar:1 example:pred3 ?obj3 }\n" +
                "		bar:1 example:pred4 ?obj4.\n" +
                "       bar:1 example:pred5 ?obj5.\n" +
                "	}\n" +
                "}\n";
        queryCase.filterStatement = queryCase.replaceQuery;
        queryCase.filterAll =  queryCase.replaceQuery;
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
        queryCase.unionStatement = queryCase.replaceQuery;;
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
