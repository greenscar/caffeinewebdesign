import javax.servlet.http.*;
import java.util.Vector;
import java.text.DecimalFormat;
import HTML.*;

/*
 * BasketCell acts as a shopping basket.
 * The constructer is passed a HttpServletRequest constructor reference
 *      because it must get a reference to the basket Vector containing
 *      the currently selected movies.
 */
public class BasketCell extends HTMLTableCell {
    
    /** Creates a new instance of BasketCell */
    public BasketCell(HttpServletRequest request) {
        super(HTMLTableCell.DATA);
        int items = 0;
        double total=0;
        
        HttpSession session = request.getSession(true);
        Vector basket = (Vector)session.getValue("basket");
        if(basket != null){
            items = basket.size();
            for(int x=0;x<items;x++)
                total = total + ((Movie)basket.elementAt(x)).getPrice();
        }
        setHorizontalAlign(CENTER);
        
        HTMLTable table = new HTMLTable();
        HTMLTableRow row = new HTMLTableRow();
        HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
        cell.setHorizontalAlign(CENTER);
        
        //Add an image link to the shopping basket
        HTMLImage image = new HTMLImage("http://127.0.0.1:6669/Movies/images/shopping_cart.jpg", "ShoppingCart");
        HTMLLink link = new HTMLLink("/Movies/ShoppingBasketServlet", image);
        
        //Shopping Basket Image Link
        cell.addObject(link);
        //Add the cell to the row
        row.addObject(cell);
        //Add the row to the table
        table.addObject(row);
        
        //# of items
        row = new HTMLTableRow();
        cell = new HTMLTableCell(HTMLTableCell.DATA);
        HTMLText text = new HTMLText("Items: " + items);
        cell.addObject(text);
        row.addObject(cell);
        table.addObject(row);
        
        //Total Prices
        DecimalFormat form = new DecimalFormat("##0.00");
        row = new HTMLTableRow();
        cell = new HTMLTableCell(HTMLTableCell.DATA);
        text = new HTMLText("Total: $" + form.format(total));
        cell.addObject(text);
        row.addObject(cell);
        table.addObject(row);
        addObject(table);
    }
}
