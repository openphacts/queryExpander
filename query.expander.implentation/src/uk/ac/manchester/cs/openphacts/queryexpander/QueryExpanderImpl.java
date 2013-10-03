/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.manchester.cs.openphacts.queryexpander;

import uk.ac.manchester.cs.openphacts.queryexpander.api.QueryExpander;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.bridgedb.rdf.UriPattern;
import org.bridgedb.uri.GraphResolver;
import org.bridgedb.uri.RegexUriPattern;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.utils.Reporter;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.Dataset;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.sparql.SPARQLParser;
import uk.ac.manchester.cs.openphacts.queryexpander.mapper.IMSMapper;
import uk.ac.manchester.cs.openphacts.queryexpander.visitor.QueryExpandAndWriteVisitor;
import uk.ac.manchester.cs.openphacts.queryexpander.visitor.QueryReplaceAndWriteVisitor;

/**
 *
 * @author Christian
 */
public class QueryExpanderImpl implements QueryExpander{

    static final Logger logger = Logger.getLogger(QueryExpanderImpl.class);

    private static final SPARQLParser parser = new SPARQLParser();
    private static final List<String> ALL_ATTRIBUTES = null;
    
    IMSMapper imsMapper;
    
    public QueryExpanderImpl(IMSMapper imsMapper){
        this.imsMapper = imsMapper;
    }
    
    @Override
    public String expand(String originalQuery, List<String> parameters, String inputURI, String lensUri) throws QueryExpanderException {
        return expand(originalQuery, parameters, inputURI, lensUri, false);
    }
        
    @Override
    public String expand(String originalQuery, String lensUri) throws QueryExpanderException {
        return expand(originalQuery, new ArrayList<String>(), null, lensUri, false);
    }

    public String expand(String originalQuery, List<String> parameters, String inputURI, String lensUri, boolean verbose) 
            throws QueryExpanderException {
        logger.info("expand called with " + inputURI);
        logger.trace("expand called with " + originalQuery);
        inputURI = checkURI(inputURI);
        if (verbose) logger.info(originalQuery);
        ParsedQuery parsedQuery; 
        try {
            parsedQuery = parser.parseQuery(originalQuery, null);
        } catch (MalformedQueryException ex) {
            throw new QueryExpanderException("Unable to parse the query " + originalQuery, ex);
        }
        TupleExpr tupleExpr = parsedQuery.getTupleExpr();
        if (verbose) logger.info(tupleExpr);
        Dataset dataset = parsedQuery.getDataset();
        URI InputAsURI = null;
        if (inputURI != null && !inputURI.isEmpty()){
            InputAsURI = new URIImpl(inputURI);
        }
        String newQuery;
        if (parameters.isEmpty()){
            newQuery = QueryExpandAndWriteVisitor.convertToQueryString(tupleExpr, dataset, imsMapper, ALL_ATTRIBUTES, lensUri);
        } else {
            newQuery = QueryReplaceAndWriteVisitor.convertToQueryString(tupleExpr, dataset, parameters, InputAsURI,
                    imsMapper, ALL_ATTRIBUTES, lensUri);
        }
        try {
            parsedQuery = parser.parseQuery(newQuery, null);
        } catch (MalformedQueryException ex) {
            Reporter.error("originalQuery was " + originalQuery);
            Reporter.error("tupleExpr was:\n" + tupleExpr);
            Reporter.error("parameters were " + parameters);
            Reporter.error("newQuery \n" + newQuery);
            throw new QueryExpanderException("OOPS! Unable to parse the result query \n" + newQuery, ex);
        }
        return newQuery;
    }

    @Override
    public Map<String, Set<String>> getURISpacesPerGraph() throws QueryExpanderException {
        Map<String, Set<RegexUriPattern>> mappings;
        try {
            mappings = GraphResolver.getInstance().getAllowedUriPatterns();
        } catch (BridgeDBException ex) {
            throw new QueryExpanderException("Unable to get URISpacesPerGraph", ex);
        }
        Map<String, Set<String>> results = new  HashMap<String, Set<String>>();
        for (String graph:mappings.keySet()){
           Set<String> patternStrings = new HashSet<String>();
           for (RegexUriPattern pattern:mappings.get(graph)){
               patternStrings.add(pattern.getUriPattern());
           }
           results.put(graph, patternStrings);   
        }
        return results;
    }

    @Override
    public List<String> mapURI(String inputURI, String graph, String lensUri) throws QueryExpanderException {
        URI uri = new URIImpl(inputURI);
        List<URI> mappings;
        if (graph != null && !graph.isEmpty()){
            mappings =  imsMapper.getSpecificMatchesForURI(uri, graph, lensUri);
        } else {
           mappings =  imsMapper.getMatchesForURI(uri, lensUri);
        }
        ArrayList<String> results = new ArrayList<String>();
        for (URI mapping: mappings){
            results.add(mapping.stringValue());
        }
        return results;
    }

    private String checkURI(String inputURI) {
        if (inputURI == null || inputURI.isEmpty()) return inputURI;
        String checked = inputURI.trim();
        URI test = new URIImpl(inputURI);
        return inputURI;
    }
    
}
