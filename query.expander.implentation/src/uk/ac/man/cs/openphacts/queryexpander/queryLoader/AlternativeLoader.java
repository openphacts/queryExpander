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
    }

   private void loadSmall() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Alt";
        queryCase.name = "Alternative Query";
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
                "       FiLTER (?replacedURI1 = bar:1 || ?replacedURI1 = bar:2 ||\n" +
                "               ?replacedURI1 = foo:subj1) \n" +
                "	}\n" +
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
        queryCase.filterStatement = "";
        queries.put(queryCase.key, queryCase);
    }

 
}
