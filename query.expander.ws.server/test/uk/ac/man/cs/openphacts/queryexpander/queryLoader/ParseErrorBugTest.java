package uk.ac.man.cs.openphacts.queryexpander.queryLoader;


import org.bridgedb.utils.Reporter;
import org.bridgedb.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderException;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.mapper.DummyIMSMapper;

public class ParseErrorBugTest extends TestUtils {

	String query = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " +
"PREFIX chemspider: <http://rdf.chemspider.com/#> " + 
"PREFIX sio: <http://semanticscience.org/resource/> "+ 
"PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/> "+ 
"SELECT DISTINCT ?molformula " + 
"WHERE { " + 
"        GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {  " +
"                <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node1 . " + 
"        } " + 
"}";
	
	String bigQuery = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " +
"PREFIX chemspider: <http://rdf.chemspider.com/#> " + 
"PREFIX sio: <http://semanticscience.org/resource/> "+ 
"PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/> "+ 
"SELECT DISTINCT ?compound_name ?csid_uri ?molformula ?molweight ?inchi ?inchi_key ?smiles  ?alogp ?hba ?hbd ?mw_freebase ?num_ro5_violations ?psa ?rtb ?meltingPoint ?affectedOrganism ?biotransformation ?description ?indication ?proteinBinding ?toxicity " + 
"WHERE { " + 
"        GRAPH <http://www.conceptwiki.org> { " + 
"                <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> skos:prefLabel ?compound_name. " + 
"        } " + 
"        GRAPH <http://www.chemspider.com> { " + 
"                <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> chemspider:inchi ?inchi; " + 
"                        chemspider:inchikey ?inchi_key; " + 
"                        chemspider:smiles ?smiles . " + 
"        } "+ 
"        GRAPH <http://data.kasabi.com/dataset/chembl-rdf> {  " +
"                <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node1 . " + 
"                _:node1 a sio:CHEMINF_000042 ;  " +
"                        sio:SIO_000300 ?molformula  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node2 . " + 
"                        _:node2 a sio:CHEMINF_000305 ; " +
"                                sio:SIO_000300 ?alogp }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node3 . " + 
"                        _:node3 a sio:CHEMINF_000309 ;  " +
"                                sio:SIO_000300 ?hba }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node4 . " + 
"                        _:node4 a sio:CHEMINF_000310 ;  " +
"                                sio:SIO_000300 ?hbd }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node5 . " + 
"                        _:node5 a sio:CHEMINF_000198 ;  " +
"                                sio:SIO_000300 ?molweight } " + 
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node6 . " + 
"                        _:node6 a sio:CHEMINF_000350 ;  " +
"                                sio:SIO_000300 ?mw_freebase } " + 
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node7 . " + 
"                        _:node7 a sio:CHEMINF_000314 ;  " +
"                                sio:SIO_000300 ?num_ro5_violations } " + 
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node8 . " + 
"                        _:node8 a sio:CHEMINF_000308 ;  " +
"                                sio:SIO_000300 ?psa }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> sio:CHEMINF_000200 _:node9 . " + 
"                        _:node9 a sio:CHEMINF_000311 ;  " +
"                                sio:SIO_000300 ?rtb }  " +
"        } " + 
"        OPTIONAL { GRAPH <http://linkedlifedata.com/resource/drugbank> { " + 
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> drugbank:affectedOrganism ?affectedOrganism } " + 
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> drugbank:biotransformation ?biotransformation }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> drugbank:description ?description }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> drugbank:indication ?indication }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> drugbank:proteinBinding ?proteinBinding } " + 
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> drugbank:toxicity ?toxicity }  " +
"                OPTIONAL { <http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1> drugbank:meltingPoint ?meltingPoint } " + 
"        } } " + 
"}";
    DummyIMSMapper imsMapper = new DummyIMSMapper();
    
    @Before
    public void loadMapper() {
    	imsMapper.addMapping("http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1", "http://www.bar.com/1");
    	imsMapper.addMapping("http://www.conceptwiki.org/concept/6161dfde-69c2-4466-8a02-cc5a5d6f6cf1", "http://www.bar.com/2");
    }
	
	@Test
	public void testFBG() throws QueryExpanderException {         
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        String newQuery = queryExpander.expandWithStrategy(query, "FILTER_GRAPH");
        Reporter.println("FBG Expanded query success");    
	}

	@Test
	public void testUBG() throws QueryExpanderException {
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        String newQuery = queryExpander.expandWithStrategy(query, "UNION_GRAPH");
        Reporter.println("UBG Expanded query success");    
	}

	
}
