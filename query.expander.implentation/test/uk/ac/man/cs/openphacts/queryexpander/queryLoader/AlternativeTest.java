package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

import org.junit.Ignore;
import java.util.List;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpander;
import java.util.Set;
import org.junit.Test;
import org.openrdf.query.Dataset;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.sparql.SPARQLParser;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;
import uk.ac.man.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.IMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.visitor.ExpansionStategy;
import uk.ac.man.cs.openphacts.queryexpander.visitor.UnionExpansionVisitor;
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
    
    public String expand(String originalQuery, ExpansionStategy expansionStategy)
            throws QueryExpansionException {
        ParsedQuery parsedQuery;
        SPARQLParser parser = new SPARQLParser();
        try {
            parsedQuery = parser.parseQuery(originalQuery, null);
        } catch (MalformedQueryException ex) {
            throw new QueryExpansionException("Unable to parse the query " + originalQuery, ex);
        }
        TupleExpr tupleExpr = parsedQuery.getTupleExpr();
        Dataset dataset = parsedQuery.getDataset();
        String newQuery;
        DummyIMSMapper imsMapper = new DummyIMSMapper();
        imsMapper.addMapping("http://www.foo.com/subj1", "http://www.bar.com/1");
        imsMapper.addMapping("http://www.foo.com/subj2", "http://www.bar.com/2");
        newQuery = UnionExpansionVisitor.convertToQueryString(tupleExpr, dataset, imsMapper, expansionStategy);
        try {
            parsedQuery = parser.parseQuery(newQuery, null);
        } catch (MalformedQueryException ex) {
            throw new QueryExpansionException("OOPS! Unable to parse the result query " + newQuery, ex);
        }
        return newQuery;
    }

    @Test
    public void testAll() throws Exception{
        AlternativeLoader loader = new AlternativeLoader();
        Set<String> queryKeys = loader.keySet();
        DummyIMSMapper imsMapper = new DummyIMSMapper();
        imsMapper.addMapping("http://www.foo.com/subj1", "http://www.bar.com/1");
        imsMapper.addMapping("http://www.foo.com/subj2", "http://www.bar.com/2");
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

            newQuery = expand(originalQuery, ExpansionStategy.UNION_ALL);
            targetQuery = loader.getUnionAll(queryKey);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, false, loader.getQueryName(queryKey)+ " UNION_ALL"));

            newQuery = expand(originalQuery, ExpansionStategy.UNION_STATEMENT);
            //ystem.out.println(newQuery);
            targetQuery = loader.getUnionStatement(queryKey);
            assertTrue(QueryUtils.sameTupleExpr(targetQuery, newQuery, true, loader.getQueryName(queryKey)+ " UNION_STATEMENT"));
            System.out.println("All ok");
        }
    }

}
