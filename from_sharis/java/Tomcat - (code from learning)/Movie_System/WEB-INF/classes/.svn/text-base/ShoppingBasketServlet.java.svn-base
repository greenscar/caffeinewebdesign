import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import HTML.*;
import ConnectionPool.*;

/*
 * This servlet displays a full list of items in the shopping basket.
 * It takes the title IDs found in the basket and does a DB lookup to
 *      complete the object.
 * It then displays the list of titles. 
 * It also presents a button to complete the order or clear the contents
 *      of the shopping basket.
 * doGet() is inherited from CatalogServlet
 */
public class ShoppingBasketServlet extends CatalogServlet {
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    /*
     * selectByTitleID takes the passed in title_id and performs a DB
     *      lookup returning the ResultSet from the query
     */
    private ResultSet selectByTitleId(int title_id) throws Exception{
        Connection con = null;
        ConnectionPool pool = null;
        ResultSet rs = null;
        try{
            //Get a reference to the ConnectionPool from the Global ServletContext
            ServletContext context = getServletContext();
            //Get a connection from the connection pool
            if(context == null){
                throw new ServletException("Could not get a reference to the ServletContext");
            }
            pool = (ConnectionPool)context.getAttribute("CONNECTION_POOL");
            //Get a connection from the Broker
            if(pool == null){
                throw new ServletException("Could not reference the ConnectionPool");
            }
            con = pool.getConnection();
            if(con == null){
                throw new ServletException("Could not reference a Connection");
            }
            else{
                //Create the statement.
                Statement stmt = con.createStatement();
                
                //Use the created statement.
                String query = "SELECT * FROM Titles WHERE title_id = " + title_id;
                rs = stmt.executeQuery(query);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            //Release the connection
            pool.releaseConnection(con);
        }
        return rs;
    }
        
    /*
     * buildClientArea implement the parent's abstract class.
     * It represents the client area of the browser window.
     */ 
    public HTMLTable buildClientArea(HttpServletRequest request) throws Exception {
        /*
         * The client is getting ready to check out.
         * Get a reference to the HttpSession object
         */
        HttpSession session = request.getSession(true);
        Vector basket = null;
        
        //Create the Table to return as the client area.
        HTMLTable table = new HTMLTable();
        table.setWidthByPixel(400);
        table.setAlignment(HTMLObject.CENTER);
        HTMLHeading h = new HTMLHeading("Shopping Basket Contains", HTMLHeading.H3);
        table.setCaption(h);
        HTMLTableRow row = new HTMLTableRow();
        HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
        if(session != null){
            //Get the shopping basket's contents
            basket = (Vector)session.getValue("basket");
            //Iterate over the basket's contents.
            if(basket != null){
                for(int x=0; x < basket.size();x++){
                    row = new HTMLTableRow();
                    Movie movie = (Movie)basket.elementAt(x);
                    
                    //Get the complete db representation of the Movie based on title_id
                    ResultSet rs = selectByTitleId(movie.getTitleId());
                    if(rs.next())
                        movie.setName(rs.getString("title_name"));
                    
                    //Add the name to the table cell for display
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(movie.getName()));
                    row.addObject(cell);
                    
                    //Add the price to the table cell for display
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    Double price = new Double(movie.getPrice());
                    HTMLText amount = new HTMLText(price.toString());
                    cell.addObject((amount));
                    row.addObject(cell);
                    
                    table.addObject(row);
                }
                // Add a row containing a link to empty the basket
                row = new HTMLTableRow();
                cell = new HTMLTableCell(HTMLTableCell.DATA);
                HTMLText linkText = new HTMLText("Empty Basket");
                HTMLLink hlk = new HTMLLink("/servlet/EmptyBasketServlet", linkText);
                cell.addObject(hlk);
                row.addObject(cell);
                
                //Add a row containing a link to complete the order
                cell = new HTMLTableCell(HTMLTableCell.DATA);
                linkText.setText("Submit Order");
                HTMLLink link = new HTMLLink("/servlet/ProcessOrderServlet", linkText);
                cell.addObject(link);
                row.addObject(cell);
                table.addObject(row);
            }
            else{
                //If the basket was empty, say so
                row = new HTMLTableRow();
                cell.addObject(new HTMLText("No items in basket!"));
                row.addObject(cell);
                table.addObject(row);
            }
        }
        return table;
    }
    public String getServletInfo(){
        return "ShoppingBasketServlet Info";
    }
        
}
