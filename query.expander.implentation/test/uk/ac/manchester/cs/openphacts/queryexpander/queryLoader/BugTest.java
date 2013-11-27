package uk.ac.manchester.cs.openphacts.queryexpander.queryLoader;

import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.bridgedb.utils.Reporter;
import static org.junit.Assert.*;
import org.junit.Test;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.IMSMapper;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class BugTest {
    
    static final Logger logger = Logger.getLogger(BugTest.class);

    private final String NO_LENS = null;
           
    @Test
    public void testBugTest() throws Exception{
        //ConfigReader.logToConsole();
        BugLoader loader = new BugLoader();
        Set<String> queryKeys = loader.keySet();
        IMSMapper imsMapper = new DummyIMSMapper();
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            Reporter.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getNoReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, NO_LENS, false);
            if (!QueryUtils.sameTupleExpr(targetQuery, newQuery, false, loader.getQueryName(queryKey))){
                Reporter.println(targetQuery);
                Reporter.error("Does not match");                
                Reporter.println(newQuery);
                assertTrue(false);
            }
        }
    }

}
