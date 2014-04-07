/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.manchester.cs.openphacts.queryexpander.ws.server;

import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderException;
import org.apache.log4j.Logger;
import org.bridgedb.sql.SQLUriMapper;
import org.bridgedb.uri.api.UriMapper;
import org.bridgedb.uri.lens.Lens;
import org.bridgedb.utils.BridgeDBException;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.BridgeDBMapper;

/**
 *
 * @author Christian
 */
public class BridgeDBFactory {
    
    static final Logger logger = Logger.getLogger(BridgeDBFactory.class);
    
     public static BridgeDBMapper getBridgeDBMapper() throws QueryExpanderException, BridgeDBException{
        try {
            UriMapper urlMapper = SQLUriMapper.getExisting();
            logger.info(urlMapper.getOverallStatistics(Lens.ALL_LENS_NAME));
            return new BridgeDBMapper (urlMapper);
        } catch (Exception ex) {
            throw new QueryExpanderException("Error setting up BridgeDB mapper ", ex);
        }
    }
     
}
