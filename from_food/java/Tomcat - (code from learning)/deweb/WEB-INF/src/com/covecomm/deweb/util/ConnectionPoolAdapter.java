/*
 * ____________________________________________________
 * 
 * Copyright (c) 2001, CoveComm Inc.                         
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 *
 * Redistributions of source code must retain the above 
 * copyright notice, this list of conditions and the 
 * following disclaimer.
 *
 * Redistributions in binary form must reproduce the above 
 * copyright notice, this list of conditions and the 
 * following disclaimer in the documentation and/or other 
 * materials provided with the distribution.
 *
 * Neither the name of CoveComm Inc. nor the names of 
 * contributors may be used to endorse or promote products 
 * derived from this software without specific prior 
 * written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS 
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE LIABLE FOR 
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, 
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS 
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY 
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * ____________________________________________________
 *
 * This class is part of the DEWEB package for 
 * database-enabled web development. For more information
 * on DEWEB see http://www.covecomm.com/java
 *
 */
 
package com.covecomm.deweb.util;


 
import java.sql.*;

/** This is a generic database connection pool adapter.
 *
 * If you want a real connection pool, you need to derive
 * from this class and in particular override the
 * getConnection and freeConnection methods to 
 * use your connection pool. For an example, see
 * com.covecomm.deweb.util.ConnectionPoolDbConnectionBroker 
 */

 
public class ConnectionPoolAdapter {


public void destroy(int wait) throws java.sql.SQLException {
}
public java.lang.String freeConnection(java.sql.Connection connection) {
	try {
		connection.close();
	} catch (java.sql.SQLException e) {
		System.out.println("ConnectionPoolAdapter: " + e);
	}
	return null;
}
public java.sql.Connection getConnection() {
	Connection connection = null;
	try {
		connection = DriverManager.getConnection(getDriverUri(),
	        getUserId(),getPassword());
	} catch (SQLException e) {
		System.err.println("ConnectionPoolAdapter.getConnection: " + e.getMessage());
		System.err.println("ConnectionPoolAdapter.getConnection: " + e.getSQLState());
		System.err.println("ConnectionPoolAdapter.getConnection: " + e.getErrorCode());
	}
	return(connection);

}

private java.lang.String driverName;
private java.lang.String driverUri;
private java.lang.String logFile;
private int maxConnections;
private int minConnections;
private java.lang.String password;
private java.lang.String userId;
private double version;

public ConnectionPoolAdapter(
	String newDriverName,
	String newDriverUri,
	String newUserId,
	String newPassword)
	throws java.io.IOException {

	this(newDriverName,newDriverUri,newUserId,newPassword,0,0,"",0);

}

public ConnectionPoolAdapter(
	String newDriverName,
	String newDriverUri,
	String newUserId,
	String newPassword,
	int newMinConnections,
	int newMaxConnections,
	java.lang.String newLogFile,
	double newVersion)
	throws java.io.IOException {

	setDriverName(newDriverName);
	setDriverUri(newDriverUri);
	setUserId(newUserId);
	setPassword(newPassword);
	setMinConnections(newMinConnections);
	setMaxConnections(newMaxConnections);
	setLogFile(newLogFile);
	setVersion(newVersion);

	try {
		Class.forName(driverName).newInstance();
	} catch (ClassNotFoundException e) {
		System.err.println("ConnectionPoolAdapter constructor - class not found: " + e.getMessage());
	} catch (InstantiationException e) {
		System.err.println("ConnectionPoolAdapter constructor - instantation error: " + e.getMessage());
	} catch (IllegalAccessException e) {
		System.err.println("ConnectionPoolAdapter constructor - illegal access error: " + e.getMessage());
	}

}

public java.lang.String getDriverName() {
	return driverName;
}

public java.lang.String getDriverUri() {
	return driverUri;
}

public java.lang.String getLogFile() {
	return logFile;
}

public int getMaxConnections() {
	return maxConnections;
}

public int getMinConnections() {
	return minConnections;
}

public java.lang.String getPassword() {
	return password;
}
public java.lang.String getUserId() {
	return userId;
}
public double getVersion() {
	return version;
}
public void setDriverName(java.lang.String newDriverName) {
	driverName = newDriverName;
}

public void setDriverUri(java.lang.String newDriverUri) {
	driverUri = newDriverUri;
}

public void setLogFile(java.lang.String newLogFile) {
	logFile = newLogFile;
}

public void setMaxConnections(int newMaxConnections) {
	maxConnections = newMaxConnections;
}
public void setMinConnections(int newMinConnections) {
	minConnections = newMinConnections;
}
public void setPassword(java.lang.String newPassword) {
	password = newPassword;
}

public void setUserId(java.lang.String newUserId) {
	userId = newUserId;
}

public void setVersion(double newVersion) {
	version = newVersion;
}
}