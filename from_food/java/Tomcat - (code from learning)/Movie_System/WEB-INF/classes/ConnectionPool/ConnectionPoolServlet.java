package ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ConnectionPoolServlet extends HttpServlet{
    
    public void init(ServletConfig cfg) throws ServletException {
        super.init(cfg);
        ConnectionPool pool = new ConnectionPool();
        try{
            pool.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
            pool.setURL("jdbc:odbc:Movie");
            pool.setSize(4);
            pool.setUsername("james");
            pool.setPassword("Z23&*FjhsldZDdxc");
            
            pool.initializePool();
            /*
             * Once the pool is Initialized, add it to the Global 
             *      ServletContext.
             * This makes it available to other servlets using the same
             *      ServletContext.
             */
            ServletContext context= getServletContext();
            context.setAttribute("CONNECTION_POOL", pool);
        }catch(Exception e){
            System.err.println(e.getMessage() + " in ConnectionPoolServlet.init()");
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException{
         // Set the response content-type
         response.setContentType("text/html");
         // Get the writer object
         PrintWriter out = response.getWriter();
         out.println("This Servlet does not service requests!");
         out.close();
    }
    public void destroy(){
        // Access the ServletContext using the getAttribute() method, 
        //      which returns a reference to the ConnectionPool
        ServletContext context = getServletContext();
        ConnectionPool pool = (ConnectionPool)context.getAttribute("CONNECTION_POOL");
        if(pool != null){
            //empty the pool
            pool.emptyPool();
            //Remove the Attribute from the ServletContext
            context.removeAttribute("CONNECTION_POOL");
        }
        else{
            System.err.println("Couldn't get a reference to the Pool in ConnectionPoolServlet.destroy()");
        }
    }
    public String getServletInfo(){
        return "ConnectionPoolServlet Info";
    }        
}
