package uk.ac.cs.man.openphacts.queryexpander;

import java.util.Set;
import org.junit.Test;
import uk.ac.cs.man.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.cs.man.openphacts.queryexpander.mapper.IMSMapper;
import static org.junit.Assert.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class QueryCaseTest {
    
    @Test
    public void testAllNoMapping() throws Exception{
        QueryCaseLoader loader = new QueryCaseLoader();
        Set<String> queryKeys = loader.keySet();
        IMSMapper imsMapper = new DummyIMSMapper();
        QueryExpander queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            System.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String newQuery = queryExpander.expand(originalQuery);
            System.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(originalQuery, newQuery, true));
        }
    }

}
