package daos;
import java.sql.*;
import logging.Secretary;
import vos.*;

/**
 *
 * @author  jsandlin
 */
public class OrderDAO 
{
    private Connection conn = null;
    private OrderVO orderVO = null;
    
    /** Creates a new instance of OrderDAO */
    public OrderDAO() 
    {
    }
    
    public void setVO(OrderVO ov)
    {
        this.orderVO = ov;
    }
    public OrderVO getVO()
    {
        return this.orderVO;
    }
    /*
     * METHOD NAME : dbInsertVO
     * ARGUMENTS : n/a
     * REQUIRE: setVO function has been called to set the local OrderVO object
     * FUNCTION : Insert the empty order into the DB
     *            Assign the created order number to the OrderVO object.
     * RETURN:  n/a
     */
    public void dbInsertVO(Connection conn)
    {
        
       /*
        * 1) Insert order into shp_order
        * 2) Query shp_order for order_num
        * 3) Insert recipients into shp_recipient
        * Both 1 & 2 will be done directly because they only occur 1 time.
        *   However, 3 will be done multiple times (based on the number of
        *   recipients this order has). Therefore, it will be done using a 
        *   prepared statement.
        */        
        String insertShpOrder = "INSERT INTO shp_order "
                + "(date, contact, finalized) "
                + "VALUES ('"
                + new java.sql.Timestamp(this.orderVO.getDatePlaced().getTime()) + "', '"
                + this.orderVO.getContact() + "', "
                + "0)";
        String selectKey = "SELECT MAX(order_num) FROM shp_order";
        String insertShpRecipient = "INSERT INTO shp_recipient "
                + "(order_num, ship_to_dept_id, bill_to_dept_id) "
                + "VALUES "
                + "(?, ?, ?)";
        PreparedStatement insertShpStmt = null;
        boolean successful = false;
        try
        {
            Statement stmt = conn.createStatement();
            logging.Secretary.write(insertShpOrder);
            conn.setAutoCommit(false);
            // 1) Insert the order into shp_order
            logging.Secretary.write(insertShpOrder);
            stmt.executeUpdate(insertShpOrder);
            // 2) Get the new order number from shp_order
            logging.Secretary.write(selectKey);
            ResultSet rs = stmt.executeQuery(selectKey);
            rs.next();            
            this.orderVO.setOrderNum(rs.getInt(0));
            // 3) Insert all recipients into shp_recipient
            insertShpStmt = conn.prepareStatement(insertShpRecipient);
            insertShpStmt.setInt(1, this.orderVO.getOrderNum());
            int numShipTo = this.orderVO.getShipToSize();
            String toLog = null;
            for(int k=0; k < numShipTo; k++)
            {
                toLog = "INSERT INTO shp_recipient "
                        + "(order_num, ship_to_dept_id, bill_to_dept_id) "
                        + "VALUES (" + this.orderVO.getOrderNum()
                        + ", " + this.orderVO.getShipToAt(k)
                        + ", " + this.orderVO.getBillToAt(k)
                        + ");";
                logging.Secretary.write(toLog);
                insertShpStmt.setInt(2, this.orderVO.getShipToAt(k));
                insertShpStmt.setInt(3, this.orderVO.getBillToAt(k));
                insertShpStmt.executeUpdate();
            }
            conn.commit();
            conn.setAutoCommit(true);
        }catch(SQLException e){
            logging.Secretary.write("SQLException in OrderDAO.dbInsertVO -> " + e);
        }catch(Exception e){
            logging.Secretary.write("Exception in OrderDAO.dbInsertVO -> " + e);
        }
    }
}
