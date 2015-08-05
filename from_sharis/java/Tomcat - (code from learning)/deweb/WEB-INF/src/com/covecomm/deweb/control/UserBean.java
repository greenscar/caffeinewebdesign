
package com.covecomm.deweb.control;

/** Models a user record.
 *
 * This is a generalized JavaBean that doesn't have any
 * database-specific work. The derived class HttpUserBean
 * is usually used instead.
 *
 */

public class UserBean {
	private String username;
	private String password;
	private String hostname;
	private String realname;
	private int ID;



/**
 * UserBean constructor.
 */
public UserBean() {
	super();
}
/**
 * UserBean constructor with user, password, and host.
 */
public UserBean(String newUsername,String newPassword,String newHostname) {
	super();
	setUsername(newUsername);
	setPassword(newPassword);
	setHostname(newHostname);
}
/**
 * Get the user's computer name (IP).
 */
public java.lang.String getHostname() {
	return hostname;
}
/**
 * Get the user's unique ID
 */
public int getID() {
	return ID;
}
/**
 * Get the user's password.
 */
public java.lang.String getPassword() {
	return password;
}
/**
 * Get the user's real (first, last) name.
 */
public java.lang.String getRealname() {
	return realname;
}
/**
 * Get the name the user uses to log on
 */
public java.lang.String getUsername() {
	return username;
}
/**
 * Set the user's computer name (IP).
 */

public void setHostname(java.lang.String newHostname) {
	hostname = newHostname;
}
/**
 * Set the user's database ID (different from the user ID)
 */
public void setID(int newID) {
	ID = newID;
}
/**
 * Set the user's password.
 */
public void setPassword(java.lang.String newPassword) {
	password = newPassword;
}
/**
 * Set the user's real (i.e. first/last) name.
 */
public void setRealname(java.lang.String newRealname) {
	realname = newRealname;
}
/**
 * Set the name the user uses to log on.
 */
public void setUsername(java.lang.String newUsername) {
	username = newUsername;
}
/** Return a string representation */
public String toString(){
		return(getUsername()
			+ ","
			+ getPassword()
			+ ","
			+ getHostname());
		}
}