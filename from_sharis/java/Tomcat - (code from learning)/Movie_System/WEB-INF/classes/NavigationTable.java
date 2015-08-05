import javax.servlet.*;
import java.util.Vector;
import HTML.*;

/*
 * This class serves a container for the Navigation Table
 * It gets a reference to the DOMAIN_LIST in the ServletContext
 *      and iterates over it listing the Categories.
 */
public class NavigationTable extends HTMLTable {
    
    /** Creates a new instance of NavigationTable */
    public NavigationTable(ServletContext context) {
        System.err.println("NavigationTable Constructor");
        setWidthByPixel(150);
        setHorizontalAlign(LEFT);
        setVerticalAlign(TOP);
        HTMLText caption = new HTMLText("Categories");
        caption.setBold(true);
        setCaption(caption);
        Vector categories = (Vector)context.getAttribute("DOMAIN_LIST");
        //System.err.println("categories.size() == " + categories.size());
        for(int x=0; x< categories.size(); x++){
            Category category = (Category)categories.elementAt(x);
            System.err.println("ADDING " + category.getName());
            HTMLTableRow row = new HTMLTableRow();
            HTMLTableCell cell = new HTMLTableCell(HTMLTableCell.DATA);
            HTMLLink l = new HTMLLink("/Movies/TitleListServlet?category_id=" + category.getId(), new HTMLText(category.getName()));
            cell.addObject(l);
            row.addObject(cell);
            addObject(row);
        }
        HTMLTableRow row;
        HTMLTableCell cell;
        
        row = new HTMLTableRow();
        cell = new HTMLTableCell(HTMLTableCell.DATA);
        
        //Create a simple separator
        row = new HTMLTableRow();
        cell = new HTMLTableCell(HTMLTableCell.DATA);
        cell.addObject(new HTMLHorizontalRule());
        row.addObject(cell);
        addObject(row);
        
        //Link to the WelcomeServlet
        row = new HTMLTableRow();
        cell = new HTMLTableCell(HTMLTableCell.DATA);
        HTMLLink l = new HTMLLink("/Movies/WelcomeServlet", new HTMLText("Home"));
        cell.addObject(l);
        row.addObject(cell);
        addObject(row);
        
        //Link to the ShoppingBasketServlet
        row = new HTMLTableRow();
        cell = new HTMLTableCell(HTMLTableCell.DATA);
        HTMLLink lk = new HTMLLink("/Movies/servletShoppingBasketServlet", new HTMLText("Checkout"));
        cell.addObject(lk);
        row.addObject(cell);
        addObject(row);
    }
}
