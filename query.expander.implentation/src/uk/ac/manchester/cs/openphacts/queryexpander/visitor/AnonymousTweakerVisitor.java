/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.manchester.cs.openphacts.queryexpander.visitor;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;

/**
 * Tweaks the anonymous values given to variables in inner queries which are not passed out.
 * As these are given UUIDs they are always different.
 * This class removes the UUID
 * @author Christian
 */
public class AnonymousTweakerVisitor extends QueryModelVisitorBase<MalformedQueryException>{
        
    @Override
    public void meet(Var var) {
        if (var.hasValue()) return;
        if (!(var.isAnonymous())) return;
        String name = var.getName();
        if (name.length() < 30) return;
        if (!(name.contains("-"))) return;
        String actualName = name.substring(0, name.indexOf('-'));
        String tail = name.substring(name.indexOf('-'));
        //Check if it ends with a UUID
        if (tail.matches("\\-\\w+\\-\\w+\\-\\w+\\-\\w+\\-\\w+")){
            var.setName(actualName + "-" + actualName);
        }
    }
}
