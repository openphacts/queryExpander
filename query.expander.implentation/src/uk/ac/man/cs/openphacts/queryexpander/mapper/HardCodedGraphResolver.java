package uk.ac.man.cs.openphacts.queryexpander.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Christian
 */
public class HardCodedGraphResolver {

    private HashMap<String,Set<String>> allowedNamespaces;
    
    public HardCodedGraphResolver(){
        setupHardCodedFilter();
    }
    
    private void setupHardCodedFilter() {
        allowedNamespaces = new HashMap<String,Set<String>>();
        HashSet<String> nameSpaces;
        
        nameSpaces = new HashSet<String>();
        nameSpaces.add("http://www.conceptwiki.org/concept/");
        getAllowedNamespaces().put("http://larkc.eu#Fixedcontext", nameSpaces);

        getAllowedNamespaces().put("http://www.conceptwiki.org", nameSpaces);

        nameSpaces = new HashSet<String>();
        nameSpaces.add("http://data.kasabi.com/dataset/chembl-rdf/target/");
        nameSpaces.add("http://data.kasabi.com/dataset/chembl-rdf/molecule/");
        getAllowedNamespaces().put("http://data.kasabi.com/dataset/chembl-rdf", nameSpaces);

        nameSpaces = new HashSet<String>();
        nameSpaces.add("http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/");
        nameSpaces.add("http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/");
                        http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00398
        getAllowedNamespaces().put("http://linkedlifedata.com/resource/drugbank", nameSpaces);

        nameSpaces = new HashSet<String>();
        nameSpaces.add(" http://purl.uniprot.org/uniprot/");
        getAllowedNamespaces().put("http://purl.uniprot.org", nameSpaces);

        nameSpaces = new HashSet<String>();
        nameSpaces.add("http://rdf.chemspider.com/");
        getAllowedNamespaces().put("http://www.chemspider.com", nameSpaces);
    }

    /**
     * @return the allowedNamespaces
     */
    public HashMap<String,Set<String>> getAllowedNamespaces() {
        return allowedNamespaces;
    }


}
