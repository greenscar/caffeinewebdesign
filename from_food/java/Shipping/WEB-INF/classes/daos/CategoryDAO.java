package daos;
import java.sql.*;
import htmlConverter.EscapeChars;
//import db.DbConnectionBroker;
import vos.*;
import logging.Secretary;
public class CategoryDAO {
    
    //private Connection conn = null;
    //private Connection conn;
    private CategoryVO categoryVO;
    private SubCategoryDAO subCategoryDAO;
    public CategoryDAO() {
        /*
         *
        try{
            conn = new DbConnectionBroker();
        }catch(java.io.IOException e){
            Secretary.write("IOException in CategoryDAO constructor: " + e.getMessage());
        }
         */
    }
    public void setVO(CategoryVO c)
    {
        //Secretary.startFxn("CategoryDAO.setVO(CategoryVO)");
        this.categoryVO = c;
        this.subCategoryDAO = new SubCategoryDAO();
        if(this.categoryVO.getSubCategory() != null)
        {
            this.subCategoryDAO.setVO(this.categoryVO.getSubCategory());
        }
        //Secretary.endFxn("CategoryDAO.setVO(CategoryVO)");
    }
    public CategoryVO getVO()
    {
        //Secretary.startFxn("CategoryDAO.getVO()");
        CategoryVO toReturn = this.categoryVO;
        //Secretary.endFxn("CategoryDAO.getVO()");
        return toReturn;
    }
    /*
     * METHOD NAME : dbInsertVO
     * ARGUMENTS : n/a
     * REQUIRE: setVO function has been called to set the local CategoryVO object
     * FUNCTION : Insert the local CategoryVO object into the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbInsertVO(Connection conn)
    {
        //Secretary.startFxn("CategoryDAO.dbInsertVO()");
        String insertStr;
        PreparedStatement insertStmt = null;
        boolean successful = false;
        //conn = conn.getConnection();
        Secretary.write("INSERT INTO shp_category (cat_id, nick, name) VALUES ("
                    + this.categoryVO.getID() + this.categoryVO.getNick() + ", " 
                    + this.categoryVO.getName() + ");");
        insertStr = "INSERT INTO shp_category "
                  + "(cat_id, nick, name) VALUES "
                  + "(?, ?, ?);";
        try{
            insertStmt = conn.prepareStatement(insertStr);
            insertStmt.setInt(1, this.categoryVO.getID());
            insertStmt.setString(2, this.categoryVO.getNick());
            insertStmt.setString(3, this.categoryVO.getName());
            insertStmt.executeUpdate();
            successful = true;
        }catch(SQLException e){
            Secretary.write("SQLException in CategoryDAO.dbInsertVO(): " + e.getMessage());
            successful = false;
        }catch(Exception e){
            Secretary.write("Exception in CategoryDAO.dbInsertVO(): " + e.getMessage());
            successful = false;
        }finally{
            try{
                insertStmt.close();
            }catch(SQLException e){
                Secretary.write("SQLException in CategoryDAO.dbInsertVO(): " + e.getMessage());
                successful = false;
            }
            //conn.freeConnection(conn);
        }
        //Secretary.endFxn("CategoryDAO.dbInsertVO()");
        return successful;
    }
    /*
     * METHOD NAME : dbUpdateVO
     * ARGUMENTS : n/a
     * REQUIRE: setVO function has been called to set the local CategoryVO object
     * FUNCTION : Update the name of the local CategoryVO object in the DB.
     *            The cat_id is not changable in the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbUpdateVO(Connection conn)
    {
        //Secretary.startFxn("CategoryDAO.dbUpdateVO()");
        String updateStr;
        PreparedStatement updateStmt = null;
        boolean successful = false;
        //conn = conn.getConnection();
        Secretary.write("UPDATE shp_category SET nick = \"" + this.categoryVO.getNick()
                    + "\", name = \"" + this.categoryVO.getName() + "\" WHERE "
                    + "cat_id = " + this.categoryVO.getID() + ";");
        updateStr = "UPDATE shp_category "
                  + "SET nick = ?, "
                  + "name = ? "
                  + "WHERE "
                  + "cat_id = ?;";
        try{
            updateStmt = conn.prepareStatement(updateStr);
            updateStmt.setString(1, this.categoryVO.getNick());
            updateStmt.setString(2, this.categoryVO.getName());
            updateStmt.setInt(3, this.categoryVO.getID());
            updateStmt.executeUpdate();
        }catch(SQLException e){
            Secretary.write("SQLException in CategoryDAO.dbUpdateVO(): " + e.getMessage());
            successful = false;
        }catch(Exception e){
            Secretary.write("Exception in CategoryDAO.dbUpdateVO(): " + e.getMessage());
            successful = false;
        }finally{
            try{
                updateStmt.close();
            }catch(SQLException e){
                Secretary.write("SQLException in CategoryDAO.dbUpdateVO(): " + e.getMessage());
                successful = false;
            }
            //conn.freeConnection(conn);
        }
        //Secretary.endFxn("CategoryDAO.dbUpdateVO()");
        return successful;
    }
    /*
     * METHOD NAME : dbDeleteVO
     * ARGUMENTS : n/a
     * REQUIRE: setVO function has been called to set the local CategoryVO object
     * FUNCTION : Update the name of the local CategoryVO object in the DB.
     *            The cat_id is not changable in the DB.
     * RETURN:  TRUE if insert was successful
     *          FALSE if insert was unsuccessful.
     */
    public boolean dbDeleteVO(Connection conn)
    {
        //Secretary.write("CategoryDAO.dbDeleteVO()");
        Secretary.write("THIS FUNCTION HAS NOT BEEN IMPLEMENTED.");
        return true;
    }
    /*
     * METHOD NAME : dbLoadSubCategories
     * ARGUMENTS : n/a
     * REQUIRE: This CategoryVO's ID is set.
     * FUNCTION : Load all subcategories of this category from the DB.
     * RETURN: n/a
    public void dbLoadSubCategories()
    {
        Secretary.write("CatalogDAO.dbLoadSubCategories()");
        String query;
        PreparedStatement statement = null;
        query = "SELECT * FROM shp_sub_category WHERE cat_id = " + this.categoryVO.getID();
        Secretary.write(query);
        try{
            //conn = conn.getConnection();
            //Secretary.write("conn = " + conn);
            statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            this.categoryVO.clearSubCategoryList();
            while(rs.next())
            {
                SubCategoryVO subCatVO = new SubCategoryVO();
                subCatVO.setID(rs.getString("sub_cat_id").trim());
                subCatVO.setName(rs.getString("name").trim());
                //Secretary.write("catalogVO.addCategory("+categoryVO.getID()+", "+categoryVO.getName()+")");
                this.categoryVO.addSubCategory(subCatVO);
            }
        }catch(SQLException e){
            Secretary.write("SQLException in CatalogDAO.dbLoadSubCategories(): " + e.getMessage());
        }finally{
            try{
                statement.close();
            }catch(SQLException e){
                Secretary.write("SQLException in CatalogDAO.dbLoadSubCategories(): " + e.getMessage());
            }
            //conn.freeConnection(conn);
        }
    }
    
     */    
    public void dbInsertSubCategory(Connection conn)
    {
        //Secretary.startFxn("CategoryDAO.dbInsertSubCategory()");
        this.subCategoryDAO.setVO(this.categoryVO.getSubCategory());
        this.subCategoryDAO.dbInsertVO(conn, this.categoryVO.getID());
        //Secretary.endFxn("CategoryDAO.dbInsertSubCategory()");
    }
    public void dbUpdateSubCategory(Connection conn)
    {
        //Secretary.startFxn("CategoryDAO.dbUpdateSubCategory()");
        this.subCategoryDAO.setVO(this.categoryVO.getSubCategory());
        this.subCategoryDAO.dbUpdateVO(conn, this.categoryVO.getID());
        //Secretary.endFxn("CategoryDAO.dbUpdateSubCategory()");
    }
    public void dbUpdateItem(Connection conn)
    {
        //Secretary.startFxn("CategoryDAO.dbUpdateItem()");
        this.subCategoryDAO.setVO(this.categoryVO.getSubCategory());
        this.subCategoryDAO.dbUpdateItem(conn);
        //Secretary.endFxn("CategoryDAO.dbUpdateItem()");
    }
    /*
     * METHOD NAME : dbLoadSubCategories
     * ARGUMENTS : n/a
     * REQUIRE: 
     * FUNCTION : Load all subcategories of this CategoryVO into the SubCategory list.
     * RETURN: n/a
     */
    public void dbLoadSubCategories(Connection conn)
    {
        //Secretary.startFxn("CategoryDAO.dbLoadSubCategories()");
        String query;
        PreparedStatement statement = null;
        this.categoryVO.subCategoryListClear();
        query = "SELECT sub_cat_id, name FROM shp_sub_category WHERE cat_id = "
              + this.categoryVO.getID() + " ORDER BY sub_cat_id";
        Secretary.write(query);
        try{
            SubCategoryVO subCatVO = null;
            //conn = conn.getConnection();
            //Secretary.write("conn = " + conn);
            statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                subCatVO = new SubCategoryVO();
                subCatVO.setID(rs.getInt("sub_cat_id"));
                subCatVO.setName(EscapeChars.forHTMLTag(rs.getString("name").trim()));
                this.categoryVO.subCategoryAdd(subCatVO);
            }
        }catch(SQLException e){
            Secretary.write("SQLException in CatalogDAO.dbLoadSubCategories(): " + e.getMessage());
        }finally{
            try{
                statement.close();
            }catch(SQLException e){
                Secretary.write("SQLException in CatalogDAO.dbLoadSubCategories(): " + e.getMessage());
            }
            //conn.freeConnection(conn);
        }
        //Secretary.endFxn("CategoryDAO.dbLoadSubCategories()");
    }
    
    public void dbLoadItemList(Connection conn)
    {
        //Secretary.startFxn("CategoryDAO.dbLoadItemList()");
        this.categoryVO.subCategoryIteratorReset();
        while(this.categoryVO.subCategoryListHasMore())
        {
            this.categoryVO.setNextSubCategory();
            this.subCategoryDAO.setVO(this.categoryVO.getSubCategory());
            this.subCategoryDAO.dbLoadItemList(conn);
        }        
        //Secretary.endFxn("CategoryDAO.dbLoadItemList()");
    }   
    
    /*
     * METHOD NAME : 
     * ARGUMENTS : 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:
     */
}
