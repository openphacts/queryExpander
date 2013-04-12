package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.bridgedb.utils.TestUtils;
import static org.junit.Assert.*;
import org.junit.Test;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.man.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.IMSMapper;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class BugTest extends TestUtils{
    
    static final Logger logger = Logger.getLogger(BugTest.class);

    private final String NO_PROFILE = null;
           
    @Test
    public void testBugTest() throws Exception{
        //ConfigReader.logToConsole();
        BugLoader loader = new BugLoader();
        Set<String> queryKeys = loader.keySet();
        IMSMapper imsMapper = new DummyIMSMapper();
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            report("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getNoReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, NO_PROFILE, false);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, false, loader.getQueryName(queryKey)));
        }
    }

}
