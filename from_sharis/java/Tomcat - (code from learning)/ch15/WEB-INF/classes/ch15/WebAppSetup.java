package ch15;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.*;
import java.util.*;
import com.javaexchange.dbConnectionBroker.*;

public class WebAppSetup extends HttpServlet {
    DbConnectionBroker dbPool;
    HttpLinksCacheBean linksCache;
    
    protected void createDbPool() throws ServletException{
        if(dbPool == null){
            try{
                dbPool = new DbConnectionBroker(
                    getInitParameter("DriverName"),
                    getInitParameter("DriverType")
                    + "://"
                    + getInitParameter("Host")
                    + ":"
                    + Integer.parseInt(getInitParameter("Port"))
                    + "/"
                    + getInitParameter("DbName"),
                      getInitParameter("UserID"),
                      getInitParameter("Password"),
                      5, 50, "dbconn.log", 1.0);
            }catch (IOException e){
                throw new ServletException(e);
            }
        }
        if(dbPool != null){
            getServletContext().setAttribute("dbPool", dbPool);
        }
        else{
            throw new ServletException("ch15: Unable to create dbPool");
        }
    }
    
    protected void createMenus() throws ServletException{
        if (dbPool == null)
            throw new ServletException("No dbPool, can't create menus");
        if (linksCache == null)
            linksCache = new HttpLinksCacheBean();
        linksCache.init(dbPool);
        getServletContext().setAttribute("linksCache", linksCache);
    }
    public void destroy(){
        super.destroy();
        try{
            //give the db pool 10 secs to shut down.
            dbPool.destroy(10000);
        }catch(SQLException e){
            System.out.println("One or more database pool conns left open.");
        }
    }
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Admin</title>");
        out.println("</head>");
        out.println("<body>");
        String cmd = request.getParameter("cmd");
        if(cmd != null){
            try{
                if (cmd.equals("refreshmenus")){
                    createMenus();
                    out.println("Admin action: Refresh Menus");
                }
            }catch (Exception e){
                System.out.println("Error initializing the web app " + e);
            }
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        try{
            createDbPool();
            createMenus();
        }catch(ServletException e){
            throw new ServletException(e);
        }
    }   
}
