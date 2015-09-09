
package servlets;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import logging.Secretary;
import users.Admin;
import java.sql.*;
import htmlConverter.EscapeChars;
import com.javaexchange.dbConnectionBroker.*;
/**
 *
 * @author  jsandlin
 * @version
 */
public class CategoryServlet extends HttpServletJXGB {
    Connection conn = null;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void destroy() {
        super.destroy();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        this.processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        this.processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        Secretary.startFxn("CategoryServlet.processRequest() do = " + request.getParameter("do"));
        String goTo = "/categoryList.jsp";
        javax.servlet.http.HttpSession session = request.getSession(false);
        if(request.getParameter("do").equals("create"))
        {
            if(request.getParameter("catName") == null)
            {
                goTo = "/categoryCreate.jsp";
            }
            else
            {
                Secretary.write("CategoryServlet processing category Create");
                Admin admin = (Admin)(session.getAttribute("admin"));
                conn = myBroker.getConnection();
                String nick = EscapeChars.forHTMLTag(request.getParameter("catNick").trim());
                String name = EscapeChars.forHTMLTag(request.getParameter("catName").trim());
                admin.categoryCreate(conn, nick, name);
                myBroker.freeConnection(conn);
            }
        }        
        else if(request.getParameter("do").equals("mod"))
        {
            if(request.getParameter("catName") == null)
            {
                // FORWARD TO categoryMod.jsp
                goTo = "/categoryMod.jsp";
            }
            else
            {
                Secretary.write("CategoryServlet processing category Mod");
                Admin admin = (Admin)(session.getAttribute("admin"));
                //Secretary.write("catID = " + request.getParameter("catID"));
                //Secretary.write("catName = " + request.getParameter("catName"));
                conn = myBroker.getConnection();
                String nick = EscapeChars.forHTMLTag(request.getParameter("catNick").trim());
                String name = EscapeChars.forHTMLTag(request.getParameter("catName").trim());
                admin.categoryModify(conn, request.getParameter("catID"), nick, name);
                myBroker.freeConnection(conn);
            }
        }
        else if(request.getParameter("do").equals("disp"))
        {
            goTo = "/categoryList.jsp";
        }
        else if(request.getParameter("do").equals("del"))
        {
            if(request.getParameter("catID") != null)
            {
                Admin admin = (Admin)(session.getAttribute("admin"));
                Secretary.write("deleting category catID " + request.getParameter("catID").trim());
                Integer id = new Integer(request.getParameter("catID"));
                conn = myBroker.getConnection();
                admin.categoryDelete(conn, id.intValue());
                myBroker.freeConnection(conn);
                goTo = "/categoryList.jsp";
                // A category has been selected. Delete it.
                //session.setAttribute("catalog", admin.getCatalog());
            }
            else
            {
                // No Category has been selected. Display a list to select.
                // FORWARD TO categoryDelete.jsp
                goTo = "/categoryDelete.jsp";
            }
        }
        javax.servlet.RequestDispatcher rd = null;
        rd = getServletConfig().getServletContext().getRequestDispatcher(goTo);
        Secretary.endFxn("CategoryServlet.processRequest() do = " + request.getParameter("do"));
        rd.forward(request, response);
        
    
    }
    public String getServletInfo() {
        return "Short description";
    }
}
