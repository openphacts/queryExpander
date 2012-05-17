/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Christian
 */
public class QueryExpanderWSClient implements QueryExpander{

    protected final String serviceAddress;

    protected final WebResource webResource;

    public QueryExpanderWSClient(String serviceAddress) {
        this.serviceAddress = serviceAddress;
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource(serviceAddress);        
    }
    
    @Override
    public String expand(String originalQuery) throws QueryExpansionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String expand(String originalQuery, boolean verbose) throws QueryExpansionException {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("query", originalQuery);
        ExpanderBean bean = 
                webResource.path("expand")
                .queryParams(params)
                .accept(MediaType.APPLICATION_XML_TYPE)
                .get(new GenericType<ExpanderBean>() {});
        return bean.getExpandedQuery();
    }
    
}
