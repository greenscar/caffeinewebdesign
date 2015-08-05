package ConnectionPool;
import java.sql.*;

public class PooledConnection {
    private Connection connection = null;
    private boolean inuse = false;
    
    /* 
     * Constructor that takes the passed in JDBC Connection and 
     *      stores it in the connection attribute.
     */
    public PooledConnection(Connection v) {
        connection = v;
    }
    
    // Returns a reference to the JDBC Connection
    public Connection getConnection(){
        // Get the JDBC Connection
        return connection;
    }
    
    //Set the status of the PooledConnection
    public void setInUse(boolean value){
        inuse = value;
    }
    public boolean inUse(){
        return inuse;
    }
    public void close(){
        try{
            connection.close();
        }catch(SQLException e){
            System.err.println(e.getMessage() + " in PooledConnection.close()");
        }
    }
}