/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class BrokenLoader extends QueryCaseLoader{

    public BrokenLoader(){
        loadCountDistinct();
        loadCountDistinct2();
        loadCountDistinct3();
    }
    
    private void loadCountDistinct() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenCountDistinct";
        queryCase.name = "Count Distinct Query";
        queryCase.originalQuery = "SELECT (count(distinct ?s) as ?count) (count (?s) as ?count2)\n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "  }\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadCountDistinct2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenCountDistinct2";
        queryCase.name = "Count Distinct Query Complex";
        queryCase.originalQuery = "SELECT ?s \n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "    FILTER(?p != <http://www.w3.org/2004/02/skos/core%23exactMatch>) \n"
                + "  }\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadCountDistinct3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenCountDistinct2";
        queryCase.name = "Count Distinct Query Complex";
        queryCase.originalQuery = "SELECT (count(distinct ?s) as ?count) \n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "    FILTER(?p != <http://www.w3.org/2004/02/skos/core%23exactMatch>) \n"
                + "    FILTER(REGEX(str(?s),\"http://rdf.chemspider.com/\"))\n"
                + "  }\n"
                + "}";
        //Filters swap order
        queryCase.noReplaceQuery = "SELECT (count(distinct ?s) as ?count) \n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "    FILTER(REGEX(str(?s),\"http://rdf.chemspider.com/\"))\n"
                + "    FILTER(?p != <http://www.w3.org/2004/02/skos/core%23exactMatch>) \n"
                + "  }\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Broken";
        queryCase.name = " Query";
        queryCase.originalQuery = "";
        queries.put(queryCase.key, queryCase);
    }

}
