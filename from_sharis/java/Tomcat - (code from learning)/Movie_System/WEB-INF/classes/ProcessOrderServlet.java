import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import HTML.*;
/*
 * ProcessOrderServlet.java
 * Represents the final step in the order process.
 * It takes a request, empties the contents of the basket and responds with
 *      a simple thank you message. This is where you would add the appropriate
 *      functionality to handle your orders. 
 * This could be sending a message to fulfillment or just logging it in a DB
 *      for later processing.
 * Created on June 4, 2003, 11:31 AM
 */
public class ProcessOrderServlet extends CatalogServlet {
    
    public void init(ServletConfig cfg) throws ServletException{
        super.init(cfg);
    }
    private void emptyBasket(HttpServletRequest request){
        // Get/Create a reference to the HttpSession object.
        HttpSession session = request.getSession(true);
        if(session != null){
            Vector basket = (Vector)session.getValue("basket");
            if(basket != null)
                basket.removeAllElements();
        }
    }
    public HTMLTable buildClientArea(HttpServletRequest request) throws Exception{
        // Empty the basket so future requests will begin with an empty basket
        emptyBasket(request);
        
        // Get ready to check out.
        HttpSession session = request.getSession(true);
        Vector basket = null;
        
        // Create a table container for the client area
        HTMLTable table = new HTMLTable();
        // Set a fixed width.
        table.setWidthByPixel(400);
        table.setAlignment(HTMLObject.CENTER);
        
        HTMLTableRow row = new HTMLTableRow();
        HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
        //Create and add a thank you message
        HTMLHeading h = new HTMLHeading("Thank you.", HTMLHeading.H3);
        cell.addObject(h);
        row.addObject(cell);
        table.addObject(row);
        return table;
    }
    public String getServletInfo(){
        return "ProcessOrderServlet Info";
    }
}
