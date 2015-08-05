import exam.*;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminServlet extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void destroy() {
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        String goTo = request.getParameter("goTo");
        if((goTo == null) || (goTo.equals(Exam.GET_HEADER))){
            /*
             * 1) Display the Header Form
             */
            rd = getServletConfig().getServletContext().getRequestDispatcher("/jspExam/getHeaderInfo.jsp");
        }
        else if(goTo.equals(Exam.GET_FIRST_Q)){
            /*
             * 1) Display the Header.
             * 2) Display the Question Type Form.
             */
            rd = getServletConfig().getServletContext().getRequestDispatcher("/jspExam/getFirstQ.jsp");
        }
        else if (goTo.equals(Exam.GET_NEXT_Q)){
            /*
             * 1) Display the Header.
             * 2) Display the exam thus far, with solutions.
             * 3) Display the Question Type Form.
             */
            rd = getServletConfig().getServletContext().getRequestDispatcher("/jspExam/getHeaderInfo.jsp");
        }
        else if (goTo.equals(Exam.DISPLAY)){
            /*
             * 1) Display the exam with answers
             */
            rd = getServletConfig().getServletContext().getRequestDispatcher("/jspExam/getHeaderInfo.jsp");
        }
        if(rd != null){
            rd.forward(request, response);
        }
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
