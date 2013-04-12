/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Implements QueryExpander where these methods are documented.
 * @author Christian
 * @see QueryExpander
 */
public class QueryExpanderWSClientPost implements QueryExpander{

    protected final String serviceAddress;

    protected final WebResource webResource;

    public QueryExpanderWSClientPost(String serviceAddress) {
        this.serviceAddress = serviceAddress;
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource(serviceAddress);        
    }
    
    @Override
    public String expand(String originalQuery, List<String> parameters, String inputURI, String profileUri) throws QueryExpansionException {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add(QueryExpanderConstants.QUERY, originalQuery);
        for (String parameter:parameters){
            params.add(QueryExpanderConstants.PARAMETER, parameter);
        }
        if (inputURI != null && !inputURI.isEmpty()) {
            params.add(QueryExpanderConstants.INPUT_URI, inputURI);
        }
        if (profileUri != null && !profileUri.isEmpty()){
            params.add(QueryExpanderConstants.PROFILE_URI, profileUri);
        }
        ExpanderBean bean = 
                webResource.path("expand")
                .queryParams(params)
                .accept(MediaType.APPLICATION_XML_TYPE)
                .post(new GenericType<ExpanderBean>() {});
        return bean.getExpandedQuery();
    }

    @Override
    public String expand(String originalQuery, List<String> parameters, String inputURI, String profileUri, boolean verbose) throws QueryExpansionException {
        return expand(originalQuery, parameters, inputURI, profileUri);
    }
    
    /**
     * @deprecated 
     */
    @Override
    public String expand(String originalQuery, String profileUri) throws QueryExpansionException {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("query", originalQuery);
        if (profileUri != null && !profileUri.isEmpty()){
            params.add(QueryExpanderConstants.PROFILE_URI, profileUri);
        }
        ExpanderBean bean = 
                webResource.path("expand")
                .queryParams(params)
                .accept(MediaType.APPLICATION_XML_TYPE)
                .post(new GenericType<ExpanderBean>() {});
        return bean.getExpandedQuery();
    }

    @Override
    public Map<String, Set<String>> getURISpacesPerGraph() throws QueryExpansionException {
        List<URISpacesInGraphBean> beans = 
                webResource.path("URISpacesPerGraph")
                .accept(MediaType.APPLICATION_XML_TYPE)
                .post(new GenericType<List<URISpacesInGraphBean>>() {});
        HashMap<String, Set<String>> results = new HashMap<String, Set<String>>();
        for (URISpacesInGraphBean bean:beans){
            results.put(bean.getGraph(), bean.getURISpace());
        }
        return results;
    }

    @Override
    /** Currently not implemented but can be if required */
    public List<String> mapURI(String inputURI, String graph, String profileUri) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
