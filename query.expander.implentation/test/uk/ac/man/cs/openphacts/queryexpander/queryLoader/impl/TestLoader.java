/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.queryLoader.impl;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.bridgedb.IDMapperException;
import org.bridgedb.linkset.LinksetLoader;
import org.bridgedb.metadata.validator.ValidationType;
import org.bridgedb.sql.TestSqlFactory;
import org.bridgedb.utils.Reporter;
import org.bridgedb.utils.StoreType;
import org.bridgedb.utils.TestUtils;
import org.junit.BeforeClass;
import org.openrdf.OpenRDFException;

/**
 *
 * @author Christian
 */
public class TestLoader {

    static final Logger logger = Logger.getLogger(TestLoader.class);

    public static void LoadTestData() throws IDMapperException, IOException, OpenRDFException  {
        //Check database is running and settup correctly or kill the test.
        TestSqlFactory.checkSQLAccess();

        LinksetLoader linksetLoader = new LinksetLoader();
        linksetLoader.clearExistingData(StoreType.TEST);
        
        linksetLoader.loadFile("../query.expander.implentation/test-data/cw-cs.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.loadFile("../query.expander.implentation/test-data/cw-cm.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.loadFile("../query.expander.implentation/test-data/cw-dd.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.loadFile("../query.expander.implentation/test-data/cw-ct.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.loadFile("../query.expander.implentation/test-data/cw-dt.ttl", StoreType.TEST, 
                ValidationType.LINKSMINIMAL);
	}

}
