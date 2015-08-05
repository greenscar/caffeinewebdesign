import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import HTML.*;
import ConnectionPool.*;

/*
 * This servlet displays a list of titles from a category or title search.
 *      Each of the titles in stock present the user with the option to order
 *      them. This servlet inherits its doGet() method from CatalogServlet
 */
public class TitleListServlet extends CatalogServlet {
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    /*
     * This method takes the passed in category_id and performs a DB lookup
     *      returning the ResultSet from the query
     */
    private ResultSet selectByCategory(int category_id){
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
                String query = "SELECT * FROM Titles WHERE category = " + category_id;
                System.err.println("QUERY = " + query);
                rs = stmt.executeQuery(query);
            }
        }catch(SQLException e){
            System.err.println("TitleListServlet.selectByCategory() SQLException: " + e.getMessage());
        }catch(Exception e){
            System.err.println("TitleListServlet.selectByCategory() Exception: " + e.getMessage());
        }finally{
            //Release the connection
            pool.releaseConnection(con);
        }
        return rs;
    }
    
    /*
     * selectBySearchString takes the passed in search_string and performs
     *      a DB lookup returning the ResultSet from the query
     */
    private ResultSet selectBySearchString(String search_string){
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
                String query = "SELECT * FROM Titles WHERE title_name = \'" 
                        + search_string + "\'";
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
     * buildClientArea implements the parent's abstract method. It represents
     *      the client area of the browser window.
     */
    public HTMLTable buildClientArea(HttpServletRequest request) throws Exception {
        //Create the Table to return as the client area
        HTMLTable table = new HTMLTable();
        ResultSet rs = null;
        table.setHorizontalAlign(HTMLObject.LEFT);
        table.setVerticalAlign(HTMLObject.TOP);
        table.setWidthByPixel(650);
        
        //Check for the parameter "category_id"
        String category_id = request.getParameter("category_id");
        // If category_id is found, perform the category_id select
        if(category_id != null){
            rs = selectByCategory(new Integer(category_id).intValue());
        }
        // Else perform the title select
        else{
            String search_string = request.getParameter("search_string");
            System.err.println("TitleListServlet line 128 search_string = " + search_string);
            rs = selectBySearchString(search_string);
        }
        //Iterate over the ResultSet
        try{
            if(rs != null){
                // This value is only used to switch the background of the Title List
                boolean flag = true;
                while(rs.next()){
                    HTMLTableRow row = new HTMLTableRow();
                    //Switch every other row to lightgrey
                    if(flag){
                        row.setBackground("lightgrey");
                        flag = false;
                    }
                    else
                        flag = true;
                    // Get the values from the ResultSet
                    String id = new Integer(rs.getInt("title_id")).toString();
                    String name = rs.getString("title_name");
                    String price = new Double(rs.getDouble("price")).toString();
                    int quantity = rs.getInt("quantity");
                    
                    // Add the name value to the table cell
                    HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(name));
                    row.addObject(cell);
                    
                    // Add the price value to the table cell
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.setWidth(55);
                    cell.addObject(new HTMLText(price));
                    row.addObject(cell);
                    /*
                     * If quantity is 0, display "Out of Stock"
                     * Else create a link to the AddMovieSerlet with the ID of
                     *      the selected movie
                     */
                    if(quantity == 0){
                        cell = new HTMLTableCell(HTMLTableCell.DATA);
                        cell.setWidth(145);
                        cell.addObject(new HTMLText("Out of Stock"));
                        row.addObject(cell);
                    }
                    else{
                        cell = new HTMLTableCell(HTMLTableCell.DATA);
                        cell.setWidth(145);
                        String current_url = request.getServletPath() + "?"
                                + request.getQueryString();
                        HTMLLink hl = new HTMLLink("/Movies/AddMovieServlet?id="
                            + id + "&price=" + price + "&trans=" 
                            + current_url, new HTMLText("Buy"));
                        cell.addObject(hl);
                        row.addObject(cell);
                    }
                    table.addObject(row);
                }
            }
            else{
                HTMLTableRow row = new HTMLTableRow();
                HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
                
                // The result was empty, probably an error.
                cell.addObject(new HTMLText("The RS was null. Please contact tech support."));
                row.addObject(cell);
                table.addObject(row);
            }
        }finally{
            // Close the ResultSet
            if(rs != null){
                rs.close();
            }
        }
        return table;
    }
    public String getServletInfo() {
        return "TitleListServlet description";
    }
    
    
    
}
