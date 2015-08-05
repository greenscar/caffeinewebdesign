import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import HTML.*;
/*
 * CatalogServlet is a base class for all our servlets with visual responses.
 * This abstract class defines a single abstract method called buildClientArea.
 */
public abstract class CatalogServlet extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        System.err.println("CatalogServlet.init()");
        super.init(config);
    }
    
    /*
     * buildTopTable builds a table that acts as a container for the LogoCell,
     *      SearchCell, and BasketCell Objects.
     */
    private HTMLTable buildTopTable(HttpServletRequest request){
        System.err.println("CatalogServlet.buildTopTable()");
        HTMLTable table = new HTMLTable();
        table.setWidth(100);
        HTMLTableRow row = new HTMLTableRow();
        
        // Create and add the LogoCell
        LogoCell logo_cell = new LogoCell();
        logo_cell.setAlignment(HTMLObject.CENTER);
        row.addObject(logo_cell);
        
        // Create and add the SearchCell
        SearchCell search_cell = new SearchCell();
        search_cell.setAlignment(HTMLObject.CENTER);
        row.addObject(search_cell);
        
        // Create and add the BasketCell
        row.addObject(new BasketCell(request));
        table.addObject(row);
        return table;
    }
    
    /*
     * buildBottomTable builds a table that acts as a container for the 
     *      NavigationTable and Client Area Objects.
     */
    private HTMLTable buildBottomTable(HttpServletRequest request) throws Exception{
        System.err.println("CatalogServlet.buildBottomTable()");
        HTMLTable table = new HTMLTable();
        table.setVerticalAlign(HTMLObject.TOP);
        table.setHeight(500);
        HTMLTableRow row = new HTMLTableRow();
        
        // Create and add the NavigationTable
        HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
        cell.setVerticalAlign(HTMLObject.TOP);
        cell.setHorizontalAlign(HTMLObject.LEFT);
        cell.addObject(new NavigationTable(getServletContext()));
        
        //Call the child class's buildClientArea method, which returns 
        //  an HTMLTable object to be added to the bottom table
        cell.addObject(buildClientArea(request));
        row.addObject(cell);
        table.addObject(row);
        return table;
    }
    
    /*
     * This is the abstrac method that must be implemented by all children
     */
    public abstract HTMLTable buildClientArea(HttpServletRequest request) throws Exception;
    
    /*
     * This method defines the framework for all child classes
     */
    public final HTMLDocument processRequest(HttpServletRequest request) throws Exception{
        System.err.println("CatalogServlet.processRequest()");
        // Create the HTMLDocument
        HTMLDocument doc = new HTMLDocument("Sam's Online Video Store");
        HTMLTable table = new HTMLTable();
        HTMLTableRow row = new HTMLTableRow();
        HTMLTableCell cell = new HTMLTableCell();
        
        // Build the Top Table
        cell.addObject(buildTopTable(request));
        row.addObject(cell);
        table.addObject(row);
        
        //Build the Bottom Table
        row = new HTMLTableRow();
        cell = new HTMLTableCell(HTMLTableCell.DATA);
        cell.addObject(buildBottomTable(request));
        row.addObject(cell);
        table.addObject(row);
        doc.addObject(table);
        return doc;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.err.println("CatalogServlet.doGet()");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            /*
             * Send the request through the processRequest() method, which
             *      returns an HTMDocument. The returned HTMLDocument.toHTML()
             *      method is called returning a String representing the HTML text
             */
            out.println(processRequest(request).toHTML());
        }catch(Exception e){
            throw new ServletException(e.getMessage());
        }
        out.close();
    }
}
