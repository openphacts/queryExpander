/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cs.man.openphacts.queryexpander;

/**
 *
 * @author Christian
 */
public interface QueryExpander {
    
    public String expand(String originalQuery, boolean verbose) throws Exception;
}
