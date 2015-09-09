//import logging.Secretary;
package sites;

import sites.location.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import logging.Secretary;

public class SiteAdmin {
    
    private LinkedHashMap sites;
    private Iterator siteIterator;
    private Site site;
    private String dbDriver, dbServer, dbLogin, dbPassword;
    Connection conn;
    public SiteAdmin() {
        dbDriver = "net.sourceforge.jtds.jdbc.Driver";
        dbServer = "jdbc:jtds:sqlserver://PSTEST:2433/HRPLAY";
        dbLogin = "sa";
        dbPassword = "sa";
        conn = null;
    }
    
     /*
     * METHOD NAME : dbLoadSiteList
     * ARGUMENTS : n/a
     * REQUIRE: n/a
     * FUNCTION : Load all sites from the DB and place them in the local site list
     * RETURN: The number of sites loaded.
     */
    public int dbLoadSiteList()
    {
        Secretary.startFxn("SiteAdmin.dbLoadSiteList()");
        int numLocs = 0;
        sites = new LinkedHashMap();
        String query = "SELECT pdt.DEPTID, pdt.DESCR, pdt.DESCRSHORT, pdt.LOCATION, "
                        + "pst.REGION, pst.DISTRICT FROM PS_SH_TERRITORY pst "
                        + "right join PS_DEPT_TBL pdt "
                        + "ON "
                        + "pst.DEPTID = pdt.DEPTID "
                        + "WHERE "
                        + "pdt.COMPANY = 'SMC' "
                        + "AND "
                        + "pdt.EFF_STATUS = 'A'";
        Secretary.write(query);
        try{
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbServer, dbLogin, dbPassword);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                Site aSite = null;
                Integer tempID;
                int deptID = 0;
                int regionNum = 0;
                try{
                    tempID = new Integer(rs.getString("DEPTID").trim());
                    deptID = tempID.intValue();
                }catch(NumberFormatException e)
                {}
                regionNum = rs.getInt("REGION");
                aSite = new Site();
                aSite.setRegion(regionNum);
                aSite.setDistrict(rs.getInt("DISTRICT"));
                aSite.setIdNumber(deptID);
                aSite.setName((rs.getString("DESCR")).trim());
                sites.put(new Integer(aSite.getIdNumber()), aSite);
                numLocs++;
                //System.out.println("num sites = " + sites.size());
            }
        }catch (ClassNotFoundException e) {
            System.out.println("Error creating connection: " + e);
        }catch(SQLException e){
            System.out.println("SQLException in SiteAdmin.dbLoadSiteList() -> " + e);
        }finally{
            try{
                conn.close();
            }catch(Exception e)
            {
                System.out.println("Can't close connection");
            }
            Secretary.endFxn("SiteAdmin.dbLoadSiteList()");
        }
        return numLocs;
    }
    
    public int dbLoadDMList()
    {
        Secretary.startFxn("SiteAdmin.dbLoadDMList()");
        String query = "select "
                     + "psj.EMPLID, pse.FIRST_NAME, pse.LAST_NAME, "
                     + "pse.ADDRESS1, pse.ADDRESS2, pse.CITY, pse.STATE, pse.POSTAL "
                     + "from "
                     + "PS_EMPLOYEES pse, PS_JOB psj "
                     + "where "
                     + "psj.DEPTID <= 3 "
                     + "and "
                     + "psj.EFFDT = (SELECT MAX(EFFDT) FROM PS_JOB J1 WHERE J1.EMPLID = pse.EMPLID) "
                     + "and "
                     + "psj.EFFSEQ = (SELECT MAX(EFFSEQ) FROM PS_JOB J2 WHERE J2.EMPLID = pse.EMPLID AND J2.EFFDT = psj.EFFDT) "
                     + "and "
                     + "pse.EMPLID = psj.EMPLID "
                     + "and "
                     + "psj.JOBCODE = 1 "
                     + "order by DEPTNAME";
        Secretary.write(query);
        int numDMs = 0;
        try{
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbServer, dbLogin, dbPassword);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                Site dm_home = new Site();
                numDMs++;
                System.out.println(rs.getString("LAST_NAME"));
            }
        }catch (ClassNotFoundException e) {
            System.out.println("Error creating connection: " + e);
        }catch(SQLException e){
            Secretary.write("SQLException in SiteAdmin.dbLoadDMList() -> " + e);
        }
        Secretary.endFxn("SiteAdmin.dbLoadDMList() = " + numDMs);
        return numDMs;
    }
    public Iterator getSiteIterator()
    {
        return this.sites.values().iterator();
    }    
    public boolean siteListHasMore()
    {
        boolean temp = false;
        if(this.siteIterator != null)
            temp = this.siteIterator.hasNext();
        return temp;
    }
    public void siteIteratorReset()
    {
        this.siteIterator = this.sites.values().iterator();
    }
    public void setNextSite()
    {
        if(this.siteListHasMore())
        {
            this.site = (Site)(this.siteIterator.next());
        }
    }    
    public void siteListClear()
    {
        this.sites = new LinkedHashMap();
    }
    
    public int getSiteId()
    {
        return this.site.getIdNumber();
    }
    public String getSiteName()
    {
        return this.site.getName();
    }
    public String getSiteStreet()
    {
        return this.site.getStreet();
    }
    public String getSiteStreet2()
    {
        return this.site.getStreet2();
    }
    public String getSiteCity()
    {
        return this.site.getCity();
    }
    public String getSiteState()
    {
        return this.site.getState();
    }
    public String getSiteZip()
    {
        return this.site.getZip();
    }
    public String getSiteFirstName()
    {
        return this.site.getFirstName();
    }
    public String getSiteLastName()
    {
        return this.site.getLastName();
    }
    public int getSiteRegion()
    {
        return this.site.getRegion();
    }
    public int getSiteDistrict()
    {
        return this.site.getDistrict();
    }
}
