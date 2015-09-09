package vos;

import java.util.*;
import logging.Secretary;
import vos.*;

public class CatalogVO extends ItemCollection {

    private LinkedHashMap categories;
    private CategoryVO categoryVO;
    private Iterator categoryIterator;
    private int numItems; 
    private LinkedHashMap orderQuantities;
    private Iterator orderQuantityIterator;
    private OrderQuantityVO orderQuantityVO;
    //private Vector orderQuantityIds;
    //private Vector orderQuantityNames;
    
    // The looseItems represent items whose category and subcategory are undefined.
    // Therefore, they are in the catalog but not any category tree.
    /*
    private LinkedHashMap looseItems;
    private Iterator looseItemIterator;
    private ItemVO looseItemVO;
    */
    public CatalogVO() {
        this.categories = new LinkedHashMap();
        numItems = 0;
    }
    public ItemVO pullItemViaID(int id)
    {
        Secretary.startFxn("CatalogVO.pullItemViaID("+id+")");
        ItemVO t = null;
        t = this.categoryVO.pullItemViaID(id);
        Secretary.endFxn("CatalogVO.pullItemViaID("+id+")");
        return t;
    }
    public void setItemViaID(int itemID, int subCatID, int catID)
    {
        Secretary.startFxn("CatalogVO.setItemViaID("+itemID+", "+subCatID+", "+catID+")");
        this.setCategoryViaID(catID);
        this.categoryVO.setItemViaID(itemID, subCatID);
        Secretary.endFxn("CatalogVO.setItemViaID("+itemID+", "+subCatID+", "+catID+")");
    }
    /*
     * METHOD NAME : getNumCategories
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Get the number of categories that exist.
     * RETURN: The number of categories that exist
     */
    public int getNumCategories()
    {
        Secretary.startFxn("CatalogVO.getNumCategories()");
        int num = 0;
        if(this.categories != null)
            num = this.categories.size();
        Secretary.endFxn("CatalogVO.getNumCategories() => " + num);
        return num;
    }  
    /*
     * METHOD NAME : setCategoryViaID
     * ARGUMENTS : the ID of a category
     * REQUIRE: A Category of this ID exists
     * FUNCTION : Search the category list for a category with the
     *            provided ID. 
     *            Assign this category to the one in memory.
     * RETURN:  n/a
     */
    public void setCategoryViaID(int id)
    {
        Secretary.startFxn("CatalogVO.setCategoryViaID("+id+")");
        this.categoryVO = (CategoryVO)(this.categories.get(new Integer(id)));
        Secretary.endFxn("CatalogVO.setCategoryViaID("+id+")");
    }  
    public void setCategoryIteratorViaID(int id)
    {
        
    }   
    public void setCategoryVO(CategoryVO cv)
    {
        this.categoryVO = cv;
    }
    /*
     * METHOD NAME : getCategoryViaID
     * ARGUMENTS : the ID of a category
     * REQUIRE: A category via this id exists.
     * FUNCTION : Search the category list for a category with the
     *            provided ID.
     * RETURN:  a CategoryVO object
     */
    public CategoryVO getCategoryViaID(int id)
    {
        Secretary.startFxn("CatalogVO.getCategoryViaID("+id+")");
        CategoryVO aCat =  (CategoryVO)(this.categories.get(new Integer(id)));
        Secretary.endFxn("CatalogVO.getCategoryViaID("+id+")");
        return aCat;
    }
    
    // return the categoryVO currently in memory.
    public CategoryVO getCategory()
    {
        Secretary.startFxn("CatalogVO.getCategory()");
        CategoryVO temp = this.categoryVO;
        Secretary.endFxn("CatalogVO.getCategory()");
        return temp;
    }
    /*
     * METHOD NAME : getCategoryName
     * ARGUMENTS : n/a
     * REQUIRE: setCategoryXXXXX has been called to set the catalog currently
     *          being used.
     * FUNCTION : Get the name of the category currently being used
     * RETURN: The name of the category currently in use.
     */
    public String getCategoryName()
    {
        //Secretary.startFxn("CatalogVO.getCategoryName()");
        String temp = this.categoryVO.getName();
        //Secretary.endFxn("CatalogVO.getCategoryName() => " + temp);
        return temp;
    }
    /*
     * METHOD NAME : getCategoryID
     * ARGUMENTS : n/a
     * REQUIRE: setCategoryXXXXX has been called to set the catalog currently
     *          being used.
     * FUNCTION : Get the ID of the category currently in use.
     * RETURN: The ID of the category currently in use
     */
    public int getCategoryID()
    {
        //Secretary.startFxn("CatalogVO.getCategoryID()");
        int toReturn = this.categoryVO.getID();
        //Secretary.endFxn("CatalogVO.getCategoryID() => " + toReturn);
        return toReturn;
    }
    /*
     * METHOD NAME : getCategoryNick
     * ARGUMENTS : n/a
     * REQUIRE: setCategoryXXXXX has been called to set the catalog currently
     *          being used.
     * FUNCTION : Get the nick of the category currently in use.
     * RETURN: The nick of the category currently in use
     */
    public String getCategoryNick()
    {
        //Secretary.startFxn("CatalogVO.getCategoryNick()");
        String toReturn = this.categoryVO.getNick();
        //Secretary.endFxn("CatalogVO.getCategoryNick() => " + toReturn);
        return toReturn;
    }
    /*
     * METHOD NAME : categoryNameExists
     * ARGUMENTS : A category name
     * REQUIRE: n/a
     * FUNCTION : Determine if the provided category name 
     *            already exists in the catalog.
     * RETURN: TRUE if it exists
     *         FALSE if it doesn't exist.
     */
    public boolean categoryNameExists(String name)
    {
        Secretary.startFxn("CatalogVO.categoryNameExists("+name+")");
        boolean temp = this.categories.containsValue(name);
        Secretary.endFxn("CatalogVO.categoryNameExists("+name+") => " + temp);
        return(temp);
    }
    public void categoryListSetViaID(int id)
    {
        this.categoryIteratorReset();
        do{
            this.setNextCategory();
        }while(this.categoryVO.getID() != id);
    }
    /*
     * METHOD NAME : categoryIDExists
     * ARGUMENTS : a String category ID
     * REQUIRE: n/a
     * FUNCTION : Search the categories for this category ID.
     * RETURN: TRUE if category ID exists
     *         FALSE if category ID doesn't exist.
     */
    public boolean categoryIDExists(String id)
    {
        Secretary.startFxn("CatalogVO.categoryIDExists("+id+")");
        boolean temp = this.categories.containsKey(id);
        Secretary.endFxn("CatalogVO.categoryIDExists("+id+") => " + temp);
        return temp;
    }
    
    /*
     * METHOD NAME : resetIterator
     * ARGUMENTS : n/a
     * REQUIRE: The categories vector is assigned
     * FUNCTION : Copy the category Vector into an Iterator to be gone through 
     *            to display the categories.
     * RETURN: n/a
     */
    public void categoryIteratorReset()
    {
        //Secretary.startFxn("CatalogVO.categoryIteratorReset()");
        this.categoryIterator = this.categories.values().iterator();
        //Secretary.endFxn("CatalogVO.categoryIteratorReset()");
    }
    /*
     * METHOD NAME : addCategory
     * ARGUMENTS : a CategoryVO object
     * REQUIRE: n/a
     * FUNCTION : Add the provided CategoryVO to the category list
     *            in this Catalog
     * RETURN: n/a
     */
    public void addCategory(CategoryVO newCat)
    {
        Secretary.startFxn("CatalogVO.addCategory(" + newCat.getID() + ", " + newCat.getName() + ")");
        this.categories.put(new Integer(newCat.getID()), newCat);
        this.categoryIteratorReset();
        Secretary.endFxn("CatalogVO.addCategory(" + newCat.getID() + ", " + newCat.getName() + ")");
    }  
    /*
     * METHOD NAME : categoryCreate
     * ARGUMENTS : The nick of the category you wish to create
     *             The name of the category you wish to create
     * REQUIRE: A category via this ID does not already exist.
     * FUNCTION : Create a category using the name provided
     *            Return the ID of the category.
     *            If a category by this name already exists,
     *            return the ID of this category.
     * RETURN: n/a
     */
    public void categoryCreate(String nick, String name)
    {   
        Secretary.startFxn("CatalogVO.categoryCreate("+nick+", "+name+")");
        this.categoryVO = new CategoryVO();
        this.categoryVO.setName(name);
        this.categoryVO.setNick(nick);
        this.categoryVO.setID(this.getMaxCatID() + 1);
        this.addCategory(this.categoryVO);
        Secretary.endFxn("CatalogVO.categoryCreate("+nick+", "+name+")");
    }
    
    /*
     * METHOD NAME : getMaxCatID
     * ARGUMENTS : n/a
     * REQUIRE: The categories have been loaded from the DB
     * FUNCTION : Go through the list of categories and get the largest
     *              ID. 
     * RETURN: The largest ID of the existing categories.
     */
    public int getMaxCatID()
    {
        Secretary.startFxn("CatalogVO.getMaxCatID()");
        int max = 0;
        this.categoryIteratorReset();
        while(this.categoryIterator.hasNext())
        {
            int id = ((CategoryVO)categoryIterator.next()).getID();
            if(id > max)
            {
                max = id;
            }
        }
        Secretary.endFxn("CatalogVO.getMaxCatID() returning " + max);
        return max;
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
    public void categoryDelete(int id)
    {
        Secretary.startFxn("CatalogVO.categoryDelete("+id+")");
        this.categories.remove(new Integer(id));
        Secretary.endFxn("CatalogVO.categoryDelete("+id+")");
    }
    
    /*
     * METHOD NAME: categoryRename
     * ARGUMENTS: a category ID, a new name for the category
     * REQUIRE: There is a category with the provided ID
     * FUNCTION: Rename the category with the given ID to the the given name.
     * RETURN: n/a
     */
    public void categoryRename(int catID, String newNick, String newName)
    {
        Secretary.startFxn("CatalogVO.categoryRename("+newName+")");
        //CategoryVO cat = this.getCategoryViaID(catID);
        this.categoryVO.setName(newName);
        this.categoryVO.setNick(newNick);
        //this.categoryDelete(catID);
        this.categories.put(new Integer(catID), this.categoryVO);
        Secretary.endFxn("CatalogVO.categoryRename("+newName+")");
    }
    
    /*
     * METHOD NAME: clearCategories
     * ARGUMENTS: n/a
     * REQUIRE: n/a
     * FUNCTION: Empty the local list of categories.
     * RETURN: n/a
     */
    public void clearCategories()
    {
        Secretary.startFxn("CatalogVO.clearCategories()");
        this.categories = new LinkedHashMap();
        Secretary.endFxn("CatalogVO.clearCategories()");
    }
    
    /*
     * METHOD NAME: subCategoryCreate
     * ARGUMENTS: the name of the new subcategory
     *            the id of the will be parent.
     * REQUIRE:  a category of this id exists.
     * FUNCTION: Create a new SubCategoryVO in the proper CategoryVO.
     *           Place the new SubCategoryVO in the CategoryVO's memory to use.
     *           Place the CategoryVO parent in this object's memory to use.
     * RETURN:  This new SubCategoryVO
     */          
    public SubCategoryVO subCategoryCreate(String name, int parentID)
    {
        Secretary.startFxn("CatalogVO.subCategoryCreate("+name+", "+parentID+")");
        this.setCategoryViaID(parentID);
        // CATALOG WILL GENERATE THE SUBCATID BECAUSE IT CAN SEE INTO EVERY CATEGORY.
        int id = this.getMaxSubCatID() + 1;
        SubCategoryVO vo =  this.categoryVO.subCategoryCreate(id, name);
        Secretary.endFxn("CatalogVO.subCategoryCreate("+name+", "+parentID+")");
        return vo;
    }           
    public void itemModify(int itemID, String name, float cost, int glNum, boolean active, int orderQuantityID)
    {
        Secretary.startFxn("CatalogVO.itemModify("+itemID+", "+name+", "+cost+", "+
                            glNum+", "+active+", "+orderQuantityID+")");
        //Secretary.write("CLASS = " + this.orderQuantities.get(new Integer(orderQuantityID)).getClass().toString());
        OrderQuantityVO oqvo = (OrderQuantityVO)(this.orderQuantities.get(new Integer(orderQuantityID)));
        this.categoryVO.itemModify(itemID, name, cost, glNum, active, orderQuantityID, oqvo.getName());
        Secretary.endFxn("CatalogVO.itemModify("+itemID+", "+name+", "+cost+", "+
                            glNum+", "+active+", "+orderQuantityID+")");
    }
    public void itemMove(int itemID, int oldCatID, int oldSubCatID, int newCatID, int newSubCatID)
    {
        Secretary.startFxn("CatalogVO.itemMove("+itemID+", "+oldCatID+", "+oldSubCatID+", "+newCatID+", "+newSubCatID+")");
        if(oldSubCatID != newSubCatID)
        {
            if(oldCatID == newCatID)
            {
                CategoryVO cat = this.getCategoryViaID(oldCatID);
                cat.setSubCategoryViaID(oldSubCatID);
                ItemVO itemVO = cat.pullItemViaID(itemID);
                cat.setSubCategoryViaID(newSubCatID);
                cat.itemAdd(itemVO);
                this.setCategoryVO(cat);
            }
            else
            {
                CategoryVO daOld = this.getCategoryViaID(oldCatID);
                //Secretary.write("Old category NAME = " + daOld.getName());
                CategoryVO daNew = this.getCategoryViaID(newCatID);
                //Secretary.write("New category NAME = " + daNew.getName());
                daOld.setSubCategoryViaID(oldSubCatID);
                //Secretary.write("Old SubCategory NAME = " + daOld.getSubCategoryName());
                daNew.setSubCategoryViaID(newSubCatID);
                //Secretary.write("New SubCategory NAME = " + daNew.getSubCategoryName());
                ItemVO temp = daOld.pullItemViaID(itemID);
                //Secretary.write("temp.getName() = " + temp.getName());
                daNew.itemAdd(temp);
                this.setCategoryVO(daNew);
            }
            //this.categoryUpdate(daOld);
            //this.categoryUpdate(daNew);
        }
        Secretary.endFxn("CatalogVO.itemMove("+itemID+", "+oldCatID+", "+oldSubCatID+", "+newCatID+", "+newSubCatID+")");
    }
    /*
    public void categoryUpdate(CategoryVO c)
    {
        this.categoryDelete(c.getID());
        this.addCategory(c);
    }
    */
    /*
     * METHOD NAME : subCategoryMove
     * ARGUMENTS : The SubCategory's ID
     *             The old category's ID
     *             The new category's ID
     * REQUIRE: the category provided holds the subcategory provided.
     * FUNCTION : Move the subcategory from the old category to the new category
     * RETURN: n/a
     */
    public void subCategoryMove(int subCatID, int oldCatID, int newCatID)
    {
        Secretary.startFxn("CatalogVO.subCategoryMove("+subCatID+", "+oldCatID+", "+newCatID+")");
        if(newCatID != oldCatID)
        {
            CategoryVO daOld = this.getCategoryViaID(oldCatID);
            CategoryVO daNew = this.getCategoryViaID(newCatID);
            daNew.subCategoryAdd(daOld.getSubCategoryViaID(subCatID));
            daOld.subCategoryDelete(subCatID);
            this.setCategoryVO(daNew);
        }        
        Secretary.endFxn("CatalogVO.subCategoryMove("+subCatID+", "+oldCatID+", "+newCatID+")");
    }
    public void subCategoryRename(int catID, int subCatID, String subCatName)
    {
        Secretary.startFxn("CatalogVO.subCategoryRename("+catID+", "+subCatID+", "+subCatName+")");
        if(this.categoryVO.getID() != catID)
            this.categoryVO = this.getCategoryViaID(catID);
        this.categoryVO.subCategoryRename(subCatID, subCatName);
        Secretary.endFxn("CatalogVO.subCategoryRename("+catID+", "+subCatID+", "+subCatName+")");
    }
    /*
     * METHOD NAME : getMaxSubCatID
     * ARGUMENTS : n/a
     * REQUIRE: this.subCategories has been set.
     * FUNCTION : Search the subcategory list for the largest ID number.
     * RETURN: the largest subcategory id number.
     */
    public int getMaxSubCatID()
    {
        Secretary.startFxn("CatalogVO.getMaxSubCatID()");
        //Collection c = this.categories.values();
        this.categoryIterator = this.categories.values().iterator();
        int max = 0;
        while(this.categoryIterator.hasNext())
        {
            int id = ((CategoryVO)categoryIterator.next()).getMaxSubCatID();
            if(id > max)
            {
                max = id;
            }
        }
        Secretary.endFxn("CatalogVO.getMaxSubCatID()");
        return max;
    }
        
    /*
     * METHOD NAME: getCurrentCategoryVO
     * ARGUMENTS: n/a
     * REQUIRE: One of my setCategoryXXXXX method has been called successfully.
     * FUNCTION: Return the CategoryVO currently in memory.
     * RETURN: Return the CategoryVO currently in memory.
     */
    public CategoryVO getCurrentCategoryVO()
    {
        Secretary.startFxn("CatalogVO.getCurrentCategoryVO()");
        CategoryVO temp = this.categoryVO;
        Secretary.endFxn("CatalogVO.getCurrentCategoryVO()");
        return temp;
    }
    
    
    /*
     * METHOD NAME : categoryListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the category iterator and determine
     *            if there are more categories to display
     * RETURN:  TRUE if another category is in the list
     *          FALSE if another category is not in the list
     */
    public boolean categoryListHasMore()
    {
        //Secretary.startFxn("CatalogVO.categoryListHasMore()");
        boolean temp = this.categoryIterator.hasNext();
        //Secretary.endFxn("CatalogVO.categoryListHasMore() => " + temp);
        return temp;
    }
    
    /*
     * METHOD NAME : setNextCategory
     * ARGUMENTS :  
     * REQUIRE: n/a
     * FUNCTION : Set the category in the memory to be the 
     *            next category in the iterator 
     * RETURN:  n/a
     */
    public void setNextCategory()
    {
        Secretary.startFxn("CatalogVO.setNextCategory()");
        if(this.categoryListHasMore())
            this.categoryVO = (CategoryVO)categoryIterator.next();
        Secretary.endFxn("CatalogVO.setNextCategory()");
    }
    
    /***************************************************************
     * SUB CATEGORY FXNS
     **************************************************************/
    
    /*
     * METHOD NAME : setSubCategoryViaID
     * ARGUMENTS : the ID of a subcategory
     * REQUIRE: A SubCategory of this ID exists
     * FUNCTION : Search the subcategory list of the category currently in 
     *            memory for a subcategory with the provided ID.
     * RETURN:  n/a
     */
    public void setSubCategoryViaID(int id)
    {
        Secretary.startFxn("CatalogVO.setSubCategoryViaID("+id+")");
        this.categoryVO.setSubCategoryViaID(id);
        Secretary.endFxn("CatalogVO.setSubCategoryViaID("+id+")");
    }  
    /*
     * METHOD NAME : subCategoryExists
     * ARGUMENTS : the name of the category 
     *             the parent id to check for this name.
     * REQUIRE: The catalog has been loaded from the DB.
     * FUNCTION : Look in the subcategories of the cateogory provided
     *              and tell if this subCategory exists.
     * RETURN:  TRUE if the category holds a subcategory of this name
     *          FALSE if the category does not hold a subcategory of this name
     */
    
    public boolean subCategoryExists(String name, int parentID)
    {
        Secretary.startFxn("CatalogVO.subCategoryExists(" +name +", "+parentID+")");
        CategoryVO c = (CategoryVO)this.getCategoryViaID(parentID);
        boolean toReturn = c.subCatNameExists(name);
        Secretary.endFxn("CatalogVO.subCategoryExists(" +name +", "+parentID+")");
        return toReturn;
    }
    
    
    /*
     * METHOD NAME : subCategoryIteratorReset
     * ARGUMENTS : n/a
     * REQUIRE: The categories vector is assigned
     * FUNCTION : Copy the category Vector into an Iterator to be gone through 
     *            to display the categories.
     * RETURN: n/a
     */
    public void subCategoryIteratorReset()
    {
        Secretary.startFxn("CatalogVO.subCategoryIteratorReset()");
        this.categoryVO.subCategoryIteratorReset();
        //this.categoryIterator = this.categories.values().iterator();
        Secretary.endFxn("CatalogVO.subCategoryIteratorReset()");
    }
    
    
    /*
     * METHOD NAME : subCategoryListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the category's subcategory iterator and determine
     *            if there are more subcategories to display
     * RETURN:  TRUE if another subcategory is in the list
     *          FALSE if another subcategory is not in the list
     */
    public boolean subCategoryListHasMore()
    {
        //Secretary.startFxn("CatalogVO.subCategoryListHasMore()");
        boolean temp = this.categoryVO.subCategoryListHasMore();
        //Secretary.endFxn("CatalogVO.subCategoryListHasMore() => " + temp);
        return temp;
    }
    
    /*
     * METHOD NAME : setNextSubCategory
     * ARGUMENTS :  
     * REQUIRE: n/a
     * FUNCTION : Set the next subcategory in the category's iterator to be the 
     *            current subcategory in the category's memory.
     * RETURN:  n/a
     */
    public void setNextSubCategory()
    {
        //Secretary.startFxn("CatalogVO.setNextSubCategory()");
        this.categoryVO.setNextSubCategory();
        //Secretary.endFxn("CatalogVO.setNextSubCategory()");
    }
    
    public String getSubCategoryName()
    {
        //Secretary.startFxn("CatalogVO.getSubCategoryName()");
        String temp = this.categoryVO.getSubCategoryName();
        //Secretary.endFxn("CatalogVO.getSubCategoryName()");
        return temp;
    }
    public int getSubCategoryID()
    {
        //Secretary.startFxn("CatalogVO.getSubCategoryID()");
        int toReturn = this.categoryVO.getSubCategoryID();
        //Secretary.endFxn("CatalogVO.getSubCategoryID()");
        return toReturn;
    }
    public int getNumSubCats()
    {
        return this.categoryVO.getNumSubCats();
    }
    
    /***************************************************************
     * ITEM FXNS
     **************************************************************/
   public int getSubCatNumItems()
    {
        return this.categoryVO.getSubCatNumItems();
    }
    
    /*
     * METHOD NAME : itemIteratorReset
     * ARGUMENTS : n/a
     * REQUIRE: The catalog is loaded from the DB
     * FUNCTION : Call the function in the current Category
     * RETURN: n/a
     */
    public void itemIteratorReset()
    {
        //Secretary.startFxn("CatalogVO.itemIteratorReset()");
        this.categoryVO.itemIteratorReset();
        //Secretary.endFxn("CatalogVO.itemIteratorReset()");
    }
    
    
    /*
     * METHOD NAME : itemListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the category's subcategory's item iterator and determine
     *            if there are more items to display
     * RETURN:  TRUE if another item is in the list
     *          FALSE if another item is not in the list
     */
    public boolean itemListHasMore()
    {
        //Secretary.startFxn("CatalogVO.itemListHasMore()");
        boolean temp = this.categoryVO.itemListHasMore();
        //Secretary.endFxn("CatalogVO.itemListHasMore() => " + temp);
        return temp;
    }
    
    /*
     * METHOD NAME : setNextItem
     * ARGUMENTS :  
     * REQUIRE: n/a
     * FUNCTION : Set the next item in the subcategory's iterator to be the 
     *            current item in the subcategory's memory.
     * RETURN:  n/a
     */
    public void setNextItem()
    {
        //Secretary.startFxn("CatalogVO.setNextItem()");
        this.categoryVO.setNextItem();
        //Secretary.endFxn("CatalogVO.setNextItem()");
    }
    public int getItemID()
    {
        //Secretary.startFxn("CatalogVO.getItemID()");
        int temp = this.categoryVO.getItemID();
        //Secretary.endFxn("CatalogVO.getItemID() => " + temp);
        return temp;
    }
    public String getItemName()
    {
        //Secretary.startFxn("CatalogVO.getItemName()");
        String temp = this.categoryVO.getItemName();
        //Secretary.endFxn("CatalogVO.getItemName() => " + temp);
        return temp;
    }
    public float getItemCost()
    {
       // Secretary.startFxn("CatalogVO.getItemCost()");
        float temp = this.categoryVO.getItemCost();
       // Secretary.endFxn("CatalogVO.getItemCost() => " + temp);
        return temp;
    }
    public int getItemGlNum()
    {
       // Secretary.startFxn("CatalogVO.getItemGlNum()");
        int temp = this.categoryVO.getItemGlNum();
        //Secretary.endFxn("CatalogVO.getItemGlNum() => " + temp);
        return temp;
    }
    public boolean getItemActive()
    {
        //Secretary.startFxn("CatalogVO.getItemActive()");
        boolean temp = this.categoryVO.getItemActive();
        //Secretary.endFxn("CatalogVO.getItemActive() => " + temp);
        return temp;
    }
    
    public String getItemOrderQuantityName()
    {
        return this.categoryVO.getItemOrderQuantityName();
    }
    
    public int getItemOrderQuantityID()
    {
        return this.categoryVO.getItemOrderQuantityID();
    }
    
    /*
     * METHOD NAME : getNumItems
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Get the total number of items in the catalog.
     *            Set the Catalog's number of items variable so later if
     *            this function is called, it doesn't have to sum up the 
     *            numbers from the categories.
     * RETURN:  the number of items in the catalog
     */
    public int getNumItems()
    {
        Secretary.startFxn("CatalogVO.getNumItems()");
        if(this.numItems == 0)
        {
            this.categoryIteratorReset();
            while(this.categoryListHasMore())
            {
                this.setNextCategory();
                this.numItems += this.categoryVO.getNumItems();
            }   
        }
        Secretary.endFxn("CatalogVO.getNumItems() => "+numItems);
        return this.numItems;
    }
    /***************************************************************
     * END ITEM FXNS
     **************************************************************/
    public void orderQuantitiesReset()
    {
        Secretary.startFxn("Catalog.orderQuantitiesReset()");
        this.orderQuantities = new LinkedHashMap();
        this.orderQuantityIterator = this.orderQuantities.values().iterator();
        Secretary.endFxn("Catalog.orderQuantitiesReset()");
    }
    public void orderQuantityListAdd(Integer id, String name)
    {
        //Secretary.startFxn("Catalog.orderQuantityListAdd("+id+", "+name+")");
        this.orderQuantityVO = new OrderQuantityVO(id.intValue(), name);
        this.orderQuantities.put(id, orderQuantityVO);
        //Secretary.endFxn("Catalog.orderQuantityListAdd("+id+", "+name+")");
    }
    public void orderQuantityIteratorReset()
    {
        Secretary.startFxn("Catalog.orderQuantityIteratorReset()");
        this.orderQuantityIterator = this.orderQuantities.values().iterator();
        Secretary.write("size = " + this.orderQuantities.size());
        Secretary.write("hasNext = " + this.orderQuantityIterator.hasNext());
        Secretary.endFxn("Catalog.orderQuantityIteratorReset()");
    }
    public void setNextOrderQuantity()
    {
        Secretary.startFxn("Catalog.setNextOrderQuantity()");
        if(this.orderQuantityListHasMore())
        {
            this.orderQuantityVO = (OrderQuantityVO)(orderQuantityIterator.next());
        }
        Secretary.endFxn("Catalog.setNextOrderQuantity()");
    }
    public boolean orderQuantityListHasMore()
    {
        Secretary.startFxn("Catalog.orderQuantityListHasMore()");
        boolean x = this.orderQuantityIterator.hasNext();
        Secretary.endFxn("Catalog.orderQuantityListHasMore() => " + x);
        return x;
    }
    public int getTempOrderQuantityID()
    {
        return this.orderQuantityVO.getID();
    }
    public String getTempOrderQuantityName()
    {
        return this.orderQuantityVO.getName();
    }
    /*
    public int getLooseItemID()
    {
        Secretary.startFxn("CatalogVO.getLooseItemID()");
        int temp = this.looseItemVO.getID();
        Secretary.endFxn("CatalogVO.getLooseItemID() => " + temp);
        return temp;
    }
    public String getLooseItemName()
    {
        Secretary.startFxn("CatalogVO.getLooseItemName()");
        String temp = this.looseItemVO.getName();
        Secretary.endFxn("CatalogVO.getLooseItemName() => " + temp);
        return temp;
    }
    public float getLooseItemCost()
    {
        Secretary.startFxn("CatalogVO.getLooseItemCost()");
        float temp = this.looseItemVO.getCost();
        Secretary.endFxn("CatalogVO.getLooseItemCost() => " + temp);
        return temp;
    }
    public int getLooseItemGlNum()
    {
        Secretary.startFxn("CatalogVO.getLooseItemGlNum()");
        int temp = this.looseItemVO.getGlNum();
        Secretary.endFxn("CatalogVO.getLooseItemGlNum() => " + temp);
        return temp;
    }
    public boolean getLooseItemActive()
    {
        Secretary.startFxn("CatalogVO.getLooseItemActive()");
        boolean temp = this.looseItemVO.getActive();
        Secretary.endFxn("CatalogVO.getLooseItemActive() => " + temp);
        return temp;
    }
    
    
    */
    
    
    
    
    /*
     * METHOD NAME : setCategoryViaVectorLoc
     * ARGUMENTS : the Vector location of the category.
     * REQUIRE: The category Vector is loaded.
     * FUNCTION : Used to itterate through the entire list of categories.
     *            Takes the category in the provided vector location and 
     *            places it into the category variable.
     * RETURN: n/a
    private void setCategoryViaVectorLoc(String key)
    {
        log.write("CatalogVO.setCategoryViaVectorLoc("+loc+")");
        this.category = (CategoryVO)categories.elementAt(loc);
    }
     */
    /*
     * METHOD NAME : setCategoryViaName
     * ARGUMENTS : String name
     * REQUIRE: n/a
     * FUNCTION : Set the current category in memory to the 
     *            one with the provided name.
     * RETURN:  true if this name is found.
     *          false if this name doesn't exist.
    private boolean setCategoryViaName(String name)
    {
        this.category = null;
        log.write("CatalogVO.setCategoryViaName("+name+")");
        for(int x = 0; x < this.categories.size(); x++)
        {
            CategoryVO c = (CategoryVO)(this.categories.elementAt(x));
            if(name.compareTo(c.getName()) == 0)
            {
                this.category = c;
                return true;
            }
        }
        return false;
    }
     */
    /*
     * METHOD NAME : getMaxCategoryID
     * ARGUMENTS : n/a
     * REQUIRE: the categories vector not null
     * FUNCTION : getMaxCategoryID returns the largest ID of the categories.
     *            called from categoryCreate to generate an ID
     * RETURN: The ID of the category with the largest ID
     * OUTDATED ==== CATEGORY ID HAS BEEN CONVERTED TO A STRING SO WE NO LONGER
     *      NEED THIS FUNCTION
     
    private int getMaxCategoryID()
    {
        log.write("CatalogVO.getMaxCategoryID()");
        int max = 0;
        for(int x=0;x<this.categories.size();x++)
        {
            CategoryVO cat = (CategoryVO)(this.categories.elementAt(x));
            if(cat.id >= max)
            {
                max = cat.id;
            }
        }
        return max;quantityListHasMore
    }
    */
    public int categoryListGetFirstKey()
    {
        Object[] keys = this.categories.keySet().toArray();
        Integer theKey = (Integer)(keys[0]);
        return theKey.intValue();
    }
    public int categoryListGetPrevKey()
    {
        Object[] keys = this.categories.keySet().toArray();
        for(int c=0; c < keys.length; c++)
        {
            Integer theKey = (Integer)(keys[c]);
            if(theKey.intValue() == this.categoryVO.getID())
            {
                return (((Integer)(keys[c-1])).intValue());
            }
        }
        return -1;
    }
    public int categoryListGetNextKey()
    {
        Object[] keys = this.categories.keySet().toArray();
        for(int c=0; c < keys.length; c++)
        {
            Integer theKey = (Integer)(keys[c]);
            if(theKey.intValue() == this.categoryVO.getID())
            {
                return (((Integer)(keys[c+1])).intValue());
            }
        }
        return -1;
    }
    public int categoryListGetLastKey()
    {
        Object[] keys = this.categories.keySet().toArray();
        Integer theKey = (Integer)(keys[keys.length - 1]);
        return theKey.intValue();
    }
    
    /*
    public void categoryGoToFirst()
    {
        Object[] keys = this.categories.keySet().toArray();
        Integer theKey = (Integer)(keys[0]);
        this.setCategoryViaID(theKey.intValue());
    }
    public void categoryGoToPrev()
    {
        Object[] keys = this.categories.keySet().toArray();
        for(int c=0; c < keys.length; c++)
        {
            Integer theKey = (Integer)(keys[c]);
            if(theKey.intValue() == this.categoryVO.getID())
            {
                this.categoryVO = this.getCategoryViaID(((Integer)(keys[c-1])).intValue());
            }
        }
    }
    public void categoryGoToNext()
    {
        Object[] keys = this.categories.keySet().toArray();
        for(int c=0; c < keys.length; c++)
        {
            Integer theKey = (Integer)(keys[c]);
            if(theKey.intValue() == this.categoryVO.getID())
            {
                this.categoryVO = this.getCategoryViaID(((Integer)(keys[c+1])).intValue());
            }
        }
    }
    public void categoryGoToLast()
    {
        Object[] keys = this.categories.keySet().toArray();
        Integer theKey = (Integer)(keys[keys.length-1]);
        this.setCategoryViaID(theKey.intValue());
    }
     */
}
