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
        super.init(config);
        logging.Secretary.startFxn("HttpServletJXGB.init()");
        if(myBroker == null) { // Only created by first servlet to call
            Properties p = new Properties();
            try {
                /*
                p.load(new
                FileInputStream("/home/javaexchange/servlet/JXGBconfig.dat"));

                String dbDriver = (String) p.get("dbDriver");
                String dbServer = (String) p.get("dbServer");
                String dbLogin  = (String) p.get("dbLogin");
                String dbPassword = (String) p.get("dbPassword");
                int minConns   = Integer.parseInt((String) p.get("minConns"));
                int maxConns   = Integer.parseInt((String) p.get("maxConns"));
                String logFileString = (String) p.get("logFileString");
                double maxConnTime   = (new Double((String)p.get("maxConnTime"))).doubleValue();
                 */
                //myBroker = new DbConnectionBroker(dbDriver,dbServer,dbLogin,dbPassword, minConns,maxConns,logFileString,maxConnTime);
                myBroker = new DbConnectionBroker();
            }
            catch (FileNotFoundException f) {}
            catch (IOException e) {}
        }
        logging.Secretary.endFxn("HttpServletJXGB.init() END");
    }
    public void destroy()
    {
        myBroker.destroy();
    }
}
