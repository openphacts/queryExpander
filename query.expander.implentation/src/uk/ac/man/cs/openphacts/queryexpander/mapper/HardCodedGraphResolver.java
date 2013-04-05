package uk.ac.man.cs.openphacts.queryexpander.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.bridgedb.rdf.UriPattern;

/**
 *
 * @author Christian
 */
public class HardCodedGraphResolver {

    private HashMap<String,Set<UriPattern>> allowedUriPattern;
    
    public HardCodedGraphResolver(){
        setupHardCodedFilter();
    }
    
    private void setupHardCodedFilter() {
        allowedUriPattern = new HashMap<String,Set<UriPattern>>();
        HashSet<UriPattern> patterns;
        
        patterns = new HashSet<UriPattern>();
        UriPattern pattern = UriPattern.byNameSpace("http://www.conceptwiki.org/concept/");
        patterns.add(pattern);
        getAllowedUriPatterns().put("http://larkc.eu#Fixedcontext", patterns);

        getAllowedUriPatterns().put("http://www.conceptwiki.org", patterns);

        patterns = new HashSet<UriPattern>();
        pattern = UriPattern.byNameSpace("http://data.kasabi.com/dataset/chembl-rdf/target/t");
        patterns.add(pattern);
        pattern = UriPattern.byNameSpace("http://data.kasabi.com/dataset/chembl-rdf/molecule/m");
        patterns.add(pattern);
        getAllowedUriPatterns().put("http://data.kasabi.com/dataset/chembl-rdf",  patterns);

        patterns = new HashSet<UriPattern>();
        pattern = UriPattern.byNameSpace("http://www4.wiwiss.fu-berlin.de/drugbank/resource/targets/");
        patterns.add(pattern);
        pattern = UriPattern.byNameSpace("http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/");
        patterns.add(pattern);
        getAllowedUriPatterns().put("http://linkedlifedata.com/resource/drugbank",  patterns);

        patterns = new HashSet<UriPattern>();
        pattern = UriPattern.byNameSpace("http://purl.uniprot.org/uniprot/");
        patterns.add(pattern);
        getAllowedUriPatterns().put("http://purl.uniprot.org",  patterns);

        patterns = new HashSet<UriPattern>();
        pattern = UriPattern.byNameSpace("http://rdf.chemspider.com/");
        patterns.add(pattern);
        getAllowedUriPatterns().put("http://www.chemspider.com",  patterns);
    }

    /**
     * @return the allowedNamespaces
     */
    public HashMap<String,Set<UriPattern>> getAllowedUriPatterns() {
        return allowedUriPattern;
    }


}
