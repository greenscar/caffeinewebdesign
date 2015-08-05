package vos;


public class ItemVO implements java.io.Serializable {
    /*
     * VARIABLES
     */
    private int id;
    private String name;
    private float cost;
    private boolean active;
    private boolean orderable;
    private java.util.Date dateLastMod;
    private int glNum;
    private String orderQuantityName;
    private int orderQuantityID;
    //private int numOrdered;
    //private int numOnHand;
    //private String category;
    //private String subCategory;
    
    /*
     * CONSTRUCTOR
     */
    public ItemVO() {
    }
    public void logValues()
    {
        logging.Secretary.startFxn("ItemVO.logValues()");
        logging.Secretary.write("_____ITEM_VALUES_____");
        logging.Secretary.write("id = " + id);
        logging.Secretary.write("name = " + name);
        logging.Secretary.write("cost = " + cost);
        logging.Secretary.write("active = " + active);
        logging.Secretary.write("glNum = " + glNum);
        logging.Secretary.endFxn("ItemVO.logValues()");
    }
    /*************************************************************************
     * SET FUNCTIONS
     ************************************************************************/
    public void setID(int i){
        this.id = i;
    }
    public void setName(String n){
        this.name = n;
    }
    public void setCost(float c){
        this.cost = c;
    }
    public void setActive(boolean a){
        this.active = a;
    }
    private void setOrderable(boolean o){
        this.orderable = o;
    }
    private void setDateLastMod(java.util.Date d){
        this.dateLastMod = d;
    }
    public void setGlNum(int n){
        this.glNum = n;
    }
    /*
    private void setnumOrdered(int n){
        this.numOrdered = n;
    }
    private void setNumOnHand(int n){
        this.numOnHand = n;
    }
    private void setCategory(String c){
        this.category = c;
    }
    private void setSubCategory(String c){
        this.subCategory = c;
    }
     */
    public void setOrderQuantityName(String name)
    {
        this.orderQuantityName = name;
    }
    public void setOrderQuantityID(int id)
    {
        this.orderQuantityID = id;
    }
    /*************************************************************************
     * END SET FUNCTIONS
     ************************************************************************/
    /*************************************************************************
     * GET FUNCTIONS
     ************************************************************************/
    public int getID()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public boolean getActive()
    {
        return this.active;
    }
    public float getCost()
    {
        return this.cost;
    }
    public int getGlNum()
    {
        return this.glNum;
    }
    public String getOrderQuantityName()
    {
        return this.orderQuantityName;
    }
    public int getOrderQuantityID()
    {
        return this.orderQuantityID;
    }
    /*************************************************************************
     * END GET FUNCTIONS
     ************************************************************************/
}
