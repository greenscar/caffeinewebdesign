import java.sql.*;
public class InsertDataApp {
    
    public InsertDataApp() {
    }
    public void insertData(){
        Connection conn = null;
        try{
            // Load the Driver class file
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //Make a connection to the ODBC datasource
            conn = DriverManager.getConnection("jdbc:odbc:Movie", "james", "Zoolu4");
            System.err.println("con = " + conn.getClass());
            //Create the statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE * FROM Categories");
            stmt.executeUpdate("DELETE * FROM Titles");
            stmt.executeUpdate("INSERT INTO Categories VALUES(0, 'Action')");
            stmt.executeUpdate("INSERT INTO Categories VALUES(1, 'Comedy')");
            stmt.executeUpdate("INSERT INTO Categories VALUES(2, 'Drama')");
            stmt.executeUpdate("INSERT INTO Categories VALUES(3, 'Western')");
            stmt.executeUpdate("INSERT INTO Categories VALUES(4, 'Sci-Fi')");
            stmt.executeUpdate("INSERT INTO Categories VALUES(5, 'Classics')");
            String ins = "INSERT INTO Titles VALUES (0, 'Saving Private Ryan', '19.95', 12, 0)";
            stmt.executeUpdate(ins);
            ins = "INSERT INTO Titles VALUES (0, 'So I Married an Axe Murderer', '19.95', 15, 1)";
            stmt.executeUpdate(ins);
            ins = "INSERT INTO Titles VALUES (1, 'Life of Bryan', '19.95', 12, 1)";
            stmt.executeUpdate(ins);
            ins = "INSERT INTO Titles VALUES (2, 'Dune', '29.95', 14, 4)";
            stmt.executeUpdate(ins);
            ins = "INSERT INTO Titles VALUES (3, 'Dark Crystal', '19.95', 12, 4)";
            stmt.executeUpdate(ins);
            ins = "INSERT INTO Titles VALUES (4, 'Wizard of Oz', '19.95', 3, 5)";
            stmt.executeUpdate(ins);
            ins = "INSERT INTO Titles VALUES (5, 'The Last Emperor', '17.95', 11, 2)";
            stmt.executeUpdate(ins);
            ins = "INSERT INTO Titles VALUES (6, 'Krull', '39.95', 12, 4)";
            stmt.executeUpdate(ins);
        }catch(SQLException e){
            System.err.println("SQLException " + e.getMessage() + " in insertDataApp.insertData()");
        }catch(ClassNotFoundException e){
            System.err.println("ClassNotFoundException " + e.getMessage() + " in insertDataApp.insertData()");
        }catch(Exception e){
            System.err.println("Exception " + e.getMessage() + " in insertDataApp.insertData()");
        }finally{
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException e){
                System.err.println("SQLException " + e.getMessage() + " in insertDataApp.insertData() when releasing the connection");
            }
            System.err.println("SQL Done.");
        }
    }
    public static void main(String[] args) {
        InsertDataApp insertDataApp = new InsertDataApp();
        insertDataApp.insertData();
    }
    
}
