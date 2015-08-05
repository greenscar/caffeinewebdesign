/**
 * Database Global Broker Superclass.
 * @version 1.0.0 7/28/99
 * @author Marc A. Mnich
 */
package com.javaexchange.dbConnectionBroker;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.*;
import java.util.*;
//import com.javaexchange.dbConnectionBroker.*;

/**
 * Creates a two-tier database connection pool that can be shared
 * by many servlets through inheritance.
 * @version 1.0.0 7/28/99
 * @author Marc A. Mnich
 */
public class HttpServletJXGB extends HttpServlet {

    protected static DbConnectionBroker myBroker;

    public void init (ServletConfig config) throws ServletException  {
        logging.Secretary.startFxn("HttpServletJXGB init");
        super.init(config);
        if(myBroker == null) { // Only created by first servlet to call
            Properties p = new Properties();
            try {
                myBroker = new DbConnectionBroker();
                logging.Secretary.write("myBroker = " + myBroker.toString());
            }
            catch (FileNotFoundException f) {}
            catch (IOException e) {}
        }
        logging.Secretary.endFxn("HttpServletJXGB.init() END");
    }
    public void destroy(int numMilliSeconds)
    {
        try
        {
            myBroker.destroy(numMilliSeconds);
        }catch(SQLException e)
        {
            logging.Secretary.write("ERROR IN HttpServletJXGB.destroy("+numMilliSeconds+")");
        }
    }
}
