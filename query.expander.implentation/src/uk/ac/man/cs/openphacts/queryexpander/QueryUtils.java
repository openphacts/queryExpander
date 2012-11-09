package uk.ac.man.cs.openphacts.queryexpander;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bridgedb.utils.Reporter;
import org.openrdf.model.URI;
import org.openrdf.query.Dataset;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.helpers.QueryModelTreePrinter;
import org.openrdf.query.impl.DatasetImpl;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.sparql.SPARQLParser;
import uk.ac.man.cs.openphacts.queryexpander.visitor.AnonymousTweakerVisitor;

/**
 * Provides a few static Utility methods.
 * 
 * @author Christian
 */
public class QueryUtils {
   
    static SPARQLParser parser = new SPARQLParser();
    
    /**
     * Convenience method for converting a String into a Tuple Expresions.
     * 
     * Based purely on openrdf code.
     * 
     * @param querySting text of query to parse.
     * @return Tuple Expression representation of this query 
     * @throws MalformedQueryException If openrdf can not read the query.
     */
    public static TupleExpr queryStringToTupleExpr(String querySting) throws MalformedQueryException{                 
        ParsedQuery parsedQuery = parser.parseQuery(querySting, null); 
        return parsedQuery.getTupleExpr();
    }
    
    /**
     * Compares two Datasets. Allows for both to be null otherwise uses Equals();
     * <p>
     * If used to confirm two datasets are not equal, the suggestion is to set verbose to false.
     * 
     * @param dataset1
     * @param dataset2
     * @param verbose If true outputs messages if the datasets are not equal.
     * @return True if both Datasets are nulls or dataset1.equals(dataset2), otherwise false.
     */
    public static boolean compare(Dataset  dataset1, Dataset  dataset2, boolean verbose) {
        if (dataset1 == null){
            if (dataset2 == null){
                return true;
            } else {
                if (verbose){
                    System.out.println("Dataset 1 is null while Dataset 2 is:");
                    System.out.println(dataset2);
                }
                return false;
            }         
        } else {
            if (dataset2 == null){
                if (verbose){
                    System.out.println("Dataset 2 is null while Dataset 1 is:");
                    System.out.println(dataset1);
                }
                return false;
            }         
        }
        Set<URI> defaultGraphs1 = dataset1.getDefaultGraphs();
        Set<URI> defaultGraphs2 = dataset2.getDefaultGraphs();
        //ystem.out.println("defaultGraphs");
        //ystem.out.println(defaultGraphs1);
        //ystem.out.println(defaultGraphs2);
        if (!(defaultGraphs1.equals(defaultGraphs2))){
            if (verbose){
               System.out.println("*** defaultGraphs do not match ***");
            }
            return false;
        }
        Set<URI> namedGraphs1 = dataset1.getNamedGraphs();
        Set<URI> namedGraphs2 = dataset2.getNamedGraphs();
        //ystem.out.println("namedGraphs");
        //ystem.out.println(namedGraphs1);
        //ystem.out.println(namedGraphs2);
        if (!(namedGraphs1.equals(namedGraphs2))){
            if (verbose){
                System.out.println("*** namedGraphs do not match ***");
            }
            return false;
        }
        return true;
    }
    
    /**
     * Compares two queryStrings to see if they generate the same TupleExpr.
     * <p>
     * This allows query Strings that differ only on whitespacing to be considered equal.
     * <p>
     * It may also allow some queries with statments in a slightly different order to be considered equal,
     *    but only if the openrdf parse would switch the order in one of the queries.
     * <p>
     * However a false does not mean that the queries can not be semantically equivellant.
     * <p>
     * Based on the implementation of TupleExpr's and its Children's Equals methods, 
     * so supports query not yet convertable from tupleExpr to string.
     * 
     * @param query1 A Sparql query as a String
     * @param query2 Another Sparql query as a String
     * @param verbose If true outputs messages if the datasets are not equal.
     * @return True if and only if the two queries generate equals TupleExpr. 
     * @throws MalformedQueryException 
     */
    public static boolean sameTupleExpr(String query1, String query2, boolean verbose, String text) throws MalformedQueryException{
        ParsedQuery parsedQuery1 = parser.parseQuery(query1, null); 
        TupleExpr tupleExpr1 =  parsedQuery1.getTupleExpr();
        ParsedQuery parsedQuery2 = parser.parseQuery(query2, null); 
        TupleExpr tupleExpr2 =  parsedQuery2.getTupleExpr();
        boolean equal = tupleExpr1.equals(tupleExpr2);
        if (!equal){
            //Lets check for and remove any UUIDs in Anonymous vars
            AnonymousTweakerVisitor tweaker = new AnonymousTweakerVisitor();
            tupleExpr1.visit(tweaker);
            tupleExpr2.visit(tweaker);
        }
        equal = tupleExpr1.equals(tupleExpr2);
        if (equal){
            Dataset  dataset1 = parsedQuery1.getDataset();
            Dataset  dataset2 = parsedQuery2.getDataset();
            return compare(dataset1, dataset2, verbose);
        } else {
            String tree1 = QueryModelTreePrinter.printTree(tupleExpr1);
            String tree2 = QueryModelTreePrinter.printTree(tupleExpr2);
            if (verbose){
                Reporter.println("*** Queries do not match ***");
                Reporter.println(query1);
                Reporter.println(tree1);
                Reporter.println("*");
                Reporter.println(query2);
                Reporter.println(tree2);
            }
            int len = tree1.length();
            if (tree2.length() < len) len = tree2.length();
            int line = 0;
            for (int pos = 0; pos < len; pos ++){
                //ystem.out.println (pos + " " + tree1.charAt(pos) + tree2.charAt(pos));
                if (tree1.charAt(pos) != tree2.charAt(pos)) {
                    if (verbose){
                        Reporter.println(pos + " in line " + line);
                    }
                    int start = pos -20;
                    if (start < 0) start = 0;
                    int end = pos + 20;
                    if (pos > len) pos = len;
                    if (verbose){
                        Reporter.println(""+tree1.length() + "  " + start + "  " + end);
                        Reporter.println(tree1.subSequence(start, end).toString());
                        Reporter.println(tree2.subSequence(start, end).toString());
                        Reporter.println (text);
                    }
                    return false;
                }
                if (tree1.charAt(pos) == '\n') {
                    line++;
                }
            }
            if (tree1.length() != tree2.length()){
                if (verbose){
                    Reporter.println ("Length difference");
                }
            } else {
                if (verbose){
                    Reporter.println("No printable diff found");
                }
                //How am I ever going to find that.
                return true;
            }
        }
        Reporter.println (text);
        return false;
    }
    
    /**
     * Compares two queryStrings to see if they generate the same TupleExpr.
     * <p>
     * This allows query Strings that differ only on whitespacing to be considered equal.
     * <p>
     * It may also allow some queries with statments in a slightly different order to be considered equal,
     *    but only if the openrdf parse would switch the order in one of the queries.
     * <p>
     * However a false does not mean that the queries can not be semantically equivellant.
     * <p>
     * Based on the implementation of TupleExpr's and its Children's Equals methods, 
     * so supports query not yet convertable from tupleExpr to string.
     * 
     * @param query1 A Sparql query as a String
     * @param option2 A Sparql query as a String
     * @param option2 Another Sparql query as a String
     * @param verbose If true outputs messages if the datasets are not equal.
     * @return True if and only if the query generate a TupleExpr equal to one of the two options. 
     * @throws MalformedQueryException 
     */
    public static boolean sameTupleExpr(String query, String option1, String option2, boolean verbose) throws MalformedQueryException{
        ParsedQuery parsedQuery = parser.parseQuery(query, null); 
        TupleExpr tupleExpr =  parsedQuery.getTupleExpr();
        ParsedQuery parsedQuery1 = parser.parseQuery(option1, null); 
        TupleExpr tupleExpr1 =  parsedQuery1.getTupleExpr();
        if (tupleExpr1.equals(tupleExpr)){
            Dataset  dataset1 = parsedQuery.getDataset();
            Dataset  dataset2 = parsedQuery1.getDataset();
            return compare(dataset1, dataset2, verbose);
        }
        ParsedQuery parsedQuery2 = parser.parseQuery(option2, null); 
        TupleExpr tupleExpr2 =  parsedQuery2.getTupleExpr();
        //if (compare(tupleExpr1, tupleExpr2)){
        if ((tupleExpr2.equals(tupleExpr))){
            Dataset  dataset1 = parsedQuery1.getDataset();
            Dataset  dataset2 = parsedQuery2.getDataset();
            return compare(dataset1, dataset2, verbose);
        } else {
            if (verbose){
                System.out.println("*** Queries do not match ***");
                System.out.println(query);
                //System.out.println(QueryModelTreePrinter.printTree(tupleExpr));
                System.out.println("*");
                System.out.println(option1);
                System.out.println("nor");
                System.out.println(option2);
                //System.out.println(QueryModelTreePrinter.printTree(tupleExpr1));
            }
            return false;
        }
    }

    /**
     * Compares two queryStrings to see if they generate the same TupleExpr.
     * <p>
     * This allows query Strings that differ only on whitespacing to be considered equal.
     * <p>
     * It may also allow some queries with statments in a slightly different order to be considered equal,
     *    but only if the openrdf parse would switch the order in one of the queries.
     * <p>
     * However a false does not mean that the queries can not be semantically equivellant.
     * <p>
     * Based on the implementation of TupleExpr's and its Children's Equals methods, 
     * so supports query not yet convertable from tupleExpr to string.
     * 
     * @param query1 A Sparql query as a String
     * @param query2 Another Sparql query as a String
     * @param verbose (ALWAYS TRUE) If true outputs messages if the datasets are not equal.
     * @return True if and only if the two queries generate equals TupleExpr. 
     * @throws MalformedQueryException 
     */
    public static boolean sameTupleExpr(String query1, String query2, String text) throws MalformedQueryException{
        return sameTupleExpr(query1, query2, true, text);
    }
    
 
}
