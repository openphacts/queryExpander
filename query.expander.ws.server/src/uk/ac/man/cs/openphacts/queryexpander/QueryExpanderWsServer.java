/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.common.util.StringUtils;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.Ops1_1QueryLoader;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.QueryCaseLoader;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.SparqlLoader;

/**
 *
 * @author Christian
 */
public class QueryExpanderWsServer {
    
    private QueryExpander queryExpander;
    
    public QueryExpanderWsServer() throws QueryExpansionException{
        BridgeDBMapper imsMapper = BridgeDBFactory.getBridgeDBMapper();
        queryExpander = new QueryExpanderImpl(imsMapper);
    }
    
    private final String HEADER_START = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
            + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
            + "<html xmlns:v=\"urn:schemas-microsoft-com:vml\">\n"
            + "<head>\n"
            + " <title>"
            + "     Manchester University OpenPhacts Query Expander"
            + "	</title>\n"
            + "	<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"></meta>\n"
            + "	<script>"
            + "		function getObj(id) {"
            + "			return document.getElementById(id)"
            + "		}"
            + "		function DHTML_TextHilight(id) {"
            + "			getObj(id).classNameOld = getObj(id).className;"
            + "			getObj(id).className = getObj(id).className + \"_hilight\";"
            + "		}"
            + "		function DHTML_TextRestore(id) {"
            + "			if (getObj(id).classNameOld != \"\")"
            + "				getObj(id).className = getObj(id).classNameOld;"
            + "		}"
            + "	</script>\n";
    private final String TOGGLER ="<script language=\"javascript\">\n"
            + "function getItem(id)\n"
            + "{\n"
            + "    var itm = false;\n"
            + "    if(document.getElementById)\n"
            + "        itm = document.getElementById(id);\n"
            + "    else if(document.all)\n"
            + "        itm = document.all[id];\n"
            + "     else if(document.layers)\n"
            + "        itm = document.layers[id];\n"
            + "    return itm;\n"
            + "}\n\n"
            + "function toggleItem(id)\n"
            + "{\n"
            + "    itm = getItem(id);\n"
            + "    if(!itm)\n"
            + "        return false;\n"
            + "    if(itm.style.display == 'none')\n"
            + "        itm.style.display = '';\n"
            + "    else\n"
            + "        itm.style.display = 'none';\n"
            + "    return false;\n"
            + "}\n\n"
            + "function hideDetails()\n"
            + "{\n"
            + "     toggleItem('ops')\n"
            + "     toggleItem('sparql')\n"
            + "     return true;\n"
            + "}\n\n"
            + "</script>\n";
    private final String HEADER_END = "	<style type=\"text/css\">"
            + "		.texthotlink, .texthotlink_hilight {"
            + "			width: 150px;"
            + "			font-size: 85%;"
            + "			padding: .25em;"
            + "			cursor: pointer;"
            + "			color: black;"
            + "			font-family: Arial, sans-serif;"
            + "		}"
            + "		.texthotlink_hilight {"
            + "			background-color: #fff6ac;"
            + "		}"
            + "		.menugroup {"
            + "			font-size: 90%;"
            + "			font-weight: bold;"
            + "			padding-top: .25em;"
            + "		}"
            + "		input { background-color: #EEEEFF; }"
            + "		body, td {"
            + "			background-color: white;"
            + "			font-family: sans-serif;"
            + "		}"
            + "	</style>\n"
            + "</head>\n";            
    private final String HEADER = HEADER_START + HEADER_END;
    private final String TOGGLE_HEADER = HEADER_START + TOGGLER + HEADER_END;
    private final String BODY ="<body style=\"margin: 0px\">";
    private final String TOP_LEFT ="	<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
            + "		<tr valign=\"top\">\n"
            + "			<td style=\"background-color: white;\">"
            + "				<a href=\"http://www.openphacts.org/\">"
            + "                 <img style=\"border: none; padding: 0px; margin: 0px;\" "
            + "                     src=\"http://www.openphacts.org/images/stories/banner.jpg\" "
            + "                     alt=\"Open PHACTS\" height=\"50\">"
            + "                 </img>"
            + "             </a>"
            + "			</td>\n"
            + "			<td style=\"font-size: 200%; font-weight: bold; font-family: Arial;\">\n";
    private final String TOP_RIGHT = "         </td>"
            + "			<td style=\"background-color: white;\">"
            + "				<a href=\"http://www.cs.manchester.ac.uk//\">"
            + "                 <img style=\"border: none; padding: 0px; margin: 0px;\" align=\"right\" "
            + "                     src=\"http://www.manchester.ac.uk/media/corporate/theuniversityofmanchester/assets/images/logomanchester.gif\" "
            + "                    alt=\"The University of Manchester\" height=\"50\">"
            + "                 </img>"
            + "             </a>"
            + "			</td>"
            + "		</tr>"
            + "	</table>";
    private final String MAIN_START = "	<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">"
            + "		<tr valign=\"top\">"
            + "			<td style=\"border-top: 1px solid #D5D5FF\">"
            + "				<div class=\"menugroup\">Query Expander</div>"
            + "				<div id=\"menuQueryExpanderHome_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuQueryExpanderHome_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuQueryExpanderHome_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/QueryExpander&quot;;\">Home</div>"
            + "				<div id=\"menuQueryExpanderAPI_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuQueryExpanderAPI_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuQueryExpanderAPI_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/QueryExpander/API&quot;;\">API</div>"
            + "				<div id=\"menuQueryExpanderExamples_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuQueryExpanderExamples_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuQueryExpanderExamples_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/QueryExpander/examples&quot;;\">Examples</div>"
            + "				<div id=\"menuQueryExpanderURISpacesPerGraph_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuQueryExpanderURISpacesPerGraph_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuQueryExpanderURISpacesPerGraph_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/QueryExpander/URISpacesPerGraph&quot;;\">"
            + "                   URISpaces per Graph</div>"
            + "				<div class=\"menugroup\">OPS Identity Mapping Service</div>"
            + "				<div id=\"menuOpsHome_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuOpsHome_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuOpsHome_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/OPS-IMS&quot;;\">Home</div>"
            + "				<div id=\"menuOpsInfo_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuOpsInfo_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuOpsInfo_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/OPS-IMS/getMappingInfo&quot;;\">"
            + "                   Mappings Summary</div>"
            + "				<div id=\"menuOpsApi_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuOpsApi_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuOpsApi_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/OPS-IMS/api&quot;;\">API</div>"
            + "			</td>"
            + "			<td width=\"5\" style=\"border-right: 1px solid #D5D5FF\"></td>"
            + "			<td style=\"border-top: 1px solid #D5D5FF; width:100%\">";
    private final String DEMO_EXPLAIN = "<p>Use this demo to test the expansion of any query.</p>"
            + "<p>This demo and the underlying service depends on the information held by the Query Expander, including the "
            + "     <a href=\"/OPS-IMS/getMappingInfo\">mappings</a> and the specific "
            + "     <a href=\"/QueryExpander/URISpacesPerGraph\">URISpaces per Graph</a>."
            + "</p>";
    private final String FORM_START = "<form method=\"get\" action=\"/QueryExpander/expand\">"
            + " <p>Output Format:"
            + "     <select size=\"1\" name=\"format\">"
            + "         <option value=\"html\">HTML page</option>"
            + "         <option value=\"xml\">XML/JASON</option>"
            + " 	</select>"
            + " </p>"
            + " <p><textarea rows=\"15\" name=\"query\" style=\"width:100%; background-color: #EEEEFF;\">";
    private final String FORM_PARAMETERS = "</textarea></p>"
            + " <p>Parameters to Expand. White space seperated. </p>"
            + " <p><input type=\"text\" name=\"parameter\" style=\"width:100%\" value=\"";
    private final String FORM_INPUTURI = "\"/></p>"
            + " <p>Input URI (URI to be looked up in Identity Mapping Service, Mapping results to be used in Expanded Query)</p>"
            + " <p><input type=\"text\" name=\"inputURI\" style=\"width:100%\"  value=\"";
    private final String FORM_END = "\"/></p>"
            + " <p><input type=\"submit\" value=\"Expand!\"></input> "
            + "    Note: If the new page does not open click on the address and press enter</p>"
            + "</form>";
    private final String MAIN_END = "			</td>"
            + "		</tr>"
            + "	</table>"
            + "	<div style=\"border-top: 1px solid #D5D5FF; padding: .5em; font-size: 80%;\">"
            + "		This site is run by <a href=\"https://wiki.openphacts.org/index.php/User:Christian\">Christian Brenninkmeijer</a>."
            + "	</div>";
    private final String BODY_END = "</body>"
            + "</html>";
    private final String END = MAIN_END + BODY_END;
      
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response welcomeMessage() {
        List<String> parameters = new ArrayList<String>();
        parameters.add("?");
        return demo("SELECT  ?s ?p ?o\nWHERE {\n\t?s ?p ?o.\n}", new ArrayList<String>(), "http://www.example.com");
    }
   
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/expand") 
    public Response expandHtml(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters,            
            @QueryParam("inputURI") String inputURI,
            @QueryParam("format") String format){
        try{
            parameters = scrubInput(query, parameters, inputURI);
            if ("xml".equals(format)){
                return xmlRedirect(query, parameters, inputURI);
            }
            String result = queryExpander.expand(query, parameters, inputURI);
            //Find out how big to make the result box
            StringBuilder sb = topAndSide("Query Expander Results");
            addTextArea(sb, "Expanded Query.", result);
            sb.append("<h2>Input Parameters.</h2>");
            addForm(sb, query, parameters, inputURI);
            sb.append(END);
            return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
        } catch (Exception e){
            return showError(e.getMessage(), query, parameters, inputURI);                
        }
    }
 
    private void addTextArea(StringBuilder sb, String title, String text){
        int lines = 1;
        for (int i=0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') lines++;
        }
        sb.append("<h2>");
        sb.append(title);
        sb.append("</h2>");
        sb.append("<p><textarea readonly style=\"width:100%;\" rows=");
        sb.append(lines);
        sb.append(">");
        sb.append(text);
        sb.append("</textarea></p>\n");       
    }
    
    private List<String> scrubInput(String query, List<String> parameters, String inputURI) 
            throws QueryExpansionException {
        if (query == null){
            throw new QueryExpansionException("query paramater is missing!");
        }
        //Check that parameters is not a single empty String
        if (parameters.size() == 1) {
            if (parameters.get(0).isEmpty()){
                parameters = new ArrayList<String>();
            }
        }
        //Split parameters if required.
        if (parameters.size() == 1) {
            String[] tokens = parameters.get(0).split("[\\s,]+");
            parameters = Arrays.asList(tokens);
        }
        //check for both or neither extra parameters
        if (parameters.isEmpty()){
            if (inputURI != null && !inputURI.isEmpty()) {
                throw new QueryExpansionException("\"parameter\" is missing! \n"
                        + "If you specify an \"inputURI\" you must also specify one or more parameters.");
            }
            //Ok no Parameters and no inputURI old URI lookup method will be used.
        } else {
            if (inputURI == null || inputURI.isEmpty()){
                throw new QueryExpansionException("\"inputURI\" is missing!\n"
                        + "You have specified " + parameters.size() + " parameters. \n"
                        + "If you specify one or more parameters you must specify an \"inputURI\".");
            }
            //Check the URI is valid
            URI check = new URIImpl(inputURI);
        }              
        return parameters;
    }
    
    private Response showError(String error, String query, List<String> parameters, String inputURI){
        int lines = 1;
        for (int i=0; i < error.length(); i++) {
            if (error.charAt(i) == '\n') lines++;
        }
        StringBuilder sb = topAndSide("Error Expanding Query");
        addTextArea(sb, "Error", error);
		sb.append("<h2>Input Parameters.</h2>");
        addForm(sb, query, parameters, inputURI);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/demo") 
    public Response demo(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters,            
            @QueryParam("inputURI") String inputURI){
        StringBuilder sb = topAndSide("Query Expander Demo Page");
        sb.append(DEMO_EXPLAIN);
        addForm(sb, query, parameters, inputURI);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    private StringBuilder topAndSide(String header){
        StringBuilder sb = new StringBuilder(HEADER);
        sb.append(BODY);
        sb.append(TOP_LEFT);
        sb.append(header);
        sb.append(TOP_RIGHT);
        sb.append(MAIN_START);
        return sb;
    }
    
    private void addForm(StringBuilder sb, String query, List<String> parameters, String inputURI){
        sb.append(FORM_START);
        if (query != null) {
            sb.append(query);
        }
        sb.append(FORM_PARAMETERS);
        if (parameters != null && !parameters.isEmpty()){
            sb.append(parameters.get(0));
            for (int i = 1; i < parameters.size(); i++){
                sb.append(" ");
                sb.append(parameters.get(i));
            }
        }
        sb.append(FORM_INPUTURI);
        if (inputURI != null){
            sb.append(inputURI);
        }
        sb.append(FORM_END);        
    }
    
    private Response xmlRedirect(String query, List<String> parameters, String inputURI) throws URISyntaxException {
        StringBuilder sb = new StringBuilder("expandXML?query=");
        if (query == null && query.isEmpty()){
            query = "SELECT  ?s ?p ?o\nWHERE {\n\t?s ?p ?o.\n}";
        }
        sb.append(URLEncoder.encode(query));
        for (String parameter:parameters){
            sb.append("&parameter=");
            sb.append(parameter);
        }
        if (inputURI != null && !inputURI.isEmpty()){
            sb.append("&inputURI=");        
            sb.append(inputURI);
        }
        java.net.URI uri;
        uri = new java.net.URI(sb.toString());
        return Response.temporaryRedirect(uri).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/examples") 
    public Response examples() {
        StringBuilder sb = new StringBuilder(TOGGLE_HEADER);
        sb.append(BODY);
        sb.append(TOP_LEFT);
        sb.append("Groups of Examples");
        sb.append(TOP_RIGHT);
        sb.append(MAIN_START);
        sb.append("<p>Click on any Group to expand or contract it.</p>\n");  
        sb.append("<p>Click on any Query Title to load it into the demo page</p>\n");  
        sb.append("<H2 onclick=\"toggleItem('ops')\" style=\"color:blue;\"> <u>Queries used in Open Phacts</u></H2>\n");
        loaderExamples(sb, new Ops1_1QueryLoader(), "ops");
        sb.append("<H2 onclick=\"toggleItem('sparql')\" style=\"color:blue;\"> <u>Queries found in Sparql 1.1 specifications</u></H2>\n");
        loaderExamples(sb, new SparqlLoader(), "sparql");
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    public void loaderExamples(StringBuilder sb, QueryCaseLoader loader, String group) {
        sb.append("<ul id =\"");
        sb.append(group);
        sb.append("\" >\n");
        Set<String> queryKeys = loader.keySet();
        for (String queryKey:queryKeys){
            sb.append("<li><a href=\"demo?query=");
            sb.append(URLEncoder.encode(loader.getOriginalQuery(queryKey)));
            sb.append("\">");
            sb.append(loader.getQueryName(queryKey));
            sb.append("</a></li>\n");
        }
    	sb.append("</ul>");
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/expand") 
    public ExpanderBean expandXML(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters ,            
            @QueryParam("inputURI") String inputURI) throws QueryExpansionException{
        parameters = scrubInput(query, parameters, inputURI);
        ExpanderBean result = new ExpanderBean();
        result.setOrginalQuery(query);
        result.setExpandedQuery(queryExpander.expand(query, parameters, inputURI));
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/expandXML") 
    public ExpanderBean expandAsXML(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters ,            
            @QueryParam("inputURI") String inputURI) throws QueryExpansionException{
        return expandXML(query, parameters, inputURI);
    }
        
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/URISpacesPerGraph") 
    public List<URISpacesInGraphBean> URISpacesPerGraphAsXML() throws QueryExpansionException{
        Map<String, Set<String>> URISpacesPerGraph = queryExpander.getURISpacesPerGraph();
        ArrayList<URISpacesInGraphBean> results = new ArrayList<URISpacesInGraphBean>();
        for (String graph:URISpacesPerGraph.keySet()){
           results.add(new URISpacesInGraphBean(graph, URISpacesPerGraph.get(graph)));
        }
        return results;
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/URISpacesPerGraph") 
    public Response URISpacesPerGraphAsHtml() throws QueryExpansionException{
        Map<String, Set<String>> URISpacesPerGraph = queryExpander.getURISpacesPerGraph();
        
        StringBuilder sb = topAndSide("URI Spaces per Graph (Query Context)");
        sb.append("<h2>URISpaces Per Graph</H2>\n"); 
        sb.append("<p>");
        sb.append("<table border=\"1\">");
        sb.append("<tr>");
        sb.append("<th>Graph</th>");
        sb.append("<th>NameSpace</th>");
        sb.append("</tr>");
        for (String graph:URISpacesPerGraph.keySet()){
             for (String URISpace:URISpacesPerGraph.get(graph)){
                sb.append("<tr>");
                sb.append("<td>");
                sb.append(graph);
                sb.append("</td>");
                sb.append("<td>");
                sb.append(URISpace);
                sb.append("</td>");
                sb.append("</tr>");
             }
            sb.append("<tr>");
            sb.append("</tr>");
        }
        sb.append("</body>");
        sb.append("</html>");
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }
}
