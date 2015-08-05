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
 
package com.covecomm.deweb.forms;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.sql.Connection;
import com.covecomm.deweb.util.*;
import com.covecomm.deweb.doc.*;

/** A servlet which manages form requests and completions.
 *
 * Each form has a single URL, whether this is a request
 * for a new form or a form completion. The servlet determines
 * which is the case by examining the received parameters.
 */

public class FormServlet extends HttpServlet {
	private ConnectionPoolAdapter dbPool;
public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	WebDocBean webdoc = (WebDocBean) request.getAttribute("webdoc");
	System.out.println("webdoc " + webdoc);
	System.out.println("FormServlet: request " + webdoc.getUri());
	FormBean form = null;
	FormFieldBean b = null;
	if (webdoc == null)
		throw new ServletException("Internal processing error on form");
	try {
		System.out.println("trying to create class " + webdoc.getExtraInfo());
		Class c = Class.forName(webdoc.getExtraInfo());
		form = (FormBean) c.newInstance();

		Enumeration e = request.getParameterNames();
		HashMap params = new HashMap();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String[] values = request.getParameterValues(key);
			params.put(key,values);
		}
		form.setParameters(params);

	} catch (ClassNotFoundException e) {
		System.out.println(e);
	} catch (InstantiationException e) {
		System.out.println(e);
	} catch (IllegalAccessException e) {
		System.out.println(e);
	} catch (NullPointerException e) {
		System.out.println(e);
	}
	System.out.println("formbean " + form);

	if (!form.isNewForm()){
		System.out.println("saving a form");
		if (form.validate()) {
			if (!findDbPool()) {
				System.out.println("could not find db pool");
			} else {
				Connection connection = dbPool.getConnection();
				if (connection == null)
					System.out.println("could not get connection");
				else form.save(connection);
			}
		}
	} else {
		System.out.println("getting a form");
	}
	form.setAction(webdoc.getUri());
	request.setAttribute("includePage",webdoc.getIncludePage());
	request.setAttribute(form.getName(),form);
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(webdoc.getJspName());
	System.out.println("got dispatcher " + dispatcher);
	if (dispatcher != null)
		dispatcher.forward(request, response);
	else throw new ServletException("No JSP found for " + request.getRequestURI());
	return;

		
}
	public void doPost(HttpServletRequest request,
					  HttpServletResponse response)
		throws IOException, ServletException
	{
		doGet(request, response);
	}
protected boolean findDbPool() {
	if (dbPool == null) 
		dbPool = (ConnectionPoolAdapter)getServletContext().getAttribute("dbPool");
	return (dbPool != null);
 }      
}