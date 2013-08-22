package uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.impl;

import java.io.IOException;
import org.bridgedb.IDMapperException;
import org.bridgedb.utils.BridgeDBException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openrdf.OpenRDFException;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.TestBridgeDBFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class Ops1_1QueryTest extends uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.Ops1_1QueryTest {
        
    @BeforeClass
    public static void LoadTestData() throws IDMapperException, IOException, OpenRDFException{
        TestLoader.LoadTestData();
    }
            
    @Before
    public void setupMapper() throws QueryExpanderException, BridgeDBException{
        BridgeDBMapper imsMapper = TestBridgeDBFactory.getBridgeDBMapper();
        queryExpander = new QueryExpanderImpl(imsMapper);
    }
    
}
