/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class Version1Loader extends QueryCaseLoader{
    
   public Version1Loader(){
       loadPartCompoundLookup();
       loadPartNoOptionalCompoundLookup();
       loadPartThreeMapsp();
       loadSameGraphUsedTwice();
       loadBigCompoundLookup();
       loadCompoundLookupStatementsOptionalOutside();
       loadCompoundLookupStatementsOptionalInside();
       loadCompoundLookupInandOutsideOfOPTIONAL();
       loadCompoundLookupOptinalwithPartGraphInside();
       loadSingleInOptional();
       loadSimple();
       loadNoneOptionalNone();
       loadNoneGraphNone();
       loadNoneGraphOptionalNone();
       loadNoneOptional2None();
       loadNoneGraph2None();
       loadNoneGraphOptional2None();
       loadNoneOptionalGraphGraphNone();
       loadNone_OptionalGraphNone_None();
       loadNone_GraphNoneOptional_None();
  //     loadNone_GraphNoneOptionalNone_None();
       loadNone_GraphOptionalOptional_None();
       loadNone_GraphOptionalNone_None();
   }
    
   private void loadPartCompoundLookup() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1PartCompoundLookup";
        queryCase.name = "PartCompoundLookup Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_name ?ligand_displaced ?smiles ?inchi \n\n"
                + "WHERE {\n\n"
                + "  OPTIONAL {\n\n"
                + "    GRAPH <http://PDSP_DB/Data> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced>  ?ligand_displaced .\n\n"
                + "    } \n\n"
                + "  } OPTIONAL {\n\n"
                + "    GRAPH <http://rdf.chemspider.com/data>  {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "           <http://rdf.chemspider.com/#smiles>  ?smiles;\n\n"
                + "           <http://rdf.chemspider.com/#inchi>  ?inchi .\n\n"
                + "    }\n\n"
                + "  }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
    }

   private void loadPartNoOptionalCompoundLookup() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1PartNoOptionalCompoundLookup";
        queryCase.name = "PartNoOptionalCompoundLookup Query From March Tests";
        queryCase.originalQuery = "SELECT DISTINCT ?ligand_name ?ligand_displaced ?smiles ?inchi \n\n"
                + "WHERE {\n\n"
                + "    GRAPH <http://PDSP_DB/Data> \n{\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced>  ?ligand_displaced .\n\n"
                + "    } \n\n"
                + "    GRAPH <http://rdf.chemspider.com/data>  {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "           <http://rdf.chemspider.com/#smiles>  ?smiles;\n\n"
                + "           <http://rdf.chemspider.com/#inchi>  ?inchi .\n\n"
                + "    }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }

   private void loadPartThreeMapsp() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1PartThreeMapsp";
        queryCase.name = "PartThreeMapsp Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_name ?ligand_displaced ?smiles ?inchi \n\n"
                + "WHERE {\n\n"
                + "    GRAPH <http://PDSP_DB/Data> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced>  ?ligand_displaced .\n\n"
                + "    } \n\n"
                + "    GRAPH <http://rdf.chemspider.com/data>  {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "           <http://rdf.chemspider.com/#smiles>  ?smiles;\n\n"
                + "           <http://rdf.chemspider.com/#inchi>  ?inchi .\n\n"
                + "    }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadSameGraphUsedTwice() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1SameGraphUsedTwice";
        queryCase.name = "SameGraphUsedTwice Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_name ?ligand_displaced ?smiles ?inchi \n\n"
                + "WHERE {\n\n"
                + "    GRAPH <http://PDSP_DB/Data> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced>  ?ligand_displaced .\n\n"
                + "    } \n\n"
                + "    GRAPH <http://PDSP_DB/Data> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "           <http://rdf.chemspider.com/#smiles>  ?smiles;\n\n"
                + "           <http://rdf.chemspider.com/#inchi>  ?inchi .\n\n"
                + "    }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadBigCompoundLookup() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1BigCompoundLookup";
        queryCase.name = "BigCompoundLookup Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?csid_uri ?ligand_name ?ligand_displaced ?cas ?receptor_name \n\n"
                + "?pdsp_species ?pdsp_source ?smiles ?inchi ?inchi_key ?med_chem_friendly ?molweight ?hhd ?alogp \n\n"
                + "?mw_freebase ?psa ?molformula ?molregno ?num_ro5_violations ?ro3_pass ?hha ?rtb \n\n"
                + "?predictedWaterSolubility ?experimentalWaterSolubility ?molecularWeightAverage ?description \n\n"
                + "?halfLife ?state ?predictedLogs ?brandName ?predictedLogpHydrophobicity \n\n"
                + "?experimentalLogpHydrophobicity ?drugCategoryLabel ?targetLabel \n\n"
                + "WHERE {\n\n"
                + "  OPTIONAL {\n\n"
                + "    GRAPH <http://PDSP_DB/Data> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced>  ?ligand_displaced ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_cas_num>  ?cas ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_receptor_name>  ?receptor_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#species>  ?pdsp_species ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#source>  ?pdsp_source .\n\n"
                + "    } \n\n"
                + "  } OPTIONAL {\n\n"
                + "    GRAPH <http://rdf.chemspider.com/data>  {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "           <http://rdf.chemspider.com/#smiles>  ?smiles;\n\n"
                + "           <http://rdf.chemspider.com/#inchi>  ?inchi ;\n\n"
                + "           <http://rdf.chemspider.com/#inchikey>  ?inchi_key .\n\n"
                + "      ?csid_uri <http://rdf.chemspider.com/#inchikey> ?inchi_key .\n\n"
                + "    }\n\n"
                + "  } OPTIONAL {\n\n"
                + "    GRAPH <http://chem2bio2rdf.org/data> {\n\n"
                + "       <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/med_chem_friendly> ?med_chem_friendly ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/molweight> ?molweight ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/hhd> ?hhd ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/alogp> ?alogp ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/mw_freebase> ?mw_freebase ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/psa> ?psa ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/molformula> ?molformula ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/molregno> ?molregno ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/num_ro5_violations> ?num_ro5_violations ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/ro3_pass> ?ro3_pass;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/hha> ?hha ;\n\n"
                + "           <http://chem2bio2rdf.org/chembl/resource/rtb> ?rtb;\n\n"
                + "    }\n\n"
                + "  } OPTIONAL {\n\n"
                + "    GRAPH <http://www4.wiwiss.fu-berlin.de/data> {\n\n"
                + "       <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/predictedWaterSolubility> \n\n"
                + "                ?predictedWaterSolubility ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/experimentalWaterSolubility> \n\n"
                + "                ?experimentalWaterSolubility ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/molecularWeightAverage> \n\n"
                + "                ?molecularWeightAverage ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/drugCategory> ?cat_uri ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/description> ?description ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/halfLife> ?halfLife ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/state> ?state ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/predictedLogs> ?predictedLogs ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/brandName> ?brandName ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/target> ?target_uri ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/predictedLogpHydrophobicity> \n\n"
                + "                ?predictedLogpHydrophobicity ;\n\n"
                + "           <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/experimentalLogpHydrophobicity> \n\n"
                + "                ?experimentalLogpHydrophobicity .\n\n"
                + "       ?cat_uri <http://www.w3.org/2000/01/rdf-schema#label> ?drugCategoryLabel .\n\n"
                + "       ?target_uri <http://www.w3.org/2000/01/rdf-schema#label> ?targetLabel\n\n"
                + "    }\n\n"
                + "  }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadCompoundLookupStatementsOptionalOutside() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1CompoundLookupStatementsOptionalOutside";
        queryCase.name = "CompoundLookupStatementsOptionalOutside Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_name ?pdsp_source \n\n"
                + "WHERE {\n\n"
                + "  OPTIONAL {\n\n"
                + "    GRAPH <http://PDSP_DB/DataQ> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#source>  ?pdsp_source .\n\n"
                + "    } \n\n"
                + "  }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadCompoundLookupStatementsOptionalInside() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1CompoundLookupStatementsOptionalInside";
        queryCase.name = "CompoundLookupStatementsOptionalInside Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_name ?pdsp_source \n\n"
                + "WHERE {\n\n"
                + "  GRAPH <http://PDSP_DB/DataQ> {\n\n"
                + "    OPTIONAL {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#source>  ?pdsp_source .\n\n"
                + "    } \n\n"
                + "  }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadCompoundLookupInandOutsideOfOPTIONAL() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1CompoundLookupInandOutsideOfOPTIONAL";
        queryCase.name = "CompoundLookupInandOutsideOfOPTIONAL Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_displaced ?cas ?ligand_name ?pdsp_source \n\n"
                + "WHERE {\n\n"
                + "  GRAPH <http://PDSP_DB/DataQ> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced> ?ligand_displaced ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_cas_num> ?cas ;\n\n"
                + "    OPTIONAL {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#source>  ?pdsp_source .\n\n"
                + "    } \n\n"
                + "  }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadCompoundLookupOptinalwithPartGraphInside() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1CompoundLookupOptinalwithPartGraphInside";
        queryCase.name = "CompoundLookupOptinalwithPartGraphInside Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_displaced ?cas ?ligand_name ?pdsp_source \n\n"
                + "WHERE {\n\n"
                + "  OPTIONAL {\n\n"
                + "    GRAPH <http://PDSP_DB/DataQ> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced> ?ligand_displaced ;\n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_cas_num> ?cas ;\n\n"
                + "    } \n\n"
                + "    <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "        <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n\n"
                + "        <http://wiki.openphacts.org/index.php/PDSP_DB#source>  ?pdsp_source .\n\n"
                + "  }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }

   private void loadSingleInOptional() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1SingleInOptional";
        queryCase.name = "SingleInOptional Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT  ?pdsp_source \n\n"
                + "WHERE {\n\n"
                + "  OPTIONAL {\n\n"
                + "    GRAPH <http://PDSP_DB/Data> {\n\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#source>  ?pdsp_source .\n\n"
                + "    } \n\n"
                + "  }\n\n"
                + "}\n\n"
                + "LIMIT 10";
        queries.put(queryCase.key, queryCase);
   }
   
   private void loadSimple() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1Simple";
        queryCase.name = "Simple Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?first \n"
                + "WHERE\n"
                + "  { <http://input.com#1> foaf:name ?name .\n"
                + "    <http://input.com#1> foaf:mbox ?mbox .\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneOptionalNone() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "NoneOptionalNoneVersion1";
        queryCase.name = "NoneOptionalNone Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?first \n"
                + "WHERE\n"
                + "  { <http://input.com#1> foaf:name ?name .\n"
                + "Optional {\n"
                + "    <http://input.com#1> foaf:mbox ?mbox . \n"
                + "}\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneGraphNone() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneGraphNone";
        queryCase.name = "NoneGraphNone Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?first \n"
                + "WHERE {\n"
                + "  <http://input.com#1> foaf:name ?name .\n"
                + "  GRAPH  <http://foo.com> {\n"
                + "    <http://input.com#1> foaf:mbox ?mbox . \n"
                + "}\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneGraphOptionalNone() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneGraphOptionalNone";
        queryCase.name = "NoneGraphOptionalNone Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?first \n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    GRAPH <http://foo.com> {\n"
                + "        Optional {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "        }\n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queryCase.noReplaceQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?first \n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    {}"
                + "    Optional {\n"
                + "        GRAPH <http://foo.com> {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "        }\n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneOptionalGraphNone() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneOptionalGraphNone";
        queryCase.name = "NoneOptionalGraphNone Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?first \n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    Optional {\n"
                + "        GRAPH <http://foo.com> {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "    }   }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneOptional2None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneOptional2None";
        queryCase.name = "NoneOptional2None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n"
                + "WHERE\n"
                + "  { <http://input.com#1> foaf:name ?name .\n"
                + "Optional {\n"
                + "    <http://input.com#1> foaf:mbox ?mbox . \n"
                + "    <http://input.com#1> foaf:email ?email . \n"
                + "}\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneGraph2None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneGraph2None";
        queryCase.name = "NoneGraph2None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n"
                + "WHERE {\n"
                + "  <http://input.com#1> foaf:name ?name .\n"
                + "  GRAPH  <http://foo.com> {\n"
                + "    <http://input.com#1> foaf:mbox ?mbox . \n"
                + "    <http://input.com#1> foaf:email ?email . \n"
                + "  }\n"
                + "  <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneGraphOptional2None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneGraphOptional2None";
        queryCase.name = "NoneGraphOptional2None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    GRAPH <http://foo.com> {\n"
                + "        Optional {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "            <http://input.com#1> foaf:email ?email . \n"
                + "        }\n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "\n}";
        queryCase.noReplaceQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    {}\n"
                + "    Optional {\n"
                + "        GRAPH <http://foo.com> {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "            <http://input.com#1> foaf:email ?email . \n"
                + "        }\n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "\n}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneOptionalGraph2None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneOptionalGraph2None";
        queryCase.name = "NoneOptionalGraph2None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n\n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    Optional {\n"
                + "        GRAPH <http://foo.com> {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "            <http://input.com#1> foaf:email ?email . \n"
                + "    }   }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNoneOptionalGraphGraphNone() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1NoneOptionalGraphGraphNone";
        queryCase.name = "NoneOptionalGraphGraphNone Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n\n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name . \n\n"
                + "    Optional { \n\n"
                + "        GRAPH <http://foo.com> {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "        }\n"
                + "        GRAPH <http://bar.com> {\n"
                + "            <http://input.com#1> foaf:email ?email . \n"
                + "    }   }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNone_OptionalGraphNone_None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1None_OptionalGraphNone_None";
        queryCase.name = "None_OptionalGraphNone_None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n\n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    Optional {\n"
                + "        GRAPH <http://foo.com> {\n"
                + "            <http://input.com#1> foaf:mbox ?mbox . \n"
                + "        }\n"
                + "        <http://input.com#1> foaf:email ?email . \n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNone_GraphNoneOptional_None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1None_GraphNoneOptional_None";
        queryCase.name = "None_GraphNoneOptional_None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n\n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    GRAPH <http://foo.com> {\n"
                + "        <http://input.com#1> foaf:mbox ?mbox . \n"
                + "        Optional {\n"
                + "            <http://input.com#1> foaf:email ?email . \n"
                + "        }\n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNone_GraphNoneOptionalNone_None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1None_GraphNoneOptionalNone_None";
        queryCase.name = "None_GraphNoneOptionalNone_None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first ?second \n\n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    GRAPH <http://foo.com> {\n"
                + "        <http://input.com#1> foaf:mbox ?mbox . \n"
                + "        Optional {\n"
                + "            <http://input.com#1> foaf:email ?email . \n"
                + "        }\n"
                + "        <http://input.com#1> foaf:second ?second . \n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNone_GraphOptionalOptional_None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1None_GraphOptionalOptional_None";
        queryCase.name = "None_GraphOptionalOptional_None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n\n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    GRAPH <http://foo.com> {\n"
                + "        Optional {\n"
                + "           <http://input.com#1> foaf:mbox ?mbox . \n"
                + "        }\n"
                + "        Optional {\n"
                + "            <http://input.com#1> foaf:email ?email . \n"
                + "        }\n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void loadNone_GraphOptionalNone_None() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1None_GraphOptionalNone_None";
        queryCase.name = "None_GraphOptionalNone_None Query From March Tests";
        queryCase.originalQuery = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
                + "SELECT ?name ?mbox ?email ?first \n\n"
                + "WHERE {\n"
                + "    <http://input.com#1> foaf:name ?name .\n"
                + "    GRAPH <http://foo.com> {\n"
                + "        Optional {\n"
                + "           <http://input.com#1> foaf:mbox ?mbox . #test \n\n"
                + "        }\n"
                + "        <http://input.com#1> foaf:email ?email . \n"
                + "    }\n"
                + "    <http://input.com#1> foaf:first ?first .\n"
                + "}";
        queries.put(queryCase.key, queryCase);
    }
   
   private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1";
        queryCase.name = " Query From March Tests";
        queryCase.originalQuery = "";
        queries.put(queryCase.key, queryCase);
    }
   
}
