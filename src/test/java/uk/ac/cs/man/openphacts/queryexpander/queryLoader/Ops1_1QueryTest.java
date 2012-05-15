package uk.ac.cs.man.openphacts.queryexpander.queryLoader;

import uk.ac.cs.man.openphacts.queryexpander.QueryUtils;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpander;
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
public class Ops1_1QueryTest {
    
    @Test
    public void testAllNoMapping() throws Exception{
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
        IMSMapper imsMapper = new DummyIMSMapper();
        QueryExpander queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            System.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getNoReplaceQuery(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, true);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true));
        }
    }

}
