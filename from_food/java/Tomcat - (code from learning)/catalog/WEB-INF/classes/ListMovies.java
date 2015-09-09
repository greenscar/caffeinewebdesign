
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.*;
import ConnectionPool.*;

public class ListMovies implements Service {

    public ListMovies() {
    }

    // Returns a Vector containing the column names found in the passed
    // in ResultSet
    private Vector getResultSetColumnNames(ResultSet rs)
        throws Exception {

        try {

            // Get a reference to the ResultSet's meta data
            ResultSetMetaData md = rs.getMetaData();

            // Get the number of columns returned in the Result Set
            int count = md.getColumnCount();
            // Create a Vector to hold the Column Names
            Vector columnNames = new Vector(count);

            // Get all of the Column Names
            for ( int x = 0; x < count; x++ ) {

                // The column name indexes begin at 1
                columnNames.addElement(md.getColumnName(x + 1));
            }
            return columnNames;
        }
        catch (SQLException sqlex) {

            throw new Exception(sqlex.getMessage());
        }
    }

    /*
     Parse the ResultSet returning an array of HashMaps
     */
    public Object[] parseResultSet(ResultSet rs) throws Exception {

        Vector results = new Vector();

        // Iterate over the ResultSet
        Vector columnNames = getResultSetColumnNames(rs);

        if ( rs == null ) {

            throw new Exception("ResultSet is null");
        }

        int count = 0;

        while ( rs.next() ) {

            HashMap row = new HashMap();

            for ( int x = 0; x < columnNames.size(); x++ ) {

                String rsColumn = (String)columnNames.elementAt(x);

                String rsValue = rs.getString(rsColumn);

                if ( rsValue == null ) {

                    rsValue = new String("");
                }

                row.put(rsColumn, rsValue);
            }
            results.add(row);
        }
        // Return the result
        return results.toArray();
    }

    /*
     implemented method from Service interface
     */
    public void execute(HttpServletRequest request,
        HttpServletResponse response,
        ServletContext context) throws Exception {

        // Get categorie_id from the request
        String catid = request.getParameter("catid");

        // Get a database connection from CATALOG_POOL
        ConnectionPool pool = (ConnectionPool)context.getAttribute("CATALOG_POOL");
        Connection con = pool.getConnection();

        try {

            if ( con != null ) {

                Statement statement = con.createStatement();

                // Get titles with matching category id
                StringBuffer query = new StringBuffer("SELECT * FROM TITLES");
                query.append(" WHERE CATEGORY_ID = " + catid);
                ResultSet rs =
                    statement.executeQuery(query.toString());

                Object[] movies = parseResultSet(rs);

                // Add the resulting movies to the request
                request.setAttribute("movies", movies);
            }
        }
        finally {

            pool.releaseConnection(con);
        }
    }
}