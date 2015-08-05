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

/** A generic class for modeling an HTML form field.
 *
 * This is the base class for form field beans for all
 * HTML field types.
 */
public abstract class FormFieldBean 
	implements com.covecomm.deweb.util.Message, Comparable {
private String type=null;
private String name=null;
private String prompt=null;
private String ID="";
private String value="";
private int width=25;
private int height=1;
private String html;
private boolean required=false;
private String message;
public FormFieldBean() {
	super();
	
}
public void clear() {
	value = "";
}
public int compareTo(java.lang.Object o){
	FormFieldBean otherField = (FormFieldBean)o;
	return(getID().compareTo(otherField.getID()));
}
public int getHeight() {
	return height;
}
public abstract java.lang.String getHtml();
public java.lang.String getID() {
	return ID;
}
public java.lang.String getMessage() {
	return message;
}
public java.lang.String getName() {
	return name;
}
public java.lang.String getPrompt() {
	if (prompt != null)	return prompt;
	if (name != null) return name;
	if (ID != null) return ID;
	return("unknown field");
}
public java.lang.String getType() {
	return type;
}
public java.lang.String getValue() {
	return value;
}
public java.lang.String getValueEscaped() {
	if (value == null)
	    return (null);

	StringBuffer result = new StringBuffer();
	for (int i = 0; i < value.length(); i++) {
	    char ch = value.charAt(i);
	    if (ch == '\'')
			result.append("''");
	    else
			result.append(ch);
	}
	return (result.toString());
}
public int getWidth() {
	return width;
}
public boolean isRequired() {
	return required;
}
public void setHeight(int newHeight) {
	height = newHeight;
}
public void setID(java.lang.String newID) {
	ID = newID;
}
public void setMessage(java.lang.String newMessage) {
	message = newMessage;
}
public void setName(java.lang.String newName) {
	name = newName;
}
public void setPrompt(java.lang.String newPrompt) {
	if (newPrompt != null)
		prompt = newPrompt;
}
public void setRequired(boolean newRequired) {
	required = newRequired;
}
public void setType(java.lang.String newType) {
	if (newType != null)
		type = newType;
}
public void setValue(java.lang.String newValue) {
	if (newValue != null) {
		value = newValue;
	}
}
	
public void setWidth(int newWidth) {
	width = newWidth;
}
public boolean validate(){
	if ( (isRequired())  && (value.equals("")) ) {
		setMessage("The field \"" + getPrompt()
			+ "\" is required");
		return false;
	}
	else return true;
}
}