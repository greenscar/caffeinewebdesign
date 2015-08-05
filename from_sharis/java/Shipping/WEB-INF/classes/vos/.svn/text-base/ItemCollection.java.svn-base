package vos;
import logging.Secretary;

public abstract class ItemCollection implements java.io.Serializable 
{    
    protected java.util.Vector items;
    
    public ItemCollection() 
    {
        this.items = new java.util.Vector();
    }
    
    /*
     * METHOD NAME : 
     * ARGUMENTS : 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:
     */
    
    private void addItem(ItemVO i)
    {
        Secretary.startFxn("ItemCollection.addItem(ItemVO)");
        this.items.add(i);
        Secretary.endFxn("ItemCollection.addItem(ItemVO)");
    }
    
    
    /*
     * METHOD NAME : 
     * ARGUMENTS : 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:
     */
    private void delItem(String id)
    {
        Secretary.startFxn("ItemCollection.delItem("+id+")");
        for(int x=0; x < this.items.size(); x++)
        {
            ItemVO theItem = (ItemVO)(items.elementAt(x));
            Integer temp = new Integer(id);
            if(temp.intValue() == theItem.getID())
            //if(id.compareTo(new String(theItem.getID())) == 0)
            {
                this.items.remove(x);
            }
        }
        Secretary.endFxn("ItemCollection.delItem("+id+")");
    }
    
    /*
     * METHOD NAME : 
     * ARGUMENTS : 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:
     */
    private ItemVO getItem(String id)
    {
        Secretary.startFxn("ItemCollection.getItem(String "+id+")");
        ItemVO toReturn = null;
        for(int x=0; x < this.items.size(); x++)
        {
            ItemVO theItem = (ItemVO)(items.elementAt(x));
            Integer temp = new Integer(id);
            if(temp.intValue() == theItem.getID())
            //if(id.compareTo(theItem.getID()) == 0)
            {
                toReturn = theItem;
                break;
            }
        }
        Secretary.endFxn("ItemCollection.getItem(String "+id+")");
        return toReturn;
    }
    
    /*
     * METHOD NAME : 
     * ARGUMENTS : 
     * REQUIRE: 
     * FUNCTION : 
     * RETURN:
     */
    private ItemVO getItem(int listLoc)
    {
        Secretary.startFxn("ItemCollection.getItem(int "+listLoc+")");
        ItemVO x = (ItemVO)(items.elementAt(listLoc));
        Secretary.endFxn("ItemCollection.getItem(int "+listLoc+")");
        return x;
    }
}
