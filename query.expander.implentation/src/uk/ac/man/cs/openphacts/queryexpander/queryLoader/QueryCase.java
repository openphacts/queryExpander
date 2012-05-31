package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.util.ArrayList;

/**
 *     //http://www.w3.org/TR/2012/WD-sparql11-query-20120105/

 * @author Christian
 */
public class QueryCase {

    public String name;
    public String originalQuery;
    public String key;
    public String noReplaceQuery;
    public String replaceQuery;
    public String serverReplaceQuery;
    public String textReplaceQuery;
    public String note;
    public ArrayList<String> parameters;
    public String insertURI;
    
    public QueryCase() {
        parameters = new ArrayList<String>();
    }
}
    



