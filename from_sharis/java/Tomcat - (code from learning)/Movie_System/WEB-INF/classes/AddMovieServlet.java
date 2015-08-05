import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
/**
 * This servlet is used only to add a movie to the Shopping basket
 */
public class AddMovieServlet extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    /*
     * This method takes the Movie object param and adds it to 
     *      the Shopping Basket Vector stored in the HttpSession Object
     */
    private void addToBasket(Movie movie, HttpServletRequest request){
        //Get or create the session
        HttpSession session = request.getSession(true);
        if(session != null){
            //Try to get a reference to the basket Vector
            Vector basket = (Vector)session.getValue("basket");
            
            //If basket is null, create one
            if(basket == null){
                basket = new Vector(5);
                session.putValue("basket", basket);
            }
            //Add the passed in movie to the basket
            basket.addElement(movie);
        }
    }
    
    public void destroy() {
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        // Get the movie params passed in the request
        String id = request.getParameter("id");
        String price = request.getParameter("price");
        String redirect_url = request.getParameter("trans");
        
        /*
         * We are not checking out. We are only adding an item to the basket
         *      and redirecting to our previous TitleListServlet response
         */
        if(id != null){
            // Create the movie
            Movie movie = new Movie();
            movie.setTitleId((new Integer(id)).intValue());
            movie.setPrice((new Double(price)).doubleValue());
            
            // Add the movie to the basket
            addToBasket(movie, request);
            // Redirect the browser to the calling page
            response.sendRedirect(redirect_url);
        }
    }
    
    public String getServletInfo() {
        return "AddMovieServlet description";
    }
    
}
