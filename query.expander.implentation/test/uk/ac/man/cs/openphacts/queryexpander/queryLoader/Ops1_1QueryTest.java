package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bridgedb.utils.TestUtils;
import static org.junit.Assert.*;
import org.junit.Test;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.man.cs.openphacts.queryexpander.mapper.GraphResolver;

/**
 *
 * @author Christian
 */
public abstract class Ops1_1QueryTest extends TestUtils {
    
    protected QueryExpander queryExpander;
               
    private final String NO_LENS = null;
            
    @Test
    public void testAllNoMapping() throws Exception{
        GraphResolver.addTestMappings();
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
         
        for (String queryKey:queryKeys){
            report("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery;
            targetQuery = loader.getTextReplaceQuery(queryKey);
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
    public void testURISpacesInGraph() throws QueryExpansionException{
        Map<String, Set<String>> result = queryExpander.getURISpacesPerGraph();
        assertFalse(result.isEmpty());
        for (String graph:result.keySet()){
            Set<String> URISpaces = result.get(graph);
            assertFalse(URISpaces.isEmpty());        
        }
    }
}
