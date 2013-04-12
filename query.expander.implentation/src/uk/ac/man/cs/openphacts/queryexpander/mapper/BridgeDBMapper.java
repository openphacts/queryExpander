package uk.ac.man.cs.openphacts.queryexpander.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.bridgedb.rdf.UriPattern;
import org.bridgedb.uri.UriMapper;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;

/**
 *
 * @author Christian
 */
public class BridgeDBMapper implements IMSMapper{

    private HashMap<String,Set<UriPattern>> allowedUriPatterns;
    private UriMapper bridgeDB;
    private final UriPattern[] EMPTY_URIPATTERNG_ARRAY = new UriPattern[0];
    
    public BridgeDBMapper (HashMap<String,Set<UriPattern>> allowedNamespaces, UriMapper bridgeDB){
        this.allowedUriPatterns = allowedNamespaces;
        this.bridgeDB = bridgeDB;
    }
    
    @Override
    public List<URI> getMatchesForURI(URI uri, String profileUri) throws QueryExpanderException {
        try {
            Set<String> stringResults = bridgeDB.mapUri(uri.stringValue(), profileUri);
            //Hack sort the results for testing
            TreeSet<String> sorted = new TreeSet(stringResults);
            ArrayList<URI> results = new ArrayList<URI>();
            for (String stringResult:sorted){
                results.add(new URIImpl(stringResult));
            }
            return results;
        } catch (Exception ex) {
            throw new QueryExpanderException("Unable to map " + uri , ex);
        }
    }

    @Override
    public List<URI> getSpecificMatchesForURI(URI uri, String graph, String profileUri) throws QueryExpanderException {
        try {
            Set<UriPattern> specificNameSpaces =  allowedUriPatterns.get(graph);
            Set<String> stringResults;
            if (specificNameSpaces == null){
                stringResults = bridgeDB.mapUri(uri.stringValue(), profileUri);
            } else {
                stringResults = bridgeDB.mapUri(uri.stringValue(), profileUri, specificNameSpaces.toArray(EMPTY_URIPATTERNG_ARRAY));
            }
            if (stringResults == null){
                throw new QueryExpanderException("null results returned for " + uri + " and graph " + graph);
            }
            //Hack sort the results for testing
            TreeSet<String> sorted = new TreeSet(stringResults);
            ArrayList<URI> results = new ArrayList<URI>();
            for (String stringResult:sorted){
                results.add(new URIImpl(stringResult));
            }
            return results;
        } catch (Exception ex) {
            throw new QueryExpanderException("Unable to map " + uri , ex);
        }
    }

    @Override
    public Map<String, Set<UriPattern>> getURISpacesPerGraph() {
        return allowedUriPatterns;
    }
    
}
