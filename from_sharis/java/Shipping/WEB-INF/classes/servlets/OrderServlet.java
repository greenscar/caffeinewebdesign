
package servlets;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import logging.Secretary;
import users.*;

import java.sql.*;
import com.javaexchange.dbConnectionBroker.*;

public class OrderServlet extends HttpServletJXGB {
    Connection conn = null;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void destroy() {
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Secretary.startFxn("OrderServlet.processRequest() do = " + request.getParameter("do"));
        //logging.Secretary.logAllRequestVars(request);
        javax.servlet.http.HttpSession session = request.getSession(false);
        String goTo="/Order";
        if(request.getParameter("do") == null)
        {
            goTo="/orderMain.jsp";
            Secretary.write("goTo = " + goTo);
        }        
        else if(request.getParameter("do").equalsIgnoreCase("getPossibleRecipients"))
        {
            goTo="/orderCreate.jsp";
            if((User)(session.getAttribute("admin")) instanceof Admin)
            {
                // Load the list of sites to choose recipients
                ((User)(session.getAttribute("admin"))).siteListLoad();
            }   
        }
        else if(request.getParameter("do").equalsIgnoreCase("processRecipients"))
        {
            // Create orders for the recipients and enter the empty orders into the DB
            // Set nextCat = firstCat and forward to order page
            User theUser = ((User)(session.getAttribute("admin")));
            conn = myBroker.getConnection();
            if(theUser instanceof Admin)
            {
                ((Admin)theUser).orderCreate(conn, request.getParameterValues("recipients"), request.getParameter("contact"));
            }
            myBroker.freeConnection(conn);
            // Reset the category list to the first category
            theUser.setCategoryViaID(theUser.categoryListGetFirstKey());
            request.setAttribute("catID", theUser.getCategoryIDString());
            goTo="/orderCreate.jsp";
            //goTo="http://127.0.0.1/phpinfo.php";
        }
        else if(request.getParameter("do").equalsIgnoreCase("cont"))
        {
            Secretary.write("do = cont");
            User theUser = ((User)(session.getAttribute("admin")));
            /*
             * 1) Process the previous form and enter all items into the basket.
             * 2) Figure out where to go from here and send the user.
             */
            java.util.Map paramMap = request.getParameterMap();
            java.util.Set paramSet = paramMap.keySet();
            java.util.Iterator params = paramSet.iterator();
            while(params.hasNext())
            { 
                String name = (String)(params.next());
                if(name.startsWith("quantity"))
                {
                    String value = request.getParameter(name);
                    if(!(value.equals("") || value.equals("0")))
                    {
                        Integer itemNum = new Integer(value.substring((value.indexOf('_'))));
                        Secretary.write(name + " = " + value);
                        Secretary.write("itemNum = " + itemNum);
                    }
                }
            }
        
            if((String)(request.getParameter("goTo")) != null)
            {
                /*
                 * USER HAS CLICKED ON AN ARROW BUTTON TO TAKE THEM TO 
                 * FIRST, PREV, NEXT, OR LAST
                 */
                 String goToCat = (String)(request.getParameter("goTo"));
                 if(goToCat.endsWith("First"))
                 {
                    theUser.setCategoryViaID(theUser.categoryListGetFirstKey());
                    request.setAttribute("catID", theUser.getCategoryIDString());
                 }
                 else if(goToCat.endsWith("Prev"))
                 {
                    theUser.setCategoryViaID(theUser.categoryListGetPrevKey());
                    request.setAttribute("catID", theUser.getCategoryIDString());
                 }
                 else if(goToCat.startsWith("Next"))
                 {
                    theUser.setCategoryViaID(theUser.categoryListGetNextKey());
                    request.setAttribute("catID", theUser.getCategoryIDString());
                 }
                 else if(goToCat.startsWith("Last"))
                 {
                    theUser.setCategoryViaID(theUser.categoryListGetLastKey());
                    request.setAttribute("catID", theUser.getCategoryIDString());
                 }
            }
            else if(!(((String)(request.getParameter("nextCat"))).equalsIgnoreCase("Select Category")))
            {
                /*
                 * User has selected a category from the dropdown menu to goto.
                 */
                String nextCat = (String)(request.getParameter("nextCat"));
                theUser.setCategoryViaID(nextCat);
                request.setAttribute("catID", nextCat);
            }
            
            goTo="/orderCreate.jsp";
        }
        javax.servlet.RequestDispatcher rd = null;
        rd = getServletConfig().getServletContext().getRequestDispatcher(goTo);
        Secretary.endFxn("OrderServlet.processRequest() do = " + request.getParameter("do"));
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
