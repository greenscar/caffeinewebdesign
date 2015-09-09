package ch15;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String forwardTo;
        String URI = request.getServletPath();
        if(URI.equalsIgnoreCase("/index.html"))
            forwardTo = "/index.jsp";
        else{
            forwardTo = "/article.jsp";
            request.setAttribute("includePage", URI);
        }
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher(forwardTo);
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    
    
}
