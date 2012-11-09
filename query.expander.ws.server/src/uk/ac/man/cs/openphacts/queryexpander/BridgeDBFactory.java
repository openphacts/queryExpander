/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander;

import java.io.File;
import org.apache.log4j.Logger;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.bridgedb.file.IDMapperText;
import org.bridgedb.mysql.MySQLSpecific;
import org.bridgedb.sql.SQLAccess;
import org.bridgedb.sql.SQLUrlMapper;
import org.bridgedb.sql.SqlFactory;
import org.bridgedb.url.URLMapper;
import org.bridgedb.utils.StoreType;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.HardCodedGraphResolver;

/**
 *
 * @author Christian
 */
public class BridgeDBFactory {
    
    static final Logger logger = Logger.getLogger(BridgeDBFactory.class);
    
     public static BridgeDBMapper getBridgeDBMapper() throws QueryExpanderException{
        HardCodedGraphResolver resolver = new HardCodedGraphResolver();
        try {
            URLMapper urlMapper =new SQLUrlMapper(false, StoreType.LIVE);
            logger.info(urlMapper.getOverallStatistics());
            return new BridgeDBMapper (resolver.getAllowedNamespaces(), urlMapper);
        } catch (Exception ex) {
            throw new QueryExpanderException("Error setting up BridgeDB mapper ", ex);
        }
    }
     
}
