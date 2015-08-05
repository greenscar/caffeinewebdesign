package daos;
import java.sql.*;
import logging.Secretary;
import vos.*;
import htmlConverter.EscapeChars;

public class CatalogDAO {
    private CatalogVO catalogVO;
    private CategoryDAO categoryDAO;   
    
    
    /** Creates a new instance of CatalogDAO */
    public CatalogDAO() {
        this.categoryDAO = new CategoryDAO();
        this.catalogVO = new CatalogVO();
    }
    
    public void setVO(CatalogVO cvo)
    {
        //Secretary.startFxn("CatalogDAO.setVO(CatalogVO)");
        this.catalogVO = cvo;
        if(this.catalogVO.getCategory() != null)
        {
            Secretary.write("catalogVO.getCategory() != null");
            this.categoryDAO.setVO(this.catalogVO.getCategory());
        }
        //Secretary.endFxn("CatalogDAO.setVO(CatalogVO)");
    }
    public CatalogVO getVO()
    {
        //Secretary.startFxn("CatalogDAO.getVO()");
        CatalogVO temp = this.catalogVO;
        //Secretary.endFxn("CatalogDAO.getVO()");
        return temp;
    }
    
    /*
     * METHOD NAME : dbInsertCategory
     * ARGUMENTS : n/a
     * REQUIRE: The CatalogVO.setCategoryViaID method has been called
     *          followed by the CatalogDAO.setVO. Therefore, the catalogVO 
     *          object in this CatalogDAO's memory has the new CategoryVO in 
     *          memory.
     * FUNCTION : Insert this CategoryVO into the DB.
     * RETURN:  TRUE if successful
     *          FALSE if failure.
     */
    public boolean dbInsertCategory(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbInsertCategory()");
        this.categoryDAO.setVO(this.catalogVO.getCurrentCategoryVO());
        boolean temp =  this.categoryDAO.dbInsertVO(conn);
        Secretary.endFxn("CatalogDAO.dbInsertCategory()");
        return temp;
    }
    /*
     * METHOD NAME : dbUpdateCategory
     * ARGUMENTS : n/a
     * REQUIRE: The CatalogVO.setCategoryViaID method has been called
     *          followed by the CatalogDAO.setVO. Therefore, the catalogVO 
     *          object in this CatalogDAO's memory has the new CategoryVO in 
     *          memory.
     *          A CategoryVO of this ID already exists in the DB.
     * FUNCTION : Update the name of this CategoryVO in the DB.
     * RETURN:  TRUE if successful
     *          FALSE if failure.
     */
    public boolean dbUpdateCategory(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbUpdateCategory()");
        this.categoryDAO.setVO(this.catalogVO.getCurrentCategoryVO());
        boolean temp = this.categoryDAO.dbUpdateVO(conn);
        Secretary.endFxn("CatalogDAO.dbUpdateCategory()");
        return temp;
    }
    
    public boolean dbDeleteCategory(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbDeleteCategory()");
        this.categoryDAO.setVO(this.catalogVO.getCurrentCategoryVO());
        boolean temp = this.categoryDAO.dbDeleteVO(conn);
        Secretary.endFxn("CatalogDAO.dbDeleteCategory()");
        return temp;
    }
     
    /*
     * METHOD NAME : dbLoadCategoryList
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Load the CategoryList from the DB into the CatalogVO
     * RETURN:  n/a
     */
    private void dbLoadCategoryList(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbLoadCategoryList()");
        String query;
        PreparedStatement statement = null;
        
        query = "SELECT * FROM shp_category ORDER BY cat_id";
        Secretary.write(query);
        try{
            CategoryVO categoryVO = null;
            statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            this.catalogVO.clearCategories();
            while(rs.next())
            {
                categoryVO = new CategoryVO();
                categoryVO.setID(rs.getInt("cat_id"));
                categoryVO.setNick(rs.getString("nick").trim());
                categoryVO.setName(EscapeChars.forHTMLTag((rs.getString("name").trim())));
                this.catalogVO.addCategory(categoryVO);
            }
        }catch(SQLException e){
            Secretary.write("SQLException in CatalogDAO.dbLoadCategoryList(): " + e.getMessage());
        }catch(Exception e){
            Secretary.write("Exception in CatalogDAO.dbLoadCategoryList(): " + e.getMessage());
        }finally{
            try{
                statement.close();
            }catch(SQLException e){
                Secretary.write("SQLException in CatalogDAO.dbLoadCategoryList(): " + e.getMessage());
            }
            Secretary.endFxn("CatalogDAO.dbLoadCategoryList()");
        }
    }
    
    private void dbLoadSubCategories(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbLoadSubCategories()");
        this.catalogVO.categoryIteratorReset();
        while(this.catalogVO.categoryListHasMore())
        {
            this.catalogVO.setNextCategory();
            this.categoryDAO.setVO(this.catalogVO.getCategory());
            this.categoryDAO.dbLoadSubCategories(conn);
        }        
        Secretary.endFxn("CatalogDAO.dbLoadSubCategories()");
    }
    private void dbLoadItems(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbLoadItems()");
        // LOAD ITEMS IN CATEGORIES
        this.catalogVO.categoryIteratorReset();
        while(this.catalogVO.categoryListHasMore())
        {
            this.catalogVO.setNextCategory();
            this.categoryDAO.setVO(this.catalogVO.getCategory());
            this.categoryDAO.dbLoadItemList(conn);
        }        
        
        // LOAD ITEMS THAT ARE NOT IN ANY CATEGORY
        //this.dbLoadLooseItems();
        Secretary.endFxn("CatalogDAO.dbLoadItems()");
    }
    public CatalogVO dbLoadCatalog(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbLoadCatalog()");
        this.dbLoadOrderQuantityList(conn);
        this.dbLoadCategoryList(conn);
        this.dbLoadSubCategories(conn);
        this.dbLoadItems(conn);
        Secretary.endFxn("CatalogDAO.dbLoadCatalog()");
        return this.catalogVO;
    }
    
    public void dbLoadOrderQuantityList(Connection conn)
    {
        Secretary.startFxn("ItemDAO.dbLoadOrderQuantityList()");        
        this.catalogVO.orderQuantitiesReset();
        String selectStr;
        PreparedStatement selectStmt = null;
        boolean successful = false;
        selectStr = "SELECT * FROM shp_order_quantity";
        Secretary.write(selectStr);
        try
        {
            selectStmt = conn.prepareStatement(selectStr);
            ResultSet rs = selectStmt.executeQuery();
            while(rs.next())
            {
                Integer id = new Integer(rs.getInt("qty_id"));
                String name = new String(rs.getString("name").trim());
                this.catalogVO.orderQuantityListAdd(id, name);
            }
            successful = true;
        }catch(SQLException e){
            Secretary.write("SQLException in ItemDAO.dbLoadOrderQuantityList(): " + e.getMessage());
            successful = false;
        }catch(Exception e){
            Secretary.write("Exception in ItemDAO.dbLoadOrderQuantityList(): " + e.getMessage());
            successful = false;
        }finally{
            try{
                selectStmt.close();
            }catch(SQLException e){
                Secretary.write("SQLException in ItemDAO.dbLoadOrderQuantityList(): " + e.getMessage());
                successful = false;
            }
        }
        Secretary.endFxn("ItemDAO.dbLoadOrderQuantityList()");
    }
    /*
     * METHOD NAME : dbInsertSubCategory
     * ARGUMENTS :  n/a
     * REQUIRE: The current Category in my memory is the category holding
     *          the subcategory that needs to be entered.
     * FUNCTION : Call the insert subcategory function in the correct instance
     *              of category.
     * RETURN:  n/a
     */
    public void dbInsertSubCategory(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbInsertSubCategory()");
        //the catalogVO holds the proper categoryVO.
        this.categoryDAO.setVO(this.catalogVO.getCategory());
        this.categoryDAO.dbInsertSubCategory(conn);
        Secretary.endFxn("CatalogDAO.dbInsertSubCategory()");
    }
    public void dbUpdateSubCategory(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbUpdateSubCategory()");
        this.categoryDAO.setVO(this.catalogVO.getCategory());
        this.categoryDAO.dbUpdateSubCategory(conn);
        Secretary.endFxn("CatalogDAO.dbUpdateSubCategory()");
    }
    public void dbUpdateItem(Connection conn)
    {
        Secretary.startFxn("CatalogDAO.dbUpdateItem()");
        this.categoryDAO.setVO(this.catalogVO.getCategory());
        this.categoryDAO.dbUpdateItem(conn);
        Secretary.endFxn("CatalogDAO.dbUpdateItem()");
    }
}
