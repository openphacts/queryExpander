package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import org.junit.Ignore;
import java.util.List;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import java.util.Set;
import org.junit.Test;
import uk.ac.man.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.IMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.visitor.ExpansionStategy;
import static org.junit.Assert.*;

/*
 * See also
 * http://code.google.com/p/fbench/wiki/Queries#Life_Science_%28LS%29
 */

/**
 *
 * @author Christian
 */
public class AlternativeTest {
    
    @Test
    public void testAll() throws Exception{
        AlternativeLoader loader = new AlternativeLoader();
        Set<String> queryKeys = loader.keySet();
        DummyIMSMapper imsMapper = new DummyIMSMapper();
        imsMapper.addMapping("http://www.foo.com/subj1", "http://www.bar.com/1");
        imsMapper.addMapping("http://www.foo.com/subj1", "http://www.bar.com/2");
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            System.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getTextReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, false, ExpansionStategy.FILTER_GRAPH);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey) + " FILTER_GRAPH"));

            newQuery = queryExpander.expand(originalQuery, parameters, inputURI, false, ExpansionStategy.FILTER_STATEMENT);
            targetQuery = loader.getFilterStatement(queryKey);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey)+ " FILTER_STATEMENT"));

            newQuery = queryExpander.expand(originalQuery, parameters, inputURI, false, ExpansionStategy.FILTER_ALL);
            targetQuery = loader.getFilterAll(queryKey);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey)+ " FILTER_ALL"));
        }
    }

}
