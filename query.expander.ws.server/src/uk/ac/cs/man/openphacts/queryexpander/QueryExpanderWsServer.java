/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

import java.io.UnsupportedEncodingException;
import javax.ws.rs.ApplicationPath;
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

        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
        sb.append("<head>\n");
        sb.append("<title>Manchester OpenPhacts Query Expander </title></head>\n");
        sb.append("<FRAMESET rows=\"120, 200\">\n");
        sb.append("<FRAME src=\"QueryExpander/top\" name=\"topFrame\">\n");
        sb.append("<FRAME src=\"QueryExpander/bottom\" name =\"bottomFrame\">\n");
        sb.append("</FRAMESET>\n");
        sb.append("<NOFRAMES>");
        sb.append("<P>this page requires frames! Trust Me!");
        sb.append("</NOFRAMES>\n");
        sb.append("</FRAMESET>\n");
        sb.append("</html>");
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/top") 
    public Response topFrame() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
        sb.append("<body>\n");
        sb.append("<FORM METHOD=GET ACTION=\"expand\" Target=\"bottomFrame\" >\n");
        sb.append("<h2>Enter your query:</H2>\n");
        sb.append("<TEXTAREA NAME=query ROWS=10 COLS=100>\n");
        sb.append("SELECT *\n");
        sb.append("WHERE { \n");
        sb.append("?s ?p ?o\n");
        sb.append("}\n");
        sb.append("</TEXTAREA>\n");
        sb.append("<BR>\n");
        sb.append("<INPUT TYPE=submit VALUE=\"Expand this query\">\n");
        sb.append("</FORM>\n");
        sb.append("</body>\n");
        sb.append("</html>");
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/bottom") 
    public Response bottomFrame() {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"/>");
        sb.append("<body>");
        sb.append("The Bottom");
        sb.append("</body>");
        sb.append("</html>");
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
