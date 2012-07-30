package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.util.List;
import org.junit.Ignore;
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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class SparqlTest {
    
    @Test
    public void testAllNoMapping() throws Exception{
        SparqlLoader loader = new SparqlLoader();
        Set<String> queryKeys = loader.keySet();
        IMSMapper imsMapper = new DummyIMSMapper();
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        for (String queryKey:queryKeys){
            System.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getNoReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI, false,
                    ExpansionStategy.FILTER_GRAPH);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey)));
        }
    }

}
