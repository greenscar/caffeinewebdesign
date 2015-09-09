package sites.location;
public class Site {
    
    private int idNumber;
    private String name;
    private String street;
    private String street2;
    private String city;
    private String state;
    private String zip;
    
    private String firstName;
    private String lastName;
    private int region;
    private int district;
    
    /** Creates a new instance of Site */
    public Site() {
    }
    
    public int getIdNumber()
    {
        return this.idNumber;
    }
    public String getName()
    {
        return this.name;
    }
    public String getStreet()
    {
        return this.street;
    }
    public String getStreet2()
    {
        return this.street2;
    }
    public String getCity()
    {
        return this.city;
    }
    public String getState()
    {
        return this.state;
    }
    public String getZip()
    {
        return this.zip;
    }
    
    public void setIdNumber(int n)
    {
        this.idNumber = n;
    }
    public void setName(String n)
    {
        this.name = n;
    }
    public void setStreet(String s)
    {
        this.street = s;
    }
    public void setStreet2(String s)
    {
        this.street2 = s;
    }
    public void setCity(String c)
    {
        this.city = c;
    }
    public void setState(String s)
    {
        this.state = s;
    }
    public void setZip(String z)
    {
        this.zip = z;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public void setFirstName(String n)
    {
        this.firstName = n;
    }
    public void setLastName(String n)
    {
        this.lastName = n;
    }
    
    public void setRegion(int r)
    {
        this.region = r;
    }
    public void setDistrict(int d)
    {
        this.district = d;
    }
    public int getRegion()
    {
        return this.region;
    }
    public int getDistrict()
    {
        return this.district;
    }
}
