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
 
package com.covecomm.deweb.control;

/* 
 *
 */

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


import com.covecomm.deweb.util.*;
import com.covecomm.deweb.doc.*;

/** Initialize the web application 
 *
 * This class creates any global objects necessary for
 * the operation of the web application.
 */


public final class WebAppSetup extends HttpServlet {

	private ConnectionPoolAdapter dbPool;
	private HttpLinksCacheBean linksCache;

/** Create the database connection pool.
 *
 * Actually what this method really does is create an instance
 * of ConnectionPoolAdapter, which doesn't pool connections
 * at all, mainly because this would require anyone using
 * this code to have a connection pool. And if you're using
 * MySQL, which makes connections very quickly, you can get away
 * without the pool for small apps. If you want to experiment with 
 * connection pools, derive from ConnectionPoolAdapter and implement
 * your access to the pool there. For an example, see the 
 * ConnectionPoolDbConnectionBroker that uses the JavaExchange 
 * connection pool. 
 */
	protected void createDbPool() throws ServletException {
		if (dbPool == null) {
			System.out.println("Attempting to create dbPool object");
			try {
				dbPool = 
					new ConnectionPoolAdapter(
						getInitParameter("DriverName"), 
						getInitParameter("DriverType")
							+ "://"
							+ getInitParameter("Host")
							+ ":"
							+ Integer.parseInt(getInitParameter("Port"))
							+ "/"
							+ getInitParameter("DbName"), 
						getInitParameter("UserID"), 
						getInitParameter("Password"), 
						5, 
						50, 
						"dbconn.log", 
						1.0); 
			} catch (IOException e) {
				System.out.println("Pool creation error: " + e.getMessage());
				throw new ServletException(e);
			}

		}
		if (dbPool != null) {
			getServletContext().setAttribute("dbPool", dbPool);
			System.out.println("Created dbPool object " + dbPool);
		} else
			throw new ServletException("Unable to create dbPool");

	}

/** Create cached menu objects.
 * 
 * To save database access, commonly used groups of links are 
 * cached at program startup. With a fast database server (such
 * as MySQL) this technique can be more trouble than it's worth
 */	
	protected void createMenus() throws ServletException {
		if (dbPool == null)
			throw new ServletException("No dbPool, can't create menus");
		if (linksCache == null)
			linksCache = new HttpLinksCacheBean();
		System.out.println("trying to create menus");
		linksCache.init(dbPool,contextPath);
		getServletContext().setAttribute("linksCache", linksCache);
	}
/** Clean up on shutdown */
public void destroy() {
		super.destroy();
		try {
			// give the pool 10 seconds to shut down 
			dbPool.destroy(10000);
		} catch (SQLException e) {
			System.out.println(e + ": One or more database " 
	            + "pool connections left open?");
		}
	}
/** Handle any direct requests to the servlet. 
 * 
 * Possibilities include calling the servlet to update menus.
 */	
public void doGet(HttpServletRequest request, 
	HttpServletResponse response)
	throws IOException, ServletException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Admin</title>");
	out.println("</head>");
	out.println("<body>");
	String cmd = request.getParameter("cmd");
	if (cmd != null) {
		try {
			if (cmd.equals("refreshmenus")) {
				createMenus();
				out.println("Refreshed menus.");
			}
			if (cmd.equals("refreshsidebar")) {
				linksCache.createLinks("leftsidebar");
				out.println("Refreshed sidebar menu.");
			}
		} catch (Exception e) {
			System.out.println("Error initializing " 
	            + "the web application " + e);
		}

	}
	out.println("</body>");
	out.println("</html>");
}

/** Initialize the servlet 
 * When the servlet is loaded the container calls this
 * method, which creates the connection pool, any 
 * cached menus, and a logger bean.
 */ 

	public void init(ServletConfig config) 
		throws ServletException {
		super.init(config);
		if (getInitParameter("ContextPath") != null)
			contextPath = getInitParameter("ContextPath");
		getServletContext().setAttribute("contextPath", contextPath);
		System.out.println("set global contextPath to " + contextPath);
		try {
			createDbPool();
			createMenus();
			LoggerBean logger = new LoggerBean();
			logger.setDbPool(dbPool);
			getServletContext().setAttribute("logger", logger);
		} catch (ServletException e) {
			throw new ServletException(e);
		}
	}
	private String contextPath;				private LoggerBean logger;
	private String contextName = "";
}