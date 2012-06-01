package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import java.util.Date;
import java.util.List;
import org.bridgedb.IDMapperException;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;
import java.util.Map;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.Ops1_1QueryLoader;
import org.junit.Ignore;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderWSClient;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
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
    public void testAllNoMapping() throws Exception{
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
 /*       DummyIMSMapper dummyMapper = new DummyIMSMapper();       
        dummyMapper.addMapping("http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5", 
                "http://data.kasabi.com/dataset/chembl-rdf/target/t197");
        dummyMapper.addMapping("http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5", 
                "http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734");
        dummyMapper.addMapping("http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5", 
                "http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228");
        dummyMapper.addMapping("http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5", 
                "http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398");
        dummyMapper.addMapping("http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5", 
                "http://rdf.chemspider.com/187440");
        
        HardCodedGraphResolver imsMapper = new HardCodedGraphResolver(dummyMapper);*/
        
        QueryExpander queryExpander = QueryExpanderClientFactory.createTestQueryExpanderWSClient();
        for (String queryKey:queryKeys){
            System.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getServerReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI);
            //ystem.out.println(newQuery);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true));
        }
    }

    @Test
    public void testURISpacesInGraph() throws IDMapperException, QueryExpansionException{
        QueryExpander queryExpander = QueryExpanderClientFactory.createTestQueryExpanderWSClient();
        Map<String, Set<String>> result = queryExpander.getURISpacesPerGraph();
        assertFalse(result.isEmpty());
        for (String graph:result.keySet()){
            Set<String> URISpaces = result.get(graph);
            assertFalse(URISpaces.isEmpty());        
        }
    }

    @Test
    @Ignore
    public void testSpeed() throws Exception{
        Date start = new Date();
        Ops1_1QueryLoader loader = new Ops1_1QueryLoader();
        Set<String> queryKeys = loader.keySet();
         
        QueryExpander queryExpander = QueryExpanderClientFactory.createOpenPhactsQueryExpanderWSClient();
        System.out.println("speed test");
        for (String queryKey:queryKeys){
            //ystem.out.println("Testing " + loader.getQueryName(queryKey));
            String originalQuery = loader.getOriginalQuery(queryKey);
            String targetQuery = loader.getServerReplaceQuery(queryKey);
            List<String> parameters = loader.getParameters(queryKey);
            String inputURI = loader.getInsertURI(queryKey);
            //ystem.out.println(originalQuery);
            String newQuery = queryExpander.expand(originalQuery, parameters, inputURI);
            //ystem.out.println(newQuery);
            //assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true));
        }
        System.out.println(new Date().getTime() - start.getTime());
    }

}
