// BridgeDb,
// An abstraction layer for identifier mapping services, both local and online.
//
// Copyright      2012  Christian Y. A. Brenninkmeijer
// Copyright      2012  OpenPhacts
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package uk.ac.manchester.cs.openphacts.queryexpander.tester;
import java.io.File;
import org.bridgedb.IDMapperException;
import org.bridgedb.sql.SQLUriMapper;
import org.bridgedb.uri.loader.LinksetListener;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.utils.ConfigReader;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

/**
 * @author Christian
 */
public class SetupLoaderWithTestData {
    
    private static LinksetListener instance;
    static final String MAIN_JUSTIFCATION = "http://www.w3.org/2000/01/rdf-schema#isDefinedBy";
    static final String LENS_JUSTIFCATION = "http://www.bridgedb.org/test#testJustification";
    static final URI LINK_PREDICATE = new URIImpl("http://www.w3.org/2004/02/skos/core#exactMatch");
     
    private static void loadFile(String fileName) throws BridgeDBException{
        File file = new File(fileName);
        instance.parse(file, LINK_PREDICATE, MAIN_JUSTIFCATION);
    }

    //It is recommended to use the IMS method rather than this one.
    public static void main(String[] args) throws IDMapperException {
        ConfigReader.logToConsole();
        ConfigReader.useTest();
        SQLUriMapper uriListener = SQLUriMapper.createNew();
        instance = new LinksetListener(uriListener);

        loadFile("../query.expander.implentation/test-data/cw-cs.ttl");
        loadFile("../query.expander.implentation/test-data/cw-cm.ttl");
        loadFile("../query.expander.implentation/test-data/cw-dd.ttl");
        loadFile("../query.expander.implentation/test-data/cw-ct.ttl");
        loadFile("../query.expander.implentation/test-data/cw-dt.ttl");   }   
}
