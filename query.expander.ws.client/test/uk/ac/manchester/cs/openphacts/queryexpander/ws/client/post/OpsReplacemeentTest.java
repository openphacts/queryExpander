package uk.ac.manchester.cs.openphacts.queryexpander.ws.client.post;

import org.bridgedb.IDMapperException;
import org.junit.Before;
import org.junit.Ignore;
import uk.ac.manchester.cs.openphacts.queryexpander.ws.client.QueryExpanderClientFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
@Ignore //As assumes no mappimngs
public class OpsReplacemeentTest  extends uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.OpsReplacemeentTest {
        
    @Before
    public void setupMapper() throws IDMapperException {
        queryExpander = QueryExpanderClientFactory.createTestQueryExpanderWSClientPost(); 
    }
    
}
