package com.covecomm.deweb.example;

import java.sql.*;
import com.covecomm.deweb.forms.*;
 
public class TestFormBean extends com.covecomm.deweb.forms.FormBean {

/** Testing form handling. 
 */
	
public TestFormBean() {
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

	FormFieldSubmitBean f8 = new FormFieldSubmitBean("Save Comments");
	addField(f8);

}
public void save(Connection conn) {
	try {
		Statement stmt = conn.createStatement();
		 stmt.executeQuery(
			"insert into Comments (FirstName,LastName,Email,Comments) values("
				+ "\""
				+ getFieldValue("firstName")
				+ "\",\""
				+ getFieldValue("lastName")
				+ "\",\""
				+ getFieldValue("email")
				+ "\",\""
				+ getFieldValue("comments")
				+ "\")");
				
	} catch (SQLException e) {
		System.out.println("Unable to add Messages record: " + e.toString());
	}
	return;
}
}