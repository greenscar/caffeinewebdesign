package login;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class processLogin extends HttpServlet {
    private String target = "/welcome.jsp";
    
    private String getUser(String username, String password){
        if(username.compareTo("c8h10n4o2") == 0){
            if(password.compareTo("6669") == 0)
                return "c8h10n4o2";
            else
                return "Password Incorrect";
        }
        else{
            return "UserName <B>" + username + "</B> not found.";
        }
    }
    
    /** Initializes the servlet.*/
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
        
    /** Handles the HTTP <code>GET</code> method.*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.*/
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
        /*
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String user = getUser(username, password);
        
        //Add the user to the request
        request.setAttribute("USER", user);
        
        //Forward the request to the target named.
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
        */
         
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet</title>");
        out.println("</head>");
        out.println("<H1 align=\"CENTER\">I luv Aubri.</H1>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        
    }
    
    /** Returns a short description of the servlet.*/
    public String getServletInfo() {
        return "This servlet is being used to learn JSP / Servlet technology.";
    }
    
    /** Destroys the servlet.*/
    public void destroy() {   
    }
}
