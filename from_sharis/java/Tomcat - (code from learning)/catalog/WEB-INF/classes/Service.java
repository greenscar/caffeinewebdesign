import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

/**
 * An inteface that provides the base functionality for all
 * Services.
 * <p>
 * To implement a <code>Service</code>, the <code>execute()</code>
 * method is the only method that must be implemented.
 *
 */
public interface Service {

    /**
     * Single method to become a service.
     *
     * @throws Exception if any exceptions need to be thrown back
     * to the calling Controller.
     */
    public void execute(HttpServletRequest request,
        HttpServletResponse response,
        ServletContext context) throws Exception;
}
