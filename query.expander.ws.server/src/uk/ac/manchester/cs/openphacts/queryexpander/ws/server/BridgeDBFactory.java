/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.manchester.cs.openphacts.queryexpander.ws.server;

import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderException;
import org.apache.log4j.Logger;
import org.bridgedb.sql.SQLUriMapper;
import org.bridgedb.uri.Lens;
import org.bridgedb.uri.UriMapper;
import org.bridgedb.utils.BridgeDBException;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.GraphResolver;

/**
 *
 * @author Christian
 */
public class BridgeDBFactory {
    
    static final Logger logger = Logger.getLogger(BridgeDBFactory.class);
    
     public static BridgeDBMapper getBridgeDBMapper() throws QueryExpanderException, BridgeDBException{
        GraphResolver resolver = GraphResolver.getInstance();
        try {
            UriMapper urlMapper = SQLUriMapper.getExisting();
            logger.info(urlMapper.getOverallStatistics(Lens.getAllLens()));
            return new BridgeDBMapper (resolver.getAllowedUriPatterns(), urlMapper);
        } catch (Exception ex) {
            throw new QueryExpanderException("Error setting up BridgeDB mapper ", ex);
        }
    }
     
}
