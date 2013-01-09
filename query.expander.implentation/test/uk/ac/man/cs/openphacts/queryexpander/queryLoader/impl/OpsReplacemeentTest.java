package uk.ac.man.cs.openphacts.queryexpander.queryLoader.impl;

import java.io.IOException;
import org.junit.Ignore;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import java.util.List;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import java.util.Set;
import org.bridgedb.IDMapperException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openrdf.OpenRDFException;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.TestBridgeDBFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class OpsReplacemeentTest extends uk.ac.man.cs.openphacts.queryexpander.queryLoader.OpsReplacemeentTest {
        
    @BeforeClass
    public static void LoadTestData() throws IDMapperException, IOException, OpenRDFException{
        TestLoader.LoadTestData();
    }
            
    @Before
    public void setupMapper() throws QueryExpanderException{
        BridgeDBMapper imsMapper = TestBridgeDBFactory.getBridgeDBMapper();
        queryExpander = new QueryExpanderImpl(imsMapper);
    }
    
}
