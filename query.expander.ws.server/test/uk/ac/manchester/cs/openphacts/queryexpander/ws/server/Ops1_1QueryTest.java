package uk.ac.manchester.cs.openphacts.queryexpander.ws.server;

import uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.TestBridgeDBFactory;
import uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.Ops1_1QueryLoader;
import java.io.IOException;
import org.junit.Ignore;
import java.util.Date;
import java.util.List;
import uk.ac.manchester.cs.openphacts.queryexpander.ws.server.BridgeDBFactory;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.manchester.cs.openphacts.queryexpander.api.QueryExpander;
import java.util.Set;
import org.bridgedb.IDMapperException;
import org.bridgedb.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openrdf.OpenRDFException;
import org.bridgedb.uri.GraphResolver;
import uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.impl.TestLoader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class Ops1_1QueryTest extends TestUtils {

    private final String NO_LENS = null;

    @BeforeClass
    public static void LoadTestData() throws IDMapperException, IOException, OpenRDFException{
        TestLoader.LoadTestData();
    }
            
    @Test
    public void testAllWithMapping() throws Exception{
        GraphResolver.addTestMappings();
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
         
        BridgeDBMapper imsMapper = TestBridgeDBFactory.getBridgeDBMapper();
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            report("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getServerReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, NO_LENS, false);
            //ystem.out.println(newQuery);
            if (!QueryUtils.sameTupleExpr(targetQuery, newQuery, false, loader.getQueryName(queryKey))){
                assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey)));
            }
        }
    }

    @Test
    @Ignore
    public void testSpeed() throws Exception{
        Date start = new Date();
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
         
        BridgeDBMapper imsMapper = BridgeDBFactory.getBridgeDBMapper();
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        report("speed test");
        for (String queryKey:queryKeys){
            //ystem.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getServerReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, NO_LENS, false);
            //ystem.out.println(newQuery);
            //assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true));
        }
        report("Test took " + (new Date().getTime() - start.getTime()));
    }
}
