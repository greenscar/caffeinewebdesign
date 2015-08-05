import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ConnectionPool.*;
/*
 * This servlet acts as a cache for domain values.
 * In the Catalog Exceptionit caches the Category Names and Codes
 *      preventing excessive DB queries.
 */
public class CatalogDomainServlet extends HttpServlet {
    
    
    public void init(ServletConfig config) throws ServletException {
        System.err.println("CatalogDomainServlet.init()");
        super.init(config);
        initializeDomains();
        System.err.println("END CatalogDomainServlet.init()");
    }
    /*
     * Get a reference to the DB ConnectionPool from the ServletContext
     * Perform a query getting all of the Category values in the DB
     * Store these values in a Vector and place it in the ServletContext
     *      keyed by the string DOMAIN_LIST
     */
    private void initializeDomains() throws ServletException{
        System.err.println("CatalogDomainServlet.initializeDomains()");
        Connection con = null;
        ConnectionPool pool = null;
        Vector categories = new Vector(8);
        Category category = null;
        try{
            //Get a reference to the ConnectionPool from the Global ServletContext
            System.err.println("Setting context.");
            ServletContext context = getServletContext();
            System.err.println("Context set complete");
            //Get a connection from the ConnectionPool
            if(context == null)
                throw new ServletException("Could not get a reference to the ServletContext");
            System.err.println("Setting pool");
            pool = (ConnectionPool)context.getAttribute("CONNECTION_POOL");
            System.err.println("Pool set complete.");
            if(pool == null){
                //Sleep for 5 secs then try again.
                try{
                    Thread.sleep(5000);
                    pool = (ConnectionPool)context.getAttribute("CONNECTION_POOL");
                }catch(Exception e){
                    throw new ServletException("Exception trying to set pool: " + e.getMessage());
                }
                if(pool == null){
                    //Still could not get a reference.
                    throw new ServletException("Could not get a reference to the CONNECTION_POOL");
                }
            }
            con = pool.getConnection();
            if(con == null){
                throw new ServletException("Could not get a reference Connection");
            }
            else{
                //Create the statement
                Statement stmt = con.createStatement();
                System.err.println("stmt = " + stmt.toString());
                //SELECT DATA FROM the Categories Table
                ResultSet rs = stmt.executeQuery("SELECT * FROM Categories");
                System.err.println("rs.toString() = " + rs.toString());
                //Iterate over the ResultSet
                while(rs.next()){
                    category = new Category();
                    category.setId(rs.getInt("category_id"));
                    category.setName(rs.getString("category_name"));
                    // Add the resulting category to the Vector
                    categories.addElement(category);
                }
                //Close the ResultSet
                rs.close();
                
                /*
                 * Once the domain list is Initialized, add it to the Global
                 *      ServletContext. 
                 * This makes it available to other servlets using the same 
                 *      ServletContext.
                 */
                context.setAttribute("DOMAIN_LIST", categories);
                System.err.println("DOMAIN_LIST initialized.");
            }
        }catch(SQLException e){
            System.err.println("CatalogDomainServlet.initializeDomains() SQLException: " + e.getMessage());
        }catch(Exception e){
            System.err.println("CatalogDomainServlet.initializeDomains() Exception: " + e.getMessage());
        }finally{
            pool.releaseConnection(con);
            System.err.println("END CatalogDomainServlet.initializeDomains()");
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>CatalogServlet</title>");
        out.println("</head>");
        out.println("<body>");
         
        //Get reference to ServletContext
        ServletContext context = getServletContext();
        //Get reference to the DOMAIN_LIST Vector
        Vector categories = (Vector)context.getAttribute("DOMAIN_LIST");
        
        //Iterate over the list of categories
        for(int x = 0; x < categories.size(); x++){
            Category category = (Category)categories.elementAt(x);
            out.println(category.getId() + "-->" + category.getName()+"<BR>");
        }
        out.println("</body></html>");
        out.close();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public void destroy() {
        ServletContext context = getServletContext();
        Vector categories = (Vector)context.getAttribute("DOMAIN_LIST");
        if(categories != null){
            //Empty List
            categories.removeAllElements();
            //Remove the Attribute from the ServletContext
            context.removeAttribute("DOMAIN_LIST");
        }
        else{
            System.err.println("Couldn't get a reference to DOMAIN_LIST");
        }
    }
    
    public String getServletInfo() {
        return "CatalogDomainServlet Info";
    }
    
}
