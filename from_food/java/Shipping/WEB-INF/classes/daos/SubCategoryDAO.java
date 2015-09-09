package daos;
import java.sql.*;
import htmlConverter.EscapeChars;

import logging.Secretary;
import vos.*;
public class SubCategoryDAO {
    
    private Connection conn = null;
    //private Connection conn;
    private SubCategoryVO subCategoryVO;
    private ItemDAO itemDAO;
    
    public SubCategoryDAO() {
        /*
        try{
            myBroker = new DbConnectionBroker();
        }catch(java.io.IOException e){
            Secretary.write("IOException in SubCategoryDAO constructor: " + e.getMessage());
        }
         */
    }
    public void setVO(SubCategoryVO c)
    {
        this.subCategoryVO = c;
        this.itemDAO = new ItemDAO();
        if(this.subCategoryVO.getItem() != null)
        {
            this.itemDAO.setVO(this.subCategoryVO.getItem());
        }
    }
    private SubCategoryVO getVO()
    {
        return this.subCategoryVO;
    }
    /*
     * METHOD NAME : dbInsertVO
     * ARGUMENTS : the parent category ID
     * REQUIRE: setVO function has been called to set the local SubCategoryVO object
     * FUNCTION : Insert the local SubCategoryVO object into the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbInsertVO(Connection conn, int parentID)
    {
        Secretary.startFxn("SubCategoryDAO.dbInsertVO("+parentID+")");
        String insertStr;
        PreparedStatement insertStmt = null;
        boolean successful = false;
        //conn = myBroker.getConnection();
        Secretary.write("INSERT INTO shp_sub_category (sub_cat_id, cat_id, name) VALUES ("
            + this.subCategoryVO.getID() + ", " + parentID + ", '" + this.subCategoryVO.getName() + "');");
        insertStr = "INSERT INTO shp_sub_category "
                  + "(sub_cat_id, cat_id, name) VALUES "
                  + "(?, ?, ?);";
        try{
            insertStmt = conn.prepareStatement(insertStr);
            insertStmt.setInt(1, this.subCategoryVO.getID());
            insertStmt.setInt(2, parentID);
            insertStmt.setString(3, this.subCategoryVO.getName());
            insertStmt.executeUpdate();
            successful = true;
        }catch(SQLException e){
            Secretary.write("SQLException in SubCategoryDAO.dbInsertVO("+parentID+"): " + e.getMessage());
            successful = false;
        }catch(Exception e){
            Secretary.write("Exception in SubCategoryDAO.dbInsertVO("+parentID+"): " + e.getMessage());
            successful = false;
        }finally{
            try{
                insertStmt.close();
            }catch(SQLException e){
                Secretary.write("SQLException in SubCategoryDAO.dbInsertVO(): " + e.getMessage());
                successful = false;
            }
            //myBroker.freeConnection(conn);
        }
        Secretary.endFxn("SubCategoryDAO.dbInsertVO("+parentID+") => " + successful);
        return successful;
    }
    /*
     * METHOD NAME : dbUpdateVO
     * ARGUMENTS : The parent category ID
     * REQUIRE: setVO function has been called to set the local SubCategoryVO object
     * FUNCTION : Update the name of the local SubCategoryVO object in the DB.
     *            Update the ID of the parent Category in the DB.
     *            The cat_id is not changable in the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbUpdateVO(Connection conn, int parentID)
    {
        Secretary.startFxn("SubCategoryDAO.dbUpdateVO()");
        String updateStr;
        PreparedStatement updateStmt = null;
        boolean successful = false;
        //conn = myBroker.getConnection();
        Secretary.write("UPDATE shp_sub_category SET cat_id = " + parentID
                  + ", name = " + this.subCategoryVO.getName() + " WHERE "
                  + "sub_cat_id = " + this.subCategoryVO.getID() + ";");
        updateStr = "UPDATE shp_sub_category "
                  + "SET cat_id = ? "
                  + ", name = ? " 
                  + "WHERE "
                  + "sub_cat_id = ?;";
        try{
            updateStmt = conn.prepareStatement(updateStr);
            updateStmt.setInt(1, parentID);
            updateStmt.setString(2, this.subCategoryVO.getName());
            updateStmt.setInt(3, this.subCategoryVO.getID());
            updateStmt.executeUpdate();
        }catch(SQLException e){
            Secretary.write("SQLException in SubCategoryDAO.dbUpdateVO(): " + e.getMessage());
            successful = false;
        }catch(Exception e){
            Secretary.write("Exception in SubCategoryDAO.dbUpdateVO(): " + e.getMessage());
            successful = false;
        }finally{
            try{
                updateStmt.close();
            }catch(SQLException e){
                Secretary.write("SQLException in SubCategoryDAO.dbUpdateVO(): " + e.getMessage());
                successful = false;
            }
            //myBroker.freeConnection(conn);
        }
        Secretary.endFxn("SubCategoryDAO.dbUpdateVO() => " + successful);
        return successful;
    }
    /*
     * METHOD NAME : dbDeleteVO
     * ARGUMENTS : n/a
     * REQUIRE: setVO function has been called to set the local SubCategoryVO object
     * FUNCTION : Update the name of the local SubCategoryVO object in the DB.
     *            The cat_id is not changable in the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbDeleteVO()
    {
        Secretary.write("SubCategoryDAO.dbDeleteVO()");
        Secretary.write("THIS FUNCTION HAS NOT BEEN IMPLEMENTED.");
        return true;
    }
    /*
     * METHOD NAME : dbLoadSubCategoryList
     * ARGUMENTS : n/a
     * REQUIRE: This SubCategoryVO's ID is set.
     * FUNCTION : Load all items of this subcategory from the DB.
     * RETURN: n/a
     */
    public void dbLoadItemList(Connection conn)
    {
        Secretary.startFxn("SubCategoryDAO.dbLoadItemList()");
        String query;
        PreparedStatement statement = null;
        query = "SELECT i.item_id, i.name as item_name, i.cost, i.gl_acct, i.active, oq.qty_id, oq.name as qty_name"
              + " FROM shp_item i, shp_order_quantity oq WHERE i.sub_cat_id = " + this.subCategoryVO.getID()
              + " AND i.qty_id = oq.qty_id"
              + " ORDER BY i.item_id";
        Secretary.write(query);
        try{
            //conn = myBroker.getConnection();
            statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            this.subCategoryVO.clearItemList();
            ItemVO anItem = null;
            while(rs.next())
            {
                anItem = new ItemVO();
                anItem.setID(rs.getInt("item_id"));
                anItem.setName(rs.getString("item_name").trim());
                anItem.setCost(rs.getFloat("cost"));
                anItem.setGlNum(rs.getInt("gl_acct"));
                anItem.setActive(rs.getBoolean("active"));
                anItem.setOrderQuantityID(rs.getInt("qty_id"));
                anItem.setOrderQuantityName(rs.getString("qty_name").trim());
                //Secretary.write("ADD ITEM " + anItem.getName());
                this.subCategoryVO.addItem(anItem);
            }
            this.subCategoryVO.itemIteratorReset();
        }catch(SQLException e){
            Secretary.write("SQLException in SubCategoryDAO.dbLoadItemList(): " + e.getMessage());
        }finally{
            try{
                statement.close();
            }catch(SQLException e){
                Secretary.write("SQLException in SubCategoryDAO.dbLoadItemList(): " + e.getMessage());
            }
            //myBroker.freeConnection(conn);
        }
        Secretary.endFxn("SubCategoryDAO.dbLoadItemList()");
    }
    
    public void dbUpdateItem(Connection conn)
    {
        Secretary.startFxn("CategoryDAO.dbUpdateItem()");
        this.itemDAO.setVO(this.subCategoryVO.getItem());
        this.itemDAO.dbUpdateVO(conn, this.subCategoryVO.getID());
        Secretary.endFxn("CategoryDAO.dbUpdateItem()");
    }
    /*
     * METHOD NAME : 
     * ARGUMENTS : 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:
     */
}
