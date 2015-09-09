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

/** Handler bean for radio buttons.
 *
 * Since you can have multiple radio buttons, only one
 * of which sets the return value, a separate class is
 * required which registers and manages the grouped
 * radio buttons. 
 */

public class FormFieldRadioHandlerBean extends FormFieldBean {
private HashMap fieldsMap;
private String checkedRadioBean="";
public FormFieldRadioHandlerBean() {
	super();
	fieldsMap = new HashMap();
}
public FormFieldRadioHandlerBean(String newId) {
	this();
	setID(newId);
	setName(newId);
}
public FormFieldRadioHandlerBean(String newId,String newPrompt) {
	this(newId);
	setPrompt(newPrompt);
}
	public void addField(FormFieldBean formField) {
		fieldsMap.put(formField.getID(), formField);
	}
public java.lang.String getCheckedRadioBean() {
	return checkedRadioBean;
}
public String getHtml() {
	return null;
}
public void setValue(String newValue) {
	super.setValue(newValue);
	checkedRadioBean = "";
	Iterator iterator = fieldsMap.values().iterator();
	FormFieldRadioBean field = null;
	System.out.println("** set value of radio button handler to " + getValue());
	while (iterator.hasNext()) {
		/*  loop through all the radio buttons that
			are part of this set. If their value
			matches the handler's value, then set
			the radio to checked. Clear all other
			buttons.
		*/
		field = (FormFieldRadioBean) iterator.next();
		System.out.println("** comparing field " + field.getID() + " with value " + field.getValue());
		if (field.getValue().equals(getValue())){
			checkedRadioBean = field.getID();
			System.out.println("set checkedRadioBean to " + checkedRadioBean);
			field.setChecked(true);
		}
		else field.setChecked(false);
	}
}
}