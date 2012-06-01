/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bridgedb.DataSource;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.bridgedb.file.IDMapperText;
import org.bridgedb.url.WrapperURLMapper;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.HardCodedGraphResolver;

/**
 *
 * @author Christian
 */
public class TestBridgeDBFactory {
    
    private static final File OFFLINE_TEST_FILE = new File ("../query.expander.implentation/test-data/offlineTest.txt");
	private static final String[] targetNameSpaces = 
            {"http://www.conceptwiki.org/concept/",
            "http://data.kasabi.com/dataset/chembl-rdf/target/",
            "http://data.kasabi.com/dataset/chembl-rdf/molecule/",
            "http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/",
            "http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/",
            "http://rdf.chemspider.com/"};

    
    public static BridgeDBMapper getBridgeDBMapper() throws QueryExpansionException{
        HardCodedGraphResolver resolver = new HardCodedGraphResolver();
        try {
            for (int i = 0; i < targetNameSpaces.length; i++){
                DataSource.getByNameSpace(targetNameSpaces[i]);
            }
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
