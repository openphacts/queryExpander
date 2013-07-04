package uk.ac.man.cs.openphacts.queryexpander.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.bridgedb.rdf.UriPattern;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.utils.ConfigReader;

/**
 *
 * @author Christian
 */
public class GraphResolver {

    private HashMap<String,Set<UriPattern>> allowedUriPattern;
    
    private final static String PROPERTIES_FILE = "graph.properties";
    private final static String PROPERTY_PREFIX = "context.";
    private final static String PATTERN  = "pattern";
    private final static String GRAPH_POSTFIX  = ".graph";
    
    public GraphResolver() throws BridgeDBException{
        readProperties();
    }
    
    private void readProperties() throws BridgeDBException{
        allowedUriPattern = new HashMap<String,Set<UriPattern>>();
        Properties properties = ConfigReader.getProperties(PROPERTIES_FILE);
        Set<String> keys = properties.stringPropertyNames();
        for (String key:keys){
            if (key.startsWith(PROPERTY_PREFIX)){
                String[] parts = key.split("\\.");
                if (parts[2].equals(PATTERN)){
                    String graphKey = PROPERTY_PREFIX + parts[1] + GRAPH_POSTFIX;
                    String graph =  properties.getProperty(graphKey);
                    Set<UriPattern> patterns = allowedUriPattern.get(graph);
                    if (patterns == null){
                        patterns = new HashSet<UriPattern>();
                    }
                    String pattern = properties.getProperty(key);
                    UriPattern uriPattern = UriPattern.byPattern(pattern);
                    patterns.add(uriPattern);
                    allowedUriPattern.put(graph, patterns);
                }
            }
        }
    }
    
    /**
     * @return the allowedNamespaces
     */
    public HashMap<String,Set<UriPattern>> getAllowedUriPatterns() {
        return allowedUriPattern;
    }


}
