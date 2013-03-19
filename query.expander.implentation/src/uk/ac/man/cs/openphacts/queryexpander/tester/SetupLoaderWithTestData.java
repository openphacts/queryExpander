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
package uk.ac.man.cs.openphacts.queryexpander.tester;
import org.bridgedb.IDMapperException;
import org.bridgedb.linkset.LinksetLoader;
import org.bridgedb.tools.metadata.validator.ValidationType;
import org.bridgedb.utils.ConfigReader;
import org.bridgedb.utils.StoreType;

/**
 * @author Christian
 */
public class SetupLoaderWithTestData {
    
    public static void main(String[] args) throws IDMapperException {
        ConfigReader.logToConsole();
        LinksetLoader linksetLoader = new LinksetLoader();
        linksetLoader.clearExistingData(StoreType.LOAD);
        linksetLoader.load("../query.expander.implentation/test-data/cw-cs.ttl", StoreType.LOAD, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.load("../query.expander.implentation/test-data/cw-cm.ttl", StoreType.LOAD, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.load("../query.expander.implentation/test-data/cw-dd.ttl", StoreType.LOAD, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.load("../query.expander.implentation/test-data/cw-ct.ttl", StoreType.LOAD, 
                ValidationType.LINKSMINIMAL);
        linksetLoader.load("../query.expander.implentation/test-data/cw-dt.ttl", StoreType.LOAD, 
                ValidationType.LINKSMINIMAL);   }   
}
