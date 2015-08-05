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
 
package com.covecomm.deweb.taglib;

/**
 * Insert the type's description here.
 * Creation date: (9/7/00 10:53:29 AM)
 * @author: 
 */

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.*;
import com.covecomm.deweb.doc.*;
import com.covecomm.deweb.util.*;
import java.sql.*;
 
 /** A tag to create a list of HTTP links for the specified articles.
 *
 */

 public class ArticlesTag extends TagSupport {
	HttpLinksBean links;
	boolean summary=true;
	private int month;
	private int year;
	private String uri;
	private String articlesBean="articles";
	private String status="P";

	private boolean desc=true;
	private String order="";
	private String filter="";
	private String type="";
	private int limit=0;
public int doStartTag() throws JspException {
	ServletRequest request = pageContext.getRequest();
	links = new HttpLinksBean();
	Connection conn = null;
	Statement stmt = null;
	ConnectionPoolAdapter dbPool = null;
	String contextPath = (String)pageContext.getAttribute(
		"contextPath",PageContext.APPLICATION_SCOPE);
	if (contextPath == null) contextPath = "";
	System.out.println("ArticlesTag contextpath " + contextPath);
		try {
		dbPool = 
			(ConnectionPoolAdapter) pageContext.getAttribute(
				"dbPool", 
				PageContext.APPLICATION_SCOPE); 
		if (dbPool != null) {
			conn = dbPool.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = null;
			//System.out.println("summary " + summary);
			StringBuffer select = new StringBuffer();
			select.append("select URI,PublicationDate,Title,Summary "
						+ "from Articles where ArticleType='"
						+ articleType + "' "
						+ "and Status= \"" + status + "\" ");
			if (!status.equals("V"))
				select.append("and PublicationDate <= now() ");
			select.append(" and uri <> '' and Title <> ''");
			if (!filter.equals(""))
				select.append(" and " + filter.trim() + " ");
			if (!order.equals(""))
				select.append(" order by " + order.trim() + " ");
			if (limit > 0)
				select.append(" limit " + limit);
			System.out.println("ArticlesTag: " + select);
			rs = stmt.executeQuery(select.toString());
			while (rs.next()) {
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
						desc);
				links.add(link);
			}

		} else
			System.out.println("no dbPool found");

	} catch (ClassCastException e) {
		System.out.println("NewsTag: " + e.toString());
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
	//System.out.println("set links bean to attribute " + articlesBean);
	pageContext.setAttribute(articlesBean, links,PageContext.PAGE_SCOPE);
	//return SKIP_BODY;
	return EVAL_BODY_INCLUDE;

}
/**
 * Insert the method's description here.
 * Creation date: (1/8/01 12:15:20 PM)
 * @return java.lang.String
 */
public java.lang.String getArticlesBean() {
	return articlesBean;
}
/**
 * Insert the method's description here.
 * Creation date: (1/19/01 3:17:37 PM)
 * @return java.lang.String
 */
public java.lang.String getFilter() {
	return filter;
}
/**
 * Insert the method's description here.
 * Creation date: (1/19/01 3:23:24 PM)
 * @return int
 */
public int getLimit() {
	return limit;
}

/**
 * Insert the method's description here.
 * Creation date: (1/19/01 3:17:37 PM)
 * @return java.lang.String
 */
public java.lang.String getOrder() {
	return order;
}
/**
 * Insert the method's description here.
 * Creation date: (1/8/01 12:15:20 PM)
 * @return java.lang.String
 */
public java.lang.String getStatus() {
	return status;
}
/**
 * Insert the method's description here.
 * Creation date: (1/8/01 9:27:56 PM)
 * @return boolean
 */
public boolean isDesc() {
	return desc;
}
/**
 * Insert the method's description here.
 * Creation date: (1/8/01 12:15:20 PM)
 * @param newArticlesBean java.lang.String
 */
public void setArticlesBean(java.lang.String newArticlesBean) {
	articlesBean = newArticlesBean;
}
/**
 * Insert the method's description here.
 * Creation date: (1/8/01 9:27:56 PM)
 * @param newDesc boolean
 */
public void setDesc(String newDesc) {
	if (newDesc.equalsIgnoreCase("true"))
		desc = true;
	else
		desc = false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/8/01 9:27:56 PM)
 * @param newDesc boolean
 */
public void setDesc(boolean newDesc) {
	desc = newDesc;
}
/**
 * Insert the method's description here.
 * Creation date: (1/19/01 3:17:37 PM)
 * @param newFilter java.lang.String
 */
public void setFilter(java.lang.String newFilter) {
	filter = newFilter;
}
/**
 * Insert the method's description here.
 * Creation date: (1/19/01 3:23:24 PM)
 * @param newLimit int
 */
public void setLimit(int newLimit) {
	limit = newLimit;
}
/**
 * Insert the method's description here.
 * Creation date: (1/19/01 3:23:24 PM)
 * @param newLimit int
 */
public void setLimit(String newLimit) {
	limit = Integer.parseInt(newLimit);
}


public void setMonth(String newMonth) {
	try {
		month = Integer.parseInt(newMonth);
	} catch (NumberFormatException e) {
	}
}
/**
 * Insert the method's description here.
 * Creation date: (1/19/01 3:17:37 PM)
 * @param newOrder java.lang.String
 */
public void setOrder(java.lang.String newOrder) {
	order = newOrder;
}
/**
 * Insert the method's description here.
 * Creation date: (1/8/01 12:15:20 PM)
 * @param newStatus java.lang.String
 */
public void setStatus(java.lang.String newStatus) {
	status = newStatus;
}
public void setSummary(String newSummary) {
	if (newSummary.equalsIgnoreCase("true"))
		summary = true;
	else
		summary = false;
}
public void setYear(String newYear) {
	try {
		year = Integer.parseInt(newYear);
	} catch (NumberFormatException e) {
	}
}

	private String articleType="CMAG";

/**
 * Insert the method's description here.
 * Creation date: (4/10/01 9:28:57 PM)
 * @return java.lang.String
 */
public java.lang.String getArticleType() {
	return articleType;
}

/**
 * Insert the method's description here.
 * Creation date: (4/10/01 9:28:57 PM)
 * @param newArticleType java.lang.String
 */
public void setArticleType(java.lang.String newArticleType) {
	articleType = newArticleType;
}
}