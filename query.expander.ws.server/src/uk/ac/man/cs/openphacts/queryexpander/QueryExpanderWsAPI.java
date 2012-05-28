/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander;

import uk.ac.man.cs.openphacts.queryexpander.ExpanderBean;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpansionException;

/**
 *
 * @author Christian
 */
public interface QueryExpanderWsAPI {
    
    public ExpanderBean expand(String query) throws QueryExpansionException;

}
