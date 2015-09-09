package vos;
import java.util.*;
import logging.Secretary;

public class SubCategoryVO extends Section implements java.io.Serializable {
    
    private int id;
    private java.util.LinkedHashMap items;
    private Iterator itemIterator;
    private ItemVO itemVO = null;
    
    public SubCategoryVO() {
        this.items = new java.util.LinkedHashMap();
    }
    public void setID(int id){
        //logging.//Secretary.startFxn("SubCategoryVO.setID("+id+")");
        this.id = id;
        //logging.//Secretary.endFxn("SubCategoryVO.setID("+id+")");
    }
    public int getID()
    {
        //logging.//Secretary.startFxn("SubCategoryVO.getID()");
        int x = this.id;
        //logging.//Secretary.endFxn("SubCategoryVO.getID()");
        return x;
    }
   /*
     * METHOD NAME : addItem
     * ARGUMENTS : An ItemVO instance
     * REQUIRE: ItemVO's ID has been set.
     * FUNCTION : Insert this Item into this subcategory's Item list
     * RETURN: n/a
     */ 
    public void addItem(ItemVO item)
    {
        //logging.Secretary.startFxn("SubCategoryVO.addItem("+item.getID()+")");
        this.items.put(new Integer(item.getID()), item);
        this.itemVO = item;
        //logging.Secretary.endFxn("SubCategoryVO.addItem(ItemVO)");
    }
    
    public void delItem(int itemID)
    {
        
    }
    public void clearItemList()
    {
        //logging.//Secretary.startFxn("SubCategoryVO.clearItemList()");
        this.items = new java.util.LinkedHashMap();
        //logging.//Secretary.endFxn("SubCategoryVO.clearItemList()");
    }
    public int getNumItems()
    {
        return this.items.size();
    }
    
    
     /*
     * METHOD NAME : setNextSubItem
     * ARGUMENTS :  
     * REQUIRE: this.itemListHasMore() == true
     * FUNCTION : Set the item in the memory to be the 
     *            next item in the iterator 
     * RETURN:  n/a
     */
    public void setNextItem()
    {
        //Secretary.startFxn("SubCategoryVO.setNextItem()");
        this.itemVO = (ItemVO)itemIterator.next();
        //Secretary.endFxn("SubCategoryVO.setNextItem()");
    }    
    /*
     * METHOD NAME : itemListHasMore
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Check the item iterator and determine
     *            if there are more items to in the list
     * RETURN:  TRUE if another item is in the list
     *          FALSE if another item is not in the list
     */
    public boolean itemListHasMore()
    {
        //Secretary.startFxn("SubCategoryVO.itemListHasMore()");
        boolean temp = false;
        if(this.itemIterator != null)
            temp = this.itemIterator.hasNext();
        //Secretary.endFxn("SubCategoryVO.itemListHasMore() => " + temp);
        return temp;
    }
    /*
     * METHOD NAME: subCategoryIteratorReset
     * ARGUMENTS: n/a
     * REQUIRE: This category's subcategory list is filled.
     * FUNCTION: Reset the pointer of the subcategory iterator back to the beginning.
     * RETURN: n/a
     */
    public void itemIteratorReset()
    {   
        //Secretary.startFxn("SubCategoryVO.itemIteratorReset()");
        this.itemIterator = this.items.values().iterator();
        //Secretary.endFxn("SubCategoryVO.itemIteratorReset()");
    }
    public ItemVO getItem()
    {
        return this.itemVO;
    }
    
    public String getItemOrderQuantityName()
    {
        return this.itemVO.getOrderQuantityName();
    }
    public int getItemOrderQuantityID()
    {
        return this.itemVO.getOrderQuantityID();
    }
    public int getItemID()
    {
        int temp = 0;
       //Secretary.startFxn("SubCategoryVO.getItemID()");
        if(this.itemVO != null)
            temp = this.itemVO.getID();
       //Secretary.endFxn("SubCategoryVO.getItemID() => " + temp);
        return temp;
    }
    public String getItemName()
    {
       //Secretary.startFxn("SubCategoryVO.getItemName()");
        String temp = this.itemVO.getName();
       //Secretary.endFxn("SubCategoryVO.getItemName() => " + temp);
        return temp;
    }
    public float getItemCost()
    {
       //Secretary.startFxn("SubCategoryVO.getItemCost()");
        float temp = this.itemVO.getCost();
       //Secretary.endFxn("SubCategoryVO.getItemCost() => " + temp);
        return temp;
    }
    public int getItemGlNum()
    {
       //Secretary.startFxn("SubCategoryVO.getItemGlNum()");
        int temp = this.itemVO.getGlNum();
        //Secretary.endFxn("SubCategoryVO.getItemGlNum() => " + temp);
        return temp;
    }
    public boolean getItemActive()
    {
        //Secretary.startFxn("SubCategoryVO.getItemActive()");
        boolean temp = this.itemVO.getActive();
        //Secretary.endFxn("SubCategoryVO.getItemActive() => " + temp);
        return temp;
    }
    
    public ItemVO getItemViaID(int id)
    {
        return (ItemVO)(this.items.get(new Integer(id)));
    }
    public void setItemViaID(int id)
    {
        //Secretary.startFxn("SubCategoryVO.setItemViaID("+id+")");
        this.itemVO = (ItemVO)(this.items.get(new Integer(id)));
        //Secretary.endFxn("SubCategoryVO.setItemViaID("+id+")");
    }
   /*
     * METHOD NAME : deleteItemViaID
     * ARGUMENTS : an item id
     * REQUIRE: 
     * FUNCTION : Remove the item with this id from the item list of this
     *              subcategory.
     * RETURN: If this item does exist in this subcategory, return this item.
     *         If this item does not exist in this subcategory, return null.
     */
    public ItemVO pullItemViaID(int id)
    {
        Secretary.startFxn("SubCategoryVO.pullItemViaID("+id+")");
        this.logItems();
        Object x = this.items.remove(new Integer(id));
        Secretary.endFxn("SubCategoryVO.pullItemViaID("+id+")");
        if(x == null)
        {
            Secretary.write("RETURNING NULL");
            return null;
        }
        else
        {
            Secretary.write("RETURNING OBJECT");
            return (ItemVO)x;
        }
    }
    
    public void logItems()
    {
        Secretary.startFxn("SubCategoryVO.logItems()");
        this.itemIteratorReset();
        Secretary.write("ITEMS OF SUBCAT " + this.name);
        while(this.itemListHasMore())
        {
            this.setNextItem();
            Secretary.write("id = " + this.itemVO.getID());
            Secretary.write("name = " + this.itemVO.getName());
        }
        Secretary.endFxn("SubCategoryVO.logItems()");
    }
    
    public void itemModify(int itemID, String name, float cost, int glNum, boolean active, int orderQuantityID, String orderQuantityName)
    {
        Secretary.startFxn("SubCategoryVO.itemModify("+itemID+", "+name+", "+cost+", "+
                glNum+", "+active+", "+orderQuantityID+", "+orderQuantityName+")");
        //this.itemVO.logValues();
        //this.itemIterator = null;
        this.itemVO.setName(name);
        this.itemVO.setCost(cost);
        this.itemVO.setGlNum(glNum);
        this.itemVO.setActive(active);
        this.itemVO.setOrderQuantityID(orderQuantityID);
        this.itemVO.setOrderQuantityName(orderQuantityName);
        //this.itemIteratorReset();
        //this.itemVO.logValues();
        //((ItemVO)this.items.get(new Integer(itemID))).logValues();
        Secretary.endFxn("SubCategoryVO.itemModify("+itemID+", "+name+", "+cost+", "+
                glNum+", "+active+", "+orderQuantityID+", "+orderQuantityName+")");
    }
   /*
     * METHOD NAME : 
     * ARGUMENTS : 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:
     */ 
}
