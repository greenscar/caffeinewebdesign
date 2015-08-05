import java.io.*;
import java.net.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 * This servlet is called anytime a person enters *.htm or *.html in the 
 *      browser's address bar. It will redirect them to login.jsp
 */
public class ControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String forwardTo;
        String URI = request.getServletPath();
        System.err.println("URI = " + URI);
        if(URI.equalsIgnoreCase("/index.html"))
            forwardTo = "/login.jsp";
        else{
            forwardTo = "/login.jsp";
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
