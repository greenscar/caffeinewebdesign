import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import ConnectionPool.ConnectionPool;

public class CatalogConnectionPool extends HttpServlet {

  //Initialize global variables
  public void init(ServletConfig config)
    throws ServletException {

    super.init(config);

    // Instantiate the ConnectionPool
    ConnectionPool pool = new ConnectionPool();

    try {

      // Set the JDBC Driver
      pool.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
      pool.setURL("jdbc:odbc:catalog");
      pool.setSize(2);

      // Set the Username
      pool.setUsername("");
      // Set the Password
      pool.setPassword("");

      // Initialize the pool
      pool.initializePool();

      // Once the pool is Initailized, add it to the
      // Global ServletContext.  This makes it available
      // To other servlets using the same ServletContext.
      ServletContext context = getServletContext();
      context.setAttribute("CATALOG_POOL", pool);
      System.err.println("ADDED CATALOG_POOL");
    }
    catch (Exception e) {

      System.err.println(e.getMessage());
    }
  }

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request,
    HttpServletResponse response)
      throws ServletException, IOException {

    // Set the response content-type
    response.setContentType("text/html");
    // get the Writer object
    PrintWriter out = response.getWriter();
    out.println("This Servlet does not service requests!");
    out.close();
  }

  public void destroy() {

    // Access the ServletContext using the getAttribute()
    // method, which returns a reference to the ConnectionPool.
    ServletContext context = getServletContext();
    ConnectionPool pool =
        (ConnectionPool)
        context.getAttribute("CATALOG_POOL");

    if ( pool != null ) {

      // empty the pool
      pool.emptyPool();
      // Remove the Attribute from the ServletContext
      context.removeAttribute("CATALOG_POOL");
    }
    else {

      System.err.println("Could not get a reference to Pool!");
    }
  }
}
