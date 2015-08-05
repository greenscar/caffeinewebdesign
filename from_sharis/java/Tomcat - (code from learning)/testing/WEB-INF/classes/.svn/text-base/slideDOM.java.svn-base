/*
 * slideDOM.java
 * This is the layout of a JAXP file
 * Created on April 30, 2003, 11:41 AM
 */
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.*;

public class slideDOM {
    static org.w3c.dom.Document document;
    public static void main(String[] args) {
        System.out.println("args length = " + args.length);
        File f = new File(args[0]);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        try{
            long timeStamp = f.lastModified();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse( new File(args[0]) );
        }catch(ParserConfigurationException pce){
            System.out.println("Constructor threw " + pce.toString());
        }catch(SAXParseException spe){
            StringBuffer sb = new StringBuffer(spe.toString());
            System.out.print(sb.toString());
        }catch(SAXException se){
            System.out.println("Constructor threw " + se.toString());
            se.printStackTrace(System.out);
        }catch(IOException ie){
            System.out.println("Constructor threw " + ie.toString() + " trying to read " + f.getAbsolutePath());
        }
    }
    
}
