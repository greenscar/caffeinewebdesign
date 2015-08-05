import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 * This servlet is used to empty the contents of the Shopping Basket only.
 * Once the basket is empty, the servlet redirects the browser to the Welcome screen.
 */
public class EmptyBasketServlet extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    private void emptyBasket(HttpServletRequest req){
        // Get/Create a reference to the HttpSession object.
        HttpSession session = req.getSession(true);
        if(session != null){
            // Get a reference to the basket Vector
            Vector basket = (Vector)session.getValue("basket");
            // If basket is null, create one
            if(basket != null){
                // Empty the basket
                basket.removeAllElements();
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        emptyBasket(request);
        response.sendRedirect("/servlet/WelcomeServlet");
    }
    
    public String getServletInfo() {
        return "EmptyBasketServlet description";
    }
    
}
