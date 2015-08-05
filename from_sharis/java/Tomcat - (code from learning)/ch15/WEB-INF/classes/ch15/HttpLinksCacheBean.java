package ch15;

import java.util.*;
import java.sql.*;
import com.javaexchange.dbConnectionBroker.*;

public class HttpLinksCacheBean {
    Map linksMap;
    protected DbConnectionBroker dbPool;
    
    /** Creates a new instance of HttpLinksCacheBean */
    public HttpLinksCacheBean() {
        super();
    }
    
    public HttpLinksBean getLinks(String listName){
        HttpLinksBean links = (HttpLinksBean)linksMap.get(listName);
        return links;
    }
    
    public void init(DbConnectionBroker newDbPool){
        //Assign the connection pool reference
        if(dbPool == null)
            dbPool = newDbPool;
        HttpLinksBean thisList = null;
        //Create or clear the list of lists of HTTP links
        if(linksMap == null)
            linksMap = new HashMap();
        else
            linksMap.clear();
        /*
         * Select all of the database records which correspond to HTTP links.
         *  Data returned includes:
         *  listName - the list this link belongs to
         *  URL - the raw URL
         *  Name - the link text
         *  Text - the descriptive text associated with the link
         *          (such as an article summary)
         */
        String listName = "";
        String name = "";
        String url = "";
        String text = "";
        String style = "";
        
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = dbPool.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT g.Name, " 
                + "a.Title, "
                + "a.URL, "
                + "a.Summary, "
                + "a.PublicationDate, "
                + "from Articles a, "
                + "ArtGrpLink l, ArticleGroups g, "
                + "WHERE a.ArticleID = l.ArticleID  "
                + "and g.ArticleGroupID = l.ArticleGroupID "
                + "ORDER BY g.Name");
            while(rs.next()){
                // linksMap stores multiple lists of links.
                // Therefore, first try to get this list.
                listName = rs.getString("Name");
                thisList = (HttpLinksBean)linksMap.get(listName);
                //If the list doesn't exist, create it.
                if(thisList == null){
                    thisList = newHttpLinksBean(listName);
                    linksMap.put(listName, thisList);
                }
                //Create a new HttpLink object and add it to the list.
                HttpLinkBean link = new HttpLinkBean(
                    rs.getString("URL"),
                    rs.getString("Title"),
                    rs.getString("Summary"),
                    rs.getDate("PublicationDate"));
                thisList.add(link);
            }
        }catch (SQLException e1){
            System.out.println("ch15:SQL Error: " + e1);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch (SQLException e2){};
            //Return the connection to the Broker
            if(dbPool != null)
                dbPool.freeConnection(conn);
        }
    }
}
