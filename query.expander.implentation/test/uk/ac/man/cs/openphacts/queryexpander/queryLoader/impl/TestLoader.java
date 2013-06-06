/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.openphacts.queryexpander.queryLoader.impl;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.bridgedb.IDMapperException;
import org.bridgedb.sql.SQLUriMapper;
import org.bridgedb.uri.loader.LinksetListener;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.utils.StoreType;
import org.openrdf.OpenRDFException;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

/**
 *
 * @author Christian
 */
public abstract class TestLoader {

    static final Logger logger = Logger.getLogger(TestLoader.class);
 
    private static LinksetListener instance;
    static final String MAIN_JUSTIFCATION = "http://www.w3.org/2000/01/rdf-schema#isDefinedBy";
    static final String LENS_JUSTIFCATION = "http://www.bridgedb.org/test#testJustification";
    static final URI LINK_PREDICATE = new URIImpl("http://www.w3.org/2004/02/skos/core#exactMatch");
     
    private static void loadFile(String fileName) throws BridgeDBException{
        File file = new File(fileName);
        instance.parse(file, LINK_PREDICATE, MAIN_JUSTIFCATION);
    }

    //It is recommended to use the IMS method rather than this one.
    public static void LoadTestData() throws IDMapperException, IOException, OpenRDFException  {
        SQLUriMapper uriListener = SQLUriMapper.factory(true, StoreType.TEST);
        instance = new LinksetListener(uriListener);

        loadFile("../query.expander.implentation/test-data/cw-cs.ttl");
        loadFile("../query.expander.implentation/test-data/cw-cm.ttl");
        loadFile("../query.expander.implentation/test-data/cw-dd.ttl");
        loadFile("../query.expander.implentation/test-data/cw-ct.ttl");
        loadFile("../query.expander.implentation/test-data/cw-dt.ttl");     
    }

}
