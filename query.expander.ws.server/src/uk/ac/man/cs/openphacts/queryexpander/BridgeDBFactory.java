/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.bridgedb.file.IDMapperText;
import org.bridgedb.mysql.MySQLSpecific;
import org.bridgedb.sql.SQLAccess;
import org.bridgedb.sql.SQLUrlMapper;
import org.bridgedb.sql.SqlFactory;
import org.bridgedb.url.URLMapper;
import org.bridgedb.utils.StoreType;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.HardCodedGraphResolver;

/**
 *
 * @author Christian
 */
public class BridgeDBFactory {
    
     public static BridgeDBMapper getBridgeDBMapper() throws QueryExpansionException{
        HardCodedGraphResolver resolver = new HardCodedGraphResolver();
        try {
            URLMapper urlMapper =new SQLUrlMapper(false, StoreType.LIVE);
            System.out.println(urlMapper.getOverallStatistics());
            return new BridgeDBMapper (resolver.getAllowedNamespaces(), urlMapper);
        } catch (Exception ex) {
            throw new QueryExpansionException("Error setting up BridgeDB mapper ", ex);
        }
    }
     
}
