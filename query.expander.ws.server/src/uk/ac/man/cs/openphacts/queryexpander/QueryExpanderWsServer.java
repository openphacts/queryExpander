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
import org.bridgedb.rdf.RdfConfig;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.ws.WsUriConstants;
import org.bridgedb.ws.uri.WSApiShower;
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
public class QueryExpanderWsServer extends WSApiShower{
    
    private QueryExpander queryExpander;
    
    public QueryExpanderWsServer() throws IDMapperException, QueryExpanderException {
        super();
        BridgeDBMapper imsMapper = BridgeDBFactory.getBridgeDBMapper();
        queryExpander = new QueryExpanderImpl(imsMapper);
    }
    
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
            + "     <li>Exteranal Systems such as OpenPhacts calls Query Expander through this Interface.</li>"
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
            + "             <li><a href=\"#" + QueryExpanderConstants.QUERY +  "\">" + QueryExpanderConstants.QUERY +  "</a></li>"
            + "             <li><a href=\"#" + QueryExpanderConstants.PARAMETER +  "\">" + QueryExpanderConstants.PARAMETER +  "(s)</a></li>"
            + "             <li><a href=\"#" + QueryExpanderConstants.INPUT_URI +  "\">" + QueryExpanderConstants.INPUT_URI +  "</a></li>"
            + "         </ul>"
            + "         <li>Optional arguement:</li>"
            + "         <ul>"
            + "             <li><a href=\"#" + QueryExpanderConstants.LENS_URI +  "\">" + QueryExpanderConstants.LENS_URI +  "</a></li>"
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
            + "         <li><a href=\"#" + QueryExpanderConstants.QUERY +  "\">" + QueryExpanderConstants.QUERY +  "</a></li>"
            + "         <li><a href=\"#" + QueryExpanderConstants.PARAMETER +  "\">" + QueryExpanderConstants.PARAMETER +  "(s)</a></li>"
            + "         <li><a href=\"#" + QueryExpanderConstants.INPUT_URI +  "\">" + QueryExpanderConstants.INPUT_URI +  "</a></li>"
            + "     </ul>"
            + "     <li>Optional arguement:</li>"
            + "     <ul>"
            + "         <li><a href=\"#" + QueryExpanderConstants.LENS_URI +  "\">" + QueryExpanderConstants.LENS_URI +  "</a></li>"
            + "         <li><a href=\"#format\">format</a></li>"
            + "     </ul>"
            + "     <li>For examples of expand select Home or Examples pages on the menu on your left.</li>"
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
            + "             <li><a href=\"#" + QueryExpanderConstants.INPUT_URI +  "\">" + QueryExpanderConstants.INPUT_URI +  "</a></li>"
            + "             <ul><li>If no " + QueryExpanderConstants.INPUT_URI +  " is provided this just brings up an input form.</li></ul>"
            + "             <li><a href=\"#" + QueryExpanderConstants.GRAPH +  "\">" + QueryExpanderConstants.GRAPH +  "</a></li>"
            + "             <li><a href=\"#" + QueryExpanderConstants.LENS_URI +  "\">" + QueryExpanderConstants.LENS_URI +  "</a></li>"
            + "         </ul>"
            + "         <li> Currently not supported bu WSClient. (ask if required)</li>"
            + "     </ul>\n"
              + "<h2>\"demo\" Method</h2>"
            + " <ul>"
            + "     <li>Support function to build a demo page. </li>"
            + "     <li>Required arguements:</li>"
            + "     <ul>"
            + "         <li><a href=\"#" + QueryExpanderConstants.QUERY +  "\">" + QueryExpanderConstants.QUERY +  "</a></li>"
            + "         <li><a href=\"#" + QueryExpanderConstants.PARAMETER +  "\">" + QueryExpanderConstants.PARAMETER +  "(s)</a></li>"
            + "         <li><a href=\"#" + QueryExpanderConstants.INPUT_URI +  "\">" + QueryExpanderConstants.INPUT_URI +  "</a></li>"
            + "     </ul>"
            + " </ul>"
            + "<h1>Parameters</h1>\n"
            + " <ul>"
            + "     <dt><a name=\"" + QueryExpanderConstants.QUERY +  "\">" + QueryExpanderConstants.QUERY +  "</a></dt>"
            + "     <ul>"
            + "         <li>The original query to be expanded.</li>"
            + "         <li>Must be in a format that OpenRDF can parse.</li>"
            + "     </ul>"
            + "     <dt><a name=\"" + QueryExpanderConstants.PARAMETER +  "\">" + QueryExpanderConstants.PARAMETER +  "</a></dt>"
            + "     <ul>"
            + "         <li>A Parameter for which a filter will be added.</li>"
            + "         <li>Must start with a question mark.</li>"
            + "         <li>May be more than one.</li>"
            + "         <li>If no paramter and no inputURI is supplied Every URI is looked up.</li>"
            + "         <li>WS API call only: May be a single value of whitespace seperated parameters.</li>"
            + "     </ul>"
            + "     <dt><a name=\"" + QueryExpanderConstants.INPUT_URI +  "\">" + QueryExpanderConstants.INPUT_URI +  "</a></dt>"
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
            + "     <dt><a name=\"" + QueryExpanderConstants.LENS_URI +  "\">" + QueryExpanderConstants.LENS_URI +  "</a></dt>"
            + "     <ul>"
            + "         <li>Selects the IMS mapping lens to be used.</li>"
            + "         <li>This allows the for different lenses to be applied.</li>"
            + "         <li>Should be the URi of one of the lenss supported by the IMS.</li>"
            + "         <li>If no lens is provided the default lens will be used."
            + "         </li>To See a list and description of all lenses visit.</li>"
            + "         <ul>"
            + "             <li><a href=\"" + RdfConfig.getTheBaseURI() + WsUriConstants.LENS + "\">" + WsUriConstants.LENS + "</a></li>"    
            + "         </ul>"
            + "     </ul>"
            + "     <dt><a name=\"" + QueryExpanderConstants.GRAPH +  "\">" + QueryExpanderConstants.GRAPH +  "</a></dt>"
            + "     <ul>"
            + "         <li>Limits the resulting mapped URIs to ones known to be in this Graph/Context.</li>"
            + "         <li>String format like <a href=\"#inputURI\">inputURI</a> (not enforced).</li>"
            + "         <li>If null or nor a known graph (see <a href=\"#getURISpacesPerGraph\">getURISpacesPerGraph</a>)"
            + "              All mapped URIs are returned.</li>"
            + "     </ul>"
            + "     <dt><a name=\"" + QueryExpanderConstants.FORMAT +  "\">" + QueryExpanderConstants.FORMAT +  "t</a></dt>"
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
    public void addSideBarMiddle(StringBuilder sb, HttpServletRequest httpServletRequest) {
        addSideBarQueryExpander(sb, httpServletRequest);
        super.addSideBarMiddle(sb, httpServletRequest);
    }
    
    /**
     * Allows Super classes to add to the side bar
     */
    private void addSideBarQueryExpander(StringBuilder sb, HttpServletRequest httpServletRequest){
        sb.append("<div class=\"menugroup\">Query Expander</div>");
        addSideBarItem(sb, "", "Home", httpServletRequest);
        addSideBarItem(sb, "api", "Query Expander API", httpServletRequest);
        addSideBarItem(sb, "examples", "Examples", httpServletRequest);
        addSideBarItem(sb, "URISpacesPerGraph", "URISpaces per Graph", httpServletRequest);
        addSideBarItem(sb, "mapURI", "Check Mapping for an URI", httpServletRequest);
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
    public Response expandHtml(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @QueryParam(QueryExpanderConstants.FORMAT) String format,
            @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri,
            @Context HttpServletRequest httpServletRequest) throws IDMapperException{
        try{
            parameters = scrubInput(query, parameters, inputURI);
            if ("xml".equals(format)){
                return xmlRedirect(query, parameters, inputURI);
            }
            String result = queryExpander.expand(query, parameters, inputURI, lensUri);
            //Find out how big to make the result box
            StringBuilder sb = topAndSide("Query Expander Results", httpServletRequest);
            addTextArea(sb, "Expanded Query.", result);
            sb.append("<h2>Input Parameters.</h2>");
            addForm(sb, query, parameters, inputURI, httpServletRequest);
            sb.append(END);
            return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
        } catch (Exception e){
            return showError(e.getMessage(), query, parameters, inputURI, httpServletRequest);                
        }
    }
 
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/expand") 
    public Response expandHtmlGet(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @QueryParam(QueryExpanderConstants.FORMAT) String format,
            @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri,
            @Context HttpServletRequest httpServletRequest) throws IDMapperException{
        return expandHtml(query, parameters, inputURI, format, lensUri, httpServletRequest);
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
        addForm(sb, query, parameters, inputURI, httpServletRequest);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/demo") 
    public Response demo(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @Context HttpServletRequest httpServletRequest) throws BridgeDBException{
        StringBuilder sb = topAndSide("Query Expander Demo Page.", httpServletRequest);
        sb.append("<p>Use this demo to test the expansion of any query.</p>");
        sb.append("<p>This demo and the underlying service depends on the information held by the Query Expander, including the ");
        sb.append("<a href=\"");
        sb.append(httpServletRequest.getContextPath());
        sb.append("/URISpacesPerGraph\">URISpaces per Graph</a>.");
        sb.append("</p>");
        addForm(sb, query, parameters, inputURI, httpServletRequest);
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/demo") 
    public Response demoGet(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @Context HttpServletRequest httpServletRequest) throws IDMapperException{
        return demo(query, parameters, inputURI, httpServletRequest);
    }
    
    private void addForm(StringBuilder sb, String query, List<String> parameters, String inputURI, 
            HttpServletRequest httpServletRequest) throws BridgeDBException{    
        sb.append("<form method=\"get\" action=\"");
        sb.append(httpServletRequest.getContextPath());
        sb.append("/expand\">");
        sb.append(" <p>Output Format:");
        sb.append("     <select size=\"1\" name=\"format\">");
        sb.append("         <option value=\"html\">HTML page</option>");
        sb.append("         <option value=\"xml\">XML/JASON</option>");
        sb.append(" 	</select>");
        sb.append(" </p>");
        sb.append(" <p><textarea rows=\"15\" name=\"query\" style=\"width:100%; background-color: #EEEEFF;\">");
        if (query != null) {
            sb.append(query);
        }
        sb.append("</textarea></p>");
        sb.append(" <p>Parameters to Expand. White space seperated. ");
        sb.append("    (see <a href=\"");
        sb.append(     httpServletRequest.getContextPath());
        sb.append("   /api#parameter\">API</a>)</p>");
        sb.append(" <p><input type=\"text\" name=\"parameter\" style=\"width:100%\" value=\"");
        if (parameters != null && !parameters.isEmpty()){
            sb.append(parameters.get(0));
            for (int i = 1; i < parameters.size(); i++){
                sb.append(" ");
                sb.append(parameters.get(i));
            }
        }
        sb.append("\"/></p>");
        sb.append(" <p>Input URI (URI to be looked up in Identity Mapping Service, Mapping results to be used in Expanded Query)");
        sb.append(    "(see <a href=\"");
        sb.append(     httpServletRequest.getContextPath());
        sb.append(    "/api#inputURI\">API</a>)</p>");
        sb.append(" <p><input type=\"text\" name=\"inputURI\" style=\"width:100%\"  value=\"");
        if (inputURI != null){
            sb.append(inputURI);
        }
        sb.append("\"/>");
        generateLensSelector(sb);
        //TODO add Parameter
        sb.append("</p>");
        sb.append(" <p><input type=\"submit\" value=\"Expand!\"></input> ");
        sb.append("    Note: If the new page does not open click on the address and press enter</p>");
        sb.append("</form>");
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/expand") 
    public ExpanderBean expandXML(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters ,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri) throws QueryExpansionException{
        parameters = scrubInput(query, parameters, inputURI);
        ExpanderBean result = new ExpanderBean();
        result.setOrginalQuery(query);
        result.setExpandedQuery(queryExpander.expand(query, parameters, inputURI, lensUri));
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/expand") 
    public ExpanderBean expandXMLGet(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters ,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri) throws QueryExpansionException{
        return expandXML(query, parameters, inputURI, lensUri);
    }
    
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/expandXML") 
    public ExpanderBean expandAsXML(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters ,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri) throws QueryExpansionException{
        return expandXML(query, parameters, inputURI, lensUri);
    }
        
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/expandXML") 
    public ExpanderBean expandAsXMLGet(@QueryParam(QueryExpanderConstants.QUERY) String query,
            @QueryParam(QueryExpanderConstants.PARAMETER) List<String> parameters ,            
            @QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
            @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri) throws QueryExpansionException{
        return expandXML(query, parameters, inputURI, lensUri);
    }
        
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    public Response mapURIasHtml(@QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
        @QueryParam(QueryExpanderConstants.GRAPH) String graph,
        @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri,
        @Context HttpServletRequest httpServletRequest) throws QueryExpansionException, IDMapperException{
        StringBuilder sb = topAndSide("URI Mappings available per Graph (Query Context)",  httpServletRequest);
        if (inputURI != null && !inputURI.isEmpty()) {
            List<String> mappings = queryExpander.mapURI(inputURI, graph, lensUri);
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
        sb.append("<form method=\"get\" action=\"");
        sb.append(httpServletRequest.getContextPath());
        sb.append("/mapURI\">");
        sb.append(" <p>Input URI (URI to be looked up in Identity Mapping Service.)");
        sb.append(  "(see <a href=\"");
        sb.append(      httpServletRequest.getContextPath());
        sb.append(      "/api#inputURI\">API</a>)</p>");
         sb.append(  "<form method=\"get\" action=\"");
        sb.append(      httpServletRequest.getContextPath());
        sb.append(      "/mapURI\">");
        sb.append(" <p><input type=\"text\" name=\"inputURI\" style=\"width:100%\"/></p>");
        sb.append(" <p>Graph/Context (Graph value to limit the returned URIs)");
        sb.append(  "(see <a href=\"");
        sb.append(      httpServletRequest.getContextPath());
        sb.append(      "/api#graph\">API</a>)</p>");
        sb.append(  "<form method=\"get\" action=\"");
        sb.append(      httpServletRequest.getContextPath());
        sb.append(      "/mapURI\">");
        sb.append(" <p><input type=\"text\" name=\"graph\" style=\"width:100%\"/></p>");
        sb.append(" <p><input type=\"submit\" value=\"Expand!\"></input> ");
        sb.append("    Note: If the new page does not open click on the address and press enter</p>");
        sb.append("</form>");
        sb.append(END);
        return Response.ok(sb.toString(), MediaType.TEXT_HTML).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/mapURI") 
    public Response mapURIasHtmlGet(@QueryParam(QueryExpanderConstants.INPUT_URI) String inputURI,
        @QueryParam(QueryExpanderConstants.GRAPH) String graph,
        @QueryParam(QueryExpanderConstants.LENS_URI) String lensUri,
        @Context HttpServletRequest httpServletRequest) throws QueryExpansionException, IDMapperException{
            return mapURIasHtml(inputURI, graph, lensUri, httpServletRequest);
    }    
}
