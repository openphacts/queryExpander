package uk.ac.cs.man.openphacts.queryexpander.mapper;

import java.util.List;
import org.openrdf.model.URI;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpansionException;

/**
 * Interface for the mthods a Mapper service should include.
 * 
 * @author Christian
 */
public interface IMSMapper {
    
    /**
     * Maps an URI to a list of URI.
     * <p>
     * Does not take the context/graph into conisderation.
     * <p>
     * @param uri A URI to Map.
     * @return (Possibly empty) List of URIs or even a null.
     */
    List<URI> getMatchesForURI(URI uri) throws QueryExpansionException;

    /**
     * Maps an URI to a list of URI.
     * <p>
     * May take the context/graph into conisderation. 
     * In which case URIs that are known not to be in the context/Graph will be removed.
     * For unknown graphs all possible URIs are returned.
     * <p>
     * NOTE: Implementations that do not have the information about which URIs are in which Graph 
     * will ignore the graph and just call getMatchesForURI(URI);
     * <p>
     * @param uri A URI to Map.
     * @return (Possibly empty) List of URIs (or null, that map to the URI and may be in the GRAPH. 
     *    There is no guarantee that every URI in the List will be in the GRAPH, 
     *    only that it Maps and there is not enough available information to say it can not be in the GRAPH.
     */
    List<URI> getSpecificMatchesForURI(URI uri, String graph) throws QueryExpansionException;
}
