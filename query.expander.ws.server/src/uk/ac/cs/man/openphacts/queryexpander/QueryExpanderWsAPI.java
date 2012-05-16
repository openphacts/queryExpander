/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

/**
 *
 * @author Christian
 */
public interface QueryExpanderWsAPI {
    
    public ExpanderBean expand(String query) throws QueryExpansionException;

}
