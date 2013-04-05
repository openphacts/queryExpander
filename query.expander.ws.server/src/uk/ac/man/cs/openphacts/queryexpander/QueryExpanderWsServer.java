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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bridgedb.IDMapperException;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.ws.uri.WSLinksetService;
import org.bridgedb.ws.uri.WSOpsServer;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;
import uk.ac.man.cs.openphacts.queryexpander.mapper.BridgeDBMapper;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.Ops1_1QueryLoader;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.OpsReplacementLoader;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.QueryCaseLoader;
import uk.ac.man.cs.openphacts.queryexpander.queryLoader.SparqlLoader;

/**
 *
 * @author Christian
 */
public class QueryExpanderWsServer extends WSOpsServer{
    
    private QueryExpander queryExpander;
    
    public QueryExpanderWsServer() throws IDMapperException, QueryExpanderException {
        super();
        BridgeDBMapper imsMapper = BridgeDBFactory.getBridgeDBMapper();
        queryExpander = new QueryExpanderImpl(imsMapper);
    }
    
    @Override
    public String getDefaultResourceName(){
        return "QueryExpander";
    }
/*    private final String HEADER_START = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
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
            + "                   onclick=\"document.location = &quot;/QueryExpander/api&quot;;\">API</div>"
            + "				<div id=\"menuQueryExpanderExamples_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuQueryExpanderExamples_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuQueryExpanderExamples_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/QueryExpander/examples&quot;;\">Examples</div>"
            + "				<div id=\"menuQueryExpanderURISpacesPerGraph_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuQueryExpanderURISpacesPerGraph_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuQueryExpanderURISpacesPerGraph_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/QueryExpander/URISpacesPerGraph&quot;;\">"
            + "                   URISpaces per Graph</div>"
            + "				<div id=\"menuQueryExpanderMapURI_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuQueryExpanderMapURI_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuQueryExpanderMapURI_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/QueryExpander/mapURI&quot;;\">"
            + "                   Check Mapping for an URI</div>"            
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
            + "				<div id=\"menuGraphviz_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuGraphviz_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuGraphviz_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/OPS-IMS/graphviz&quot;;\">"
            + "                   Mappings Summary in Graphviz format</div>"
            + "				<div id=\"menuOpsApi_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuOpsApi_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuOpsApi_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/OPS-IMS/api&quot;;\">API</div>"
            + "				<div id=\"menuOpsValidateVoid_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuOpsValidateVoid_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuOpsValidateVoid_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/OPS-IMS/validateVoid&quot;;\">Validate Void</div>"
            + "				<div id=\"menuOpsValidateLinkSet_text\" class=\"texthotlink\" "
            + "                   onmouseout=\"DHTML_TextRestore('menuOpsValidateLinkSet_text'); return true; \" "
            + "                   onmouseover=\"DHTML_TextHilight('menuOpsValidateLinkSet_text'); return true; \" "
            + "                   onclick=\"document.location = &quot;/OPS-IMS/validateLinkSet&quot;;\">Validate LinkSet</div>"
            + "			</td>"
            + "			<td width=\"5\" style=\"border-right: 1px solid #D5D5FF\"></td>"
            + "			<td style=\"border-top: 1px solid #D5D5FF; width:100%\">";
*/    
    
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
            + " <p>Parameters to Expand. White space seperated. "
            + "    (see <a href=\"/QueryExpander/api#parameter\">API</a>)</p>"
            + " <p><input type=\"text\" name=\"parameter\" style=\"width:100%\" value=\"";
    private final String FORM_INPUTURI = "\"/></p>"
            + " <p>Input URI (URI to be looked up in Identity Mapping Service, Mapping results to be used in Expanded Query)"
            + "    (see <a href=\"/QueryExpander/api#inputURI\">API</a>)</p>"
            + " <p><input type=\"text\" name=\"inputURI\" style=\"width:100%\"  value=\"";
    private final String FORM_END = "\"/></p>"
            + " <p><input type=\"submit\" value=\"Expand!\"></input> "
            + "    Note: If the new page does not open click on the address and press enter</p>"
            + "</form>";
    private final String URI_MAPPING_FORM = "<form method=\"get\" action=\"/QueryExpander/mapURI\">"
            + " <p>Input URI (URI to be looked up in Identity Mapping Service.)"
            + "     (see <a href=\"/QueryExpander/api#inputURI\">API</a>)</p>"
            + " <p><input type=\"text\" name=\"inputURI\" style=\"width:100%\"/></p>"
            + " <p>Graph/Context (Graph value to limit the returned URIs)"
            + "     (see <a href=\"/QueryExpander/api#graph\">API</a>)</p>"
            + " <p><input type=\"text\" name=\"graph\" style=\"width:100%\"/></p>"
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
    private final String API = "<h1>QueryExpander Java Interface</h1>\n"
            + " <ul> "
            + "     <li>Prefered method of calling the query expander.</li>"
            + "     <li>Two Implementations exists</li>"
            + "     <ul>"
            + "         <li>Local Service for running on the same machine</li>"
            + "         <li>Webservice Client for running remotely</li>"
            + "     </ul>"
            + "     <li>Larkc plugin calls Query Expander through this Interface.</li>"
            + " </ul>\n"
            + "<h2>\"expand\" Method</h2>"
            + "     <p>Given a query, a list of parameters to replace and the starting inputURI, "
            + "     extends the query so that when any of the parameters is used in a statement a Filter clause is added."
            + "     The Filter will check that the parameters is equal to the inputURI or one of its mapped version.</p>\n "
            + "     <p>If the query Expander knows which URIspace(s) are valid for a particular graph (context) only "
            + "     URIs for those URISpaces will be used in the Filter clause.</p>\n"
            + "     <ul>"
            + "         <li>Required arguements:</li>"
            + "         <ul>"
            + "             <li><a href=\"#query\">query</a></li>"
            + "             <li><a href=\"#parameter\">parameter(s)</a></li>"
            + "             <li><a href=\"#inputURI\">inputURI</a></li>"
            + "         </ul>"
            + "     </ul>\n"
            + "<h2><a name=\"getURISpacesPerGraph\">\"getURISpacesPerGraph\"</a> method.</h2>"
            + " <p> Returns a Map of Graph/Context values and the allowed URISpace(s) for each.</p>"
            + " <p> When available this set of URISpace(s) "
            + "   will limit which mapped URIs are included in the filter statements for each graph.</P>"
            + "<h1>QueryExpander WebService</h1>"
            + "<h2>\"expand\" Method</h2>"
            + " <ul>"
            + "     <li>Same functionality as Java Interface. </li>"
            + "     <li>Available as xml/Jason output. </li>"
            + "     <ul>"
            + "         <li>Format returned for WS Client calls.</li>"
            + "         <li>Result is a bean with both the original and expanded query.</li>"
            + "         <li>Select Output Format \"XML/JASON\" from the demo/home page.</li>"
            + "     </ul>"
            + "     <li>Available as a html page. </li>"
            + "     <ul>"
            + "         <li>Default for API call from a browser.</li>"
            + "         <li>Result is expanded query inside a service page.</li>"
            + "     </ul>"
            + "     <li>Required arguements:</li>"
            + "     <ul>"
            + "         <li><a href=\"#query\">query</a></li>"
            + "         <li><a href=\"#parameter\">parameter(s)</a></li>"
            + "         <li><a href=\"#inputURI\">inputURI</a></li>"
            + "     </ul>"
            + "     <li>Optional Arguement:</li>"
            + "     <ul>"
            + "         <li><a href=\"#format\">format</a></li>"
            + "     </ul>"
            + "     <li>For an example API expand a query on the Home or Examples pages.</li>"
            + " </ul>"
            + "<h2>\"expandXML\" Method</h2>"
            + " <ul>"
            + "     <li>Same as \"expand\" method but result is always XML. </li>"
            + "     <li>Actual api used for browser calls that request XML/JASON output. </li>"
            + " </ul>"
            + "<h2> \"getURISpacesPerGraph\" method.</h2>"
            + " <ul>"
            + "     <li>Same functionality as Java Interface. </li>"
            + "     <li>Available as xml/Jason output. </li>"
            + "     <ul>"
            + "         <li>Result for  API call from WS Client.</li>"
            + "         <li>Returns a bean from which the Mappings can be extracted.</li>"
            + "     </ul>"
            + "     <li>Available as a html page. </li>"
            + "     <ul>"
            + "         <li>Result for  API call from a browser.</li>"
            + "         <li>Result is expanded query inside a service page.</li>"
            + "     </ul>"
            + " </ul>"
            + "<h2> \"api\" method.</h2>"
            + " <ul>"
            + "     <li>Returns this page. </li>"
            + " </ul>"
            + "<h2> \"examples\" method.</h2>"
            + " <ul>"
            + "     <li>Returns a page with many links to many query examples. </li>"
            + " </ul>"
            + "<h2>\"mapURI\" Method</h2>"
            + "     <p>Given a URI and optionally a graph will list the Mapped URI known to the underlying IMS. "
            + "     <p>If the query Expander knows which URIspace(s) are valid for a particular graph (context) only "
            + "     URIs for those URISpaces will be returned.</p>\n"
            + "     <ul>"
            + "         <li>Optional  arguements:</li>"
            + "         <ul>"
            + "             <li><a href=\"#inputURI\">inputURI</a></li>"
            + "             <ul><li>If no inputURI is provided this just brings up an input form.</li></ul>"
            + "             <li><a href=\"#graph\">graph</a></li>"
            + "         </ul>"
            + "         <li> Currently not supported bu WSClient. (ask if required)</li>"
            + "     </ul>\n"
              + "<h2>\"demo\" Method</h2>"
            + " <ul>"
            + "     <li>Support function to build a demo page. </li>"
            + "     <li>Required arguements:</li>"
            + "     <ul>"
            + "         <li><a href=\"#query\">query</a></li>"
            + "         <li><a href=\"#parameter\">parameter(s)</a></li>"
            + "         <li><a href=\"#inputURI\">inputURI</a></li>"
            + "     </ul>"
            + " </ul>"
            + "<h1>Parameters</h1>\n"
            + " <ul>"
            + "     <dt><a name=\"query\">query</a></dt>"
            + "     <ul>"
            + "         <li>The original query to be expanded.</li>"
            + "         <li>Must be in a format that OpenRDF can parse.</li>"
            + "     </ul>"
            + "     <dt><a name=\"parameter\">parameter</a></dt>"
            + "     <ul>"
            + "         <li>A Parameter for which a filter will be added.</li>"
            + "         <li>Must start with a question mark.</li>"
            + "         <li>May be more than one.</li>"
            + "         <li>If no paramter and no inputURI is supplied Every URI is looked up.</li>"
            + "         <li>WS API call only: May be a single value of whitespace seperated parameters.</li>"
            + "     </ul>"
            + "     <dt><a name=\"inputURI\">inputURI</a></dt>"
            + "     <ul>"
            + "         <li>The original URI to be looked up in the mapping Service.</li>"
            + "         <li>A String that OpenRDF can convert to a URI</li>"
            + "         <ul>"
            + "             <li>Must included the scheme name. (ex: http://)</li>"
            + "             <li>Must included the absolute path. (ex: www.example.com)</li>"
            + "             <li>May not make use of a sparql prefix.</li>"
            + "             <li>Do not include the angle brackets</li>"
            + "             <li>Needs not be resolvable. (Even though that is bad practice).<li>"
            + "             <li>If not know to the IMS mapper, just the original URI will be used."
            + "         </ul>"
            + "     </ul>"
            + "     <dt><a name=\"graph\">graph</a></dt>"
            + "     <ul>"
            + "         <li>Limits the resulting mapped URIs to ones known to be in this Graph/Context.</li>"
            + "         <li>String format like <a href=\"#inputURI\">inputURI</a> (not enforced).</li>"
            + "         <li>If null or nor a known graph (see <a href=\"#getURISpacesPerGraph\">getURISpacesPerGraph</a>)"
            + "              All mapped URIs are returned.</li>"
            + "     </ul>"
            + "     <dt><a name=\"format\">format</a></dt>"
            + "     <ul>"
            + "         <li>Allows the output to be forced into \"xml\".</li>"
            + "         <li>Any \"format\" value other than \"xml\" will result in html output.</li>"
            + "     </ul>"
            + " </ul>"
            + "<h1>IMS Information</h1>\n"
            + "<p> Try the links on the left for IMS methods and the IMS API.</p>"
            ;
  
        /**
     * Allows Super classes to add to the side bar
     */
    @Override
    protected void addSideBarMiddle(StringBuilder sb, HttpServletRequest httpServletRequest) throws BridgeDBException{
        addSideBarQueryExpander(sb);
        super.addSideBarMiddle(sb, httpServletRequest);
    }
    
    /**
     * Allows Super classes to add to the side bar
     */
    private void addSideBarQueryExpander(StringBuilder sb) throws BridgeDBException{
        sb.append("<div class=\"menugroup\">Query Expander</div>");
        addSideBarItem(sb, "", "Home");
        addSideBarItem(sb, "api", "Query Expander API");
        addSideBarItem(sb, "examples", "Examples");
        addSideBarItem(sb, "URISpacesPerGraph", "URISpaces per Graph");
        addSideBarItem(sb, "mapURI", "Check Mapping for an URI");
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response welcomeMessage(@Context HttpServletRequest httpServletRequest) throws BridgeDBException {
        List<String> parameters = new ArrayList<String>();
        parameters.add("?s");
        return demo("SELECT  ?s ?p ?o\nWHERE {\n\t?s ?p ?o.\n}", parameters, "http://www.example.com", httpServletRequest);
    }
   
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response welcomeMessageGet(@Context HttpServletRequest httpServletRequest) throws IDMapperException {
        return welcomeMessage(httpServletRequest);
    }
   
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/expand") 
    public Response expandHtml(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters,            
            @QueryParam("inputURI") String inputURI,
            @QueryParam("format") String format,
            @Context HttpServletRequest httpServletRequest) throws IDMapperException{
        try{
            parameters = scrubInput(query, parameters, inputURI);
            if ("xml".equals(format)){
                return xmlRedirect(query, parameters, inputURI);
            }
            String result = queryExpander.expand(query, parameters, inputURI);
            //Find out how big to make the result box
            StringBuilder sb = topAndSide("Query Expander Results", httpServletRequest);
            addTextArea(sb, "Expanded Query.", result);
            sb.append("<h2>Input Parameters.</h2>");
            addForm(sb, query, parameters, inputURI);
            sb.append(END);
            return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
        } catch (Exception e){
            return showError(e.getMessage(), query, parameters, inputURI, httpServletRequest);                
        }
    }
 
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/expand") 
    public Response expandHtmlGet(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters,            
            @QueryParam("inputURI") String inputURI,
            @QueryParam("format") String format,
            @Context HttpServletRequest httpServletRequest) throws IDMapperException{
        return expandHtml(query, parameters, inputURI, format, httpServletRequest);
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
            throw new QueryExpanderException("query paramater is missing!");
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
                throw new QueryExpanderException("\"parameter\" is missing! \n"
                        + "If you specify an \"inputURI\" you must also specify one or more parameters.");
            }
            //Ok no Parameters and no inputURI old URI lookup method will be used.
        } else {
            if (inputURI == null || inputURI.isEmpty()){
                throw new QueryExpanderException("\"inputURI\" is missing!\n"
                        + "You have specified " + parameters.size() + " parameters. \n"
                        + "If you specify one or more parameters you must specify an \"inputURI\".");
            }
            //Check the URI is valid
            URI check = new URIImpl(inputURI);
        }              
        return parameters;
    }
    
    private Response showError(String error, String query, List<String> parameters, String inputURI, 
            HttpServletRequest httpServletRequest) throws IDMapperException{
        int lines = 1;
        for (int i=0; i < error.length(); i++) {
            if (error.charAt(i) == '\n') lines++;
        }
        StringBuilder sb = topAndSide("Error Expanding Query", httpServletRequest);
        addTextArea(sb, "Error", error);
		sb.append("<h2>Input Parameters.</h2>");
        addForm(sb, query, parameters, inputURI);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/demo") 
    public Response demo(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters,            
            @QueryParam("inputURI") String inputURI,
            @Context HttpServletRequest httpServletRequest) throws BridgeDBException{
        StringBuilder sb = topAndSide("Query Expander Demo Page.", httpServletRequest);
        sb.append(DEMO_EXPLAIN);
        addForm(sb, query, parameters, inputURI);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/demo") 
    public Response demoGet(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters,            
            @QueryParam("inputURI") String inputURI,
            @Context HttpServletRequest httpServletRequest) throws IDMapperException{
        return demo(query, parameters, inputURI, httpServletRequest);
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
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/examples") 
    public Response examples(@Context HttpServletRequest httpServletRequest) throws IDMapperException {
        StringBuilder sb = topAndSide("Example Queries Index", httpServletRequest);

        sb.append("<p>Click on any Group to expand or contract it.</p>\n");  
        sb.append("<p>Click on any Query Title to load it into the demo page</p>\n");  
        sb.append("<H2 onclick=\"toggleItem('linkedData')\" style=\"color:blue;\"> <u>LinkdedData Queries used in Open Phacts</u></H2>\n");
        loaderExamples(sb, new OpsReplacementLoader(), "linkedData");
        sb.append("<H2 onclick=\"toggleItem('ops')\" style=\"color:blue;\"> <u>Queries used in Open Phacts</u></H2>\n");
        loaderExamples(sb, new Ops1_1QueryLoader(), "ops");
        sb.append("<H2 onclick=\"toggleItem('sparql')\" style=\"color:blue;\"> <u>Queries found in Sparql 1.1 specifications</u></H2>\n");
        loaderExamples(sb, new SparqlLoader(), "sparql");
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/examples") 
    public Response examplesGet(@Context HttpServletRequest httpServletRequest) throws IDMapperException {
        return examples(httpServletRequest);
    }
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/api") 
    public Response api(@Context HttpServletRequest httpServletRequest) throws IDMapperException {
        StringBuilder sb = topAndSide("Query Expander API", httpServletRequest);
        sb.append(API);  
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/api") 
    public Response apiGet(@Context HttpServletRequest httpServletRequest) throws IDMapperException {
        return api(httpServletRequest);
    }
    
    public void loaderExamples(StringBuilder sb, QueryCaseLoader loader, String group) {
        sb.append("<ul id =\"");
        sb.append(group);
        sb.append("\" >\n");
        Set<String> queryKeys = loader.keySet();
        for (String queryKey:queryKeys){
            sb.append("<li><a href=\"demo?query=");
            sb.append(URLEncoder.encode(loader.getOriginalQuery(queryKey)));
            List<String> parameters = loader.getParameters(queryKey);
            if (parameters != null){
                for (String parameter:parameters){
                    sb.append("&parameter=");
                    sb.append(parameter);
                }
            }
            String inputURI = loader.getInsertURI(queryKey);
            if (inputURI != null){
                sb.append("&inputURI=");
                sb.append(URLEncoder.encode(inputURI));
            }
            sb.append("\">");
            sb.append(loader.getQueryName(queryKey));            
            sb.append("</a></li>\n");
        }
    	sb.append("</ul>");
    }

    @POST
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
    @Path("/expand") 
    public ExpanderBean expandXMLGet(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters ,            
            @QueryParam("inputURI") String inputURI) throws QueryExpansionException{
        return expandXML(query, parameters, inputURI);
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/expandXML") 
    public ExpanderBean expandAsXML(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters ,            
            @QueryParam("inputURI") String inputURI) throws QueryExpansionException{
        return expandXML(query, parameters, inputURI);
    }
        
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/expandXML") 
    public ExpanderBean expandAsXMLGet(@QueryParam("query") String query,
            @QueryParam("parameter") List<String> parameters ,            
            @QueryParam("inputURI") String inputURI) throws QueryExpansionException{
        return expandXML(query, parameters, inputURI);
    }
        
    @POST
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
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/URISpacesPerGraph") 
    public List<URISpacesInGraphBean> URISpacesPerGraphAsXMLGet() throws QueryExpansionException{
        return URISpacesPerGraphAsXML();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/URISpacesPerGraph") 
    public Response URISpacesPerGraphAsHtml(@Context HttpServletRequest httpServletRequest) 
            throws QueryExpansionException, IDMapperException{
        Map<String, Set<String>> URISpacesPerGraph = queryExpander.getURISpacesPerGraph();
        
        StringBuilder sb = topAndSide("URI Spaces per Graph (Query Context)", httpServletRequest);
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
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/URISpacesPerGraph") 
    public Response URISpacesPerGraphAsHtmlGet(@Context HttpServletRequest httpServletRequest) 
            throws QueryExpansionException, IDMapperException {
       return URISpacesPerGraphAsHtml(httpServletRequest); 
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/mapURI") 
    public Response mapURIasHtml(@QueryParam("inputURI") String inputURI,
        @QueryParam("graph") String graph,
        @Context HttpServletRequest httpServletRequest) throws QueryExpansionException, IDMapperException{
        StringBuilder sb = topAndSide("URI Mappings available per Graph (Query Context)",  httpServletRequest);
        if (inputURI != null && !inputURI.isEmpty()) {
            List<String> mappings = queryExpander.mapURI(inputURI, graph);
            sb.append("<h2>URI Mappings for ");
            sb.append(inputURI);
            sb.append("</h2>\n"); 
            if (graph != null && !graph.isEmpty()){
                sb.append("<h3>Limited to ones fro graph ");
                sb.append(graph);
                sb.append("</h3>\n");     
            }
            sb.append("<p>");
            sb.append("<ul>");
            for (String mapping:mappings){
                sb.append("<li>");
                sb.append(mapping);
                sb.append("</li>");
            }
            sb.append("</ul>");
        }
        sb.append(URI_MAPPING_FORM);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/mapURI") 
    public Response mapURIasHtmlGet(@QueryParam("inputURI") String inputURI,
        @QueryParam("graph") String graph,
        @Context HttpServletRequest httpServletRequest) throws QueryExpansionException, IDMapperException{
            return mapURIasHtml(inputURI, graph, httpServletRequest);
    }    
}
