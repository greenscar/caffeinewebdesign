package users;
import java.util.*;
import java.sql.*;
import logging.Secretary;
import vos.*;
import daos.*;
public class Admin extends User{
    
    
    /** Creates a new instance of Admin */
    public Admin() {
        super();
    }   
    /*
     * METHOD NAME : categoryCreate
     * ARGUMENTS :  The ID of the category to create
     *              The name of the category to create
     * REQUIRE: n/a
     * FUNCTION : 1) Ensure there is not already a category of this ID and name
     *            2) Create a category of this ID and name
     *            3) Insert this category into the DB.
     * RETURN:  TRUE if category created
     *          FALSE if category already existed.
     */
    public boolean categoryCreate(Connection conn, String nick, String name)
    {
        Secretary.startFxn("Admin.categoryCreate("+nick+", "+name+")");
        boolean toReturn = false;
        if(this.catalogVO.categoryNameExists(name))
        {
            Secretary.write(name + " already exists as a category name");
            // A CATEGORY OF THIS NAME EXISTS ALREADY.
        }
        else if(this.catalogVO.categoryIDExists(nick))
        {
            Secretary.write(nick + " already exists as a category nick");
            // A CATEGORY OF THIS ID EXISTS ALREADY
        }
        else
        {
            // THE CATEGORY IS NEW. CREATE IT.
            this.catalogVO.categoryCreate(nick, name);
            this.catalogDAO.setVO(this.catalogVO);
            this.catalogDAO.dbInsertCategory(conn);
            toReturn = true;
        }
        Secretary.endFxn("Admin.categoryCreate("+nick+", "+name+")");
        return toReturn;
    }   
    /*
     * METHOD NAME : categoryModify
     * ARGUMENTS : id of category to be renamed
     *             new nick for category
     *             new name for category
     * REQUIRE: The category needing renamed is already held by this Admin
     *          as the category being modified.
     * FUNCTION : Modify the nick and name of this category.
     * RETURN:  n/a
     */
    public void categoryModify(Connection conn, String newID, String newNick, String newName)
    {
        Secretary.startFxn("Admin.categoryModify("+newID+", "+newNick+", "+newName+")");
        Integer temp = new Integer(newID);
        int id = temp.intValue();
        this.catalogVO.setCategoryViaID(id);
        this.catalogVO.categoryRename(id, newNick, newName);
        this.catalogDAO.setVO(this.catalogVO);
        this.catalogDAO.dbUpdateCategory(conn);
        Secretary.endFxn("Admin.categoryModify("+newID+", "+newNick+", "+newName+")");
    }
    /*
     * METHOD NAME : categoryDelete
     * ARGUMENTS : The id of the category we wish to delete
     * REQUIRE : A category via the provided id exists.
     * FUNCTION : Delete the category with the provided id.
     *            Upon deleting the category, all subcategories and all items 
     *            in this category will also be deleted.
     * RETURN : n/a
     */
    public void categoryDelete(Connection conn, int id)
    {
        Secretary.startFxn("Admin.categoryDelete("+id+")");
        // First we set the Category to memory so after it's removed from the 
        //  category list, we can delete it from the DB.
        this.catalogVO.setCategoryViaID(id);
        this.catalogVO.categoryDelete(id);
        this.catalogDAO.setVO(this.catalogVO);
        this.catalogDAO.dbDeleteCategory(conn);
        Secretary.endFxn("Admin.categoryDelete("+id+")");
    }
     
    
    /*
     * METHOD NAME : subCategoryCreate
     * ARGUMENTS :  the name of the new SubCategory
     *              the ID of the parent 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:  TRUE if subCategory created
     *          FALSE if subCategory already existed.
     */
    public boolean subCategoryCreate(Connection conn, String name, String parent)
    {
        //Integer pid = new Integer(parent);
        int parentID = new Integer(parent).intValue();
        /*
         * 1) Get the category with parentID
         * 2) Ensure the category doesn't already hold a subcategory via this name
         * 3) If not, append this subcategory to the category.
         * 4) Insert the new subcategory into the DB.
         */
        //this.catalogVO = catalogDAO.getVO();
        Secretary.startFxn("Admin.subCategoryCreate("+name+", "+parentID+")");
        if(this.catalogVO.subCategoryExists(name, parentID))
        {
            // The category already has a subCategory of this name.
            Secretary.write("The SubCategory " + name + " already exists. subCategoryCreate returning FALSE");
            Secretary.endFxn("Admin.subCategoryCreate("+name+", "+parentID+")");
            return false;
        }
        else
        {
            // THE SUBCATEGORY IS NEW. CREATE IT.
            SubCategoryVO subCatVO = this.catalogVO.subCategoryCreate(name, parentID);
            // THE SUBCATEGORY IS NOW CREATED.
            
            // INSERT THE SUBCATEGORY INTO THE DB
            this.catalogDAO.setVO(this.catalogVO);
            this.catalogDAO.dbInsertSubCategory(conn);
            // END INSERT THE SUBCATEGORY INTO THE DB
            
            Secretary.endFxn("Admin.subCategoryCreate("+name+", "+parentID+")");
            return true;
        }
    }   
    /*
     * METHOD NAME : itemModify
     * ARGUMENTS :  The item id (this cannot be changed)
     *             New item name
     *             New cost
     *             New gl number
     *             New active state
     *             Old category ID
     *             Old subCategoryID
     *             New categoryID
     *             New subCategoryID
     * FUNCTION : process the itemMod to modify item data then insert it into the DB
     * REQUIRE : The item to be modified is the current one in the Admin's memory.
     * RETURN:  n/a
     */
    public void itemModify(Connection conn, int itemID, String name, float cost, int glNum, 
                           boolean active, int oldCatID, int oldSubCatID, 
                           int newCatID, int newSubCatID, int orderQuantityID)
    {
        Secretary.startFxn("Admin.itemModify("+conn.toString()+", "+itemID+", "+name+", "+cost+", "+
                            glNum+", "+active+", "+oldCatID+", "+oldSubCatID+", "+
                            newCatID+", "+newSubCatID+", "+orderQuantityID+")");
        
        // 1) Modify all item information.        
        this.catalogVO.itemModify(itemID, name, cost, glNum, active, orderQuantityID);
        //Secretary.write("this.catalogVO.itemModify(itemID, name, cost, glNum, active) DONE");
        this.catalogVO.itemMove(itemID, oldCatID, oldSubCatID, newCatID, newSubCatID);
        this.catalogDAO.setVO(this.catalogVO);
        this.catalogDAO.dbUpdateItem(conn);
        Secretary.endFxn("Admin.itemModify(....)");
    }
    
    /*
     * METHOD NAME : subCategoryModify
     * ARGUMENTS : the new name of this subcategory
     *             the new parent id of this subcategory
     * FUNCTION : process the subCategoryMod.jsp form for modifying information
     *            of a subcategory.
     * REQUIRE : n/a
     * RETURN:  TRUE if subCategory created
     *          FALSE if subCategory already existed.
     */
    public void subCategoryModify(Connection conn, int subCatID, String subCatName, int oldCatID, int newCatID)
    {
        Secretary.startFxn("Admin.subCategoryModify("+subCatID+", "+subCatName+", "+oldCatID+", "+newCatID+")");
        
        // 1) Rename the subcategory
        this.catalogVO.subCategoryRename(oldCatID, subCatID, subCatName);
        
        // 2) Move the subcategory to the proper category
        this.catalogVO.subCategoryMove(subCatID, oldCatID, newCatID);
        
        // 3) Process this change in the DB
        this.catalogDAO.setVO(this.catalogVO);
        this.catalogDAO.dbUpdateSubCategory(conn);
        Secretary.endFxn("Admin.subCategoryModify("+subCatID+", "+subCatName+", "+oldCatID+", "+newCatID+")");
    }
    /*
     * THIS NEEDS MODIFIED ONCE ALEX IS DONE WITH THE PS DB.
     * ONCE THE DB IS READY, THE USER MAY SELECT A REGION OR A DISTRICT
     * AS A RECIPIENT. IF THIS HAPPENS, THE ORDER IS SHIPPED TO EVERY STORE
     * IN THAT REGION / DISTRICT.
     */
    public void orderCreate(Connection conn, String[] recipients, String contact)
    {
        Secretary.startFxn("Admin.orderCreate(recipients, " + contact + ")");
        this.orderVO = new OrderVO();
        this.orderVO.setDatePlaced(new java.util.Date());
        this.orderVO.setContact(contact);
        for(int i=0; i < recipients.length; i++)
        {
            java.lang.Integer shipTo = new Integer(recipients[i]);
            this.orderVO.addShipTo(shipTo);            
            if(shipTo.intValue() < 100)
            {
                // MODIFY THIS LATER SO REGION GETS BILLED WHEN DM OR RM PLACES ORDER
                this.orderVO.addBillTo(shipTo);
            }
            else //billTo.equalsIgnoreCase("corp")
            {
                // DEPTS & STORES ALWAYS GETS BILLED FOR ITEMS SHIPPED TO IT
                this.orderVO.addBillTo(shipTo);
            }
            //logging.Secretary.write("recipients["+i+"] = " + recipients[i]);
        }   
        /*
         * The Order is now created and holds:
         *  1) A list of recipients
         *  2) The contact name
         *  3) The date placed
         * Insert this order into the DB then retrieve the order number.
         * This will enable the continuing of orders in the future. 
         */
        this.orderDAO.setVO(this.orderVO);
        this.orderDAO.dbInsertVO(conn);
        Secretary.endFxn("Admin.orderCreate(recipients, " + contact + ")");
    }
}
