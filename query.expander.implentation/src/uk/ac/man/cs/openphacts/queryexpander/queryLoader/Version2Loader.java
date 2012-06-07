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
                + "       || ?x = <http://data.kasabi.com/dataset/chembl-rdf/target/t197> \n"
                + "       || ?x = <http://rdf.chemspider.com/187440> \n"
                + "       || ?x = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> \n"
                + "       || ?x = <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> \n"
                + "       || ?x = <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228>)\n"
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
                + "    FILTER (?kasabiID = <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> \n"
                + "         || ?kasabiID = <http://data.kasabi.com/dataset/chembl-rdf/target/t197>)\n"
                + "    }\n"
                + "  }\n"
                + "} ";
        queryCase.addParameter("?kasabiID");
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
