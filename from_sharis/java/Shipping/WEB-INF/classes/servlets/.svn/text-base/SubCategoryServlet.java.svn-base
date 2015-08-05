/*
 * CLASS : SubCategoryServlet
 * AUTHOR : James Sandlin
 * DATE : 22/06/2004
 * PURPOSE: This class handles all HTTPServlet requests partaining to
 *          SubCategory maintainence. 
 *          This will include create, modify, and delete.
 */
package servlets;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import logging.Secretary;
import users.Admin;
import java.sql.*;
import com.javaexchange.dbConnectionBroker.*;
import htmlConverter.EscapeChars;

/**
 *
 * @author  jsandlin
 * @version
 */
public class SubCategoryServlet extends HttpServletJXGB {
    Connection conn = null;
    
    //Secretary log;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //this.log = new Secretary();
    }
    
    public void destroy() {
        super.destroy();
    }
    
    /*
     * processRequest is called by doGet and doPost to handle all requests.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Secretary.startFxn("SubCategoryServlet.processRequest(request, response)");
        
        // Get the admin from the session
        javax.servlet.http.HttpSession session = request.getSession(false);
        Admin admin = (Admin)(session.getAttribute("admin"));
        
        // Based on arguments, do the correct functions.
        String goTo = "/admin.jsp";
        String toDo = request.getParameter("do");
        Secretary.logAllRequestVars(request);
        if(toDo.equals("create"))
        {
            if(request.getParameter("subCatName") == null)
            {
                goTo = "/subCategoryCreate.jsp";
            }
            else
            {
                // Process create subcat form.
                String name = EscapeChars.forHTMLTag(request.getParameter("subCatName"));
                String parent = request.getParameter("catID");
                conn = myBroker.getConnection();
                admin.subCategoryCreate(conn, name, parent);
                myBroker.freeConnection(conn);
            }
        }
        else if(toDo.equals("mod"))
        {
            if(request.getParameter("subCatName") == null)
            {
                goTo = "/subCategoryMod.jsp";
            }
            else
            {
                // Process the subcat mod
                // NOTE: The category and subcategory were loaded into memory 
                // inside subCategoryMod.jsp. Therefore, the ones currently in 
                // the catalog's memory are the ones to mod.
                int oldCatID = new Integer(request.getParameter("oldCatID")).intValue();
                int newCatID = new Integer(request.getParameter("newCatID")).intValue();
                int subCatID = new Integer(request.getParameter("subCatID")).intValue();
                String subCatName = EscapeChars.forHTMLTag(request.getParameter("subCatName").trim());
                conn = myBroker.getConnection();
                admin.subCategoryModify(conn, subCatID, subCatName, oldCatID, newCatID);
                myBroker.freeConnection(conn);
            }
        }
        else if(toDo.equals("disp"))
        {
            goTo = "/subCategoryList.jsp";
        }
        Secretary.write("SubCategoryServlet.processRequest forwarding user to " + goTo);
        // Forward user to correct page.
        javax.servlet.RequestDispatcher rd = null;
        rd = getServletConfig().getServletContext().getRequestDispatcher(goTo);
        Secretary.endFxn("SubCategoryServlet.processRequest(request, response)");
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
