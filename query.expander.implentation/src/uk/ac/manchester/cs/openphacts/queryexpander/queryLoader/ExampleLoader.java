/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.manchester.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class ExampleLoader extends QueryCaseLoader{
   private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Ops";
        queryCase.name = "Ops  Query";
        queryCase.originalQuery = "";
        queries.put(queryCase.key, queryCase);
    }

}
