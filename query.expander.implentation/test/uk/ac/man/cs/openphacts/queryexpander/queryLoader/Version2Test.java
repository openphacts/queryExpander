package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.io.IOException;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import java.util.List;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import java.util.Set;
import org.bridgedb.IDMapperException;
import org.bridgedb.utils.TestUtils;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openrdf.OpenRDFException;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.impl.TestLoader;

/**
 *
 * @author Christian
 */
public abstract class Version2Test extends TestUtils{

    protected QueryExpander queryExpander;
   
    @Test
    public void testAllNoMapping() throws Exception{
        Version2Loader loader = new Version2Loader();
        Set<String> queryKeys = loader.keySet();
        for (String queryKey:queryKeys){
            report("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getTextReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            //ystem.out.println(parameters);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, false);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey)));
        }
    }
 
}
