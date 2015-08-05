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

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.*;
import java.io.*;

/** A custom tag that includes an HTML page. 
 *
 * Sometimes you want to control access to HTML pages
 * by mapping requests for these pages to a servlet.
 * If you then use a standard include approach to
 * display the HTML, <i>that</i> request will be
 * sent to the servlet again, and you'll get an exception.
 * There are two ways to approach this. One is to move the
 * .html file to a new extension (and perhaps directory) and
 * include that page,which the servlet mapping will not
 * detect. The other and easier to manage approach, at
 * least from an HTML editing point of view, is to
 * get an InputStreamReader and write the file out directly
 * to the JSP.
 */

public class IncludePageTag extends TagSupport {
	private String includeAttribute = "includePage";
	private String newExtension = "$34zj[4)wx6zz";
	private boolean fastMode=true;
	private int scope=PageContext.REQUEST_SCOPE;
public int doStartTag() throws JspException {
	ServletRequest request = pageContext.getRequest();
	String includePage = 
		(String) pageContext.getAttribute(getIncludeAttribute(), getScope()); 
	boolean mappedHtml = false;
	if (includePage == null)
		includePage = (String) pageContext.findAttribute(getIncludeAttribute());
	String origIncludePage = includePage;
	System.out.println("IncludePageTag: " + includePage);
	if (includePage != null) {
		int i = includePage.lastIndexOf(".html");
		if (i > 0) {
			mappedHtml = true;
		}
		// verify that the mapped file really exists
		File file = new File(pageContext.getServletContext().getRealPath(includePage));
		System.out.println("file: " + file);
		try {
			JspWriter out = pageContext.getOut();
			if ((mappedHtml) && (!file.exists())) {
				ServletResponse response = pageContext.getResponse();
				out.println("<p>Error: " + origIncludePage + " not found</p>");
			} else {
				if (mappedHtml) {
	                serveHtmlPage(includePage,out);
				} else {
					if (isFastMode())
						pageContext.include(includePage);
					else {
						ServletResponse response = pageContext.getResponse();
						out.flush();
						RequestDispatcher dispatcher = 
							pageContext.getServletContext().getRequestDispatcher(includePage); 
						dispatcher.include(request, response);
					}
				}
			}
		} catch (IOException e1) {
			System.out.println("IO Exception");
			throw new JspException(e1.getMessage());
		} catch (ServletException e2) {
			System.out.println("ServletException");
			throw new JspException(e2.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}
	return SKIP_BODY;
	/* The following is the original version of this method
	 * which mapped the file extension to a known (and
	 * difficult to guess) value
	 *
		ServletRequest request = pageContext.getRequest();
	String includePage = (String) pageContext.getAttribute(includeAttribute,scope);
	boolean mappedFile = false;
	if (includePage == null)
		includePage = (String) pageContext.findAttribute(includeAttribute);
	String origIncludePage = includePage;
	if (includePage != null) {
		// rewrite the extension for html files
		int i = includePage.lastIndexOf(".html");
		if (i > 0) {
			includePage = includePage.substring(0, i + 1) + newExtension;
			mappedFile = true;
		}

		// verify that the mapped file really exists
		File file = new File(pageContext.getServletContext().getRealPath(includePage));
		try {
			if ((mappedFile) && (!file.exists())) {
				ServletResponse response = pageContext.getResponse();
				JspWriter out = pageContext.getOut();
				out.println("<p>Error: " + origIncludePage + " not found</p>");
			} else {
				if (fastMode)
					pageContext.include(includePage);
				else {
					ServletResponse response = pageContext.getResponse();
					JspWriter out = pageContext.getOut();
					out.flush();
					RequestDispatcher dispatcher = 
						pageContext.getServletContext().getRequestDispatcher(includePage); 
					dispatcher.include(request, response);
				}
			}
		} catch (IOException e1) {
			System.out.println("IO Exception");
			throw new JspException(e1.getMessage());
		} catch (ServletException e2) {
			System.out.println("ServletException");
			throw new JspException(e2.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}
	return SKIP_BODY;

	*/
}

public java.lang.String getIncludeAttribute() {
	return includeAttribute;
}
public java.lang.String getNewExtension() {
	return newExtension;
}
public int getScope() {
	return scope;
}
public boolean isFastMode() {
	return fastMode;
}
public void setFastMode(boolean newFastMode) {
	fastMode = newFastMode;
}
public void setForwardRequest(String forwardRequest) {
	if ((forwardRequest != null) && 
		(forwardRequest.equalsIgnoreCase("true"))) 
		setFastMode(false);
	else setFastMode(true);
}
public void setIncludeAttribute(java.lang.String newIncludeAttribute) {
	includeAttribute = newIncludeAttribute;
}
public void setNewExtension(java.lang.String newNewExtension) {
	newExtension = newNewExtension;
}
public void setScope(int newScope) {
	scope = newScope;
}
public void setScope(java.lang.String newScope) {
	if (newScope.equalsIgnoreCase("REQUEST"))
			setScope(PageContext.REQUEST_SCOPE);
	if (newScope.equalsIgnoreCase("SESSION"))
			setScope(PageContext.SESSION_SCOPE);
	if (newScope.equalsIgnoreCase("APPLICATION"))
			setScope(PageContext.APPLICATION_SCOPE);
	if (newScope.equalsIgnoreCase("PAGE"))
			setScope(PageContext.PAGE_SCOPE);
}

private void serveHtmlPage(String pageName, JspWriter out) {
	ServletContext context = pageContext.getServletContext();
	String fileName = context.getRealPath(pageName);
	File file = new File(fileName);
	FileInputStream in = null;
	try {
		in = new FileInputStream(file);

		InputStreamReader r = new InputStreamReader(in);
		char[] buf = new char[1024];
		int read = 0;
		while ((read = r.read(buf)) != -1) {
			out.write(buf, 0, read);
		}
	} catch (IOException e) {
		System.out.println("serveHtmlPage " + e);
	}

}
}