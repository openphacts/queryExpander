/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class Ops1_1QueryLoader extends QueryCaseLoader{
    
   public Ops1_1QueryLoader(){
       loadTargetPharma();
       loadTargetInfo();
       loadCompoundPharma();
       loadCoumpoundInfo();
   } 
   
   private void loadTargetPharma() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "OpsTargetPharma";
        queryCase.name = "Ops Target Pharma Query";
        queryCase.originalQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT DISTINCT ?target_name ?smiles ?inchi ?inchi_key ?molweight ?num_ro5_violations ?std_type \n"
                + "                ?relation ?std_value ?std_unit ?target_organism\n"
                + "WHERE {\n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?target_name .\n"
                + "		?compound_cw skos:exactMatch ?csid_uri ; \n"
                + "			skos:prefLabel ?compound_name .\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "		<http://data.kasabi.com/dataset/chembl-rdf/target/t197> chembl:organism ?target_organism . \n"
                + "		?assay_uri chembl:hasTarget <http://data.kasabi.com/dataset/chembl-rdf/target/t197> .\n"
                + "		?activity_uri chembl:onAssay ?assay_uri ;\n"
                + "			chembl:type ?std_type ;\n"
                + "			chembl:relation ?relation ;\n"
                + "			chembl:standardValue ?std_value ;\n"
                + "			chembl:standardUnits ?std_unit ;\n"
                + "			chembl:forMolecule ?compound_uri .\n"
                + "		?csid_uri skos:exactMatch ?compound_uri .\n"
                + "		OPTIONAL { ?compound_uri sio:CHEMINF_000200 _:node1 . \n"
                + "			_:node1 a sio:CHEMINF_000314 ;\n"
                + "				sio:SIO_000300 ?num_ro5_violations }\n"
                + "		OPTIONAL { ?compound_uri sio:CHEMINF_000200 _:node2 . \n"
                + "			_:node2 a sio:CHEMINF_000198 ;\n"
                + "				sio:SIO_000300 ?molweight }\n"
                + "	}\n"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "		?csid_uri chemspider:smiles ?inchi;\n"
                + "			chemspider:inchikey ?inchi_key;\n"
                + "			chemspider:smiles ?smiles . \n"
                + "	}\n"
                + "}";             
        queries.put(queryCase.key, queryCase);
   }

   private void loadTargetInfo() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "OpsTargetInfo";
        queryCase.name = "Ops Target Info Query";
        queryCase.originalQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "SELECT DISTINCT \n"
                + "   ?target_name ?target_type ?description ?organism \n"
                + "   (GROUP_CONCAT( DISTINCT ?keyword ; SEPARATOR=\" , \") as ?keywords )\n"
                + "   (GROUP_CONCAT( DISTINCT ?synonym ; SEPARATOR=\" , \" ) as ?synonyms)\n"
                + "   (GROUP_CONCAT( DISTINCT ?cellularLocation ; SEPARATOR=\" , \" ) as ?cellularLocations ) \n"
                + "   ?molecularWeight ?numberOfResidues ?pdbIdPage ?specificFunction ?theoreticalPi\n"
                + "WHERE {\n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?target_name.\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "		<http://data.kasabi.com/dataset/chembl-rdf/target/t197> chembl:hasKeyword ?keyword ;\n"
                + "			chembl:hasDescription ?description ;\n"
                + "			rdfs:subClassOf ?target_type ;\n"
                + "			chembl:organism ?organism ;\n"
                + "			rdfs:label ?synonym\n"
                + "	}\n"
                + "	GRAPH <http://linkedlifedata.com/resource/drugbank> {\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:cellularLocation ?cellularLocation }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:molecularWeight ?molecularWeight }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:numberOfResidues ?numberOfResidues }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:pdbIdPage ?pdbIdPage }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:specificFunction ?specificFunction }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:theoreticalPi ?theoreticalPi }\n"
                + "	}\n"
                + "}\n"
                + "GROUP BY ?target_name ?target_type ?description ?organism ?molecularWeight ?numberOfResidues \n"
                + "         ?pdbIdPage ?specificFunction ?theoreticalPi";
        //concat variables moved down
        //Singleton set added due to all optional
        queryCase.noReplaceQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "SELECT DISTINCT \n"
                + "   ?target_name ?target_type ?description ?organism \n"
                + "   ?molecularWeight ?numberOfResidues ?pdbIdPage ?specificFunction ?theoreticalPi\n"
                + "   (GROUP_CONCAT( DISTINCT ?keyword ; SEPARATOR=\" , \") as ?keywords )\n"
                + "   (GROUP_CONCAT( DISTINCT ?synonym ; SEPARATOR=\" , \" ) as ?synonyms)\n"
                + "   (GROUP_CONCAT( DISTINCT ?cellularLocation ; SEPARATOR=\" , \" ) as ?cellularLocations ) \n"
                + "WHERE {\n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?target_name.\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "		<http://data.kasabi.com/dataset/chembl-rdf/target/t197> chembl:hasKeyword ?keyword ;\n"
                + "			chembl:hasDescription ?description ;\n"
                + "			rdfs:subClassOf ?target_type ;\n"
                + "			chembl:organism ?organism ;\n"
                + "			rdfs:label ?synonym\n"
                + "	}\n"
                + "{}"
                + "	GRAPH <http://linkedlifedata.com/resource/drugbank> {\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:cellularLocation ?cellularLocation }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:molecularWeight ?molecularWeight }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:numberOfResidues ?numberOfResidues }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:pdbIdPage ?pdbIdPage }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:specificFunction ?specificFunction }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/228> drugbank:theoreticalPi ?theoreticalPi }\n"
                + "	}\n"
                + "}\n"
                + "GROUP BY ?target_name ?target_type ?description ?organism ?molecularWeight ?numberOfResidues \n"
                + "         ?pdbIdPage ?specificFunction ?theoreticalPi";
        queries.put(queryCase.key, queryCase);
   }

   private void loadCompoundPharma() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "OpsCompoundPharma";
        queryCase.name = "Ops Compound Pharma Query";
        queryCase.originalQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX chembl: <http://rdf.farmbio.uu.se/chembl/onto/#>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "SELECT ?compound_name ?target_name ?csid_uri ?smiles ?inchi ?inchi_key ?molweight \n"
                + "       ?num_ro5_violations ?std_type ?relation ?std_value ?std_unit ?target_organism\n"
                + "WHERE {\n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name.\n"
                + "	}\n"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "		<http://rdf.chemspider.com/187440>  chemspider:smiles ?inchi;\n"
                + "			chemspider:inchikey ?inchi_key;\n"
                + "			chemspider:smiles ?smiles . \n"
                + "		?csid_uri chemspider:inchikey ?inchi_key .\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "		?activity_uri chembl:forMolecule <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> ;\n"
                + "			          chembl:type ?std_type ;\n"
                + "                   chembl:relation ?relation ;\n"
                + "                   chembl:standardValue ?std_value ;\n"
                + "                   chembl:standardUnits ?std_unit ;\n"
                + "                   chembl:onAssay ?assay_uri .\n"
                + "     ?assay_uri chembl:hasTarget ?target_uri .\n"
                + "		?target_uri dc:title ?target_name ;\n"
                + "                 chembl:organism ?target_organism .\n"
                + "		OPTIONAL { \n"
                + "         <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node1 . \n"
                + "			_:node1 a sio:CHEMINF_000314 ;\n"
                + "			    	sio:SIO_000300 ?num_ro5_violations \n"
                + "     }\n"
                + "		OPTIONAL { \n"
                + "         <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node2 . \n"
                + "			_:node2 a sio:CHEMINF_000198 ;\n"
                + "			        sio:SIO_000300 ?molweight \n"
                + "     }\n"
                + "	}\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadCoumpoundInfo() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "OpsCoumpoundInfo";
        queryCase.name = "Ops CoumpoundInfo Query";
        queryCase.originalQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "SELECT DISTINCT ?compound_name ?csid_uri ?molformula ?molweight ?inchi ?inchi_key ?smiles ?alogp "
                + "                ?hba ?hbd ?mw_freebase ?num_ro5_violations ?psa ?rtb ?meltingPoint ?affectedOrganism "
                + "                ?biotransformation ?description ?indication ?proteinBinding ?toxicity\n"
                + "WHERE {\n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name.\n"
                + "	}\n"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "		<http://rdf.chemspider.com/187440>  chemspider:smiles ?inchi;\n"
                + "			chemspider:inchikey ?inchi_key;\n"
                + "			chemspider:smiles ?smiles . \n"
                + "		?csid_uri chemspider:inchikey ?inchi_key .\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "		<http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node1 . \n"
                + "		_:node1 a sio:CHEMINF_000042 ;\n"
                + "			sio:SIO_000300 ?molformula\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node2 . \n"
                + "			_:node2 a sio:CHEMINF_000305 ;\n"
                + "				sio:SIO_000300 ?alogp }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node3 . \n"
                + "			_:node3 a sio:CHEMINF_000309 ;\n"
                + "				sio:SIO_000300 ?hba }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node4 . \n"
                + "			_:node4 a sio:CHEMINF_000310 ;\n"
                + "				sio:SIO_000300 ?hbd }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node5 . \n"
                + "			_:node5 a sio:CHEMINF_000198 ;\n"
                + "				sio:SIO_000300 ?molweight }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node6 . \n"
                + "			_:node6 a sio:CHEMINF_000350 ;\n"
                + "				sio:SIO_000300 ?mw_freebase }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node7 . \n"
                + "			_:node7 a sio:CHEMINF_000314 ;\n"
                + "				sio:SIO_000300 ?num_ro5_violations }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node8 . \n"
                + "			_:node8 a sio:CHEMINF_000308 ;\n"
                + "				sio:SIO_000300 ?psa }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node9 . \n"
                + "			_:node9 a sio:CHEMINF_000311 ;\n"
                + "				sio:SIO_000300 ?rtb }\n"
                + "	}\n"
                + "	GRAPH <http://linkedlifedata.com/resource/drugbank> {\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:affectedOrganism ?affectedOrganism }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:biotransformation ?biotransformation }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:description ?description }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:indication ?indication }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:proteinBinding ?proteinBinding }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:toxicity ?toxicity }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:meltingPoint ?meltingPoint }\n"
                + "	}\n"
                + "}";
        //Singleton set added due to all optional
        queryCase.noReplaceQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "PREFIX chemspider: <http://rdf.chemspider.com/#>\n"
                + "PREFIX sio: <http://semanticscience.org/resource/>\n"
                + "PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>\n"
                + "SELECT DISTINCT ?compound_name ?csid_uri ?molformula ?molweight ?inchi ?inchi_key ?smiles ?alogp "
                + "                ?hba ?hbd ?mw_freebase ?num_ro5_violations ?psa ?rtb ?meltingPoint ?affectedOrganism "
                + "                ?biotransformation ?description ?indication ?proteinBinding ?toxicity\n"
                + "WHERE {\n"
                + "	GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "		<http://www.conceptwiki.org/concept/38932552-111f-4a4e-a46a-4ed1d7bdf9d5> skos:prefLabel ?compound_name.\n"
                + "	}\n"
                + "	GRAPH <http://www.chemspider.com> {\n"
                + "		<http://rdf.chemspider.com/187440>  chemspider:smiles ?inchi;\n"
                + "			chemspider:inchikey ?inchi_key;\n"
                + "			chemspider:smiles ?smiles . \n"
                + "		?csid_uri chemspider:inchikey ?inchi_key .\n"
                + "	}\n"
                + "	GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {\n"
                + "		<http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node1 . \n"
                + "		_:node1 a sio:CHEMINF_000042 ;\n"
                + "			sio:SIO_000300 ?molformula\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node2 . \n"
                + "			_:node2 a sio:CHEMINF_000305 ;\n"
                + "				sio:SIO_000300 ?alogp }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node3 . \n"
                + "			_:node3 a sio:CHEMINF_000309 ;\n"
                + "				sio:SIO_000300 ?hba }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node4 . \n"
                + "			_:node4 a sio:CHEMINF_000310 ;\n"
                + "				sio:SIO_000300 ?hbd }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node5 . \n"
                + "			_:node5 a sio:CHEMINF_000198 ;\n"
                + "				sio:SIO_000300 ?molweight }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node6 . \n"
                + "			_:node6 a sio:CHEMINF_000350 ;\n"
                + "				sio:SIO_000300 ?mw_freebase }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node7 . \n"
                + "			_:node7 a sio:CHEMINF_000314 ;\n"
                + "				sio:SIO_000300 ?num_ro5_violations }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node8 . \n"
                + "			_:node8 a sio:CHEMINF_000308 ;\n"
                + "				sio:SIO_000300 ?psa }\n"
                + "		OPTIONAL { <http://data.kasabi.com/dataset/chembl-rdf/molecule/m276734> sio:CHEMINF_000200 _:node9 . \n"
                + "			_:node9 a sio:CHEMINF_000311 ;\n"
                + "				sio:SIO_000300 ?rtb }\n"
                + "	}\n"
                + " {}"
                + "	GRAPH <http://linkedlifedata.com/resource/drugbank> {\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:affectedOrganism ?affectedOrganism }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:biotransformation ?biotransformation }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:description ?description }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:indication ?indication }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:proteinBinding ?proteinBinding }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:toxicity ?toxicity }\n"
                + "		OPTIONAL { <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398> drugbank:meltingPoint ?meltingPoint }\n"
                + "	}\n"
                + "}";
        queries.put(queryCase.key, queryCase);
   }

   private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Ops";
        queryCase.name = "Ops  Query";
        queryCase.originalQuery = "";
        queries.put(queryCase.key, queryCase);
    }
 }
