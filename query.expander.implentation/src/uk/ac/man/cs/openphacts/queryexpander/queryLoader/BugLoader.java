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
       loadBug1();
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

}
