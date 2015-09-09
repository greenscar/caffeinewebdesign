/*
 * AdminServlet.java
 *
 * Created on June 17, 2004, 1:15 PM
 */

package servlets;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import com.javaexchange.dbConnectionBroker.*;
import users.*;
import vos.*;
import daos.*;
/**
 *
 * @author  jsandlin
 * @version
 */
public class AdminServlet extends HttpServletJXGB {
    Connection conn = null;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void destroy() {
        super.destroy(100000);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //logging.Secretary.clearBlanks();
        logging.Secretary.startFxn("AdminServlet.processRequest()");
        response.setContentType("text/html");
        javax.servlet.http.HttpSession session = request.getSession(true);
        Admin admin = null;
        if(session.getAttribute("admin") != null)
            admin = (Admin)(session.getAttribute("admin"));
        else
        {
            admin = new Admin();
            conn = myBroker.getConnection();
            admin.loadCatalog(conn);
            myBroker.freeConnection(conn);
            session.setAttribute("admin", admin);
        }
        admin.loadCatalog(conn);
        String goTo = "/admin.jsp";
        javax.servlet.RequestDispatcher rd = null;
        rd = getServletConfig().getServletContext().getRequestDispatcher(goTo);
        logging.Secretary.endFxn("AdminServlet.processRequest()");
        rd.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public String getServletInfo() {
        return "Short description";
    }
    
}
