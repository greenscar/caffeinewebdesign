package Security; 
import java.sql.*; 
public class Login { 
	private String username = ""; 
	private String password = ""; 
	public Login() { } 
	public void setUsername(String username) { 
		this.username = username; 
	} 
	public void setPassword(String password) { 
		this.password = password; 
	} 
	public boolean authenticate(String username2, String password2) { 
	String query="select * from Registration;"; 
	String DbUserName=""; 
	String DbPassword=""; 
	String finalUser=""; 
	try { 
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
		Connection con=DriverManager.getConnection("jdbc:odbc:register"); 
		Statement stat=con.createStatement(); 
		ResultSet rst=stat.executeQuery(query); 
		while(rst.next()) { 
			DbUserName=rst.getString("UserName"); 
			DbPassword=rst.getString("password"); 
			if (username2.equals(DbUserName) && password2.equals(DbPassword)) {
				break; 
			} 
		} 
		return true; 
		}catch(Exception e){ 
			e.printStackTrace(); return false; 
		} 
	}
}
