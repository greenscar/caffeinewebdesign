import java.lang.String;
import java.lang.Integer;
import java.lang.Float;
import java.util.Hashtable;
import java.util.Enumeration;

public class ShoppingCart {

  protected Hashtable items = new Hashtable();

  public ShoppingCart() {

  }

  /**
   * Add a new item to the shopping cart.
   *
   * attributes
   *    itemId - the unique key associted with the item
   *    desc - a text description of the item
   *    price - the unit price for this item
   *    quantity - number of this item to insert into the
   *      shopping cart
   */
  public void addItem(String itemId,
    String desc,
    float price,
    int quantity) {

    String[] item = {itemId, desc, Float.toString(price),
    Integer.toString(quantity)};

    if (items.containsKey(itemId)) {

      String[] tmpItem = (String[])items.get(itemId);
      int tmpQuant = Integer.parseInt(tmpItem[3]);
      quantity += tmpQuant;
      tmpItem[3] = Integer.toString(quantity);
    }
    else {

      items.put(itemId, item);
    }
  }

  /**
   * Remove an item from the shopping cart.
   *
   * attributes
   *    itemId - the unique key associated with the item to be
   *      removed
   */
  public void removeItem(String itemId) {

    if (items.containsKey(itemId)) {

      items.remove(itemId);
    }
  }

  /**
   * Change the quantity of a specific item in the shopping cart.
   * The item must have previously been added to perform this
   * function.
   *
   * attributes
   *    itemId - unique key for the item to be updated
   *    quantity - the new quantity to be stored in the shopping
   *      cart
   */
  public void updateQuantity(String itemId, int quantity) {

    if (items.contains(itemId)) {

      String[] tmpItem = (String[])items.get(itemId);
      tmpItem[3] = Integer.toString(quantity);
    }
  }

  /**
   * Get an Enumeration to the list of items in the shopping cart.
   */
  public Enumeration getEnumeration() {

   return items.elements();
  }

  /**
   * Get the total cost of all of the items currently in the
   * shopping cart.
   */
  public float getCost() {

    Enumeration enum = items.elements();
    String[] tmpItem;
    float totalCost = 0.00f;

    while (enum.hasMoreElements()) {

      tmpItem = (String[])enum.nextElement();
      totalCost += (Integer.parseInt(tmpItem[3]) *
        Float.parseFloat(tmpItem[2]));
    }
    return totalCost;
  }

  /**
   * Get the total number of items currently in the shopping cart.
   */
  public int getNumOfItems() {

    Enumeration enum = items.elements();
    String[] tmpItem;
    int numOfItems = 0;

    while (enum.hasMoreElements()) {

      tmpItem = (String[])enum.nextElement();
      numOfItems += Integer.parseInt(tmpItem[3]);
    }

    return numOfItems;
  }

  /**
   * Get the total number of items currently in the shopping cart.
   */
  public void empty() {

    Enumeration enum = items.elements();
    String[] item;

    while (enum.hasMoreElements()) {

      item = (String[])enum.nextElement();
      removeItem((String)item[0]);
    }
  }
}
