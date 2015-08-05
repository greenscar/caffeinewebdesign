package test;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import HTML.*;
import ConnectionPool.*;

public class TitleListGlobalPooledServlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    public void destroy() {
        
    }
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
        ConnectionPool pool = null;
        HTMLDocument document = new HTMLDocument("Title List Pooled Servlet");
        try{
            pool = (ConnectionPool)getServletContext().getAttribute("CONNECTION_POOL");
            con = pool.getConnection();
            if(con != null){
                Statement statement = con.createStatement();
                
                //Use the created statement to SELECT the DATA 
                String query = "SELECT * FROM Titles, Categories "
                    + "WHERE Titles.category = Categories.category_id"
                    + " ORDER BY title_id";
                ResultSet rs = statement.executeQuery(query);
                //Create the HTMLTable
                HTMLTable table = new HTMLTable();
                table.setBorder(1);
                HTMLTableRow row = null;
                HTMLTableCell cell = null;
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
                table.addObject(row);
                while(rs.next()){
                    row = new HTMLTableRow();
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    HTMLText txt = new HTMLText(new Integer(rs.getInt("title_id")).toString());
                    cell.addObject(txt);
                    row.addObject(cell);
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new String(rs.getString("title_name"))));
                    row.addObject(cell);
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new Float(rs.getFloat("price")).toString()));
                    row.addObject(cell);
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new Integer(rs.getInt("quantity")).toString()));
                    row.addObject(cell);
                    cell = new HTMLTableCell(HTMLTableCell.DATA);
                    cell.addObject(new HTMLText(new String(rs.getString("category_name"))));
                    row.addObject(cell);
                    table.addObject(row);
                }
                //Close the ResultSet
                rs.close();
                document.addObject(table);
            }
        }catch(SQLException e){
            System.err.println("SQLException " + e.getMessage() + " in Test.processRequest()");
        }catch(Exception e){
            System.err.println("Exception " + e.getMessage() + " in Test.processRequest()");
        }finally{
            pool.releaseConnection(con);
        }
        out.println(document.toHTML());
        out.close();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public String getServletInfo() {
        return "TitleListGlobalPooledServlet description";
    }
    
}
