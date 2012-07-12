/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.openphacts.queryexpander.tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import uk.ac.man.cs.openphacts.queryexpander.QueryExpanderImpl;
import uk.ac.man.cs.openphacts.queryexpander.QueryUtils;
import uk.ac.man.cs.openphacts.queryexpander.mapper.DummyIMSMapper;
import uk.ac.man.cs.openphacts.queryexpander.mapper.IMSMapper;

/**
 *
 * @author Christian
 */
public class DropBoxTester {
    
    private static String fileToString(File file) throws IOException, BadLocationException{
        String name = file.getName();
        if (name.endsWith("txt")){
            StringBuilder builder = new StringBuilder();
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            while (buffer.ready()){
                String line = buffer.readLine();
                builder.append(line);
                builder.append (" ");
            }
            return builder.toString();
        }
        if (name.endsWith("rtf")){
            FileInputStream stream = new FileInputStream(file);  
            RTFEditorKit kit = new RTFEditorKit();  
            Document doc = kit.createDefaultDocument();  
            kit.read(stream, doc, 0);     
            return doc.getText(0, doc.getLength());  
        }
        throw new IOException("Unexpected file ending" + name);
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println ("ready");
        File test = new File ("C:/Dropbox/OPS-queries/02July/LDA_Expanded/compoundInfo1.rtf");
        //File test = new File ("C:/Dropbox/OPS-queries/15May/Production/compoundInfo.txt");
        String query = fileToString (test);
        IMSMapper imsMapper = new DummyIMSMapper();
        QueryExpanderImpl queryExpander = new QueryExpanderImpl(imsMapper);
        String newQuery = queryExpander.expand(query);
        if (QueryUtils.sameTupleExpr(query, newQuery, true)){
            System.out.println("ok");
        } else {
            System.out.println("error");
        }
    }
}
