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
    public DOMEcho(){
       // Make a nice border
       EmptyBorder eb = new EmptyBorder(5,5,5,5);
       BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);
       CompoundBorder cb = new CompoundBorder(eb,bb);
       this.setBorder(new CompoundBorder(cb,eb));
       
       // Create an empty tree 
       // Put it a JScrollPane so users can see its contents as it gets large
       // Set up the tree
       JTree tree = new JTree(new DomToTreeModelAdapter()); 
       // Build left-side view
       JScrollPane treeView = new JScrollPane(tree);
       treeView.setPreferredSize(  
       new Dimension( leftWidth, windowHeight ));
       
       // create a non-editable JEditPane 
       // It will eventually hold the contents pointed to by selected JTree node
       // Build right-side view
       JEditorPane htmlPane = new JEditorPane("text/html","");
       htmlPane.setEditable(false);
       JScrollPane htmlView = new JScrollPane(htmlPane);
       htmlView.setPreferredSize( new Dimension( rightWidth, windowHeight ));
       
       // Create a JSplitPane to hold the 
       // left-side JTree and the right-side JEditorPane
       // Build split-pane view
       JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                                              treeView,
                                              htmlView );
       splitPane.setContinuousLayout( true );
       splitPane.setDividerLocation( leftWidth );
       splitPane.setPreferredSize( new Dimension( windowWidth + 10, windowHeight+10 ));
       
       // Specify the layout for the panel and add the split pane
       this.setLayout(new BorderLayout());
       this.add("Center", splitPane );

    }
    static org.w3c.dom.Document document;
    // Global value so it can be ref'd by the tree-adapter
    static final int windowHeight = 460;
    static final int leftWidth = 300;
    static final int rightWidth = 340;
    static final int windowWidth = leftWidth + rightWidth; 
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
            makeFrame();
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
    public static void makeFrame(){
        // Set up a GUI framework
        JFrame frame = new JFrame("DOM Echo");
        frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        // Set up the tree, the views, and display it all
        final DOMEcho echoPanel = new DOMEcho();
        frame.getContentPane().add("Center", echoPanel );
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = windowWidth + 10;
        int h = windowHeight + 10;
        frame.setLocation(screenSize.width/3 - w/2, screenSize.height/2 - h/2);
        frame.setSize(w, h);
        frame.setVisible(true);
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
    //This adapter converts the current Document (a DOM) into a JTree model. 
    public class DomToTreeModelAdapter implements javax.swing.tree.TreeModel 
    {
      // Basic TreeModel operations
      //getRoot method returns the root node of the DOM, wrapped as an AdapterNode object
      public Object  getRoot() {
        //System.err.println("Returning root: " +document);
        return new AdapterNode(document);
      }
      public boolean isLeaf(Object aNode) {
        // Determines whether the icon shows up to the left.
        // Return true for any node with no children
        AdapterNode node = (AdapterNode) aNode;
        if (node.childCount() > 0) return false;
        return true;
      }
      public int     getChildCount(Object parent) {
        AdapterNode node = (AdapterNode) parent;
        return node.childCount();
      }
      public Object  getChild(Object parent, int index) {
        AdapterNode node = (AdapterNode) parent;
        return node.child(index);
      }
      public int     getIndexOfChild(Object parent, Object child) {
        AdapterNode node = (AdapterNode) parent;
        return node.index((AdapterNode) child);
      }
      public void    valueForPathChanged(TreePath path, Object newValue) {
        // Null. We won't be making changes in the GUI
        // If we did, we would ensure the new value was really new
        // and then fire a TreeNodesChanged event.
      }
      private Vector listenerList = new Vector();
      public void addTreeModelListener( TreeModelListener listener ) {
        if ( listener != null && ! listenerList.contains( listener ) ) {
           listenerList.addElement( listener );
        }
      }
      public void removeTreeModelListener( TreeModelListener listener ) {
        if ( listener != null ) {
           listenerList.removeElement( listener );
        }
      }

    } // DomToTreeModelAdapter
} //DOMEcho
