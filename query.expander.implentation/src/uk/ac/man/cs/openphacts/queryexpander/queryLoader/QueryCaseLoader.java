package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openrdf.model.URI;

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
   
    public String getQueryName(String key) {
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

   public String getServerReplaceQuery(String key){
   /*    if (queries.get(key).serverReplaceQuery != null){
           return queries.get(key).serverReplaceQuery;
       }
       if (queries.get(key).replaceQuery != null){
           return queries.get(key).replaceQuery;
       }
       return getNoReplaceQuery(key);*/
       return getTextReplaceQuery(key);
   }

   public String getTextReplaceQuery(String key){
       if (queries.get(key).textReplaceQuery != null){
           return queries.get(key).textReplaceQuery;
       }
       if (queries.get(key).replaceQuery != null){
           return queries.get(key).replaceQuery;
       }
       return getNoReplaceQuery(key);
   }

   public List<String> getParameters(String key) {
       return queries.get(key).parameters;
   }

   public String getInsertURI(String key) {
       return queries.get(key).insertURI;
   }

   public String getFilterStatement(String key){
       return queries.get(key).filterStatement;
   }
 }



