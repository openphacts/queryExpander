/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander.queryLoader;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.bridgedb.file.IDMapperText;
import org.bridgedb.url.WrapperURLMapper;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpansionException;
import uk.ac.cs.man.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.cs.man.openphacts.queryexpander.mapper.HardCodedGraphResolver;

/**
 *
 * @author Christian
 */
public class TestBridgeDBFactory {
    
    private static final File OFFLINE_TEST_FILE = new File ("test-data/offlineTest.txt");
	
    static BridgeDBMapper getBridgeDBMapper() throws QueryExpansionException{
        HardCodedGraphResolver resolver = new HardCodedGraphResolver();
        try {
            IDMapper idMapper = new IDMapperText(OFFLINE_TEST_FILE.toURL());
            WrapperURLMapper urlMapper = new WrapperURLMapper(idMapper);
            return new BridgeDBMapper (resolver.getAllowedNamespaces(), urlMapper);
        } catch (Exception ex) {
            throw new QueryExpansionException("Error setting up File mapper ", ex);
        }
    }
    	
	//@Test 
    //public void testFileExists()
	//{
    //    report("FileExists");
	//	Assert.assertTrue (INTERFACE_TEST_FILE.exists());
	//}	

}
