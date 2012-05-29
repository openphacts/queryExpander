package uk.ac.man.cs.openphacts.queryexpander.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.bridgedb.url.URLMapper;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;

/**
 *
 * @author Christian
 */
public class BridgeDBMapper implements IMSMapper{

    private HashMap<String,Set<String>> allowedNamespaces;
    private URLMapper bridgeDB;
    private final String[] EMPTY_STRING_ARRAY = new String[0];
    
    public BridgeDBMapper (HashMap<String,Set<String>> allowedNamespaces, URLMapper bridgeDB){
        this.allowedNamespaces = allowedNamespaces;
        this.bridgeDB = bridgeDB;
    }
    
    @Override
    public List<URI> getMatchesForURI(URI uri) throws QueryExpansionException {
        try {
            Set<String> stringResults = bridgeDB.mapURL(uri.stringValue());
            //Hack sort the results for testing
            TreeSet<String> sorted = new TreeSet(stringResults);
            ArrayList<URI> results = new ArrayList<URI>();
            for (String stringResult:sorted){
                results.add(new URIImpl(stringResult));
            }
            return results;
        } catch (Exception ex) {
            throw new QueryExpansionException("Unable to map " + uri , ex);
        }
    }

    @Override
    public List<URI> getSpecificMatchesForURI(URI uri, String graph) throws QueryExpansionException {
        try {
            Set<String> specificNameSpaces =  allowedNamespaces.get(graph);
            Set<String> stringResults;
            if (specificNameSpaces == null){
                stringResults = bridgeDB.mapURL(uri.stringValue());
            } else {
                stringResults = bridgeDB.mapURL(uri.stringValue(), specificNameSpaces.toArray(EMPTY_STRING_ARRAY));
            }
            if (stringResults == null){
                throw new QueryExpansionException("null results returned for " + uri + " and graph " + graph);
            }
            //Hack sort the results for testing
            TreeSet<String> sorted = new TreeSet(stringResults);
            ArrayList<URI> results = new ArrayList<URI>();
            for (String stringResult:sorted){
                results.add(new URIImpl(stringResult));
            }
            return results;
        } catch (Exception ex) {
            throw new QueryExpansionException("Unable to map " + uri , ex);
        }
    }

    @Override
    public Map<String, Set<String>> getURISpacesPerGraph() {
        return allowedNamespaces;
    }
    
}
