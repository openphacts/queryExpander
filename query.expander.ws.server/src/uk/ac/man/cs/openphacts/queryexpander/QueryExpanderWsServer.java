/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    
    private final String HEADER = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
            + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
            + "<html xmlns:v=\"urn:schemas-microsoft-com:vml\">"
            + "<head>"
            + " <title>"
            + "     Manchester University OpenPhacts Query Expander"
            + "	</title>"
            + "	<!--link rel=\"stylesheet\" type=\"text/css\" href=\"/ops/stylesheet.css\"></link-->"
            + "	<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"></meta>"
            + "	<!--script type=\"text/javascript\" src=\"/ops/menus.js\"></script-->"
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
            + "	</script>"
            + "	<style type=\"text/css\">"
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
            + "	</style>"
            + "</head>";            
    private final String BODY ="<body style=\"margin: 0px\">";
    private final String TOP_LEFT ="	<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">"
            + "		<tr valign=\"top\">"
            + "			<td style=\"background-color: white;\">"
            + "				<a href=\"http://www.openphacts.org/\">"
            + "                 <img style=\"border: none; padding: 0px; margin: 0px;\" "
            + "                     src=\"http://www.openphacts.org/images/stories/banner.jpg\" "
            + "                     alt=\"Open PHACTS\" height=\"50\">"
            + "                 </img>"
            + "             </a>"
            + "			</td>"
            + "			<td style=\"font-size: 200%; font-weight: bold; font-family: Arial;\">";
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
              + "                   onclick=\"document.location = &quot;/QueryExpander/Examples&quot;;\">Examples</div>"
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
              + "			<td style=\"border-top: 1px solid #D5D5FF\">";
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
                + " <p>Parameters to Expand. Comma seperated. </p>"
                + " <p><input type=\"text\" name=\"parameter\" style=\"width:100%\" value=\"";
        private final String FORM_INPUTURI = "\"/></p>"
                + " <p>Input URI (URI to be looked up in Identity Mapping Service, Mapping results to be used in Expanded Query)</p>"
                + " <p><input type=\"text\" name=\"inputURI\" style=\"width:100%\"  value=\"";
        private final String FORM_END = "\"/></p>"
                + " <p><input type=\"submit\" value=\"Expand!\"></input></p>"
                + "</form>";
        private final String MAIN_END = "			</td>"
              + "		</tr>"
              + "	</table>"
              + "	<div style=\"border-top: 1px solid #D5D5FF; padding: .5em; font-size: 80%;\">"
              + "		This site is run by <a href=\"https://wiki.openphacts.org/index.php/User:Christian\">Christian Brenninkmeijer</a>."
              + "	</div>";
      
     private final String END = "</body>"
              + "</html>";
      
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response welcomeMessage() {       
        StringBuilder sb = new StringBuilder(HEADER);
        sb.append(BODY);
        sb.append(TOP_LEFT);
        sb.append("Query Expander Demo Page");
        sb.append(TOP_RIGHT);
        sb.append(MAIN_START);
        sb.append(DEMO_EXPLAIN);
        sb.append(FORM_START);
        sb.append("Select * \nWHERE {\n\t?s ?p ?o\n}");
        sb.append(FORM_PARAMETERS);
        sb.append(FORM_INPUTURI);
        sb.append(FORM_END);
        sb.append(MAIN_END);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }
   
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/expand") 
    public Response expandHtml(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters,            
            @QueryParam("inputURI") String inputURI)throws QueryExpansionException{
        String result = checkAndExpand(query, parameters, inputURI);
        int lines = 1;
        for (int i=0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') lines++;
        }
        StringBuilder sb = new StringBuilder(HEADER);
        sb.append(BODY);
        sb.append(TOP_LEFT);
        sb.append("Query Expander Results");
        sb.append(TOP_RIGHT);
        sb.append(MAIN_START);
		sb.append("<h2>Expanded Query.</h2>");
		sb.append("<p><textarea readonly style=\"width:100%;\" rows=");
		sb.append(lines);
		sb.append(">");
        sb.append(result);
		sb.append("</textarea></p>");
		sb.append("<h2>Input Parameters.</h2>");
        sb.append(FORM_START);
        sb.append(query);
        sb.append(FORM_PARAMETERS);
        if (!parameters.isEmpty()){
            sb.append(parameters.get(0));
            for (int i = 1; i < parameters.size(); i++){
                sb.append(", ");
                sb.append(parameters.get(i));
            }
        }
        sb.append(FORM_INPUTURI);
        sb.append(inputURI);
        sb.append(FORM_END);
        sb.append(MAIN_END);
        sb.append(END);

        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
/*        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
        appendToggler(sb);
        sb.append("<body>\n");
        sb.append("<a href=\"examples\">Return to example Index.</a>\n");
        sb.append("<a href=\"expandXML?query=");
        sb.append(URLEncoder.encode(query));
        sb.append("\">View Xml/Jason result</a>\n");
        sb.append("<h2>Expanded Query</H2>\n"); 
        sb.append("<textarea ROWS=15 COLS=100>");
        sb.append(result);
        sb.append("</textarea>");
        sb.append("</body>");
        sb.append("</html>");
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
*/    }
 
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/examples") 
    public Response exampleFrame() {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
        appendToggler(sb);
        sb.append("<body onload=\"hideDetails()\">\n");
        sb.append("<h2>Groups of Examples:</H2>\n");  
        sb.append("<H2 onclick=\"toggleItem('ops')\" style=\"color:blue;\"> <u>Queries used in Open Phacts</u></H2>\n");
        loaderExamples(sb, new Ops1_1QueryLoader(), "ops");
        sb.append("<H2 onclick=\"toggleItem('sparql')\" style=\"color:blue;\"> <u>Queries found in Sparql 1.1 specifications</u></H2>\n");
        loaderExamples(sb, new SparqlLoader(), "sparql");
        sb.append("</body>");
        sb.append("</html>");
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    private void appendToggler(StringBuilder sb){
        sb.append("<head>\n");
        sb.append("<title> New Document </title>\n");
        sb.append("<script language=\"javascript\">\n");
        sb.append("function getItem(id)\n");
        sb.append("{\n");
        sb.append("    var itm = false;\n");
        sb.append("    if(document.getElementById)\n");
        sb.append("        itm = document.getElementById(id);\n");
        sb.append("    else if(document.all)\n");
        sb.append("        itm = document.all[id];\n");
        sb.append("     else if(document.layers)\n");
        sb.append("        itm = document.layers[id];\n");
        sb.append("    return itm;\n");
        sb.append("}\n\n");
        sb.append("function toggleItem(id)\n");
        sb.append("{\n");
        sb.append("    itm = getItem(id);\n");
        sb.append("    if(!itm)\n");
        sb.append("        return false;\n");
        sb.append("    if(itm.style.display == 'none')\n");
        sb.append("        itm.style.display = '';\n");
        sb.append("    else\n");
        sb.append("        itm.style.display = 'none';\n");
        sb.append("    return false;\n");
        sb.append("}\n\n");
        sb.append("function hideDetails()\n");
        sb.append("{\n");
        sb.append("     toggleItem('ops')\n");
        sb.append("     toggleItem('sparql')\n");
        sb.append("     return true;\n");
        sb.append("}\n\n");
        sb.append("</script>\n");
        sb.append("</head>\n");
        sb.append("\n");
    }

    public void loaderExamples(StringBuilder sb, QueryCaseLoader loader, String group) {
        sb.append("<ul id =\"");
        sb.append(group);
        sb.append("\" >\n");
        Set<String> queryKeys = loader.keySet();
        for (String queryKey:queryKeys){
            sb.append("<li><a href=\"top?query=");
            sb.append(URLEncoder.encode(loader.getOriginalQuery(queryKey)));
            sb.append("\" target=\"topFrame\">");
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
        ExpanderBean result = new ExpanderBean();
        result.setOrginalQuery(query);
        result.setExpandedQuery(checkAndExpand(query, parameters, inputURI));
        return result;
    }

    private String checkAndExpand(String query, List<String> parameters, String inputURI) throws QueryExpansionException{
       if (query == null){
            throw new QueryExpansionException ("query paramater is missing!");
        }
        if (parameters.size() == 1) {
            if (parameters.get(0).isEmpty()){
                parameters = new ArrayList<String>();
            }
        }
        if (parameters.size() == 1) {
            String[] tokens = parameters.get(0).split("[\\s,]+");
            parameters = Arrays.asList(tokens);
        }
        if (parameters.isEmpty()){
            if (inputURI != null && !inputURI.isEmpty()) {
                throw new QueryExpansionException ("parameter \"parameter\" is missing! " + parameters);
            }
            
        } else {
            if (inputURI == null || inputURI.isEmpty()){
                throw new QueryExpansionException ("inputURI is missing! With parameters of size " + parameters.size() );
            }
            URI check = new URIImpl(inputURI);
        }      
        return queryExpander.expand(query, parameters, inputURI);
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
        
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
        appendToggler(sb);
        sb.append("<head>");
        sb.append("<title>Manchester OpenPhacts Query Expander</title></head>\n");
        sb.append("<body>\n");
        sb.append("<a href=\"/QueryExpander\">Return to Main Page.</a>\n");
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
