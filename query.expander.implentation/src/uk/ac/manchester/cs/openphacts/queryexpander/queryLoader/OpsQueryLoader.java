package uk.ac.manchester.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class OpsQueryLoader extends QueryCaseLoader{
    
   public OpsQueryLoader(){
       loadCompoundLookupV1();
       loadCompoundLookup();
   } 
   
   private void loadCompoundLookupV1() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Ops";
        queryCase.name = "Ops Compound Lookup Query Version 1";
        queryCase.originalQuery = "PREFIX cspr: <http://rdf.chemspider.com/#> \n"  
                + " PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n"
                + " PREFIX db: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/> \n"
                + " PREFIX chembl: <http://chem2bio2rdf.org/chembl/resource/> \n"
                + " SELECT DISTINCT ?compound_uri ?compound_name \n"
                + " WHERE { \n"
                + "   {?compound_uri cspr:synonym ?compound_name} \n"
                + " UNION \n"
                + "   {?compound_uri cspr:exturl ?mapping .\n"
                + "   ?mapping skos:exactMatch ?chebi .\n"
                + "   ?c2b2r_ChEMBL chembl:chebi ?chebi ; \n"
                + "                 chembl:cid ?cid .\n"
                + "   ?drugbank_uri db:pubchemCompoundURL ?cid ; \n"
                + "                 db:brandName ?compound_name} \n"
                + " FILTER regex(?compound_name, \"substring_value\", \"i\") } \n";             
        queries.put(queryCase.key, queryCase);
    }

   private void loadCompoundLookup() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Ops";
        queryCase.name = "Ops Compound Lookup Query ";
        queryCase.originalQuery = "PREFIX ext: <http://wiki.openphacts.org/index.php/ext_function#> \n"
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n"
                + "PREFIX cw: <http://www.conceptwiki.org/wiki/concept/> \n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/> \n"
                + "SELECT DISTINCT ?concept_label ?concept_url ?concept_uuid ?concept_alt_label ?tag_uuid \n"
                + "WHERE { \n"
                + "  GRAPH <http://larkc.eu#Fixedcontext> {\n"
                + "    ?concept_url ext:conceptwiki_search_by_tag \"substring_value\";\n"
                + "                 ext:semantic_type <http://www.conceptwiki.org/concept/87c08f16-3710-47fc-8ed7-33a1e426891d> ;\n"
                + "                 skos:prefLabel ?concept_label ; \n"
                + "                 dc:identifier ?concept_uuid ; \n"
                + "                 skos:altLabel ?concept_alt_label ;\n"
                + "                 cw:tag ?tag_uuid \n"
                + "  }"
                + "}";                              
        queries.put(queryCase.key, queryCase);
   }
   
   private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Ops";
        queryCase.name = "Ops Query ";
        queryCase.originalQuery = "";
        queries.put(queryCase.key, queryCase);
    }
   
 }
