package uk.ac.manchester.cs.openphacts.queryexpander.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.bridgedb.uri.api.UriMapper;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderException;

/**
 *
 * @author Christian
 */
public class BridgeDBMapper implements IMSMapper{

    private UriMapper bridgeDB;
    
    public BridgeDBMapper (UriMapper bridgeDB){
        this.bridgeDB = bridgeDB;
    }
    
    @Override
    public List<URI> getMatchesForURI(URI uri, String lensUri) throws QueryExpanderException {
        return getSpecificMatchesForURI(uri, null, lensUri);
    }

    @Override
    public List<URI> getSpecificMatchesForURI(URI uri, String graph, String lensUri) throws QueryExpanderException {
        try {
            Set<String> stringResults;
            stringResults = bridgeDB.mapUri(uri.stringValue(), lensUri, graph);
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
   
}
