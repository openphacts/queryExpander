package uk.ac.man.cs.openphacts.queryexpander.queryLoader.ws;

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
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class OpsReplacemeentTest  extends uk.ac.man.cs.openphacts.queryexpander.queryLoader.OpsReplacemeentTest {
        
    @Before
    public void setupMapper() throws IDMapperException {
        queryExpander = QueryExpanderClientFactory.createTestQueryExpanderWSClient(); 
    }
    
}
