package uk.ac.manchester.cs.openphacts.queryexpander.queryLoader;

import org.bridgedb.utils.TestUtils;
import java.util.List;
import org.junit.Ignore;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.OpsQueryLoader;
import uk.ac.manchester.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.manchester.cs.openphacts.queryexpander.api.QueryExpander;
import java.util.Set;
import org.junit.Test;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.IMSMapper;
import uk.ac.manchester.cs.openphacts.queryexpander.queryLoader.OpsQueryLoader;
import static org.junit.Assert.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class OpsQueryTest extends TestUtils{
    
    private final String NO_LENS = null;
            
    @Test
    public void testAllNoMapping() throws Exception{
        OpsQueryLoader loader = new OpsQueryLoader();
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
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, NO_LENS, false);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, false, loader.getQueryName(queryKey)));
        }
    }

}