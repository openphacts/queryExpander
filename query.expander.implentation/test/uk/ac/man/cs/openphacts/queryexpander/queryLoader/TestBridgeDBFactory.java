package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.io.File;
import org.bridgedb.sql.SQLUriMapper;
import org.bridgedb.sql.TestSqlFactory;
import org.bridgedb.uri.UriMapper;
import org.bridgedb.utils.StoreType;
import org.junit.Test;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;
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

    
    public static BridgeDBMapper getBridgeDBMapper() throws QueryExpanderException{
        HardCodedGraphResolver resolver = new HardCodedGraphResolver();
        try {
            TestSqlFactory.checkSQLAccess();
            UriMapper urlMapper = SQLUriMapper.factory(false, StoreType.TEST);
            return new BridgeDBMapper (resolver.getAllowedUriPatterns(), urlMapper);
        } catch (Exception ex) {
            throw new QueryExpanderException("Error setting up File mapper ", ex);
        }
    }
    	
    @Test
    public void doNothing(){
    }

}
