package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.io.File;
import org.bridgedb.DataSource;
import org.bridgedb.mysql.MySQLSpecific;
import org.bridgedb.sql.BridgeDbSqlException;
import org.bridgedb.sql.SQLAccess;
import org.bridgedb.sql.SQLUrlMapper;
import org.bridgedb.sql.SqlFactory;
import org.bridgedb.url.URLMapper;
import org.bridgedb.utils.StoreType;
import org.junit.Test;
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
            SQLAccess sqlAccess = createTestSQLAccess();
            URLMapper urlMapper =new SQLUrlMapper(false, sqlAccess, new MySQLSpecific());
            return new BridgeDBMapper (resolver.getAllowedNamespaces(), urlMapper);
        } catch (Exception ex) {
            throw new QueryExpansionException("Error setting up File mapper ", ex);
        }
    }
    	
    public static SQLAccess createTestSQLAccess() {
        try {
            SQLAccess sqlAccess = SqlFactory.createSQLAccess(StoreType.TEST);
            sqlAccess.getConnection();
            return sqlAccess;
        } catch (BridgeDbSqlException ex) {
            System.err.println(ex);
            System.err.println("**** SKIPPPING tests due to Connection error.");
            System.err.println("To run these test you must have the following:");
            System.err.println("1. A MYSQL server running as configured " + SqlFactory.CONFIG_FILE_NAME);
            System.err.println("1a. Location of that file can be set by Enviroment Variable OPS-IMS-CONFIG");
            System.err.println("1b. Otherwise it will be looked for in the run path then conf/OPS-IMS then resources ");
            System.err.println("1c.     then the conf/OPS-IMS and resources in the SQL project in that order");
            System.err.println("1d. Failing that the defaults in SqlFactory.java will be used.");
            System.err.println("2. Full rights for test user on the test database required.");
            org.junit.Assume.assumeTrue(false);        
            return null;
         }
    }

    @Test
    public void doNothing(){
    }

}
