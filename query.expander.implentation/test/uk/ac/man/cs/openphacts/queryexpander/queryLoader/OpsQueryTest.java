package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.util.List;
import org.junit.Ignore;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.OpsQueryLoader;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import java.util.Set;
import org.junit.Test;
import uk.ac.man.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.IMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.OpsQueryLoader;
import static org.junit.Assert.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class OpsQueryTest {
    
    @Test
    public void testAllNoMapping() throws Exception{
        OpsQueryLoader loader = new OpsQueryLoader();
        Set<String> queryKeys = loader.keySet();
        IMSMapper imsMapper = new DummyIMSMapper();
        QueryExpander queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            System.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getNoReplaceQuery(queryKey);
            List<String> parameters = loader.getPlaceHolders(queryKey);
            String inputURI = loader.getReplacementVariable(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, true);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, false));
        }
    }

}
