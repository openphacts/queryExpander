/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.queryLoader;

/**
 *
 * @author Christian
 */
public class OpsAlternativeLoader extends QueryCaseLoader{

    public OpsAlternativeLoader(){
        loadSmall();
 //       loadPaper();
    }

   private void loadSmall() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltMyexpample";
        queryCase.name = "Alternative Query small";
        queryCase.originalQuery = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "	GRAPH <http://www.example.org/graph1> {\n" +
                "		OPTIONAL { foo:subj1 example:pred1 ?obj1 }\n" +
                "		OPTIONAL { foo:subj1 example:pred2 ?obj2 }\n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { foo:subj1 example:pred3 ?obj3 }\n" +
                "		foo:subj1 example:pred4 ?obj4;\n" +
                "                 example:pred5 ?obj5.\n" +
                "	}\n" +
                "}\n";
        queryCase.replaceQuery = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "	GRAPH <http://www.example.org/graph1> {\n" +
                "		OPTIONAL { foo:subj1 example:pred1 ?obj1 }\n" +
                "		OPTIONAL { foo:subj1 example:pred2 ?obj2 }\n" +
                "	}\n" +
                "	GRAPH <http://www.example.org/graph2> {\n" +
                "		OPTIONAL { bar:1 example:pred3 ?obj3 }\n" +
                "		bar:1 example:pred4 ?obj4.\n" +
                "       bar:1 example:pred5 ?obj5.\n" +
                "	}\n" +
                "}\n";
        queryCase.filterStatement = queryCase.replaceQuery;
        queryCase.filterAll =  queryCase.replaceQuery;
        queryCase.unionAll = "PREFIX example: <http://www.example.com/#>\n" +
                "PREFIX foo: <http://www.foo.com/>\n" +
                "PREFIX bar: <http://www.bar.com/>\n" +
                "SELECT DISTINCT \n" +
                "   ?obj1 ?obj2 ?obj3 ?obj4 ?obj5 \n" +
                "WHERE {\n" +
                "    {\n" +
                "	    GRAPH <http://www.example.org/graph1> {\n" +
                "		    OPTIONAL { bar:1 example:pred1 ?obj1 }\n" +
                "		    OPTIONAL { bar:1 example:pred2 ?obj2 }\n" +
                "	    }\n" +
                "	    GRAPH <http://www.example.org/graph2> {\n" +
                "		    OPTIONAL { bar:1 example:pred3 ?obj3 }\n" +
                "		    bar:1 example:pred4 ?obj4;\n" +
                "                     example:pred5 ?obj5.\n" +
                "	    }\n" +
                "    } UNION {\n" +
                "	    GRAPH <http://www.example.org/graph1> {\n" +
                "		    OPTIONAL { foo:subj1 example:pred1 ?obj1 }\n" +
                "		    OPTIONAL { foo:subj1 example:pred2 ?obj2 }\n" +
                "	    }\n" +
                "	    GRAPH <http://www.example.org/graph2> {\n" +
                "		    OPTIONAL { foo:subj1 example:pred3 ?obj3 }\n" +
                "		    foo:subj1 example:pred4 ?obj4;\n" +
                "                     example:pred5 ?obj5.\n" +
                "	    }\n" +
                "    }\n" +
                "}\n";
        queryCase.unionStatement = queryCase.replaceQuery;
        queryCase.unionGraph = queryCase.replaceQuery;
        queries.put(queryCase.key, queryCase);
   }

    private void loadPaper() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "AltPaper";
        queryCase.name = "Paper Query small";
        queryCase.originalQuery = "SELECT  DISTINCT ?target_name ?compound_name ?csid_uri ?smiles ?inchi ?inchi_key \n"
                + "    ?molweight ?num_ro5_violations ?std_type ?relation ?std_value ?std_unit ?target_organism\n"
                + "WHERE {\n"
                + " GRAPH <http://www.conceptwiki.org>  {\n"
                + "     <http://www.conceptwiki.org/concept/d915a79d-e556-4614-983d-e1f7830066d7> \n"
                + "     <http://www.w3.org/2004/02/skos/core#prefLabel>  ?target_name . \n"
                + "   ?compound_cw <http://www.w3.org/2004/02/skos/core#exactMatch>  ?csid_uri . \n"
                + "   ?compound_cw <http://www.w3.org/2004/02/skos/core#prefLabel>  ?compound_name . \n"
                + " } \n"
                + " GRAPH <http://data.kasabi.com/dataset/chembl-rdf>  {\n"
                + "   <http://www.conceptwiki.org/concept/d915a79d-e556-4614-983d-e1f7830066d7> \n"
                + "      <http://rdf.farmbio.uu.se/chembl/onto/#organism>  ?target_organism . \n"
                + "   ?assay_uri <http://rdf.farmbio.uu.se/chembl/onto/#hasTarget> \n"
                + "      <http://www.conceptwiki.org/concept/d915a79d-e556-4614-983d-e1f7830066d7> . \n"
                + "   ?activity_uri <http://rdf.farmbio.uu.se/chembl/onto/#onAssay>  ?assay_uri . \n"
                + "   ?activity_uri <http://rdf.farmbio.uu.se/chembl/onto/#type>  ?std_type . \n"
                + "   ?activity_uri <http://rdf.farmbio.uu.se/chembl/onto/#relation>  ?relation .\n"
                + "   ?activity_uri <http://rdf.farmbio.uu.se/chembl/onto/#standardValue>  ?std_value . \n"
                + "   ?activity_uri <http://rdf.farmbio.uu.se/chembl/onto/#standardUnits>  ?std_unit . \n"
                + "   ?activity_uri <http://rdf.farmbio.uu.se/chembl/onto/#forMolecule>  ?compound_uri . \n"
                + "   ?compound_uri <http://www.w3.org/2002/07/owl#equivalentClass>  ?equiv_uri . \n"
                + "   ?equiv_uri <http://www.w3.org/2004/02/skos/core#exactMatch>  ?csid_uri . \n"
                + "   OPTIONAL { \n"
                + "      ?compound_uri <http://semanticscience.org/resource/CHEMINF_000200>  _:_1_0 . \n"
                + "       _:_1_0 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> \n"
                + "           <http://semanticscience.org/resource/CHEMINF_000314> . \n"
                + "       _:_1_0 <http://semanticscience.org/resource/SIO_000300>  ?num_ro5_violations . \n"
                + " } \n"
                + "OPTIONAL { \n"
                + " ?compound_uri <http://semanticscience.org/resource/CHEMINF_000200>  _:_2_0 . \n"
                + " _:_2_0 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://semanticscience.org/resource/CHEMINF_000198> . \n"
                + " _:_2_0 <http://semanticscience.org/resource/SIO_000300>  ?molweight . \n"
                + " } \n"
                + " }\n"
                + " GRAPH <http://www.chemspider.com>  {\n"
                + " ?csid_uri <http://rdf.chemspider.com/#inchi>  ?inchi . \n"
                + " ?csid_uri <http://rdf.chemspider.com/#inchikey>  ?inchi_key . \n"
                + " ?csid_uri <http://rdf.chemspider.com/#smiles>  ?smiles . \n"
                + " }\n"
                + " } ";
    }
    
   private void load() {
        QueryCase queryCase = new QueryCase();
        queryCase.key = "Alt";
        queryCase.name = "Alternative Query";
        queryCase.originalQuery = "";
        queryCase.filterStatement = "";
        queryCase.replaceQuery = "";
        queryCase.filterAll = "";
        queries.put(queryCase.key, queryCase);
    }

 
}
