package uk.ac.man.cs.openphacts.queryexpander;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Christian
 */
public interface QueryExpander {
    
    /**
     * Expands the query using the loaed mappings.
     * 
     * Default implementation is to call the  expand(String originalQuery, boolean verbose) with verbise set to false.
     * 
     * @param originalQuery Query in String Format
     * @return Expanded query
     * @throws QueryExpansionException 
     */
    public String expand(String originalQuery, List<String> parameters, String inputURI) throws QueryExpansionException;

    /**
     * Expands the query using the loaed mappings.
     * 
     * @param originalQuery Query in String Format
     * @param verbose If set to true the expander will output debug information
     * @return Expanded query
     * @throws QueryExpansionException 
     */
    public String expand(String originalQuery, List<String> parameters, String inputURI, boolean verbose) 
            throws QueryExpansionException;
            
    public Map<String, Set<String>> getURISpacesPerGraph() throws QueryExpansionException;
}
