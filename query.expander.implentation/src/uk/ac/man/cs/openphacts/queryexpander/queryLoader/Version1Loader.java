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
   private void loadPartCompoundLookup() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Version1PartCompoundLookup";
        queryCase.name = "PartCompoundLookup Query From March Tests";
        queryCase.originalQuery = "SELECT  DISTINCT ?ligand_name ?ligand_displaced ?smiles ?inchi \n"
                + "WHERE {\n"
                + "  OPTIONAL {\n"
                + "    GRAPH <http://PDSP_DB/Data> {\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#has_test_ligand_name>  ?ligand_name ;\n"
                + "          <http://wiki.openphacts.org/index.php/PDSP_DB#ligand_displaced>  ?ligand_displaced .\n"
                + "    } \n"
                + "  } OPTIONAL {\n"
                + "    GRAPH <http://rdf.chemspider.com/data>  {\n"
                + "      <http://www.conceptwiki.org/concept/d510239a-6b55-4ca9-8f64-cfc9b8e7c64c> \n"
                + "           <http://rdf.chemspider.com/#smiles>  ?smiles;\n"
                + "           <http://rdf.chemspider.com/#inchi>  ?inchi .\n"
                + "    }\n"
                + "  }\n"
                + "}\n"
                + "LIMIT 10\n";

        queries.put(queryCase.key, queryCase);
    }

}
