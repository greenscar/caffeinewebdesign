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
import javax.servlet.http.*;

/**
 * A bean that writes page request to the AccessLog table.
 * 
 */
 
public class LoggerBean {
	private ConnectionPoolAdapter dbPool;
public LoggerBean() {
	super();
}
public void setDbPool(ConnectionPoolAdapter newDbPool) {
	dbPool = newDbPool;
}
public void write(String IP,String user,String request){
	write(IP,user,request,0);
}
public void write(String IP,String user,String request,int status){
	write(IP,user,request,status,"","","");
}
public void write(
	String IP, 
	String user, 
	String request, 
	int status, 
	String referer,
	String userAgent,
	String info) {
	if (dbPool == null)
		System.out.println("No dbpool, can't record log events");
	else {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dbPool.getConnection();
			stmt = conn.createStatement();
			if (IP==null) IP="";
			if (user==null) user="";
			if (request==null) request="";
			if (info==null) info="";
			if (referer==null) referer="";
			if (userAgent==null) userAgent="";
			stmt.executeQuery(
				"insert into AccessLog "
				+ "(IP,User,Request,status,Referer,UserAgent,Info) values('"
				+ IP + "','"
				+ user + "','\""
				+ request + "\"',"
				+ status + ",'"
				+ referer + "','\""
				+ userAgent + "\"','\""
				+ info + "\"')");

		} catch (SQLException e1) {
			System.out.println("SQL Error: " + e1);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e2) {
			};

			// The connection is returned to the Broker
			if (dbPool != null)
				dbPool.freeConnection(conn);

		}

	}
}
public void write(String user,HttpServletRequest request,int status,String info){
	StringBuffer uri = new StringBuffer(request.getServletPath());
	String s = request.getQueryString();
	String ref = request.getHeader("Referer");
	if (ref==null) ref="";
	String agent = request.getHeader("User-Agent");
	if (agent==null) agent="";
	if (s != null) uri.append(" " + s);
	if (user == null) user="";
	if (info == null) info="";
	uri.append(" " + request.getProtocol());
	write(request.getRemoteAddr(),
		user,
		uri.toString().trim(),
		status,
		info,
		ref.trim(),
		agent.trim());
}

}