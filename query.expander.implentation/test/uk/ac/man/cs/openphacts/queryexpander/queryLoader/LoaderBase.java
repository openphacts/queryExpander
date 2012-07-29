/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.io.IOException;
import org.bridgedb.IDMapperException;
import org.bridgedb.linkset.LinksetLoader;
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

        Reporter.report("cw-cs.ttl");
        String[] args4 = {"test-data/cw-cs.ttl", "testnew"};
        LinksetLoader.main (args4);
        Reporter.report("cw-cm.ttl");
        String[] args5 = {"test-data/cw-cm.ttl", "test"};
        LinksetLoader.main (args5);
        Reporter.report("cw-dd.ttl");
        String[] args6 = {"test-data/cw-dd.ttl", "test"};
        LinksetLoader.main (args6);
        Reporter.report("cw-ct.ttl");
        String[] args7 = {"test-data/cw-ct.ttl", "test"};
        LinksetLoader.main (args7);
        Reporter.report("cw-dt.ttl");
        String[] args8 = {"test-data/cw-dt.ttl", "test"};
        LinksetLoader.main (args8);
	}

}
