package uk.ac.cs.man.openphacts.queryexpander.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Christian
 */
public class HardCodedGraphResolver {

    private HashMap<String,List<String>> allowedNamespaces;
    
    public HardCodedGraphResolver(){
        setupHardCodedFilter();
    }
    
    private void setupHardCodedFilter() {
        allowedNamespaces = new HashMap<String,List<String>>();
        ArrayList<String> nameSpaces;
        
        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://www.conceptwiki.org/concept/");
        getAllowedNamespaces().put("http://larkc.eu#Fixedcontext", nameSpaces);

        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://data.kasabi.com/dataset/chembl-rdf/target/");
        nameSpaces.add("http://data.kasabi.com/dataset/chembl-rdf/molecule/");
        getAllowedNamespaces().put("http://data.kasabi.com/dataset/chembl-rdf", nameSpaces);

        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/");
        nameSpaces.add("http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/");
        getAllowedNamespaces().put("http://linkedlifedata.com/resource/drugbank", nameSpaces);

        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://rdf.chemspider.com/");
        getAllowedNamespaces().put("http://www.chemspider.com", nameSpaces);
    }

    /**
     * @return the allowedNamespaces
     */
    public HashMap<String,List<String>> getAllowedNamespaces() {
        return allowedNamespaces;
    }


}
