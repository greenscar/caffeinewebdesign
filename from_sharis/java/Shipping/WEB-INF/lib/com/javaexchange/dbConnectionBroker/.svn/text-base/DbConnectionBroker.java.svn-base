/** 
 * DbConnectionBroker.  
 * @version 1.0.13 3/12/02
 * @author Marc A. Mnich
 */
package com.javaexchange.dbConnectionBroker;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.Date;
import java.util.TimeZone;
import logging.Secretary;

/**
 * DbConnectionBroker
 * A servlet-based broker for database connections.
 * Creates and manages a pool of database connections.
 * @version 1.0.13 3/12/02
 * @author Marc A. Mnich
 */
public class DbConnectionBroker implements Runnable 
{
    private Thread runner;
    
    private Connection[] connPool;
    private int[] connStatus;

    private long[] connLockTime, connCreateDate;
    private String[] connID;
    private String dbDriver, dbServer, dbLogin, dbPassword, logFileString;
    private int currConnections, connLast, minConns, maxConns, maxConnMSec,
      maxCheckoutSeconds, debugLevel;
      
    //available: set to false on destroy, checked by getConnection()
    private boolean available=true;
    private PrintWriter dbLog;
    private SQLWarning currSQLWarning;
    private String pid;
    private Secretary log;
    private final int DEFAULTMAXCHECKOUTSECONDS=60;
    private final int DEFAULTDEBUGLEVEL=2;
    
    /**
     * Creates a new Connection Broker<br>
     * dbDriver:        JDBC driver. e.g. 'oracle.jdbc.driver.OracleDriver'<br>
     * dbServer:        JDBC connect string. e.g. 'jdbc:oracle:thin:@203.92.21.109:1526:orcl'<br>
     * dbLogin:         Database login name.  e.g. 'Scott'<br>
     * dbPassword:      Database password.    e.g. 'Tiger'<br>
     * minConns:        Minimum number of connections to start with.<br>
     * maxConns:        Maximum number of connections in dynamic pool.<br>
     * logFileString:   Absolute path name for dbLog file. e.g. 'c:/temp/mylog.dbLog' <br>
     * maxConnTime:     Time in days between connection resets. (Reset does a basic cleanup)<br>
     * logAppend:       Append to logfile (optional)<br>
     * maxCheckoutSeconds:       Max time a connection can be checked out before being recycled. Zero value turns option off, default is 60 seconds.
     * debugLevel:      Level of debug messages output to the dbLog file.  0 -> no messages, 1 -> Errors, 2 -> Warnings, 3 -> Information
     */
   public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
     String dbPassword, int minConns, int maxConns,
         String logFileString, double maxConnTime) throws IOException 
   {
       this.log = new Secretary();
    log.write("-----------------DbConnectionBroker CONSTRUCTOR--------------");
      setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns, 
         maxConns, logFileString, maxConnTime, false, 
         DEFAULTMAXCHECKOUTSECONDS, DEFAULTDEBUGLEVEL);
   }
   public DbConnectionBroker() throws IOException 
   {
      //log = new Secretary();
      //dbDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
      dbDriver = "net.sourceforge.jtds.jdbc.Driver";
      //dbServer = "jdbc:odbc:traindev";
      //dbServer = "jdbc:jtds:sqlserver://psnt:1433/TRAIN";
      dbServer = "jdbc:jtds:sqlserver://psnt:1433/WEB";
      //dbLogin = "helpdesk";
      //dbPassword = "helpdesk";
      //dbLogin = "Adminshari";
      //dbPassword = "village";
      dbLogin = "sa";
      dbPassword = "sa";
      /*
      //dbServer = "jdbc:odbc:train";
      dbServer = "jdbc:jtds:sqlserver://psnt/train";
      dbLogin = "helpdesk";
      dbPassword = "helpdesk";
      */
      minConns = 1;
      maxConns = 5;
      String logDir = "c:\\Inetpub\\Tomcat\\5.0\\logs\\";
      //String logDir = "//wwwroot//logs//";
      TimeZone tz = TimeZone.getTimeZone("PST"); // or PST, MID, etc ...
      DateFormat fileDate = new SimpleDateFormat ("yyyy_MM_dd"); 
      java.util.Date now = new java.util.Date();
      fileDate.setTimeZone(tz);
      String currentDate = fileDate.format(now);
      logFileString = logDir + currentDate + "_DbConnectionBroker.log";
      double maxConnTime = 0.05;
      setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns, 
		    maxConns, logFileString, maxConnTime, false, 
		    DEFAULTMAXCHECKOUTSECONDS, DEFAULTDEBUGLEVEL);
   }
    /*
     * Special constructor to handle logfile append
     */
    public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
                String dbPassword, int minConns, int maxConns,
                String logFileString,  double maxConnTime, boolean logAppend) 
                throws IOException
    {
        setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns, 
                    maxConns, logFileString,maxConnTime, logAppend,
                    DEFAULTMAXCHECKOUTSECONDS, DEFAULTDEBUGLEVEL);
    }

    /*
     * Special constructor to handle connection checkout expiration
     */
    public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
            String dbPassword, int minConns, int maxConns,
            String logFileString,double maxConnTime, boolean logAppend,
			   int maxCheckoutSeconds, int debugLevel) 
            throws IOException 
    {
        setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns, 
                    maxConns, logFileString ,maxConnTime, logAppend, 
                    maxCheckoutSeconds, debugLevel);
    }



    private void setupBroker(String dbDriver, String dbServer, String dbLogin,
                             String dbPassword, int minConns, int maxConns,
                             String logFileString, double maxConnTime, boolean logAppend,
                             int maxCheckoutSeconds, int debugLevel) 
                             throws IOException 
    {
        connPool = new Connection[maxConns];
        connStatus = new int[maxConns];
        connLockTime = new long[maxConns];
        connCreateDate = new long[maxConns];
        connID = new String[maxConns];
        currConnections = minConns;
        this.maxConns = maxConns;
        this.dbDriver = dbDriver;
        this.dbServer = dbServer;
        this.dbLogin = dbLogin;
        this.dbPassword = dbPassword;
        this.logFileString = logFileString;
        this.maxCheckoutSeconds = maxCheckoutSeconds;
        this.debugLevel = debugLevel;
        maxConnMSec = (int)(maxConnTime * 86400000.0);  //86400 sec/day
        if(maxConnMSec < 30000) {  // Recycle no less than 30 seconds.
            maxConnMSec = 30000;
        }
        
        try {
            dbLog = new PrintWriter(new FileOutputStream(logFileString, logAppend),true);
            // Can't open the requested file. Open the default file.
        } catch (IOException e1) {  
            try {
                dbLog = new PrintWriter(new FileOutputStream("DCB_" + System.currentTimeMillis() + ".dbLog", logAppend),true);
            } catch (IOException e2) {
                Secretary.write("DbConnectionBroker.IOException : " + e2.toString());
                throw new IOException("Can't open any dbLog file");
            }
        }
         
        // println the pid file (used to clean up dead/broken connection)
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss a zzz");
        Date nowc = new Date();
        pid = formatter.format(nowc);
        //dbLog.println(pid);
        BufferedWriter pidout = new BufferedWriter(new FileWriter(logFileString + "pid"));
        pidout.write(pid);
        pidout.close();

        dbLog.println("-----------------------------------------");
        dbLog.println("-----------------------------------------");
        dbLog.println("Starting DbConnectionBroker Version 1.0.13:");
        dbLog.println("dbDriver = " + dbDriver);
        dbLog.println("dbServer = " + dbServer);
        dbLog.println("dbLogin = " + dbLogin);
        dbLog.println("dbLog file = " + logFileString);
        dbLog.println("minconnections = " + minConns);
        dbLog.println("maxconnections = " + maxConns);
        dbLog.println("Total refresh interval = " + maxConnTime + " days");
        dbLog.println("logAppend = " + logAppend);
        dbLog.println("maxCheckoutSeconds = " + maxCheckoutSeconds);
        dbLog.println("debugLevel = " + debugLevel);
        dbLog.println("-----------------------------------------");

        // Initialize the pool of connections with the mininum connections:
        // Problems creating connections may be caused during reboot when the
        //    servlet is started before the database is ready.  Handle this
        //    by waiting and trying again.  The loop allows 5 minutes for 
        //    db reboot.
        boolean connectionsSucceeded=false;
        int dbLoop=20;
        try {
            for(int i=1; i < dbLoop; i++) {
                try {
                    for(int j=0; j < currConnections; j++) { 
                        createConn(j);
                    }
                    connectionsSucceeded=true;
                    break;
                } catch (SQLException e){
                    if(debugLevel > 0) {
                        dbLog.println("--->Attempt (" + String.valueOf(i) + " of " + String.valueOf(dbLoop)
                                    + ") failed to create new connections set at startup: ");
                        dbLog.println("    " + e);
                        dbLog.println("    Will try again in 15 seconds...");
                        Secretary.write("DbConnectionBroker.SQLException : " + e.toString());
                
                    }
                    try { Thread.sleep(15000); }
                    catch(InterruptedException e1) {}
                 }
            }
            if(!connectionsSucceeded) { // All attempts at connecting to db exhausted
                if(debugLevel > 0) {
                    dbLog.println("\r\nAll attempts at connecting to Database exhausted");
                }
                throw new IOException();
            }
        } catch (Exception e) { 
            throw new IOException();
        }
        // Fire up the background housekeeping thread
        runner = new Thread(this);
        runner.start();
    }//End DbConnectionBroker()


    /**
     * Housekeeping thread.  Runs in the background with low CPU overhead.
     * Connections are checked for warnings and closure and are periodically
     * restarted.
     * This thread is a catchall for corrupted
     * connections and prevents the buildup of open cursors. (Open cursors
     * result when the application fails to close a Statement).
     * This method acts as fault tolerance for bad connection/statement programming.
     */
    public void run() 
    {
        boolean forever = true;
        Statement stmt=null;
        String currCatalog=null;
        long maxCheckoutMillis = maxCheckoutSeconds * 1000;
        while(forever) {
            // Make sure the dbLog file is the one this instance opened
            // If not, clean it up!
            try {
                BufferedReader in = new BufferedReader(new 
                FileReader(logFileString + "pid"));
                String curr_pid = in.readLine();
                if(curr_pid.equals(pid)) {
                    //dbLog.println("They match = " + curr_pid);
                }
                else {
                    //dbLog.println("No match = " + curr_pid);
                    //dbLog.close();

                    // Close all connections silently - they are definitely dead.
                    for(int i=0; i < currConnections; i++) {
                        try {
                            connPool[i].close();
                        } catch (SQLException e1) {} // ignore
                    }
                    // Returning from the run() method kills the thread
                    return;
                }
                in.close();
            } catch (IOException e1) {
                dbLog.println("Can't read the file for pid info: " + logFileString + "pid");
            }
            // Get any Warnings on connections and print to event file
            for(int i=0; i < currConnections; i++) {            
                try { 
                    currSQLWarning = connPool[i].getWarnings(); 
                    if(currSQLWarning != null) {
                        if(debugLevel > 1) {
                            dbLog.println("Warnings on connection " + String.valueOf(i) + " " + currSQLWarning);
                        }
                        connPool[i].clearWarnings();
                    }
                } catch(SQLException e) {
                    if(debugLevel > 1) {
                        dbLog.println("Cannot access Warnings: " + e);
                    }
                }
            } //END for(int i=0; i < currConnections; i++)

            for(int i=0; i < currConnections; i++) { // Do for each connection
                long age = System.currentTimeMillis() - connCreateDate[i];
                try {  // Test the connection with createStatement call
                    synchronized(connStatus) {
                        if(connStatus[i] > 0) { // In use, catch it next time!
                            // Check the time it's been checked out and recycle
                            long timeInUse = System.currentTimeMillis() - connLockTime[i];			
                            if(debugLevel > 2) {
                                dbLog.println("Warning.  Connection " + i + " in use for " + timeInUse + " ms");
                            }
                            if(maxCheckoutMillis != 0) {
                                if(timeInUse > maxCheckoutMillis) {
                                    if(debugLevel > 1) {
                                        dbLog.println("Warning. Connection " + 
                                        i + " failed to be returned in time.  Recycling...");
                                    }
                                    throw new SQLException();
                                }
                            }
                            continue;
                        }
                        connStatus[i] = 2; // Take offline (2 indicates housekeeping lock)
                    }
                    if(age > maxConnMSec) {  // Force a reset at the max conn time
                        throw new SQLException();
                    }
                    stmt = connPool[i].createStatement();
                    connStatus[i] = 0;  // Connection is O.K.
                    // Some DBs return an object even if DB is shut down
                    if(connPool[i].isClosed()) {
                        throw new SQLException();
                    }
                // Connection has a problem, restart it
                } catch(SQLException e) {
                    if(debugLevel > 1) {
                        dbLog.println(new Date().toString() + " ***** Recycling connection " + String.valueOf(i) + ":");
                    }
                    try {			
                       connPool[i].close(); 
                    } catch(SQLException e0) {
                        if(debugLevel > 0) {
                            dbLog.println("Error!  Can't close connection!  Might have been closed already.  Trying to recycle anyway... (" + e0 + ")");
                        }
                    }
                    try {
                        createConn(i);
                    } catch(SQLException e1) {
                        if(debugLevel > 0) {
                            dbLog.println("Failed to create connection: " + e1);
                        }
                        connStatus[i] = 0;  // Can't open, try again next time
                    }
                } finally {
                    try{if(stmt != null) {stmt.close();}} catch(SQLException e1){};
                }
            } //END for(int i=0; i < currConnections; i++) 
            try { Thread.sleep(20000); }  // Wait 20 seconds for next cycle
            catch(InterruptedException e) {
                // Returning from the run method sets the internal 
                // flag referenced by Thread.isAlive() to false.
                // This is required because we don't use stop() to 
                // shutdown this thread.
                return;
            } //END catch(InterruptedException e)
        } //END while(forever)
    } // End run
    
    /**
     * This method hands out the connections in round-robin order.
     * This prevents a faulty connection from locking
     * up an application entirely.  A browser 'refresh' will
     * get the next connection while the faulty
     * connection is cleaned up by the housekeeping thread.
     * 
     * If the min number of threads are ever exhausted, new
     * threads are added up the the max thread count.
     * Finally, if all threads are in use, this method waits
     * 2 seconds and tries again, up to ten times.  After that, it
     * returns a null.
     */
    public Connection getConnection() 
    { 
        //log.write("DbConnectionBroker.getConnection()");
        Connection conn=null;
        if(available){
            boolean gotOne = false;
            for(int outerloop=1; outerloop<=10; outerloop++) {
                try  {
                    int loop=0;
                    int roundRobin = connLast + 1;
                    if(roundRobin >= currConnections) roundRobin=0;
                    do {
                        synchronized(connStatus) {
                            if((connStatus[roundRobin] < 1) && (! connPool[roundRobin].isClosed())) {
                                    conn = connPool[roundRobin];
                                    connStatus[roundRobin]=1;
                                    connLockTime[roundRobin] = System.currentTimeMillis();
                                    connLast = roundRobin;
                                    gotOne = true;
                                    break;
                            } else {
                                loop++;
                                roundRobin++;
                                if(roundRobin >= currConnections) roundRobin=0;
                            }
                        } //END synchronized(connStatus)
                    } //END do
                    while((gotOne==false)&&(loop < currConnections));
                }catch (SQLException e1) {
                    dbLog.println("Error: " + e1);
                }
                if(gotOne) {
                    break;
                } 
                else {
                    synchronized(this) {  // Add new connections to the pool
                        if(currConnections < maxConns) {
                            try {
                                createConn(currConnections);
                                currConnections++;
                            } catch(SQLException e) {
                                if(debugLevel > 0) {
                                    dbLog.println("Error: Unable to create new connection: " + e);
                                }
                            }
                        } //END if(currConnections < maxConns)
                    } //END synchronized(this)
                    try { 
                        Thread.sleep(2000); 
                    }catch(InterruptedException e) {}
                    if(debugLevel > 0) {
                        dbLog.println("-----> Connections Exhausted!  Will wait and try again in loop " + 
                        String.valueOf(outerloop));
                    }
                } //END else
            } // End of try 10 times loop for(int outerloop=1; outerloop<=10; outerloop++)
        } 
        else {
            if(debugLevel > 0) {
                dbLog.println("Unsuccessful getConnection() request during destroy()");
            }
        } // End if(available)    
        if(debugLevel > 2) {
            dbLog.println("Handing out connection " + idOfConnection(conn) + " --> " +
                (new SimpleDateFormat("MM/dd/yyyy  hh:mm:ss a")).format(new java.util.Date()));
        }
        return conn;
    }
    
    /**
     * Returns the local JDBC ID for a connection.
     */
    public int idOfConnection(Connection conn) 
    {
        int match;
        String tag;
        try {
            tag = conn.toString();
        }catch (NullPointerException e1) {
            tag = "none";
        }
        match=-1;
        for(int i=0; i< currConnections; i++) {
            if(connID[i].equals(tag)) {
                match = i;
                break;
            }
        }
        return match;
    }
    
    /**
     * Frees a connection.  Replaces connection back into the main pool for
     * reuse.
     */
    public String freeConnection(Connection conn) 
    {
        dbLog.println("freeConnection("+conn.toString());
        String res="";
        int thisconn = idOfConnection(conn);
        if(thisconn >= 0) {
            connStatus[thisconn]=0;
            res = "freed " + conn.toString();
            dbLog.println(res + "\n");
        } else {
            if(debugLevel > 0) {
                dbLog.println("----> Error: Could not free connection!!!");
            }
        }
        return res;
    }
    
    /**
     * Returns the age of a connection -- the time since it was handed out to
     * an application.
     */
    public long getAge(Connection conn) // Returns the age of the connection in millisec.
    { 
        int thisconn = idOfConnection(conn);
        return System.currentTimeMillis() - connLockTime[thisconn];
    }

    private void createConn(int i) throws SQLException 
    {
        Date now = new Date();
        try {
            Class.forName (dbDriver);
            connPool[i] = DriverManager.getConnection(dbServer,dbLogin,dbPassword);
            connStatus[i]=0;
            connID[i]=connPool[i].toString();
            connLockTime[i]=0;
            connCreateDate[i] =  now.getTime();
        } catch (ClassNotFoundException e2) {
            if(debugLevel > 0) {
                dbLog.println("Error creating connection: " + e2);
            }
        }
        dbLog.println(now.toString() + "  Opening connection " + String.valueOf(i) + 
                    " " + connPool[i].toString() + ":");
    }
    
    /**
     * Shuts down the housekeeping thread and closes all connections 
     * in the pool. Call this method from the destroy() method of the servlet.
     */

    /**
     * Multi-phase shutdown.  having following sequence:
     * <OL>
     * <LI><code>getConnection()</code> will refuse to return connections.
     * <LI>The housekeeping thread is shut down.<br>
     *    Up to the time of <code>millis</code> milliseconds after shutdown of
     *    the housekeeping thread, <code>freeConnection()</code> can still be
     *    called to return used connections.
     * <LI>After <code>millis</code> milliseconds after the shutdown of the
     *    housekeeping thread, all connections in the pool are closed.
     * <LI>If any connections were in use while being closed then a
     *    <code>SQLException</code> is thrown.
     * <LI>The dbLog is closed.
     * </OL><br>
     * Call this method from a servlet destroy() method.
     *
     * @param      millis   the time to wait in milliseconds.
     * @exception  SQLException if connections were in use after 
     * <code>millis</code>.
     */
    public void destroy(int millis) throws SQLException 
    {
        // Checking for invalid negative arguments is not necessary,
        // Thread.join() does this already in runner.join().
        // Stop issuing connections
        available=false;

        // Shut down the background housekeeping thread
        runner.interrupt();

        // Wait until the housekeeping thread has died.
        try { runner.join(millis); } 
        catch(InterruptedException e){} // ignore 

        // The housekeeping thread could still be running
        // (e.g. if millis is too small). This case is ignored.
        // At worst, this method will throw an exception with the 
        // clear indication that the timeout was too short.

        long startTime=System.currentTimeMillis();

        // Wait for freeConnection() to return any connections
        // that are still used at this time.
        int useCount;
        while((useCount=getUseCount())>0 && System.currentTimeMillis() - startTime <=  millis) {
            try { Thread.sleep(500); }
            catch(InterruptedException e) {} // ignore 
        }

        // Close all connections, whether safe or not
        for(int i=0; i < currConnections; i++) {
            try {
                connPool[i].close();
            } catch (SQLException e1) {
                if(debugLevel > 0) {
                    dbLog.println("Cannot close connections on Destroy");
                }
            }
        }
        if(useCount > 0) {
            //bt-test successful
            String msg="Unsafe shutdown: Had to close "+useCount+
            " active DB connections after "+millis+"ms";
            dbLog.println(msg);
            // Close all open files
            //dbLog.close();
            // Throwing following Exception is essential because servlet authors
            // are likely to have their own error logging requirements.
            throw new SQLException(msg);
        }
        // Close all open files
        //dbLog.close();
    }//End destroy()


    /**
     * Less safe shutdown.  Uses default timeout value.
     * This method simply calls the <code>destroy()</code> method 
     * with a <code>millis</code>
     * value of 10000 (10 seconds) and ignores <code>SQLException</code> 
     * thrown by that method.
     * @see     #destroy(int)
     */
    public void destroy() 
    { 
        try {
            destroy(10000);
        }catch(SQLException e) {}
    }



    /**
     * Returns the number of connections in use.
     */
    // This method could be reduced to return a counter that is
    // maintained by all methods that update connStatus.
    // However, it is more efficient to do it this way because:
    // Updating the counter would put an additional burden on the most
    // frequently used methods; in comparison, this method is
    // rarely used (although essential).
    public int getUseCount() 
    {
        int useCount=0;
        synchronized(connStatus) {
            for(int i=0; i < currConnections; i++) {
                if(connStatus[i] > 0) { // In use
                    useCount++;
                }
            }
        }
        return useCount;
    }//End getUseCount()

    /**
     * Returns the number of connections in the dynamic pool.
     */
    public int getSize() 
    {
        return currConnections;
    }

}// End class
               

