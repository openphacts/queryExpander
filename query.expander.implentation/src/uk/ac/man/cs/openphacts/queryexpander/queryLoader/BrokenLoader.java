/*
 * This contains query that appeared broken at pone time.
 * Hopefully they now work.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class BrokenLoader extends QueryCaseLoader{

    public BrokenLoader(){
        loadCountDistinct();
        loadCountDistinct2();
        loadCountDistinct3();
        //loadBind1();
        loadReplaceURI1();
    }
    
    private void loadCountDistinct() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenCountDistinct";
        queryCase.name = "Count Distinct Query";
        queryCase.originalQuery = "SELECT (count(distinct ?s) as ?count) (count (?s) as ?count2)\n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "  }\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadCountDistinct2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenCountDistinct2";
        queryCase.name = "Count Distinct Query Complex";
        queryCase.originalQuery = "SELECT ?s \n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "    FILTER(?p != <http://www.w3.org/2004/02/skos/core%23exactMatch>) \n"
                + "  }\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadCountDistinct3() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenCountDistinct2";
        queryCase.name = "Count Distinct Query Complex";
        queryCase.originalQuery = "SELECT (count(distinct ?s) as ?count) \n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "    FILTER(?p != <http://www.w3.org/2004/02/skos/core%23exactMatch>) \n"
                + "    FILTER(REGEX(str(?s),\"http://rdf.chemspider.com/\"))\n"
                + "  }\n"
                + "}";
        //Filters swap order
        queryCase.noReplaceQuery = "SELECT (count(distinct ?s) as ?count) \n"
                + "WHERE { \n"
                + "  graph <http://www.chemspider.com> {\n"
                + "    ?s ?p ?o . \n"
                + "    FILTER(REGEX(str(?s),\"http://rdf.chemspider.com/\"))\n"
                + "    FILTER(?p != <http://www.w3.org/2004/02/skos/core%23exactMatch>) \n"
                + "  }\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadBind1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenBin1";
        queryCase.name = "Bind1 Query";
        queryCase.originalQuery = "PREFIX void: <http://rdfs.org/ns/void#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "SELECT * \n"
                + "WHERE { \n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name.\n"
                + "	}\n"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?chemSpiderID) .\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> chemspider:smiles ?smiles ;\n"
                + "			chemspider:inchi ?inchi ;\n"
                + "			chemspider:inchikey ?inchi_key.\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?kasabiId ) .\n"
                + "		?activity_uri chembl:forMolecule <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>;\n"
                + "			chembl:type ?std_type;\n"
                + "			chembl:relation ?relation;\n"
                + "			chembl:standardValue ?std_value;\n"
                + "			chembl:standardUnits ?std_unit;\n"
                + "			chembl:onAssay ?assay_uri . \n"
                + "		?assay_uri chembl:hasTarget ?target_uri . \n"
                + "		?target_uri dc:title ?target_name ;\n"
                + "			chembl:organism ?target_organism .\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node1 .\n"
                + "			?node1 a sio:CHEMINF_000198 ;\n"
                + "				sio:SIO_000300 ?molweight. }\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node2 .\n"
                + "			?node2 a sio:CHEMINF_000314 ;\n"
                + "				sio:SIO_000300 ?num_ro5_violations.}\n"
                + "	}\n"
                + "}";
        queryCase.noReplaceQuery = "PREFIX void: <http://rdfs.org/ns/void#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "SELECT * \n"
                + "WHERE { \n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name.\n"
                + "	}\n"
                + "	BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?chemSpiderID) .\n"
                + "     {}"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> chemspider:smiles ?smiles ;\n"
                + "			chemspider:inchi ?inchi ;\n"
                + "			chemspider:inchikey ?inchi_key.\n"
                + "	}\n"
                + "	BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?kasabiId ) .\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?kasabiId ) .\n"
                + "		?activity_uri chembl:forMolecule <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>;\n"
                + "			chembl:type ?std_type;\n"
                + "			chembl:relation ?relation;\n"
                + "			chembl:standardValue ?std_value;\n"
                + "			chembl:standardUnits ?std_unit;\n"
                + "			chembl:onAssay ?assay_uri . \n"
                + "		?assay_uri chembl:hasTarget ?target_uri . \n"
                + "		?target_uri dc:title ?target_name ;\n"
                + "			chembl:organism ?target_organism .\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node1 .\n"
                + "			?node1 a sio:CHEMINF_000198 ;\n"
                + "				sio:SIO_000300 ?molweight. }\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node2 .\n"
                + "			?node2 a sio:CHEMINF_000314 ;\n"
                + "				sio:SIO_000300 ?num_ro5_violations.}\n"
                + "	}\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadBind2() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenBind2";
        queryCase.name = "Bind1 Query";
        queryCase.originalQuery = "PREFIX void: <http://rdfs.org/ns/void#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "SELECT * \n"
                + "WHERE { \n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name.\n"
                + "	}\n"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?chemSpiderID) .\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> chemspider:smiles ?smiles ;\n"
                + "			chemspider:inchi ?inchi ;\n"
                + "			chemspider:inchikey ?inchi_key.\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?kasabiId ) .\n"
                + "		?activity_uri chembl:forMolecule <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>;\n"
                + "			chembl:type ?std_type;\n"
                + "			chembl:relation ?relation;\n"
                + "			chembl:standardValue ?std_value;\n"
                + "			chembl:standardUnits ?std_unit;\n"
                + "			chembl:onAssay ?assay_uri . \n"
                + "		?assay_uri chembl:hasTarget ?target_uri . \n"
                + "		?target_uri dc:title ?target_name ;\n"
                + "			chembl:organism ?target_organism .\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node1 .\n"
                + "			?node1 a sio:CHEMINF_000198 ;\n"
                + "				sio:SIO_000300 ?molweight. }\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node2 .\n"
                + "			?node2 a sio:CHEMINF_000314 ;\n"
                + "				sio:SIO_000300 ?num_ro5_violations.}\n"
                + "	}\n"
                + "	GRAPH <http://linkedlifedata.com/resource/drugbank> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?drukbankId ) .\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> drugbank:genericName ?drug_name ;\n"
                + "			drugbank:drugType ?drugType_uri .\n"
                + "		?drugType_uri rdfs:label ?drugType. \n"
                + "	} \n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadBindConstruct() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenBindConstruct";
        queryCase.name = "Bind1 Query";
        queryCase.originalQuery = "PREFIX void: <http://rdfs.org/ns/void#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
                + "CONSTRUCT { \n"
                + "	<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name ;\n"
                + "		ops:mapsTo ?chemSpiderID .\n"
                + "	?chemSpiderID chemspider:smiles ?smiles ;\n"
                + "		chemspider:inchi ?inchi ; \n"
                + "		chemspider:inchikey ?inchi_key ;\n"
                + "		void:inDataset <http://www.chemspider.com> .\n"
                + "	<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> ops:mapsTo ?drukbankId .\n"
                + "	?drukbankId drugbank:genericName ?drug_name ;\n"
                + "		drugbank:drugType ?drugType ;\n"
                + "		void:inDataset <http://linkedlifedata.com/resource/drugbank>.\n"
                + "	<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> ops:mapsTo ?kasabiId . \n"
                + "	?kasabiId ?node1 ?molweight ;\n"
                + "		?node2 ?num_ro5_violations ;\n"
                + "		ops:hasActivity ?activity_uri ;\n"
                + "		void:inDataset <http://data.kasabi.com/dataset/chembl-rdf> .\n"
                + "	ops:hasActivity owl:inverseOf chembl:forMolecule .\n"
                + "	?activity_uri chembl:forMolecule ?kasabiId;\n"
                + "		chembl:type ?std_type;\n"
                + "		chembl:relation ?relation;\n"
                + "		chembl:standardValue ?std_value;\n"
                + "		chembl:standardUnits ?std_unit;\n"
                + "		chembl:onAssay ?assay_uri .\n"
                + "	?assay_uri chembl:hasTarget ?target_uri .\n"
                + "	?target_uri dc:title ?target_name ;\n"
                + "		chembl:organism ?target_organism .\n"
                + "} WHERE { \n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name.\n"
                + "	}\n"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?chemSpiderID) .\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> chemspider:smiles ?smiles ;\n"
                + "			chemspider:inchi ?inchi ;\n"
                + "			chemspider:inchikey ?inchi_key.\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?kasabiId ) .\n"
                + "		?activity_uri chembl:forMolecule <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>;\n"
                + "			chembl:type ?std_type;\n"
                + "			chembl:relation ?relation;\n"
                + "			chembl:standardValue ?std_value;\n"
                + "			chembl:standardUnits ?std_unit;\n"
                + "			chembl:onAssay ?assay_uri . \n"
                + "		?assay_uri chembl:hasTarget ?target_uri . \n"
                + "		?target_uri dc:title ?target_name ;\n"
                + "			chembl:organism ?target_organism .\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node1 .\n"
                + "			?node1 a sio:CHEMINF_000198 ;\n"
                + "				sio:SIO_000300 ?molweight. }\n"
                + "		OPTIONAL { <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> sio:CHEMINF_000200 ?node2 .\n"
                + "			?node2 a sio:CHEMINF_000314 ;\n"
                + "				sio:SIO_000300 ?num_ro5_violations.}\n"
                + "	}\n"
                + "	GRAPH <http://linkedlifedata.com/resource/drugbank> {\n"
                + "	    BIND (<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> AS ?drukbankId ) .\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> drugbank:genericName ?drug_name ;\n"
                + "			drugbank:drugType ?drugType_uri .\n"
                + "		?drugType_uri rdfs:label ?drugType. \n"
                + "	} \n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void loadReplaceURI1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "BrokenReplaceURI1";
        queryCase.name = "ReplaceURI1 Query";
        queryCase.originalQuery = "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>"
                + "PREFIX ops: <http://www.openphacts.org/api#>"
                + "CONSTRUCT { "
                + "  <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> ops:compoundPharmacologyTotalResults ?count .   "
                + "} WHERE {"
                + "  { SELECT (COUNT(?activity_uri) AS ?count) {"
                + "     GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {"
                + "       ?activity_uri chembl:forMolecule ?replacedURI1;"
                + "          chembl:type ?std_type;"
                + "         chembl:relation ?relation;"
                + "        chembl:standardValue ?std_value;"
                + "        chembl:standardUnits ?std_unit;"
                + "        chembl:onAssay ?assay_uri . "
                + "    FILTER (?replacedURI1 = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> || ?replacedURI1 = <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> || ?replacedURI1 = <http://rdf.chemspider.com/187440> || ?replacedURI1 = <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398>)"
                + "    }"
                + "  }"
                + "  } "
                + "}";
        String originalQuery = "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "CONSTRUCT { \n"
                + "	?kasabiID ops:onAssay ?assay_uri.\n "
                + "} WHERE {\n"
                + "	{ SELECT ?assay_uri {\n"
                + "		GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "			?activity_uri chembl:forMolecule ?kasabiID;\n"
                + "				chembl:onAssay ?assay_uri . \n"
                + "		}\n"
                + "	}\n"
                + "	}\n "
                + "}\n";
        String Query2 = "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX ops: <http://www.openphacts.org/api#>\n"
                + "CONSTRUCT { \n		"
                + "	?kasabiID ops:onAssay ?assay_uri. \n  "
                + "} WHERE {\n"
                + "	{ SELECT ?assay_uri {\n"
                + "		GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "			?activity_uri chembl:forMolecule ?kasabiID;\n"
                + "				chembl:onAssay ?assay_uri . \n"
                + "     FILTER (?kasabiID = <http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5>)\n"
                + "		}\n"
                + "	}\n"
                + "	} \n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }

    private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Broken";
        queryCase.name = " Query";
        queryCase.originalQuery = "";
        queries.put(queryCase.key, queryCase);
    }
}
