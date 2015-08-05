import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Controller extends HttpServlet {

    /**
     *
     */
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
    }

    /** The forward method forwards the results of the Service to the passed in
     *  target.
     * @throws VSException if an IOException or ServletException is thrown by the
     * RequestDispatcher.
     */
    protected void forward(HttpServletRequest request,
        HttpServletResponse response,
        String target) throws ServletException {

        try {

            ServletContext context = getServletContext();

            RequestDispatcher dispatcher =
                context.getRequestDispatcher(target);
            dispatcher.forward(request, response);
        }
        catch (IOException ioe) {

            throw new ServletException(ioe.getMessage());
        }
    }

    /**
     * Calls the <code>doPost()</code> with the request and response.
     *
     * @param request   the ServletEngine created HttpServletRequest.
     * @param response  the ServletEngine created HttpServletResponse.
     * @throws ServletException if an Exception is not handled by the web application.
     * @throws IOException if there is an IO error during the request.
     */
    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }

    /**
     * Services all requests for the controllers. It expects a request
     * parameter, <code>service</code>, specifying the name of the
     * transaction that is to be executed.
     *
     * @param request  the ServletEngine created HttpServletRequest.
     * @param response the ServletEngine created HttpServletResponse.
     * @throws ServletException if an Exception is not handled by the web application.
     * @throws IOException if there is an IO error during the request.
     */
    public void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        System.err.println("Controller.doPost()");
        // Get the name of the Transaction to perform
        String serviceName = request.getParameter("service");
        System.err.println("Controller.doPost().serviceName = " + serviceName);
        if (serviceName == null) {
            throw new ServletException("No service named!");
        }

        // Get the target of the request
        String target = request.getParameter("target");
        System.err.println("Controller.doPost().target = " + target);
        if (target == null) {

            throw new ServletException("No target named!");
        }

        ServletContext context = getServletContext();

        // Create and execute an instance of the named service
        try {

            // Create an instance of the fully qualified Service Class
            Class cls = Class.forName(serviceName);
            Service service = (Service)cls.newInstance();
            // Execute the Transaction
            service.execute(request, response, context);
        }
        catch (ClassNotFoundException ce) {

            throw new ServletException("ClassNotFoundException in Controller.doPost(): " + ce.getMessage());
        }
        catch (IllegalAccessException iae) {

            throw new ServletException("IllegalAccessException in Controller.doPost(): " + iae.getMessage());
        }
        catch (Exception e) {

            throw new ServletException("Exception in Controller.doPost(): " + e.getMessage());
        }

        // Forward the results
        forward(request, response, target);
    }
}