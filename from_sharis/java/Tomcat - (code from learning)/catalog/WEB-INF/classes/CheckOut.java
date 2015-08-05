
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

public class CheckOut implements Service {

    public CheckOut() {

    }

    /*
     implemented method from Service interface
     */
    public void execute(HttpServletRequest request,
        HttpServletResponse response,
        ServletContext context) throws Exception {

        // Check the HttpSession for an exist ShoppingCart
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");

        if ( cart != null ) {

            // If there was an existing cart empty it
            cart.empty();
        }
    }
}
