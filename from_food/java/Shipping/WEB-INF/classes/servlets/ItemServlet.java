package servlets;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import logging.Secretary;
import users.Admin;
import htmlConverter.EscapeChars;
import java.sql.*;
import com.javaexchange.dbConnectionBroker.*;

public class ItemServlet extends HttpServletJXGB {
    Connection conn = null;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
    }
    
    public void destroy() {
        super.destroy();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Secretary.startFxn("ItemServlet.processRequest() do = " + request.getParameter("do"));
        String goTo = "/itemList.jsp";
        javax.servlet.http.HttpSession session = request.getSession(false);
        String toDo = request.getParameter("do");
        Secretary.write("toDo == " + toDo);
        Admin admin = (Admin)(session.getAttribute("admin"));
        if(toDo.equals("create"))
        {
        }
        else if(toDo.equals("disp"))
        {
            // DISPLAY ALL ITEMS
            goTo = "/itemList.jsp";
        }
        else if(toDo.equals("mod"))
        {
            goTo = "/itemMod.jsp";
            int itemID = 0;
            if(request.getParameter("who") == null)
            {
                Secretary.write("who == null");
                // FORWARD TO itemMod.jsp SO IT CAN
                // DISPLAY A LIST OF ITEMS TO CHOOSE FROM
            }
            else // if(request.getParameter("who") == null)
            {
                Secretary.write("who = " + request.getParameter("who"));
                Secretary.write("step = " + request.getParameter("step"));
                if(request.getParameter("step").compareTo("display") == 0)
                {
                    try{
                        // Try to get the int value of the who argument.
                        // If the argument is not an integer, do nothing.
                        //itemID = new Integer(request.getParameter("who")).intValue();
                        //int subCatID = new Integer(request.getParameter("subCat")).intValue();
                        //int catID = new Integer(request.getParameter("cat")).intValue();
                        //Secretary.write("Display item # " + itemID + " to Mod");
                        //admin.setItemViaID(itemID, subCatID, catID);
                        //Secretary.write("admin.getItemID() = " + admin.getItemID());
                        goTo = goTo.concat("?who="+request.getParameter("who"));
                        Secretary.write("goTo = " + goTo);
                    }catch(NumberFormatException n){
                        // who == all
                        // Which means we are going to display a list of all items to mod
                        //Secretary.write("Display ALL items to mod");
                        goTo = goTo.concat("?who=all");
                    }
                } //END if(request.getParameter("step").compareTo("display") == 0)
                else if(request.getParameter("step").compareTo("process") == 0)
                {
                    Secretary.startFxn("ItemServlet.process");
                    try{
                        Secretary.logAllRequestVars(request);
                        // The Admin is currently holding the correct cat, subcat, and item.
                        itemID = new Integer(request.getParameter("who")).intValue();
                        String name = EscapeChars.forHTMLTag(request.getParameter("name"));
                        int glNum = new Integer(request.getParameter("glNum")).intValue();
                        int oldCatID = new Integer(request.getParameter("categoryOrig")).intValue();  
                        int oldSubCatID = new Integer(request.getParameter("subCategoryOrig")).intValue();
                        float cost = new Float(request.getParameter("cost")).floatValue();
                        int newSubCatID = new Integer(request.getParameter("subCategoryNew")).intValue();
                        int newCatID = new Integer(request.getParameter("categoryNew")).intValue();
                        boolean active = new Boolean(request.getParameter("active")).booleanValue();
                        int orderQuantityID = new Integer(request.getParameter("orderQuantityID")).intValue();
                        
                        //Secretary.write("name = " + name);
                        //Secretary.write("glNum = " + glNum);
                        //Secretary.write("catOld = " + request.getParameter("categoryOrig"));
                        //Secretary.write("oldCatID = " + oldCatID);
                        //Secretary.write("oldSubCatID = " + oldSubCatID);
                        //Secretary.write("cost = " + cost);
                        //Secretary.write("newSubCatID = " + newSubCatID);
                        //Secretary.write("newCatID = " + newCatID);
                        //Secretary.write("active = " + active);
                        
                        /*******************************************
                         * PROCESS THE ITEM MODIFICATION
                         ******************************************/
                        conn = myBroker.getConnection();
                        admin.itemModify(conn, itemID, name, cost, glNum, active, oldCatID, oldSubCatID, newCatID, newSubCatID, orderQuantityID);
                        admin.loadCatalog(conn);
                        myBroker.freeConnection(conn);
                        //response.reset();
                        goTo = "/itemMod.jsp";
                    }catch(NumberFormatException e){
                        Secretary.write("******** NumberFormatException " + e.getMessage() + "************");
                        // who == all
                    }   
                    Secretary.endFxn("ItemServlet.process");
                } // END else if(request.getParameter("step").compareTo("process") == 0)
            } // END else NOT if(request.getParameter("who") == null)
        }// END else if(toDo.equals("mod"))
        else if(toDo.equals("selectCatTest"))
        {
            goTo = "/categorySelectTest.jsp";
        } //END else if(toDo.equals("selectCatTest"))
        Secretary.write("ItemServlet.processRequest forwarding user to " + goTo);
        // Forward user to correct page.
        javax.servlet.RequestDispatcher rd = null;
        rd = getServletConfig().getServletContext().getRequestDispatcher(goTo);
        Secretary.endFxn("ItemServlet.processRequest(request, response)");
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
