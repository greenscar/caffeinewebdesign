package vos;
import java.util.*;
import logging.Secretary;
public class CategoryVO extends Section implements java.io.Serializable {
    private LinkedHashMap subCategories;
    private Iterator subCategoryIterator;
    private SubCategoryVO subCategoryVO = null;
    // The id is the unique identifier used in the DB
    private int id;
    // The nick is the character set created by the admin to use as an identifier
    private String nick;
    
    /** Creates a new instance of Category */
    public CategoryVO() {
        this.subCategories = new java.util.LinkedHashMap();
    }
    
    public void setNick(String n)
    {
        this.nick = n;
    }
    public String getNick()
    {
        return this.nick;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getID()
    {
        return this.id;
    }
    /*
     * METHOD NAME : addSubCat
     * ARGUMENTS : a SubCategory
     * REQUIRE: n/a
     * FUNCTION : Add the provided SubCategory to this category's list
     *            of sub categories.
     * RETURN: n/a
     */
    public void subCategoryAdd(SubCategoryVO subCat) {
        //Secretary.startFxn("CategoryVO.subCategoryAdd("+subCat.getID()+")");
        int k = subCat.getID();
        this.subCategoryVO = subCat;
        this.subCategories.put(new Integer(k), subCat);
        //Secretary.endFxn("CategoryVO.subCategoryAdd("+subCat.getID()+")");
    }
        
    
    /*
     * METHOD NAME : subCategoryCreate
     * ARGUMENTS : a Name String
     * REQUIRE: n/a
     * FUNCTION : Create a new subcategory via this name.
     *            Generate the ID based on ID's that exist in my subcategories.
     * RETURN: n/a
     */
    public SubCategoryVO subCategoryCreate(int id, String name)
    {
        //Secretary.startFxn("CategoryVO.subCategoryCreate("+id+", "+name+")");
        // Create the subcategory.
        this.subCategoryVO = new SubCategoryVO();
        this.subCategoryVO.setName(name);
        this.subCategoryVO.setID(id);
            
        // Add the subcategory to the category.
        this.subCategoryAdd(this.subCategoryVO);
        //Secretary.endFxn("CategoryVO.subCategoryCreate("+id+", "+name+")");
        return this.subCategoryVO;
    }
     
    // RETURN THE SUBCATEGORY CURRENTLY IN MEMORY.
    public SubCategoryVO getSubCategory()
    {
        //Secretary.startFxn("CategoryVO.getSubCategory()");
        SubCategoryVO temp = this.subCategoryVO;
        //Secretary.endFxn("CategoryVO.getSubCategory()");
        return temp;
    }
    /*
     * METHOD NAME : getSubCategoryViaID
     * ARGUMENTS : The int ID of the subcategory wanted.
     * REQUIRE: A subcategory with the provided ID exists
     * FUNCTION : Look through this category's subcategories and return
     *              the one with the ID provided.
     * RETURN:  The subcategory with the provided ID
     */
    public SubCategoryVO getSubCategoryViaID(int id)
    {
        //Secretary.startFxn("CategoryVO.getSubCategoryViaID("+id+")");
        SubCategoryVO temp = (SubCategoryVO)(this.subCategories.get(new Integer(id)));
        //Secretary.endFxn("CategoryVO.getSubCategoryViaID("+id+")");
        return temp;
    }
    
    /*
     * METHOD NAME : subCategoryListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the subcategory iterator and determine
     *            if there are more subcategories to display
     * RETURN:  TRUE if another subcategory is in the list
     *          FALSE if another subcategory is not in the list
     */
    public boolean subCategoryListHasMore()
    {
        //Secretary.startFxn("CategoryVO.subCategoryListHasMore()");
        boolean temp = false;
        if(this.subCategoryIterator != null)
            temp = this.subCategoryIterator.hasNext();
        //Secretary.endFxn("CategoryVO.subCategoryListHasMore() => " + temp);
        return temp;
    }
     /*
     * METHOD NAME : setNextSubCategory
     * ARGUMENTS :  
     * REQUIRE: n/a
     * FUNCTION : Set the subcategory in the memory to be the 
     *            next subcategory in the iterator 
     * RETURN:  n/a
     */
    public void setNextSubCategory()
    {
        //Secretary.startFxn("CategoryVO.setNextSubCategory()");
        if(this.subCategoryListHasMore())
        {
            SubCategoryVO temp = (SubCategoryVO)subCategoryIterator.next();
            ////Secretary.write("temp = subCategoryIterator.next()");
            this.subCategoryVO = temp;
            //this.subCategoryVO.itemIteratorReset();
            ////Secretary.write("this.subCategoryVO = subCategoryIterator.next()");
        }
        //Secretary.endFxn("CategoryVO.setNextSubCategory()");
    }    
    public void setSubCategoryViaID(int id)
    {   
        Secretary.startFxn("CategoryVO.setSubCategoryViaID("+id+")");
        this.subCategoryVO = (SubCategoryVO)(this.subCategories.get(new Integer(id)));
        Secretary.endFxn("CategoryVO.setSubCategoryViaID("+id+")");
    }
    /*
     * METHOD NAME : subCategoryDelete
     * ARGUMENTS : a SubCategory ID
     * REQUIRE: n/a
     * FUNCTION : Delete the provided subcategory object from this Category.
     * RETURN: n/a
     */
    public void subCategoryDelete(int subCatID) {
        //Secretary.startFxn("CategoryVO.subCategoryDelete("+id+")");
        Integer x = new Integer(subCatID);
        this.subCategories.remove(x);
        //Secretary.endFxn("CategoryVO.subCategoryDelete("+id+")");
    }
    
    public void subCategoryRename(int subCatID, String newName)
    {
        //Secretary.startFxn("CategoryVO.subCategoryRename("+subCatID+", "+newName+")");
        ((SubCategoryVO)(this.subCategories.get(new Integer(subCatID)))).setName(newName);
        //Secretary.endFxn("CategoryVO.subCategoryRename("+subCatID+", "+newName+")");
    }
    /*
     * METHOD NAME: subCategoryListClear
     * ARGUMENTS: n/a
     * REQUIRE: n/a
     * FUNCTION: Empty the local list of categories.
     * RETURN: n/a
     */
    public void subCategoryListClear()
    {
        //Secretary.startFxn("CategoryVO.subCategoryListClear()");
        this.subCategories = new LinkedHashMap();
        //Secretary.endFxn("CategoryVO.subCategoryListClear()");
    }
    /*
     * METHOD NAME: subCategoryIteratorReset
     * ARGUMENTS: n/a
     * REQUIRE: This category's subcategory list is filled.
     * FUNCTION: Reset the pointer of the subcategory iterator back to the beginning.
     * RETURN: n/a
     */
    public void subCategoryIteratorReset()
    {   
        //Secretary.startFxn("CategoryVO.subCategoryIteratorReset()");
        this.subCategoryIterator = this.subCategories.values().iterator();
        //Secretary.endFxn("CategoryVO.subCategoryIteratorReset()");
    }
    /*
     * METHOD NAME : subCatNameExists
     * ARGUMENTS : a SubCategory Name
     * REQUIRE: n/a
     * FUNCTION : Search the subcategory list for a subcategory of this name
     * RETURN: TRUE if this subcategory exists.
     *         FALSE if this subcategory doesn't exist.
     */
    public boolean subCatNameExists(String subCatName)
    {
        //Secretary.startFxn("CategoryVO.subCatNameExists("+subCatName+")");
        boolean toReturn = this.subCategories.containsValue(subCatName);
        //Secretary.endFxn("CategoryVO.subCatNameExists("+subCatName+")");
        return toReturn;
    }
    
    public int getSubCategoryID()
    {
        //Secretary.startFxn("CategoryVO.getSubCategoryID()");
        int x = this.subCategoryVO.getID();
        //Secretary.endFxn("CategoryVO.getSubCategoryID()");
        return x;
    }
    public String getSubCategoryName()
    {
        //Secretary.startFxn("CategoryVO.getSubCategoryName()");
        String x = this.subCategoryVO.getName();
        //Secretary.endFxn("CategoryVO.getSubCategoryName()");
        return x;
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
        //Secretary.startFxn("CategoryVO.getMaxSubCatID()");
        int max = 0;
        Iterator allSubs = this.subCategories.values().iterator();
        while(allSubs.hasNext())
        {
            int id = ((SubCategoryVO)allSubs.next()).getID();
            if(id > max)
            {
                max = id;
            }
        }
        //Secretary.endFxn("CategoryVO.getMaxSubCatID()");
        return max;
    }
        
    public int getNumSubCats()
    {
        return this.subCategories.size();
    }
    
    /***************************************************************
     * ITEM FXNS
     **************************************************************/
     public int getSubCatNumItems()
     {
         return this.subCategoryVO.getNumItems();
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
        //Secretary.startFxn("CategoryVO.itemIteratorReset()");
        this.subCategoryVO.itemIteratorReset();
        //Secretary.endFxn("CategoryVO.itemIteratorReset()");
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
        //Secretary.startFxn("CategoryVO.itemListHasMore()");
        boolean temp = this.subCategoryVO.itemListHasMore();
        //Secretary.endFxn("CategoryVO.itemListHasMore() => " + temp);
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
        //Secretary.startFxn("CategoryVO.setNextItem()");
        this.subCategoryVO.setNextItem();
        //Secretary.endFxn("CategoryVO.setNextItem()");
    }
    public int getItemID()
    {
       // //Secretary.startFxn("CategoryVO.getItemID()");
        int temp = this.subCategoryVO.getItemID();
       // //Secretary.endFxn("CategoryVO.getItemID() => " + temp);
        return temp;
    }
    public String getItemName()
    {
       // //Secretary.startFxn("CategoryVO.getItemName()");
        String temp = this.subCategoryVO.getItemName();
       // //Secretary.endFxn("CategoryVO.getItemName() => " + temp);
        return temp;
    }
    public float getItemCost()
    {
        //Secretary.startFxn("CategoryVO.getItemCost()");
        float temp = this.subCategoryVO.getItemCost();
       // //Secretary.endFxn("CategoryVO.getItemCost() => " + temp);
        return temp;
    }
    public int getItemGlNum()
    {
      //  //Secretary.startFxn("CategoryVO.getItemGlNum()");
        int temp = this.subCategoryVO.getItemGlNum();
      //  //Secretary.endFxn("CategoryVO.getItemGlNum() => " + temp);
        return temp;
    }
    
    public String getItemOrderQuantityName()
    {
        return this.subCategoryVO.getItemOrderQuantityName();
    }
    public int getItemOrderQuantityID()
    {
        return this.subCategoryVO.getItemOrderQuantityID();
    }
    public boolean getItemActive()
    {
      //  //Secretary.startFxn("CategoryVO.getItemActive()");
        boolean temp = this.subCategoryVO.getItemActive();
       // //Secretary.endFxn("CategoryVO.getItemActive() => " + temp);
        return temp;
    }
    public ItemVO getItemViaID(int id)
    {
        return this.subCategoryVO.getItemViaID(id);
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
    // Go through every subcategory and get num Items.
    public int getNumItems()
    {
        //Secretary.startFxn("CategoryVO.getNumItems()");
        int numItems = 0;
        this.subCategoryIteratorReset();
        while(this.subCategoryListHasMore())
        {
            this.setNextSubCategory();
            numItems += this.subCategoryVO.getNumItems();
        }   
        //Secretary.endFxn("CategoryVO.getNumItems() => "+numItems);
        return numItems;
    }
    public ItemVO pullItemViaID(int id)
    {
        Secretary.startFxn("CategoryVO.pullItemViaID("+id+")");
        ItemVO t = null;
        t = this.subCategoryVO.pullItemViaID(id);
        Secretary.endFxn("CategoryVO.pullItemViaID("+id+")");
        return t;
    }
    public void setItemViaID(int itemID, int subCatID)
    {
        //Secretary.startFxn("CategoryVO.setItemViaID("+itemID+", "+subCatID+")");
        this.setSubCategoryViaID(subCatID);
        this.subCategoryVO.setItemViaID(itemID);
        //Secretary.endFxn("CategoryVO.setItemViaID("+itemID+", "+subCatID+")");
    }
    
    public void itemAdd(ItemVO item)
    {
        Secretary.startFxn("CategoryVO.itemAdd("+ item.getName() +")");
        //Secretary.write("subCatID = ");
        //Secretary.write(this.subCategoryVO.getName());
        //Secretary.write(" = subCatID");
        this.subCategoryVO.addItem(item);
        Secretary.endFxn("CategoryVO.itemAdd(item)");
    }
    public void itemModify(int itemID, String name, float cost, int glNum, boolean active, int orderQuantityID, String orderQuantityName)
    {
        Secretary.startFxn("CategoryVO.itemModify("+itemID+", "+name+", "+cost+", "+
                glNum+", "+active+", "+orderQuantityID+", "+orderQuantityName+")");
        this.subCategoryVO.itemModify(itemID, name, cost, glNum, active, orderQuantityID, orderQuantityName);
        //this.subCategoryIteratorReset();
        Secretary.endFxn("CategoryVO.itemModify("+itemID+", "+name+", "+cost+", "+
                glNum+", "+active+", "+orderQuantityID+", "+orderQuantityName+")");
    }
    /***************************************************************
     * END ITEM FXNS
     **************************************************************/
}
