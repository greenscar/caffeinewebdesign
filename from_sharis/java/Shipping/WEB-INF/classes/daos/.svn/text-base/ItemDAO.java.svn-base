package daos;
import java.sql.*;
import logging.Secretary;
import vos.*;
public class ItemDAO {
    
    private Connection conn = null;
    //private DbConnectionBroker myBroker;
    private ItemVO itemVO;
    /** Creates a new instance of ItemDAO */
    public ItemDAO() {
    }
    
    public void setVO(ItemVO item)
    {
        this.itemVO = item;
    }
    
    
    /*
     * METHOD NAME : dbInsertVO
     * ARGUMENTS : the parent subCategory ID
     * REQUIRE: setVO function has been called to set the local ItemVO object
     * FUNCTION : Insert the local ItemVO object into the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbInsertVO(Connection conn, int parentID)
    {
        Secretary.startFxn("ItemDAO.dbInsertVO("+parentID+")");
        String insertStr;
        PreparedStatement insertStmt = null;
        boolean successful = false;
        //conn = myBroker.getConnection();
        Secretary.write("INSERT INTO shp_item (item_id, description, cost, "
                       + "gl_acct, active, sub_cat_id, qty_id) VALUES ("
                       + this.itemVO.getID() + ", "
                       + this.itemVO.getName() + ", "
                       + this.itemVO.getCost() + ", "
                       + this.itemVO.getGlNum() + ", "
                       + this.itemVO.getActive() + ", "
                       + this.itemVO.getOrderQuantityID() + ", "
                       + parentID + ");");        
        insertStr = "INSERT INTO shp_item (item_id, description, cost, "
                       + "gl_acct, active, sub_cat_id, qty_id) VALUES "
                       + "(?, ?, ?, ?, ?, ?, ?);";        
        try{
            insertStmt = conn.prepareStatement(insertStr);
            insertStmt.setInt(1, this.itemVO.getID());
            insertStmt.setString(2, this.itemVO.getName());
            insertStmt.setFloat(3, this.itemVO.getCost());
            insertStmt.setInt(4, this.itemVO.getGlNum());
            insertStmt.setBoolean(5, this.itemVO.getActive());
            insertStmt.executeUpdate();
            successful = true;
        }catch(SQLException e){
            Secretary.write("SQLException in ItemDAO.dbInsertVO("+parentID+"): " + e.getMessage());
            successful = false;
        }catch(Exception e){
            Secretary.write("Exception in ItemDAO.dbInsertVO("+parentID+"): " + e.getMessage());
            successful = false;
        }finally{
            try{
                insertStmt.close();
            }catch(SQLException e){
                Secretary.write("SQLException in ItemDAO.dbInsertVO(): " + e.getMessage());
                successful = false;
            }
            //myBroker.freeConnection(conn);
        }
        Secretary.endFxn("ItemDAO.dbInsertVO("+parentID+") => " + successful);
        return successful;
    }
    
    
    /*
     * METHOD NAME : dbUpdateVO
     * ARGUMENTS : The parent subcategory ID
     * REQUIRE: setVO function has been called to set the local ItemVO object
     * FUNCTION : Update the name of the local ItemVO object in the DB.
     *            Update the ID of the parent SubCategory in the DB.
     *            The item_id is not changable in the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbUpdateVO(Connection conn, int parentID)
    {
        Secretary.startFxn("ItemDAO.dbUpdateVO()");
        String updateStr;
        PreparedStatement updateStmt = null;
        boolean successful = false;
        //conn = myBroker.getConnection();
        Secretary.write("UPDATE shp_item SET "
                        + "name = " + this.itemVO.getName()
                        + ", cost = " + this.itemVO.getCost()
                        + ", gl_acct = " + this.itemVO.getGlNum()
                        + ", active = "  + this.itemVO.getActive()
                        + ", sub_cat_id = "  + parentID 
                        + ", qty_id = " + this.itemVO.getOrderQuantityID()
                        + " WHERE item_id = " + this.itemVO.getID());
        
        updateStr = "UPDATE shp_item "
                  + "SET name = ? "
                  + ", cost = ?"
                  + ", gl_acct = ?"
                  + ", active = ?"
                  + ", sub_cat_id = ? "
                  + ", qty_id = ? " 
                  + "WHERE "
                  + "item_id = ?;";
        try{
            updateStmt = conn.prepareStatement(updateStr);
            updateStmt.setString(1, this.itemVO.getName());
            updateStmt.setFloat(2, this.itemVO.getCost());
            updateStmt.setInt(3, this.itemVO.getGlNum());
            updateStmt.setBoolean(4, this.itemVO.getActive());
            updateStmt.setInt(5, parentID);
            updateStmt.setInt(6, this.itemVO.getOrderQuantityID());
            updateStmt.setInt(7, this.itemVO.getID());
            updateStmt.executeUpdate();
            successful = true;
        }catch(SQLException e){
            Secretary.write("SQLException in ItemDAO.dbUpdateVO(): " + e.getMessage());
            successful = false;
        }catch(Exception e){
            Secretary.write("Exception in ItemDAO.dbUpdateVO(): " + e.getMessage());
            successful = false;
        }finally{
            try{
                updateStmt.close();
            }catch(SQLException e){
                Secretary.write("SQLException in ItemDAO.dbUpdateVO(): " + e.getMessage());
                successful = false;
            }
            //myBroker.freeConnection(conn);
        }
        Secretary.endFxn("ItemDAO.dbUpdateVO() => " + successful);
        return successful;
    }
    
    
}
