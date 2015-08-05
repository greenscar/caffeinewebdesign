package users;

import vos.*;
import daos.*;
import java.beans.*;
import logging.Secretary;
import java.io.*;
import java.sql.*;
import com.javaexchange.dbConnectionBroker.*;
import sites.SiteAdmin;

public class User{
    
    //private static final String PROP_SAMPLE_PROPERTY = "SampleProperty";
    
    protected CatalogVO catalogVO;
    protected CatalogDAO catalogDAO;
    protected OrderVO orderVO;
    protected OrderDAO orderDAO;
    protected SiteAdmin siteAdmin;
    
    public User() {
        this.catalogDAO = new CatalogDAO();  
        this.catalogVO = new CatalogVO();
        this.orderDAO = new OrderDAO();
    }
    
    
    /**********************************************************************
     * CATEGORY LIST FUNCTIONS
     **********************************************************************/
    /*
     * METHOD NAME : categoryListReset
     * ARGUMENTS : n/a
     * REQUIRE: The cateogory iterator is defined.
     * FUNCTION : Reset the category iterator pointer to the beginning of the list.
     * RETURN:  n/a
     */
    public void categoryListReset()
    {
        Secretary.startFxn("User.categoryListReset()");
        this.catalogVO.categoryIteratorReset();
        Secretary.endFxn("User.categoryListReset()");
    }
    
    /*
     * METHOD NAME : categoryListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the catalog's category iterator and determine
     *            if there are more categories to display
     * RETURN:  TRUE if another category is in the list
     *          FALSE if another category is not in the list
     */
    public boolean categoryListHasMore()
    {
        //Secretary.startFxn("User.categoryListHasMore()");
        boolean toReturn = this.catalogVO.categoryListHasMore();
        //Secretary.endFxn("User.categoryListHasMore() => " + toReturn);
        return toReturn;
    }
    
    /*
     * METHOD NAME : categoryListSetViaID
     * ARGUMENTS : String id
     * REQUIRE: A category with this ID exists.
     * FUNCTION : Set the category Iterator to the category with the
     *              id provided.
     *            IF this ID does not exist, the category will be set to
     *              the last category in the catalog
     * RETURN:  n/a
     */
    public void categoryListSetViaID(String id)
    {
        Integer i = new Integer(id);
        this.catalogVO.categoryListSetViaID(i.intValue());
    }
    /*
     * METHOD NAME : setNextCategory
     * ARGUMENTS :  
     * REQUIRE: n/a
     * FUNCTION : Set the next category in the catalog's iterator to be the 
     *            current category in the catalog's memory.
     * RETURN:  n/a
     */
    public void setNextCategory(boolean useless)
    {
        Secretary.startFxn("User.setNextCategory()");
        this.catalogVO.setNextCategory();
        this.catalogVO.subCategoryIteratorReset();
        Secretary.endFxn("User.setNextCategory()");
    }
    
    public void setNextCategory()
    {
        this.setNextCategory(false);
    }        
    /*
     * METHOD NAME : setCategoryViaID
     * ARGUMENTS : the ID of a category
     * REQUIRE: A Category of this ID exists
     * FUNCTION : Search the category list for a category with the
     *            provided ID.
     * RETURN:  n/a
     */
    public void setCategoryViaID(int id)
    {
        Secretary.startFxn("User.setCategoryViaID("+id+")");
        this.catalogVO.setCategoryViaID(id);
        Secretary.endFxn("User.setCategoryViaID("+id+")");
    }  
    public void setCategoryViaID(String id)
    {
        Secretary.startFxn("User.setCategoryViaID("+id+")");
        Integer x = new Integer(id);
        this.catalogVO.setCategoryViaID(x.intValue());
        Secretary.endFxn("User.setCategoryViaID("+id+")");
    }
    public int getCategoryID()
    {
        //Secretary.startFxn("User.getCategoryID()");
        return this.catalogVO.getCategoryID();
        //Secretary.endFxn("User.getCategoryID() => " + x);
    }
    
    public String getCategoryIDString()
    {
        //Secretary.startFxn("User.getCategoryID()");
        int id = this.catalogVO.getCategoryID();
        String x = String.valueOf(id);
        //Secretary.endFxn("User.getCategoryID() => " + x);
        return x;
    }
    public String getCategoryName()
    {
        //Secretary.startFxn("User.getCategoryName()");
        String toReturn = this.catalogVO.getCategoryName();
        //Secretary.endFxn("User.getCategoryName()");
        return toReturn;
    }
    public String getCategoryNick()
    {
        //Secretary.startFxn("User.getCategoryNick()");
        String x = this.catalogVO.getCategoryNick();
        //Secretary.endFxn("User.getCategoryNick() => " + x);
        return x;
    }
    public int getNumCategories()
    {
        if(this.catalogVO == null)
            return -99;
        else
        return this.catalogVO.getNumCategories();
    }
    public void setCategoryViaNum(int num)
    {
        this.categoryListReset();
        for(int k=0; k<num; k++)
        {
            this.setNextCategory();
        }
    }
    
    public int categoryListGetFirstKey()
    {
        return this.catalogVO.categoryListGetFirstKey();
    }
    public int categoryListGetPrevKey()
    {
        return this.catalogVO.categoryListGetPrevKey();
    }
    public int categoryListGetNextKey()
    {
        return this.catalogVO.categoryListGetNextKey();
    }
    public int categoryListGetLastKey()
    {
        return this.catalogVO.categoryListGetLastKey();
    }
    /*
    public void categoryGoToFirst()
    {
        this.catalogVO.categoryGoToFirst();
    }
    public void categoryGoToPrev()
    {
        this.catalogVO.categoryGoToPrev();
    }
    public void categoryGoToNext()
    {
        this.catalogVO.categoryGoToNext();
    }
    public void categoryGoToLast()
    {
        this.catalogVO.categoryGoToLast();
    }
     */
    /**********************************************************************
     * END CATEGORY LIST FUNCTIONS
     **********************************************************************/
    /**********************************************************************
     * SUBCATEGORY FUNCTIONS
     **********************************************************************/
     public int getSubCategoryID()
    {
        //Secretary.startFxn("User.getSubCategoryID()");
        int x = this.catalogVO.getSubCategoryID();
        //Secretary.endFxn("User.getSubCategoryID() => " + x);
        return x;
    }
    public String getSubCategoryName()
    {
        //Secretary.startFxn("User.getSubCategoryName()");
        String toReturn = this.catalogVO.getSubCategoryName();
        //Secretary.endFxn("User.getSubCategoryName() => " + toReturn);
        return toReturn;
    }
    
    /*
     * METHOD NAME : setSubCategoryViaID
     * ARGUMENTS : The ID of the subcategory we want brought to memory.
     * REQUIRE: The category this subcategory is in has been set to the current
     *          category in use.
     * FUNCTION : Set the subcategory with the provided ID into use in the 
     *              category instance held by the catalog.
     *            This is called from the subCategoryMod.jsp
     * RETURN:  n/a
     */
    public void setSubCategoryViaID(String subCatID)
    {
        Secretary.startFxn("User.setSubCategoryViaID("+subCatID+")");
        Integer x = new Integer(subCatID);
        this.catalogVO.setSubCategoryViaID(x.intValue());
        Secretary.endFxn("User.setSubCategoryViaID("+subCatID+")");
    }
    /*
     * METHOD NAME : subCategoryListReset
     * ARGUMENTS : n/a
     * REQUIRE: The cateogory's subcategory iterator is defined.
     * FUNCTION : Reset the subCategory iterator pointer to the beginning of the list.
     * RETURN:  n/a
     */
    public void subCategoryListReset()
    {
        Secretary.startFxn("User.subCategoryListReset()");
        this.catalogVO.subCategoryIteratorReset();
        Secretary.endFxn("User.subCategoryListReset()");
    }
    /*
     * METHOD NAME : categoryListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the catalog's category's subcategory iterator and determine
     *            if there are more subcategories to display
     * RETURN:  TRUE if another subcategory is in the list
     *          FALSE if another subcategory is not in the list
     */
    public boolean subCategoryListHasMore()
    {
        //Secretary.startFxn("User.subCategoryListHasMore()");
        boolean toReturn = this.catalogVO.subCategoryListHasMore();
        //Secretary.endFxn("User.subCategoryListHasMore()");
        return toReturn;
    }
    /*
     * METHOD NAME : setNextSubCategory
     * ARGUMENTS :  
     * REQUIRE: n/a
     * FUNCTION : Set the next subcategory in the category's iterator to be the 
     *            current subcategory in the category's memory.
     * RETURN:  n/a
     */
    public void setNextSubCategory(boolean useless)
    {
        //Secretary.startFxn("User.setNextSubCategory()");
        this.catalogVO.setNextSubCategory();
        //Secretary.endFxn("User.setNextSubCategory()");
    }
    
    public int getNumSubCats()
    {
        return this.catalogVO.getNumSubCats();
    }
    /**********************************************************************
     * END SUBCATEGORY FUNCTIONS
     **********************************************************************/
    /**********************************************************************
     * ITEM FUNCTIONS
     **********************************************************************/
    
    public void setNextItem(boolean useless)
    {
        //Secretary.startFxn("User.setNextItem()");
        this.catalogVO.setNextItem();
        //Secretary.endFxn("User.setNextItem()");
    }
    public void setItemViaID(int itemID, int subCatID, int catID)
    {
        Secretary.startFxn("User.setItemViaID("+itemID+", "+subCatID+", "+catID+")");
        this.catalogVO.setItemViaID(itemID, subCatID, catID);
        Secretary.endFxn("User.setItemViaID("+itemID+", "+subCatID+", "+catID+")");
    }
    /*
     * METHOD NAME : itemListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the catalog's category's subcategory's item iterator and determine
     *            if there are more items to display
     * RETURN:  TRUE if another item is in the list
     *          FALSE if another item is not in the list
     */
    public boolean itemListHasMore()
    {
        //Secretary.startFxn("User.itemListHasMore()");
        boolean toReturn = this.catalogVO.itemListHasMore();
        if(toReturn == false)
            this.catalogVO.itemIteratorReset();
        //Secretary.endFxn("User.itemListHasMore() => " + toReturn);
        return toReturn;
    }
    public int getItemID()
    {
        //Secretary.startFxn("User.getItemID()");
        int temp = this.catalogVO.getItemID();
        //Secretary.endFxn("User.getItemID() => " + temp);
        return temp;
    }
    public String getItemName()
    {
        //Secretary.startFxn("User.getItemName()");
        String temp = this.catalogVO.getItemName();
        //Secretary.endFxn("User.getItemName() => " + temp);
        return temp;
    }
    public float getItemCost()
    {
        //Secretary.startFxn("User.getItemCost()");
        float temp = this.catalogVO.getItemCost();
        //Secretary.endFxn("User.getItemCost() => " + temp);
        return temp;
    }
    public int getItemGlNum()
    {
        //Secretary.startFxn("User.getItemGlNum()");
        int temp = this.catalogVO.getItemGlNum();
        //Secretary.endFxn("User.getItemGlNum() => " + temp);
        return temp;
    }
    public boolean getItemActive()
    {
        //Secretary.startFxn("User.getItemActive()");
        boolean temp = this.catalogVO.getItemActive();
        //Secretary.endFxn("User.getItemActive() => " + temp);
        return temp;
    }
    public int getSubCatNumItems()
    {
        return this.catalogVO.getSubCatNumItems();
    }
    public String getItemOrderQuantityName()
    {
        return this.catalogVO.getItemOrderQuantityName();
    }
    public int getItemOrderQuantityID()
    {
        return this.catalogVO.getItemOrderQuantityID();
    }
    /**********************************************************************
     * END ITEM FUNCTIONS
     **********************************************************************/
    
    
    /*
     * METHOD NAME : loadCatalog
     * ARGUMENTS : The DB Connection
     * REQUIRE: n/a
     * FUNCTION : Clear the catalog from memory then load it from the DB.
     * RETURN:  n/a
     */
    public void loadCatalog(Connection conn)
    {
        Secretary.startFxn("User.reloadCatalog(conn)");
        this.catalogVO = new CatalogVO();
        this.dbLoadCatalog(conn);
        Secretary.endFxn("User.reloadCatalog(conn)");
    }
    /*
     * METHOD NAME : dbLoadCatalog
     * ARGUMENTS : The DB Connection
     * REQUIRE: n/a
     * FUNCTION : Check if the catalog is in current memory. If not, load the
     *            catalog from the DB.
     * RETURN:  n/a
     */
    private void dbLoadCatalog(Connection conn)
    {
        java.util.Calendar startCal = java.util.Calendar.getInstance();
        String start = startCal.get(java.util.Calendar.MINUTE) + ":" + startCal.get(java.util.Calendar.SECOND);
        Secretary.startFxn("User.loadCatalog() <<"+start+">>");
        if(this.catalogVO.getNumItems() == 0)
        {
            Secretary.write("Loading Catalog from the DB");
            // Load the catalog
            this.catalogDAO.setVO(this.catalogVO);
            this.catalogDAO.dbLoadCatalog(conn);
            this.catalogVO = this.catalogDAO.getVO();
            this.catalogVO.categoryIteratorReset();
        }
        else
        {
            Secretary.write("The Catalog is alredy loaded from the DB");
            // Catalog is already loaded.
            // Do nothing.
        }
        java.util.Calendar endCal = java.util.Calendar.getInstance();
        String end = endCal.get(java.util.Calendar.MINUTE) + ":" + endCal.get(java.util.Calendar.SECOND);
        Secretary.endFxn("User.loadCatalog() <<"+end+">>");
    }
    
    /**********************************************************************
     * Quantity List Functions
     *********************************************************************/
    public String getTempOrderQuantityName()
    {
        return this.catalogVO.getTempOrderQuantityName();
    }
    public int getTempOrderQuantityID()
    {
        return this.catalogVO.getTempOrderQuantityID();
    }
    public void resetOrderQuantities()
    {
        this.catalogVO.orderQuantityIteratorReset();
    }
    public void addOrderQuantity(Integer id, String name)
    {
        this.catalogVO.orderQuantityListAdd(id, name);
    }
    public void orderQuantityIteratorReset()
    {
        logging.Secretary.startFxn("User.orderQuantityIteratorReset()");
        this.catalogVO.orderQuantityIteratorReset();
        logging.Secretary.endFxn("User.orderQuantityIteratorReset()");
    }
    public void setNextOrderQuantity(boolean useless)
    {
        logging.Secretary.startFxn("User.setNextOrderQuantity()");
        this.catalogVO.setNextOrderQuantity();
        logging.Secretary.endFxn("User.setNextOrderQuantity()");
    }
    public boolean orderQuantityListHasMore()
    {
        return this.catalogVO.orderQuantityListHasMore();
    }
    /**********************************************************************
     * END Quantity List Functions
     *********************************************************************/  
    
    
    public int siteListLoad()
    {
        logging.Secretary.startFxn("User.siteListLoad()");
        this.siteAdmin = new SiteAdmin();
        int toReturn = this.siteAdmin.dbLoadSiteList();
        this.siteAdmin.siteIteratorReset();
        logging.Secretary.endFxn("User.siteListLoad(" + toReturn + ")");
        return(toReturn);
    }
    public boolean siteListHasMore()
    {
        return this.siteAdmin.siteListHasMore();
    }
    public void siteIteratorReset()
    {
        this.siteAdmin.siteIteratorReset();
    }
    public void setNextSite(boolean useless)
    {
        this.siteAdmin.setNextSite();
    }    
    
    
    public int getSiteId()
    {
        return this.siteAdmin.getSiteId();
    }
    public String getSiteName()
    {
        return this.siteAdmin.getSiteName();
    }
    public String getSiteStreet()
    {
        return this.siteAdmin.getSiteStreet();
    }
    public String getSiteStreet2()
    {
        return this.siteAdmin.getSiteStreet2();
    }
    public String getSiteCity()
    {
        return this.siteAdmin.getSiteCity();
    }
    public String getSiteState()
    {
        return this.siteAdmin.getSiteState();
    }
    public String getSiteZip()
    {
        return this.siteAdmin.getSiteZip();
    }
    public String getSiteFirstName()
    {
        return this.siteAdmin.getSiteFirstName();
    }
    public String getSiteLastName()
    {
        return this.siteAdmin.getSiteLastName();
    }
    public int getSiteRegion()
    {
        return this.siteAdmin.getSiteRegion();
    }
    public int getSiteDistrict()
    {
        return this.siteAdmin.getSiteDistrict();
    }
    
    public void orderCreate(Connection conn, String[] recipients, String contact)
    {
    }
    
    public String getOrderDate()
    {
        logging.Secretary.write("User.getOrderDate()");
        java.text.DateFormat df = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);
        String temp = df.format(this.orderVO.getDatePlaced());
        logging.Secretary.write("User.getOrderDate() >> " + temp);
        return temp;
    }
    public String getOrderContact()
    {
        return this.orderVO.getContact();
    }
    public float getOrderTotalCost()
    {
        return 0;
    }
    public int getOrderNumPieces()
    {
        return 0;
    }
}
