package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import java.util.List;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import uk.ac.man.cs.openphacts.queryexpander.visitor.ExpansionStategy;
import static org.junit.Assert.*;

/**
 *
 * @author Christian
 */
public class Version2Test extends LoaderBase {

    @Test
    public void testAllNoMapping() throws Exception{
        Version2Loader loader = new Version2Loader();
        Set<String> queryKeys = loader.keySet();
        BridgeDBMapper imsMapper = TestBridgeDBFactory.getBridgeDBMapper();
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            report("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getTextReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            //ystem.out.println(parameters);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, false,
                    ExpansionStategy.FILTER_GRAPH);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey)));
        }
    }
 
}
