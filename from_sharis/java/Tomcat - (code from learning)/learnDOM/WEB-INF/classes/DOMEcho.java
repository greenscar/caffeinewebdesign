//XML components
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
//Input & Output
import java.io.*;
// GUI components and layouts
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree; 
// For creating a TreeModel
import javax.swing.tree.*;
import javax.swing.event.*;
import java.util.*;
//import the components you need to set up a divided view (JSplitPane) 
import javax.swing.JSplitPane;
//and to display the text of the subelements (JEditorPane):
import javax.swing.JEditorPane; 
// GUI support classes
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter; 
// For creating borders
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder; 

public class DOMEcho extends JPanel {
    public DOMEcho(){}
    static org.w3c.dom.Document document;
    
    public static void main(String[] args) {   
        //args[0] = "c:/Inetpub/Tomcat 4.1/webapps/testing/book-order.xml";
        String xmlFile = "C:/Inetpub/Tomcat_4.1/webapps/learnDOM/users.xml";
        File f = new File(xmlFile);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        try{
            long timeStamp = f.lastModified();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(f);
            Element e = document.getDocumentElement();
            //NodeList nl = e.getElementsByTagName("firstName");
            //printList(document);
            printList(e);
            //makeFrame();
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
    static void printList(Element element){
            System.out.println("start of try");    
            System.out.println(element.getNodeName());
            System.out.println(element.getFirstChild().getNodeName());
            System.out.println("end of try");            
    }
} //DOMEcho
