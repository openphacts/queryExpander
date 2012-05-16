/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

import java.io.UnsupportedEncodingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bridgedb.IDMapperException;
import uk.ac.cs.man.openphacts.queryexpander.mapper.BridgeDBMapper;

/**
 *
 * @author Christian
 */
public class QueryExpanderWsServer implements QueryExpanderWsAPI{
    
    private QueryExpander queryExpander;
    
    public QueryExpanderWsServer() throws QueryExpansionException{
        BridgeDBMapper imsMapper = BridgeDBFactory.getBridgeDBMapper();
        queryExpander = new QueryExpanderImpl(imsMapper);
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response welcomeMessage() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbInnerPure;
        StringBuilder sbInnerEncoded;

        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"/>");
        sb.append("<head><title>OPS IMS</title></head><body>");
        sb.append("<h1>Open PHACTS Query Expander Service</h1>");
        sb.append("<p>Welcome to the prototype Query Expander  Service. </p>");
        
        sb.append("</body></html>");
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/expand") 
    public ExpanderBean expand(@QueryParam("query") String query) throws QueryExpansionException{
        if (query == null){
            throw new QueryExpansionException ("query paramater is missing!");
        }
        ExpanderBean result = new ExpanderBean();
        result.setOrginalQuery(query);
        result.setExpandedQuery(queryExpander.expand(query));
        return result;
    }

}
