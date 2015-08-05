//XML components
import javax.xml.parsers.*;
import javax.xml.parsers.
import org.xml.sax.*;
import org.w3c.dom.*;
//Input & Output
import java.io.*;
import java.util.*;
public class DOMTest{
    public DOMTest(){

    }
    static org.w3c.dom.Document document;
    // Global value so it can be ref'd by the tree-adapter
    public static void main(String[] args) {   
        //args[0] = "c:/Inetpub/Tomcat 4.1/webapps/testing/book-order.xml";
        File f = new File(args[0]);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        try{
            long timeStamp = f.lastModified();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse( new File(args[0]) );
            printDom();
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
    public static void printDom(){
        // Set up the tree, the views, and display it all
        final DOMTest echoPanel = new DOMTest();
        echoPanel.toString();
    } // makeFrame
    // An array of names for DOM node-types
    // (Array indexes = nodeType() values.)
    static final String[] typeName = {
        "none",
        "Element",
        "Attr",
        "Text",
        "CDATA",
        "EntityRef",
        "Entity",
        "ProcInstr",
        "Comment",
        "Document",
        "DocType",
        "DocFragment",
        "Notation",
    };
    // This class declares a variable to hold the DOM node, 
    //   and requires it to be specified as a constructor argument. 
    // Define the AdapterNode wrapper for DOM nodes: 
    public class AdapterNode{ 
      org.w3c.dom.Node domNode;

      // Construct an Adapter node from a DOM node
      public AdapterNode(org.w3c.dom.Node node) {
        domNode = node;
      }

      // Return a string that identifies this node in the tree
      // *** Refer to table at top of org.w3c.dom.Node ***
      public String toString() {
        String s = typeName[domNode.getNodeType()];
        String nodeName = domNode.getNodeName();
        if (! nodeName.startsWith("#")) {
           s += ": " + nodeName;
        }
        if (domNode.getNodeValue() != null) {
           if (s.startsWith("ProcInstr")) 
              s += ", "; 
           else 
              s += ": ";
           // Trim the value to get rid of NL's at the front
           String t = domNode.getNodeValue().trim();
           int x = t.indexOf("\n");
           if (x >= 0) t = t.substring(0, x);
           s += t;
        }
        
        return s;
      }
      public int index(AdapterNode child) {
        //System.err.println("Looking for index of " + child);
        int count = childCount();
        for (int i=0; i<count; i++) {
          AdapterNode n = this.child(i);
          if (child == n) return i;
        }
        return -1; // Should never get here.
      }

      public AdapterNode child(int searchIndex) {
        //Note: JTree index is zero-based. 
        org.w3c.dom.Node node = domNode.getChildNodes().item(searchIndex);
        return new AdapterNode(node); 
      }

      public int childCount() {
          return domNode.getChildNodes().getLength();  
      }
    } // AdapterNode
} //DOMEcho
