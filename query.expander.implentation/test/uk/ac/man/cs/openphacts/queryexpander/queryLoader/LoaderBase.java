/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.io.IOException;
import org.bridgedb.IDMapperException;
import org.bridgedb.linkset.LinksetLoader;
import org.bridgedb.metadata.validator.ValidationType;
import org.bridgedb.rdf.RdfFactory;
import org.bridgedb.rdf.RdfWrapper;
import org.bridgedb.sql.TestSqlFactory;
import org.bridgedb.utils.Reporter;
import org.bridgedb.utils.StoreType;
import org.junit.BeforeClass;
import org.openrdf.OpenRDFException;

/**
 *
 * @author Christian
 */
public abstract class LoaderBase {

    private static boolean LOAD = true;
    
    @BeforeClass
    public static void testLoader() throws IDMapperException, IOException, OpenRDFException  {
        //Check database is running and settup correctly or kill the test.
        TestSqlFactory.createTestSQLAccess();

        Reporter.report("Clearing Data");
        RdfFactory.clear(StoreType.TEST);
        
        Reporter.report("cw-cs.ttl");
        LinksetLoader.parse("../query.expander.implentation/test-data/cw-cs.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL, LOAD);
        Reporter.report("cw-cm.ttl");
        LinksetLoader.parse("../query.expander.implentation/test-data/cw-cm.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL, LOAD);
        Reporter.report("cw-dd.ttl");
        LinksetLoader.parse("../query.expander.implentation/test-data/cw-dd.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL, LOAD);
        Reporter.report("cw-ct.ttl");
        LinksetLoader.parse("../query.expander.implentation/test-data/cw-ct.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL, LOAD);
        Reporter.report("cw-dt.ttl");
        LinksetLoader.parse("../query.expander.implentation/test-data/cw-dt.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL, LOAD);
	}

}
