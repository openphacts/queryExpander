/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander.queryLoader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *     //http://www.w3.org/TR/2012/WD-sparql11-query-20120105/

 * @author Christian
 */
public class QueryCaseLoader {
    
    protected Map<String, QueryCase> queries = new LinkedHashMap<String, QueryCase>();
    
    public QueryCaseLoader(){
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

   public String getReplaceQuery(String key){
       if (queries.get(key).replaceQuery != null){
           return queries.get(key).replaceQuery;
       }
       return getNoReplaceQuery(key);
   }
}



