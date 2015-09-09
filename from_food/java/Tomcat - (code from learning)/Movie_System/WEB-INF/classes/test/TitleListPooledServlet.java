package test;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import HTML.*;
import ConnectionPool.*;

public class TitleListPooledServlet extends HttpServlet {
    private ConnectionPool pool = null;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pool = new ConnectionPool();
        pool.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
            pool.setURL("jdbc:odbc:Movie");
            pool.setSize(4);
            pool.setUsername("james");
            pool.setPassword("Z23&*FjhsldZDdxc");
            try{
                pool.initializePool();
            }catch(Exception e){
                System.err.println(e.getMessage() + " in TitleListPooledServlet.init() pool.initializePool()");
            }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String search_string = "Krull";
        Connection conn = null;
        HTMLDocument doc = new HTMLDocument("Title List Pooled Servlet");
        try{
            conn = pool.getConnection();
            System.err.println("Connection Established.");
            if(conn != null){
                Statement stmt = conn.createStatement();
                String q = "SELECT * FROM Titles, Categories WHERE Titles.title_name = '" + search_string + "'";
                System.err.println(q);
                ResultSet rs = stmt.executeQuery(q);
                HTMLTable table = new HTMLTable();
                table.setBorder(1);
                HTMLTableRow row = null;
                HTMLTableCell cell= null;
                row = new HTMLTableRow();
                cell = new HTMLTableCell(HTMLTableCell.HEADING);
                cell.addObject(new HTMLText("ID"));
                row.addObject(cell);
                cell = new HTMLTableCell(HTMLTableCell.HEADING);
                cell.addObject(new HTMLText("Name"));
                row.addObject(cell);
                cell = new HTMLTableCell(HTMLTableCell.HEADING);
                cell.addObject(new HTMLText("Price"));
                row.addObject(cell);
                cell = new HTMLTableCell(HTMLTableCell.HEADING);
                cell.addObject(new HTMLText("Quantity"));
                row.addObject(cell);
                cell = new HTMLTableCell(HTMLTableCell.HEADING);
                cell.addObject(new HTMLText("Category"));
                row.addObject(cell);
                cell = new HTMLTableCell(HTMLTableCell.HEADING);
                cell.addObject(new HTMLText("CategoryID"));
                row.addObject(cell);
                cell = new HTMLTableCell(HTMLTableCell.HEADING);
                cell.addObject(new HTMLText("Category"));
                row.addObject(cell);
                table.addObject(row);
                while(rs.next()){
                    row = new HTMLTableRow();
                    
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new Integer(rs.getInt("title_id")).toString()));
                    row.addObject(cell);
                    
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(rs.getString("title_name")));
                    row.addObject(cell);
                    
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new Float(rs.getFloat("title_id")).toString()));
                    row.addObject(cell);
                    
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new Integer(rs.getInt("quantity")).toString()));
                    row.addObject(cell);
                    
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new Integer(rs.getInt("category")).toString()));
                    row.addObject(cell);
                    
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new Integer(rs.getInt("category_id")).toString()));
                    row.addObject(cell);
                    
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(rs.getString("category_name")));
                    row.addObject(cell);
                }
                rs.close();
                doc.addObject(table);
            }
        }catch(SQLException e){
            System.err.println("SQLException " + e.getMessage());
        }catch(Exception e){
            System.err.println("Exception " + e.getMessage());
        }finally{
            pool.releaseConnection(conn);
        }
        out.println(doc.toHTML());
        out.close();
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
    
    public void destroy() {
        pool.emptyPool();
    }
}
