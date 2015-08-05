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
 
package com.covecomm.deweb.doc;

import java.util.*;
import java.sql.*;
import com.covecomm.deweb.util.*;

/** Maintains a cache of link lists.
 * 
 * This class retrieves ArticleGroup and Article 
 * records from the database and creates corresponding
 * HttpLinksBean objects, which it stores in a Map.
 */

public class HttpLinksCacheBean {
	private Map linksMap;
	private ConnectionPoolAdapter dbPool;
	private String contextPath = "";

public HttpLinksCacheBean() {
	super();
}
/** Creates a new HttpLinksBean for the specified group. 
 *
 * This method retrieves all ArticleGroup records matching
 * the specified name, and all related Articles records. 
 */
 public synchronized void createLinks(String groupName) {
	if (groupName == null) return;
	System.out.println("creating links for group " + groupName);
	HttpLinksBean thisList = new HttpLinksBean();
	Connection conn = null;
	Statement stmt = null;
	try {
		conn = dbPool.getConnection();
		stmt = conn.createStatement();
		ResultSet rs = 
			stmt.executeQuery(
				"select a.Title, "
					+ "a.URI, "
					+ "a.Summary, "
					+ "a.PublicationDate, "
					+ "g.Descending "
					+ "from Articles a, "
					+ "ArtGrpLink l, ArticleGroups g "
					+ "where a.ArticleID = l.ArticleID "
					+ "and g.ArticleGroupID = l.ArticleGroupID "
					+ "and g.Name = '" + groupName + "'"); 
		while (rs.next()) {
			// create a new HttpLlink object and add it to the list
			String tempLink = rs.getString("URI");
			if (!tempLink.startsWith("http://"))
				tempLink = contextPath + tempLink;
			HttpLinkBean link = 
				new HttpLinkBean(
					tempLink, 
					rs.getString("URI"),
					rs.getString("Title"), 
					rs.getString("Summary"), 
					rs.getDate("PublicationDate"),
					rs.getTime("PublicationDate"),
					rs.getBoolean("Descending")); 
			thisList.add(link);
			//System.out.println("created link for " + groupName + ": " + link);
		}
		// remove any existing links bean by this name
		linksMap.remove(groupName);
		linksMap.put(groupName,thisList);

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
/** Get the HttpLinksBean matching the specified name */
public HttpLinksBean getLinks(String listName) {
	HttpLinksBean links = (HttpLinksBean)linksMap.get(listName);
	return links;
}
/** Initialize the bean and create the default links lists.
 * 
 * The context path is normally an empty string - you can set a value
 * here if you need to add a prefix to the uri specified in the
 * database.
 */
public synchronized void init(ConnectionPoolAdapter newDbPool,String newContextPath) {
	// Assign the connection pool reference
	contextPath = newContextPath;
	if (dbPool == null) dbPool = newDbPool;
	HttpLinksBean thisList = null;
	// create or clear the list of lists of
	// Http links
	if (linksMap == null)
		linksMap = new HashMap();
	else
		linksMap.clear();
	/* select all of the database records which
	   correspond to Http links. Data returned includes:
	    listName - the list this link belongs to
	    url - the raw URI
	    name - the link text
	    text - descriptive text associated the the link
	           (i.e. an article summary)
	    style - a flag indicating further formatting info
	*/
	String listName = "";
	String name = "";
	String url = "";
	String text = "";
	String style = "";

	Connection conn = null;
	Statement stmt = null;
	try {
		conn = dbPool.getConnection();
		stmt = conn.createStatement();
		ResultSet rs = 
			stmt.executeQuery(
				"select Name from ArticleGroups g");
		while (rs.next()) {
			createLinks(rs.getString("Name"));
		}
		System.out.println("no more ArticleGroups records");

	} catch (SQLException e1) {
		System.out.println("ch15:SQL Error: " + e1);
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
	System.out.println("ch15: Completed links cache init");
}
}