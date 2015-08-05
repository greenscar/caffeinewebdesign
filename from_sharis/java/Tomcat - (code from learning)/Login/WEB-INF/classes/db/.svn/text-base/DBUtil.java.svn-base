package db;
import java.sql.*;
import java.io.*;
import java.util.*;

public class DBUtil {
    private String dbURL = "jdbc:odbc:psnt";
    private String dbDriverName = "sun.jdbc.odbc.JdbcOdbcDriver";
    private String user = "helpdesk";
    private String pwd = "helpdesk";
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stmt = null;
    private boolean driverLoaded = false;
    private boolean connected = false;
    private boolean statementCreated = false;
    
    // getXXX
    public ResultSet getRS(){
        return this.rs;
    }
    public String getdbDriverName(){
        return dbDriverName;
    }
    public Connection getdbConnection(){
        return conn;
    }
    public Statement getdbStmt(){
        return stmt;
    }
    
    // setXXX
    public void setdbDriverName(String name){
        dbDriverName = name;
        driverLoaded = false;
    }
    public void setdbURL(String name){
        this.dbURL = name;
        connected = false;
    }
    
    public void loadDriver() throws Exception{
        Class.forName(dbDriverName);
    }
    
    public void getConnected() throws Exception{
        if(!driverLoaded){
            loadDriver();
            driverLoaded = true;
        }
        conn = DriverManager.getConnection(dbURL, user, pwd);
        connected = true;
    }
    public void createDBStatement() throws Exception{
        if(!connected) 
            getConnected();
        stmt = conn.createStatement();
    }
    
    public PreparedStatement createPreparedStatement(String stmt) throws Exception{
        if(!connected)
            getConnected();
        return conn.prepareStatement(stmt);
    }
    
    public ResultSet executePreparedStatement(PreparedStatement pstmt)
        throws Exception{
            if(!connected)
                getConnected();
            return pstmt.executeQuery();
    }
    public int executePreparedStmtUpd(PreparedStatement pstmt)
        throws Exception{
            if(!connected)
                getConnected();
            return pstmt.executeUpdate();
    }
    public ResultSet executeQuery(String query) throws Exception{
        // Ensure we are ready to do it.
        if(!connected)
            getConnected();
        if(!statementCreated){
            createDBStatement();
            statementCreated = true;
        }
        
        // Execute query
        rs = stmt.executeQuery(query);
        return rs;
    }
    public int executeUpdDBQuery(String q) throws Exception{
        // Ensure we are ready to do it.
        if(!connected)
            getConnected();
        if(!statementCreated){
            createDBStatement();
            statementCreated = true;
        }
        
        // Execute query
        int retVal = stmt.executeUpdate(q);
        
        // Return the number of rows updated
        return retVal;
    }
    
}
