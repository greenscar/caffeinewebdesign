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
import java.io.IOException;
import com.covecomm.deweb.forms.*;

/** A tag to create an HTML form.
 *
 */
 
public class FormTag extends TagSupport {
	private String formName;
	private FormBean form=null;
	
public int doEndTag() throws JspException {
	JspWriter out = pageContext.getOut();
	try {
		if (form != null)
			out.print(form.getFooter());
		else out.print("</form>");
	} catch (IOException e) {
	    throw new JspException(e.toString());
	}
	return EVAL_PAGE;
}
public int doStartTag() throws JspException {
	//System.out.println("FormTag doStartTag");
	ServletRequest request = pageContext.getRequest();
	try {
		form = (FormBean)request.getAttribute(getFormName());
	} catch (ClassCastException e) {
		throw new JspException("FormTag class cast exception");
	}
	if (form == null) {
		System.out.println("formbean is null, skipping menu");
		return SKIP_BODY;
	}
	JspWriter out = pageContext.getOut();
	try {
	    out.print(form.getHeader());
	} catch (IOException e) {
	    throw new JspException(e.toString());
	}
	return EVAL_BODY_INCLUDE;
}
public java.lang.String getFormName() {
	return formName;
}
public void setFormName(java.lang.String newFormName) {
	formName = newFormName;
}
}