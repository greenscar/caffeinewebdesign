//import Service;
//import ShoppingCart;
import ConnectionPool.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;

public class AddToCart implements Service {

    public AddToCart() {

    }

    /*
     implemented method from Service interface
     */
    public void execute(HttpServletRequest request,
        HttpServletResponse response,
        ServletContext context) throws Exception {

        // Get categorie_id from the request
        String title_id = request.getParameter("title_id");

        // Get a database connection from CATALOG_POOL
        ConnectionPool pool =
            (ConnectionPool)context.getAttribute("CATALOG_POOL");
        Connection con = pool.getConnection();

        try {

            if ( con != null ) {

                Statement statement = con.createStatement();

                // Get the title info. from the database
                StringBuffer query = new StringBuffer("SELECT * FROM TITLES");
                query.append(" WHERE TITLE_ID = " + title_id);

                ResultSet rs =
                    statement.executeQuery(query.toString());

                if ( rs.next() ) {

                    String id = rs.getString("title_id");
                    String desc = rs.getString("title_name");
                    float price = rs.getFloat("price");

                    // Check the HttpSession for an exist ShoppingCart
                    HttpSession session = request.getSession();
                    ShoppingCart cart =
                        (ShoppingCart)session.getAttribute("cart");

                    if ( cart == null ) {

                        // if no ShoppingCart was found create one and add
                        // to the HttpSession
                        cart = new ShoppingCart();
                        session.setAttribute("cart", cart);
                    }
                    // Add the selected item to the ShoppingCart
                    cart.addItem(id, desc, price, 1);
                }
                else {

                    throw new Exception("Could not find title!");
                }
            }
        }
        finally {

            pool.releaseConnection(con);
        }

    }
}