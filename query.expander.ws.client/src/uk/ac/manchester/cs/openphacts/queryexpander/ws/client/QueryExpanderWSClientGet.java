/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.manchester.cs.openphacts.queryexpander.ws.client;

import uk.ac.manchester.cs.openphacts.queryexpander.api.ExpanderBean;
import uk.ac.manchester.cs.openphacts.queryexpander.api.QueryExpanderConstants;
import uk.ac.manchester.cs.openphacts.queryexpander.api.QueryExpansionException;
import uk.ac.manchester.cs.openphacts.queryexpander.api.QueryExpander;
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
public class QueryExpanderWSClientGet implements QueryExpander{

    protected final String serviceAddress;

    protected final WebResource webResource;

    public QueryExpanderWSClientGet(String serviceAddress) {
        this.serviceAddress = serviceAddress;
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource(serviceAddress);        
    }
    
    @Override
    public String expand(String originalQuery, List<String> parameters, String inputURI, String lensUri) throws QueryExpansionException {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add(QueryExpanderConstants.QUERY, originalQuery);
        for (String parameter:parameters){
            params.add(QueryExpanderConstants.PARAMETER, parameter);
        }
        if (inputURI != null && !inputURI.isEmpty()) {
            params.add(QueryExpanderConstants.INPUT_URI, inputURI);
        }
        if (lensUri != null && !lensUri.isEmpty()){
            params.add(QueryExpanderConstants.LENS_URI, lensUri);
        }
        ExpanderBean bean = 
                webResource.path("expand")
                .queryParams(params)
                .accept(MediaType.APPLICATION_XML_TYPE)
                .get(new GenericType<ExpanderBean>() {});
        return bean.getExpandedQuery();
    }

    @Override
    public String expand(String originalQuery, List<String> parameters, String inputURI, String lensUri, boolean verbose) throws QueryExpansionException {
        return expand(originalQuery, parameters, inputURI, lensUri);
    }
    
    /**
     * @deprecated 
     */
    @Override
    public String expand(String originalQuery, String lensUri) throws QueryExpansionException {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add(QueryExpanderConstants.QUERY, originalQuery);
        if (lensUri != null && !lensUri.isEmpty()){
            params.add(QueryExpanderConstants.LENS_URI, lensUri);
        }
        ExpanderBean bean = 
                webResource.path("expand")
                .queryParams(params)
                .accept(MediaType.APPLICATION_XML_TYPE)
                .get(new GenericType<ExpanderBean>() {});
        return bean.getExpandedQuery();
    }

    @Override
    /** Currently not implemented but can be if required */
    public List<String> mapURI(String inputURI, String graph, String lensUri) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
