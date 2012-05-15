/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openrdf.model.URI;
import uk.ac.cs.man.openphacts.queryexpander.QueryExpansionException;

/**
 *
 * @author Christian
 */
public class HardCodedGraphMapper implements IMSMapper{

    private IMSMapper inner;
    private HashMap<String,List<String>> allowedNamespaces;
    
    public HardCodedGraphMapper(IMSMapper inner){
        this.inner = inner;
        setupHardCodedFilter();
    }
    
    private void setupHardCodedFilter() {
        allowedNamespaces = new HashMap<String,List<String>>();
        ArrayList<String> nameSpaces;
        
        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://www.conceptwiki.org/concept/");
        allowedNamespaces.put("http://larkc.eu#Fixedcontext", nameSpaces);

        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://data.kasabi.com/dataset/chembl-rdf/target/");
        nameSpaces.add("http://data.kasabi.com/dataset/chembl-rdf/molecule/");
        allowedNamespaces.put("http://data.kasabi.com/dataset/chembl-rdf", nameSpaces);

        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/");
        nameSpaces.add("http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/");
        allowedNamespaces.put("http://linkedlifedata.com/resource/drugbank", nameSpaces);

        nameSpaces = new ArrayList<String>();
        nameSpaces.add("http://rdf.chemspider.com/");
        allowedNamespaces.put("http://www.chemspider.com", nameSpaces);
    }        

    @Override
    public Map<URI, List<URI>> getMatchesForURIs(Set<URI> uriSet) {
        return inner.getMatchesForURIs(uriSet);
    }

    @Override
    public List<URI> getMatchesForURI(URI uri) {
        return inner.getMatchesForURI(uri);
    }

    @Override
    public List<URI> getSpecificMatchesForURI(URI uri, String graph) throws QueryExpansionException {
        //ystem.out.println(uri);
        List<URI> fullList = inner.getMatchesForURI(uri);
        //ystem.out.println(fullList);
        
        ArrayList<URI> strippedList = new ArrayList<URI>();
        List<String> nameSpaces = allowedNamespaces.get(graph);
        if (nameSpaces == null) {
            throw new QueryExpansionException("unexpected graph " + graph);
        }
        for (URI possibleURI:fullList){
            String nameSpace = possibleURI.getNamespace();
            if (nameSpaces.contains(nameSpace)){
                strippedList.add(possibleURI);
            }
        }
        //ystem.out.println(strippedList);
        return strippedList;
    }

}
