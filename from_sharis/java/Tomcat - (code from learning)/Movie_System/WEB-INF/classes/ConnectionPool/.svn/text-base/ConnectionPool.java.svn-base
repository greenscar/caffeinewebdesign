package ConnectionPool;
import java.sql.*;
import java.util.*;
public class ConnectionPool{
    private String driver = null;
    private String url = null;
    private int size = 0;
    private String username = null;
    private String password = null;
    private Vector pool = null;
    
    public ConnectionPool() {
    }
    
    public void setDriver(String value){
        driver = value;
    }
    public String getDriver(){
        return driver;
    }
    public void setURL(String v){
        url = v;
    }
    public String getURL(){
        return url;
    }
    public void setSize(int v){
        if(v > 1)
            size = v;
    }
    public int getSize(){
        return size;
    }
    public void setUsername(String un){
        if(un != null)
            username = un;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String v){
        password = v;
    }
    public String getPassword(){
        return password;
    }
    private Connection createConnection() throws Exception{
        Connection con = null;
        con = DriverManager.getConnection(url, username, password);
        return con;
    }
    public synchronized void initializePool() throws Exception{
        if(driver == null)
            throw new Exception("No driver name specified!");
        if(url == null)
            throw new Exception("No URL specified!");
        if(size < 1)
            throw new Exception("Pool size < 1!");
        try{
            // Load the Driver class file
            Class.forName(driver);
            
            for(int x=0; x<size;x++){
                Connection con = createConnection();
                if(con != null){
                    //Create a PooledConnection to encapsulate the real JDBC Con
                    PooledConnection pcon = new PooledConnection(con);
                    addConnection(pcon);
                }
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.err.println(e.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    // Add the PooledConnection to the pool
    private void addConnection(PooledConnection v){
        // If the pool is null, create a new Vector
        if(pool == null)
            pool = new Vector(size);
        //Add the PooledConnection Object to the vector
        pool.addElement(v);
    }
    public synchronized void releaseConnection(Connection con){
        for(int x=0; x<pool.size();x++){
            PooledConnection pcon = (PooledConnection)pool.elementAt(x);
            // Check for correct Connection
            if(pcon.getConnection() == con){
                // Set inuse to false, which releases it for use
                pcon.setInUse(false);
                break;
            }
        }
    }
    //Find an available connection
    public synchronized Connection getConnection() throws Exception{
        System.err.println("START ConnectionPool.getConnection()");
        PooledConnection pcon = null;
        System.err.print("pool size = ");
        System.err.println(pool.size());
        // Find a connection not in use
        for(int x=0; x<pool.size();x++){
            pcon = (PooledConnection)pool.elementAt(x);
            //Check to see if this connection is in use
            if(!pcon.inUse()){
                // Mark it as in use
                pcon.setInUse(true);
                // Return the JDBC conn stored in the PooledConnection object
                return pcon.getConnection();
            }
        }
        //Could not find a free connection, create and add a new one
        try{
            //Create a new JDBC Connection
            Connection con = createConnection();
            //Create a new PooledConnection, passing it the JDBC Connection
            pcon = new PooledConnection(con);
            //Mark the connection in use
            pcon.setInUse(true);
            //Add the new PooledConnection object to the pool
            pool.addElement(pcon);
        }catch(Exception e){
            System.err.println(e.getMessage() + " in ConnectionPool.getConnection()");
        }
        System.err.println("END ConnectionPool.getConnection()");
        return pcon.getConnection();
    }
    //When shutting down the pool, you first need to empty it.
    public synchronized void emptyPool(){
        // Iterate over the entire pool closing the JDBC connections
        for(int x=0; x<pool.size();x++){
            PooledConnection pcon = (PooledConnection)pool.elementAt(x);
            //If the PooledConnection is not in use, close it.
            if(pcon.inUse() == false){
                pcon.close();
            }
            else{
                // If it's still in use, sleep 30 secs and force close
                try{
                    java.lang.Thread.sleep(30000);
                    pcon.close();
                }catch(InterruptedException ie){
                    System.err.println(ie.getMessage() + " in ConnectionPool.emptyPool()");
                }
            }
        }
    }
}