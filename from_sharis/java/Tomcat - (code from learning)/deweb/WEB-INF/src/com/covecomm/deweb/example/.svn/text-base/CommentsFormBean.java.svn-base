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
 
package com.covecomm.deweb.example;

import java.sql.*;
import com.covecomm.deweb.forms.*;
 
/** A class that manages a comments form.
 *
 */
 
public class CommentsFormBean extends com.covecomm.deweb.forms.FormBean {

public CommentsFormBean() {
	super();
	setFormPage("/cmag/test.jsp");
	setFormDonePage("/cmag/thanks.html");

	FormFieldRadioBean f0 = 
		new FormFieldRadioBean("ratingGood","rating","Good");
	addField(f0);

	FormFieldRadioBean f1 = 
		new FormFieldRadioBean("ratingBad","rating","Bad");
	addField(f1);

	FormFieldRadioBean f2 = 
		new FormFieldRadioBean("ratingUgly","rating","Ugly");
	f2.setChecked(true);
	addField(f2);

	FormFieldRadioHandlerBean f3 = 
		new FormFieldRadioHandlerBean("rating");
	f3.addField(f0);
	f3.addField(f1);
	f3.addField(f2);
	addField(f3);

	

	FormFieldTextBean f4 = 
		new FormFieldTextBean("firstName",30);
	f4.setRequired(true);
	addField(f4);
	
	FormFieldTextBean f5 = 
		new FormFieldTextBean("lastName",30);
	addField(f5);
	
	FormFieldTextBean f6 = 
		new FormFieldTextBean("email",30);
	addField(f6);

	FormFieldTextAreaBean f7 = 
		new FormFieldTextAreaBean("comments",30,10);
	addField(f7);

	FormFieldHiddenBean f8 = 
		new FormFieldHiddenBean("articleID","");
	addField(f8);

	FormFieldSubmitBean f9 = new FormFieldSubmitBean("Save Comments");
	addField(f9);

}
public void save(Connection conn) {
	try {
		Statement stmt = conn.createStatement();
		 stmt.executeQuery(
			"insert into Comments (FirstName,LastName,Email,ArticleID,Comments) values("
				+ "\""
				+ getFieldValue("firstName")
				+ "\",\""
				+ getFieldValue("lastName")
				+ "\",\""
				+ getFieldValue("email")
				+ "\",\""
				+ getFieldValue("articleID")
				+ "\",\""
				+ getFieldValue("comments")
				+ "\")");
				
	} catch (SQLException e) {
		System.out.println("Unable to add Messages record: " + e.toString());
	}
	return;
}
}