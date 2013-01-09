package uk.ac.man.cs.openphacts.queryexpander.test.get;

import org.bridgedb.IDMapperException;
import org.junit.Before;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.ws.QueryExpanderClientFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class Version2Test  extends uk.ac.man.cs.openphacts.queryexpander.queryLoader.Version2Test {
        
    @Before
    public void setupMapper() throws IDMapperException {
        queryExpander = QueryExpanderClientFactory.createTestQueryExpanderWSClientGet(); 
    }
    
}
