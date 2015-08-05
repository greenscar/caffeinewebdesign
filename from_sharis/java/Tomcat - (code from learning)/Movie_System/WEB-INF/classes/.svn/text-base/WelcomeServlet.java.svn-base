import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import HTML.*;
/*
 * This servlet is the first step in the order process. 
 * It's the equivalent of the index.html page.
 */
public class WelcomeServlet extends CatalogServlet {
    
    /** Creates a new instance of WelcomeServlet */
    public WelcomeServlet() {
    }
    
    public void init(ServletConfig config) throws ServletException{
        System.err.println("WelcomeServlet.init()");
        super.init(config);
    }
    /*
     * buildClientArea implements the parent's abstract method.
     * It represents the client area of the browser window.
     */
        
    
    public HTMLTable buildClientArea(HttpServletRequest request) throws Exception {
        System.err.println("WelcomeServlet.buildClientArea()");
        //Create the Table to return as the client area
        HTMLTable table = new HTMLTable();
        table.setWidthByPixel(530);
        
        HTMLTableRow row = new HTMLTableRow();
        HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
        cell.setVerticalAlign(HTMLTableCell.TOP);
        cell.setHorizontalAlign(HTMLObject.LEFT);
        
        // Add a simple message welcoming customers to the sight.
        HTMLHeading h = new HTMLHeading("Welcome to Store!", HTMLHeading.H3);
        cell.addObject(h);
        row.addObject(cell);
        table.addObject(row);
        return table;
    }
         
    public String getServletInfo(){
        return "HTML.WelcomeServlet Info";
    }
}
