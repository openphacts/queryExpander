/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class BugLoader extends QueryCaseLoader{
    
   public BugLoader(){
       loadBug2();
   }
   
   private void loadBug1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Bug1";
        queryCase.name = "Bug Query 1";
        queryCase.originalQuery = "SELECT ?var1 {    \n"
                + "	SELECT ?var1 {\n"
                + "		?s ?p ?var1\n"
                + "	}\n"
                + "}";
        queries.put(queryCase.key, queryCase);
   }

   private void loadBug2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Bug2";
        queryCase.name = "Bug Query 2";
        queryCase.originalQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "SELECT DISTINCT ?item   WHERE \n"
                + "{\n \n"
                + "    {        \n"
                + "        SELECT DISTINCT ?item ?doi \n"
                + "                 (GROUP_CONCAT(DISTINCT ?equiv_target ; SEPARATOR=' , ') AS ?target_uris) \n"
                + "                 (GROUP_CONCAT(DISTINCT ?target_name ; SEPARATOR=' , ') AS ?target_names)  {\n"
                + "             GRAPH <http://www.conceptwiki.org> {\n"
                + "                  ?cw_uri skos:prefLabel ?compound_name.\n"
                + "             }\n"
                + "        } GROUP BY ?item ?equiv_target ?doi ?target_name\n"
                + "} } ORDER BY ?item LIMIT 10 OFFSET 0";
        queries.put(queryCase.key, queryCase);
   }
}
