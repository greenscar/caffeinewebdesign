package ConnectionPool;

import java.sql.*;
import java.util.*;

public class ConnectionPool {

  // JDBC Driver Name
  private String driver = new String("");
  // URL of database
  private String url = new String("");
  // Initial number of connections.
  private int size = 0;
  // Username
  private String username = new String("");
  // Password
  private String password = new String("");
  // Vector of JDBC Connections
  private Vector pool = null;

  public ConnectionPool() {

  }

  // Set the value of the JDBC Driver
  public void setDriver(String value) {

    if ( value != null ) {

      driver = value;
    }
  }

  // Get the value of the JDBC Driver
  public String getDriver() {

    return driver;
  }

  // Set the URL Pointing to the Datasource
  public void setURL(String value ) {

    if ( value != null ) {

      url = value;
    }
  }

  // Get the URL Pointing to the Datasource
  public String getURL() {

    return url;
  }

  // Set the initial number of connections
  public void setSize(int value) {

    if ( value > 1 ) {

      size = value;
    }
  }

  // Get the initial number of connections
  public int getSize() {

    return size;
  }

  // Set the username
  public void setUsername(String value) {

    if ( value != null ) {

      username = value;
    }
  }

  // Get the username
  public String getUserName() {

    return username;
  }

  // Set the password
  public void setPassword(String value) {

    if ( value != null ) {

      password = value;
    }
  }

  // Get the password
  public String getPassword() {

    return password;
  }

  // Creates and returns a connection
  private Connection createConnection() throws Exception {

    Connection con = null;

    // Create a Connection
    con = DriverManager.getConnection(url,
      username, password);

    return con;
  }

  // Initialize the pool
  public synchronized void initializePool() throws Exception {

    // Check our initial values
    if ( driver == null ) {

      throw new Exception("No Driver Name Specified!");
    }
    if ( url == null ) {

      throw new Exception("No URL Specified!");
    }
    if ( size < 1 ) {

      throw new Exception("Pool size is less than 1!");
    }

    // Create the Connections
    try {

      // Load the Driver class file
      Class.forName(driver);

      // Create Connections based on the size member
      for ( int x = 0; x < size; x++ ) {

        System.err.println("Opening JDBC Connection " + x);

        Connection con = createConnection();

        if ( con != null ) {

          // Create a PooledConnection to encapsulate the
          // real JDBC Connection
          PooledConnection pcon = new PooledConnection(con);
          // Add the Connection the pool.
          addConnection(pcon);
        }
      }
    }
    catch (SQLException sqle) {

      System.err.println(sqle.getMessage());
    }
    catch (ClassNotFoundException cnfe) {

      System.err.println(cnfe.getMessage());
    }
    catch (Exception e) {

      System.err.println(e.getMessage());
    }
  }

  // Adds the PooledConnection to the pool
  private void addConnection(PooledConnection value) {

    // If the pool is null, create a new vector
    // with the initial size of "size"
    if ( pool == null ) {

      pool = new Vector(size);
    }
    // Add the PooledConnection Object to the vector
    pool.addElement(value);
  }

  public synchronized void releaseConnection(Connection con) {

    // find the PooledConnection Object
    for ( int x = 0; x < pool.size(); x++ ) {

      PooledConnection pcon =
        (PooledConnection)pool.elementAt(x);
      // Check for correct Connection
      if ( pcon.getConnection() == con ) {

        System.err.println("Releasing Connection " + x);
        // Set it's inuse attribute to false, which
        // releases it for use
        pcon.setInUse(false);
        break;
      }
    }
  }

  // Find an available connection
  public synchronized Connection getConnection()
    throws Exception {

    PooledConnection pcon = null;

    // find a connection not in use
    for ( int x = 0; x < pool.size(); x++ ) {

      pcon = (PooledConnection)pool.elementAt(x);

      // Check to see if the Connection is in use
      if ( pcon.inUse() == false ) {

        // Mark it as in use
        pcon.setInUse(true);
        // return the JDBC Connection stored in the
        // PooledConnection object
        return pcon.getConnection();
      }
    }

    // Could not find a free connection,
    // create and add a new one
    try {

        // Create a new JDBC Connection
        Connection con = createConnection();
        // Create a new PooledConnection, passing it the JDBC
        // Connection
        pcon = new PooledConnection(con);
        // Mark the connection as in use
        pcon.setInUse(true);
        // Add the new PooledConnection object to the pool
        pool.addElement(pcon);
    }
    catch (Exception e) {

      System.err.println(e.getMessage());
    }
    // return the new Connection
    return pcon.getConnection();
  }

  // When shutting down the pool, you need to first empty it.
  public synchronized void emptyPool() {

    // Iterate over the entire pool closing the
    // JDBC Connections.
    for ( int x = 0; x < pool.size(); x++ ) {

      System.err.println("Closing JDBC Connection " + x);

      PooledConnection pcon =
        (PooledConnection)pool.elementAt(x);

      // If the PooledConnection is not in use, close it
      if ( pcon.inUse() == false ) {

        pcon.close();
      }
      else {

        // If it still in use, sleep for 30 seconds and
        // force close.
        try {

          java.lang.Thread.sleep(30000);
          pcon.close();
        }
        catch (InterruptedException ie) {

          System.err.println(ie.getMessage());
        }
      }
    }
  }
}
