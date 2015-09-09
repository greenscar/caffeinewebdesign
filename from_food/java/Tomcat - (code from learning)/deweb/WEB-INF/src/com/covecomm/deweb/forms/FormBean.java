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


import java.util.*;
import java.sql.Connection;
import javax.servlet.http.*;
import javax.servlet.ServletContext;

/** A bean that handles common form tasks.
 *
 */
 
public abstract class FormBean implements com.covecomm.deweb.util.Message {
	private String method="POST";
	private String action="";
	private HashMap fieldsMap;
	private String uri="";
	private HashMap params;
	private boolean newForm=true;
	private String name="form";
	private String formPage;
	private String formDonePage;
	private String message;
	private String footer="</form>";
	private TreeSet repeatFields=null;
	protected HttpServletRequest request=null;
	protected HttpServletResponse response=null;
	protected ServletContext context=null;
	
public FormBean() {
	super();
	fieldsMap = new HashMap();
	FormFieldHiddenBean field = new FormFieldHiddenBean("formID","true");
	addField(field);
	repeatFields = new TreeSet();
}
public FormBean(String newFormPage,String newFormDonePage) {
	this();
	setFormPage(newFormPage);
	setFormDonePage(newFormDonePage);
}
public void addField(FormFieldBean formField){
	fieldsMap.put(formField.getID(),formField);
}
public void addRepeatField(FormFieldBean formField){
	repeatFields.add(formField);
}
public java.lang.String getAction() {
	return action;
}
public Object getField(String fieldID){
	Object f = fieldsMap.get(fieldID);
	return f;
}
public String getFieldValue(String fieldName) {
	FormFieldBean field = (FormFieldBean) getField(fieldName);
	if (field != null)
		return field.getValue();
	else return "";
}
public String getFieldValueEscaped(String fieldName) {
	FormFieldBean field = (FormFieldBean) getField(fieldName);
	if (field != null)
		return field.getValueEscaped();
	else return "";
}
public java.lang.String getFooter() {
	return footer;
}
public java.lang.String getFormDonePage() {
	return formDonePage;
}
public java.lang.String getFormPage() {
	return formPage;
}
public java.lang.String getHeader() {
	StringBuffer sb = new StringBuffer("<form method=\"");
	sb.append(getMethod());
	sb.append("\" action=\"");
	sb.append(getAction());
	sb.append("\">");
	return(sb.toString());
}
public java.util.Iterator getIterator() {
	if (repeatFields == null) repeatFields = new TreeSet();
	return repeatFields.iterator();
}
public java.lang.String getMessage() {
	return message;
}
public java.lang.String getMethod() {
	return method;
}
public java.lang.String getName() {
	return name;
}
public java.util.TreeSet getRepeatFields() {
	return repeatFields;
}
public java.lang.String getUri() {
	return uri;
}
public boolean isNewForm() {
	return newForm;
}
public abstract void save(Connection conn);
public void setAction(java.lang.String newAction) {
	action = newAction;
}
public void setFieldValue(String fieldName, String newValue) {
	FormFieldBean field = (FormFieldBean)getField(fieldName);
	if (field != null)
		field.setValue(newValue);
}
public void setFormDonePage(java.lang.String newFormDonePage) {
	formDonePage = newFormDonePage;
}
public void setFormPage(java.lang.String newFormPage) {
	formPage = newFormPage;
}
public void setMessage(java.lang.String newMessage) {
	message = newMessage;
}
public void setMethod(java.lang.String newMethod) {
	method = newMethod;
}
public void setName(java.lang.String newName) {
	name = newName;
}
public void setNewForm(boolean newNewForm) {
	newForm = newNewForm;
}
public void setParameters(HashMap requestParams) {
	/*
	System.out.println("setting form parameters");
	String[] paramValues;
	FormFieldBean field;
	// Look for the FormID hidden field in the form.
	// If found, this is a GET or POST of the form.
	if (requestParams.containsKey("formID")) {
		System.out.println("found the formID hidden field");
		paramValues = (String[]) requestParams.get("formID");
		if (paramValues[0].equals("true"))
			setNewForm(false);
			System.out.println("setting NewForm to false");
	} else {
			System.out.println("did not find the formID hidden field");
	}

	System.out.println("new form: " + isNewForm());
	if (!isNewForm()) {
		Iterator iterator = fieldsMap.values().iterator();
		while (iterator.hasNext()) {
			// get the next form field
			field = (FormFieldBean) iterator.next();
			// look for a match in the request parameters
			if (requestParams.containsKey(field.getID())) {
				System.out.println("found a match for form field " + field.getID());
				// get the parameter values from the request
				paramValues = (String[]) requestParams.get(field.getID());
				// only the first element of the array is used at present
				field.setValue(paramValues[0]);
			} else {
				System.out.println("found a match for form field " + field.getID());
				field.clear();
			}

		}
	}
	*/

}
public void setServletInfo(javax.servlet.http.HttpServletRequest newRequest,
	javax.servlet.http.HttpServletResponse newResponse,
	javax.servlet.ServletContext newContext) {
	request = newRequest;
	response = newResponse;
	context = newContext;
	Enumeration e = request.getParameterNames();
	java.util.HashMap requestParams = new java.util.HashMap();
	while (e.hasMoreElements()) {
		String key = (String) e.nextElement();
		String[] values = request.getParameterValues(key);
		requestParams.put(key, values);
	}
	//System.out.println("setting form parameters");
	String[] paramValues;
	FormFieldBean field;
	// Look for the FormID hidden field in the form.
	// If found, this is a GET or POST of the form.
	if (requestParams.containsKey("formID")) {
		//System.out.println("found the formID hidden field");
		paramValues = (String[]) requestParams.get("formID");
		if (paramValues[0].equals("true"))
			setNewForm(false);
			//System.out.println("setting NewForm to false");
	} else {
			//System.out.println("did not find the formID hidden field");
	}

	//System.out.println("new form: " + isNewForm());
	if (!isNewForm()) {
		Iterator iterator = fieldsMap.values().iterator();
		while (iterator.hasNext()) {
			// get the next form field
			field = (FormFieldBean) iterator.next();
			// look for a match in the request parameters
			if (requestParams.containsKey(field.getID())) {
				//System.out.println("found a match for form field " + field.getID());
				// get the parameter values from the request
				paramValues = (String[]) requestParams.get(field.getID());
				// only the first element of the array is used at present
				field.setValue(paramValues[0]);
			} else {
				//System.out.println("found a match for form field " + field.getID());
				field.clear();
			}

		}
	}
		
}
public void setUri(java.lang.String newUri) {
	uri = newUri;
}
public boolean validate() {
	Iterator iterator = fieldsMap.values().iterator();
	FormFieldBean field;
	while (iterator.hasNext()) {
		// get the next form field
		field = (FormFieldBean) iterator.next();
		if (!field.validate()) {
			setMessage(field.getMessage()); 
			return false; 
		}
	}
	return true; 
}
public boolean validate(Connection conn) {
	return(validate());
}
}