package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import org.junit.Ignore;
import java.util.Date;
import java.util.List;
import uk.ac.man.cs.openphacts.queryexpander.BridgeDBFactory;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import java.util.Set;
import org.junit.Test;
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
    public void testAllWithMapping() throws Exception{
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
         
        BridgeDBMapper imsMapper = BridgeDBFactory.getBridgeDBMapper();
        QueryExpander queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            System.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getServerReplaceQuery(queryKey);
            List<String> parameters = loader.getPlaceHolders(queryKey);
            String inputURI = loader.getReplacementVariable(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, true);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true));
        }
    }

    @Test
    @Ignore
    public void testSpeed() throws Exception{
        Date start = new Date();
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
         
        BridgeDBMapper imsMapper = BridgeDBFactory.getBridgeDBMapper();
        QueryExpander queryExpander = new QueryExpanderImpl(imsMapper);
        System.out.println("speed test");
        for (String queryKey:queryKeys){
            //ystem.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getServerReplaceQuery(queryKey);
            List<String> parameters = loader.getPlaceHolders(queryKey);
            String inputURI = loader.getReplacementVariable(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, false);
            //ystem.out.println(newQuery);
            //assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true));
        }
        System.out.println(new Date().getTime() - start.getTime());
    }
}
