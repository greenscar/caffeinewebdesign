/*
 * login.java
 *
 * Created on April 23, 2003, 8:46 AM
 */
package com.onjava;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author  jsandlin
 * @version
 */
public class login extends HttpServlet {
    
    public String target = "/welcome.jsp";
    
    /** Initializes the servlet.*/
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
    }
   
    public String getUser(String username, String password){
        //return a static name
        return "Aubri";
    }
        
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
     
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String user = getUser(username, password);
        
        request.setAttribute("USER", user);
        
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
    
    /** Destroys the servlet.
     */
    public void destroy() {
        
    }
}
