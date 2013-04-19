package uk.ac.man.cs.openphacts.queryexpander.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bridgedb.rdf.UriPattern;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

/**
 * A simple implmentation of the IMSMapper that only returns URIs specifically loaded after construction.
 * 
 * Mainly used for testing.
 * 
 * Ignores the graph, and does the implements getMatchesForURIs by just returning all the Maps it has ignoring the Set.
 * 
 * @author Christian
 */
public class DummyIMSMapper implements IMSMapper{

    Map<URI, List<URI>> uriMappings = new HashMap<URI, List<URI>>();
    ValueFactory valueFactory = ValueFactoryImpl.getInstance();
    
    /**
     * Allows test methods to load the Mappings they expect.
     * 
     * @param fromString
     * @param toString 
     */
    public void addMapping(String fromString, String toString){
       URI fromURI = valueFactory.createURI(fromString);
       URI toURI = valueFactory.createURI(toString);
       List<URI> uriList = uriMappings.get(fromURI);
       if (uriList == null){
           uriList = new ArrayList<URI>();
       }
       if (!uriList.contains(toURI)){
           uriList.add(toURI);
       }
       uriMappings.put(fromURI, uriList);
    }
    
    @Override
    public List<URI> getMatchesForURI(URI uri, String lensUri) {
        List<URI> mapped = uriMappings.get(uri);
        if (mapped == null){
            mapped = new ArrayList<URI>();
        }
        if (!mapped.contains(uri)){
            mapped.add(uri);
        }
        return mapped;
    }

    @Override
    public List<URI> getSpecificMatchesForURI(URI uri, String graph, String lensUri) {
        return getMatchesForURI(uri, lensUri);
    }

    @Override
    public Map<String, Set<UriPattern>> getURISpacesPerGraph() {
        HashMap<String, Set<UriPattern>> URISpacesPerGraph = new HashMap<String, Set<UriPattern>>();
        return URISpacesPerGraph;
    }
    
}
