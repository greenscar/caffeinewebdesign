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

/** A custom tag to display an HTML form field
 *
 *
 */

 
public class FormFieldTag extends TagSupport {
	private String formName;
	private String fieldName;
	private String attribute=null;
public int doStartTag() throws JspException {
	ServletRequest request = pageContext.getRequest();
	//System.out.println("FormFieldTag doStartTag");
	FormBean fb = null;
	try {
		fb = (FormBean)request.getAttribute(getFormName());
	} catch (ClassCastException e) {
		System.out.println("FormTag class cast exception on " + getFormName());
		return SKIP_BODY;
	}
	if (fb == null){
		System.out.println("Unable to create FormBean " + getFormName());
		return SKIP_BODY;
	}
	FormFieldBean field = (FormFieldBean)fb.getField(getFieldName());
	if (field == null) {
		System.out.println("Unable to get field "
			+ getFieldName()
			+ " in FormBean " + getFormName());
		return SKIP_BODY;
	}
	//System.out.println("using formbean " + fb + ", field " + field);
	//pageContext.setAttribute(field.getName(),field);
	JspWriter out = pageContext.getOut();
	try {
		if ((attribute != null) 
			&& (attribute.equalsIgnoreCase("prompt"))){
			//System.out.println("prompt text: " + field.getPrompt());
			out.print(field.getPrompt());
		} else {
			//System.out.println("field text: " + field.getHtml());
		    out.print(field.getHtml());
		}
	} catch (IOException e) {
	    throw new JspException(e.toString());
	}
	
	return (EVAL_BODY_INCLUDE);
}
public java.lang.String getAttribute() {
	return attribute;
}
public java.lang.String getFieldName() {
	return fieldName;
}
public java.lang.String getFormName() {
	return formName;
}
public void setAttribute(java.lang.String newAttribute) {
	attribute = newAttribute;
}
public void setFieldName(java.lang.String newFieldName) {
	fieldName = newFieldName;
}
public void setFormName(java.lang.String newFormName) {
	formName = newFormName;
}
}