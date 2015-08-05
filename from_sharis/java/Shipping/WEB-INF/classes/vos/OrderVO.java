package vos;
public class OrderVO {

    private int orderNum;
    private java.util.Date datePlaced;
    private String contact;
    private boolean finalized;
    private java.util.Vector shipTo;
    private java.util.Vector billTo;
    private java.util.Vector itemList;
    /** Creates a new instance of OrderVO */
    public OrderVO() {
        this.shipTo = new java.util.Vector();
        this.billTo = new java.util.Vector();
        this.itemList = new java.util.Vector();
    }
    
    public int getOrderNum()
    {
        return this.orderNum;
    }
    public java.util.Date getDatePlaced()
    {
        return this.datePlaced;
    }
    public String getContact()
    {
        return this.contact;
    }
    public boolean isFinalized()
    {
        return this.finalized;
    }
    public void setOrderNum(int n)
    {
        this.orderNum = n;
    }
    public void setContact(String c)
    {
        logging.Secretary.write("setContact("+c+")");
        this.contact = c;
    }
    public void finalize()
    {
        this.finalized = true;
    }
    public void setDatePlaced(java.util.Date d)
    {
        logging.Secretary.write("setDatePlaced("+d+")");
        this.datePlaced = d;
    }
    public void addShipTo(Integer deptID)
    {
        logging.Secretary.write("addShipTo("+deptID+")");
        this.shipTo.add(deptID);
    }
    public void addBillTo(Integer deptID)
    {
        logging.Secretary.write("addBillTo("+deptID+")");
        this.billTo.add(deptID);
    }
    public int getShipToSize()
    {
        return this.shipTo.size();
    }
    public int getShipToAt(int vLoc)
    {
        Integer temp = (Integer)(this.shipTo.elementAt(vLoc));
        return temp.intValue();
    }
    public int getBillToAt(int vLoc)
    {
        Integer temp = (Integer)(this.billTo.elementAt(vLoc));
        return temp.intValue();
    }
}
