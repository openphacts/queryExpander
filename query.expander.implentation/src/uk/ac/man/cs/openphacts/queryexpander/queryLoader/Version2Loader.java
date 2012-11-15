/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class Version2Loader extends QueryCaseLoader{
    
   public Version2Loader(){
       loadSparql2_6();
       loadJiraExample();
       loadBoundExample();
       loadUnionGraph();
       loadUnionGraph2();
   } 
   
   private void loadSparql2_6() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Sparql2_6";
        queryCase.name = "Sparql Specification section 2.6 Building RDF Graphs";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "PREFIX org:    <http://example.com/ns#> \n"
                + "CONSTRUCT { ?x foaf:name ?name }\n"
                + "WHERE  { ?x org:employeeName ?name }\n";  
        queryCase.replaceQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/> \n"
                + "PREFIX org:    <http://example.com/ns#> \n"
                + "CONSTRUCT { ?x foaf:name ?name }\n"
                + "WHERE  { \n"
                + "  ?x org:employeeName ?name . \n"
                + "  FILTER (?x = <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> \n"
                + "       || ?x = <http://linkedchemistry.info/chembl/molecule/m276734> \n"
                + "       || ?x = <http://rdf.chemspider.com/187440> \n"
                + "       || ?x = <http://www.chemspider.com/187440> \n"
                + "       || ?x = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> \n"
                + "       || ?x = <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398>)\n"
                + "}\n";  
        queryCase.addParameter("?x");
        queryCase.insertURI = "http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5";
        queries.put(queryCase.key, queryCase);
   }

   private void loadJiraExample() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "ReplacementsJiraExample";
        queryCase.name = "Jira Example Query";
        queryCase.originalQuery = "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "CONSTRUCT {\n"
                + "?kasabiID ops:onAssay ?assay_uri.\n"
                + "?kasabiID ops:hasActivity ?activity_uri.\n"
                + "} \n"
                + "WHERE {\n"
                + "  SELECT ?kasabiID ?assay_uri ?activity_uri {\n"
                + "    GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "      ?activity_uri chembl:forMolecule ?kasabiID;\n"
                + "      chembl:onAssay ?assay_uri .\n"
                + "    }\n"
                + "  }\n"
                + "} ";
        queryCase.replaceQuery = "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "CONSTRUCT {\n"
                + "?kasabiID ops:onAssay ?assay_uri.\n"
                + "?kasabiID ops:hasActivity ?activity_uri.\n"
                + "} \n"
                + "WHERE {\n"
                + "  SELECT ?kasabiID ?assay_uri ?activity_uri {\n"
                + "    GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "      ?activity_uri chembl:forMolecule ?kasabiID;\n"
                + "      chembl:onAssay ?assay_uri .\n"
                + "    FILTER (?kasabiID = <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734>)\n"
                + "    }\n"
                + "  }\n"
                + "} ";
        queryCase.addParameter("?kasabiID");
        queryCase.insertURI = "http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5";
        queries.put(queryCase.key, queryCase);
    }

   private void loadBoundExample() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "OpsBoundExample";
        queryCase.name = "Bound Example";
        queryCase.originalQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "CONSTRUCT {\n"
                + " ?concepWikiURI skos:prefLabel ?target_name ;\n"
                + "		skos:exactMatch ?drugbankURI .\n"
                + "	?drugbankURI drugbank:molecularWeight ?molecularWeight .\n"
                + "}\n"
                + "WHERE {\n"
                + " GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		?concepWikiURI skos:prefLabel ?target_name.\n"
                + "	}\n"
                + "	{ \n"
                + "     SELECT DISTINCT ?drugbankURI ?molecularWeight \n{"
                + "		   GRAPH <http://linkedlifedata.com/resource/drugbank> {\n"
                + "			 OPTIONAL { ?drugbankURI drugbank:molecularWeight ?molecularWeight }\n"
                + "		   }\n"
                + "		}\n"
                + "	}\n"
                + "}";
        queryCase.replaceQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n" +
                "CONSTRUCT {\n" +
                " ?concepWikiURI skos:prefLabel ?target_name ;\n" +
                "		skos:exactMatch ?drugbankURI .\n" +
                "	?drugbankURI drugbank:molecularWeight ?molecularWeight .\n" +
                "}\n" +
                "WHERE {\n" +
                " GRAPH <http://larkc.eu#Fixedcontext> {\n" +
                "		?concepWikiURI skos:prefLabel ?target_name.\n" +
                "     FILTER (?concepWikiURI = <http://www.conceptwiki.org/concept/notThere>)\n" +
                "	}\n" +
                "	{ \n" +
                "     SELECT DISTINCT ?drugbankURI ?molecularWeight {" +
                "        OPTIONAL { " +
                "		      GRAPH <http://linkedlifedata.com/resource/drugbank> {\n" +
                "                ?drugbankURI drugbank:molecularWeight ?molecularWeight \n" +
//                "              FILTER (!BOUND(?drugbankURI) \n" +
//                "                     || ?drugbankURI = <http://www.conceptwiki.org/concept/notThere>)\n" +
                "              FILTER (?drugbankURI = \"No Mappings Found\")\n" +
                "		      }\n" +
                "		   }\n" +
                "		}\n" +
                "	}\n" +
                "}";
        queryCase.addParameter("?concepWikiURI");
        queryCase.addParameter("?kasabiURI");
        queryCase.addParameter("?drugbankURI");
        queryCase.insertURI = "http://www.conceptwiki.org/concept/notThere";
        queries.put(queryCase.key, queryCase);
    }

   private void loadUnionGraph() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Replacement UnionGraph";
        queryCase.name = "Query Union Graph";
        queryCase.originalQuery = "SELECT  DISTINCT ?compound_name \n"
                + "WHERE {\n"
                + "	GRAPH <http://www.conceptwiki.org>  {\n"
                + "		{ \n"
                + "			?concepWikiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} UNION { \n"
                + "			?kasabiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} \n"
                + "	} \n"
                + "}\n";
        queryCase.replaceQuery = "SELECT  DISTINCT ?compound_name \n"
                + "WHERE {\n"
                + "	GRAPH <http://www.conceptwiki.org>  {\n"
                + "		{ \n"
                + "			?concepWikiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} UNION { \n"
                + "			?kasabiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} \n"
                + "     FILTER (?kasabiURI = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>)\n"
                + "     FILTER (?concepWikiURI = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>)\n"
                + "	} \n"
                + "}\n";
        queryCase.addParameter("?concepWikiURI");
        queryCase.addParameter("?kasabiURI");
        queryCase.insertURI = "http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadUnionGraph2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Replacement UnionGraph2";
        queryCase.name = "Query Union Graph 2";
        queryCase.originalQuery = "SELECT  DISTINCT ?compound_name \n"
                + "WHERE {\n"
                + "	GRAPH <http://www.conceptwiki.org>  {\n"
                + "		{ \n"
                + "			?concepWikiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} UNION { \n"
                + "			?kasabiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} \n"
                + "	    <http://www.bar.com/1> <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name "
                + "	} \n"
                + "}\n";
        queryCase.replaceQuery = "SELECT  DISTINCT ?compound_name \n"
                + "WHERE {\n"
                + "	GRAPH <http://www.conceptwiki.org>  {\n"
                + "		{ \n"
                + "			?concepWikiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} UNION { \n"
                + "			?kasabiURI <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name \n"
                + "		} \n"
                + "	    <http://www.bar.com/1> <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name "
                + "     FILTER (?kasabiURI = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>)\n"
                + "     FILTER (?concepWikiURI = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>)\n"
                + "	} \n"
                + "}\n";
        queryCase.addParameter("?concepWikiURI");
        queryCase.addParameter("?kasabiURI");
        queryCase.insertURI = "http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5";
        queries.put(queryCase.key, queryCase);
   }

   private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Replacement";
        queryCase.name = " Query";
        queryCase.originalQuery = "";
        queries.put(queryCase.key, queryCase);
    }
}
