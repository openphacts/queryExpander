package uk.ac.man.cs.openphacts.queryexpander.test.post;

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
public class Ops1_1QueryTest  extends uk.ac.man.cs.openphacts.queryexpander.queryLoader.Ops1_1QueryTest {
        
    @Before
    public void setupMapper() throws IDMapperException {
        queryExpander = QueryExpanderClientFactory.createTestQueryExpanderWSClientPost(); 
    }
    
}
