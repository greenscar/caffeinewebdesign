/*
 * MsSQLTest.java
 * This file will test the connection to a MSSQL database.
 * http://localhost:6669/fromBook/MSSQLServlet
 * Created on May 8, 2003, 10:37 AM
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DB_Test extends HttpServlet {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PrintWriter out = null;
    /** Initializes the servlet.*/
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    /** Destroys the servlet.*/
    public void destroy() {}
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        this.out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>MSSQL Test</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table border=\"1\" cellpadding=\"5\""
            + " cellspacing=\"0\">");
        out.println("<tr><th>login</th><th>firstName</th>"
            + "<th>lastName</th><th>storeNum</th><th>secLvl</th><th>pwd</th>"
            + "<th>dateCreated</th><th>lastLogin</th></tr>");
        connectToDB();
        try{
            String queryString = "SELECT * FROM users";
            statement =  connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while(resultSet.next()){
                out.println("<tr><td>"
                    + resultSet.getString("login")
                    + "</td><td>"
                    + resultSet.getString("firstName")
                    + "</td><td>"
                    + resultSet.getString("lastName")
                    + "</td><td>"
                    + resultSet.getString("storeNum")
                    + "</td><td>"
                    + resultSet.getString("secLvl")
                    + "</td><td>"
                    + resultSet.getString("pwd")
                    + "</td><td>"
                    + resultSet.getString("dateCreated")
                    + "</td><td>"
                    + resultSet.getString("lastLogin")
                    + "</td></tr>");
            }
            out.println("</table>");
            if(resultSet != null){
                resultSet.close();
            }
        }catch(SQLException e){
            out.println("SQL Problem: " + e.getMessage());
            out.println("SQL State: " + e.getSQLState());
            out.println("Vendor Error: " + e.getErrorCode());
        }
        if(connection != null){
            closeDBConn();
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    protected void closeDBConn(){
        try{
            connection.close();
        }catch(SQLException e){
            out.println(e.getMessage());
        }
    }
    protected void connectToDB(){
        String driverName = "sun.jdbc.odbc.JdbcOdbcDriver"; // JDBC driver
        String connectionURL = "jdbc:odbc:psnt"; // JDBC connection
        String userName = "helpdesk";
        String password = "helpdesk";
        try{
            //Load Driver that comes with JDK
            Class.forName(driverName); 
            //Establish the connection
            connection = DriverManager.getConnection(connectionURL, userName, password);
        }catch(ClassNotFoundException e){
            out.println("Couldn't find the database driver: "
                + e.getMessage());
        }catch(SQLException e){
            out.println("SQL Problem: " + e.getMessage());
            out.println("SQL State: " + e.getSQLState());
            out.println("Vendor Error: " + e.getErrorCode());
        }
    }
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
}
