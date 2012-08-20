/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.io.IOException;
import org.bridgedb.IDMapperException;
import org.bridgedb.linkset.LinksetLoader;
import org.bridgedb.rdf.RdfStoreType;
import org.bridgedb.rdf.RdfWrapper;
import org.bridgedb.sql.TestSqlFactory;
import org.bridgedb.utils.Reporter;
import org.junit.BeforeClass;
import org.openrdf.OpenRDFException;

/**
 *
 * @author Christian
 */
public abstract class LoaderBase {

    @BeforeClass
    public static void testLoader() throws IDMapperException, IOException, OpenRDFException  {
        //Check database is running and settup correctly or kill the test.
        TestSqlFactory.createTestSQLAccess();

        Reporter.report("Clearing Data");
        RdfWrapper.clear(RdfStoreType.TEST);
        
        LinksetLoader loader = new LinksetLoader();
        
        Reporter.report("cw-cs.ttl");
        loader.parse("test-data/cw-cs.ttl", "test");
        Reporter.report("cw-cm.ttl");
        loader.parse("test-data/cw-cm.ttl", "test");
        Reporter.report("cw-dd.ttl");
        loader.parse("test-data/cw-dd.ttl", "test");
        Reporter.report("cw-ct.ttl");
        loader.parse("test-data/cw-ct.ttl", "test");
        Reporter.report("cw-dt.ttl");
        loader.parse("test-data/cw-dt.ttl", "test");
	}

}
