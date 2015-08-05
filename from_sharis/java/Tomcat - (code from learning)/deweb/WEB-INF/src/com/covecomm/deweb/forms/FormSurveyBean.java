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

import java.sql.*;
import java.io.*;
import java.util.Iterator;
import javax.servlet.http.*;
import com.covecomm.deweb.graph.GraphBean;
import java.awt.*;

/** A specialized form for handling user surveys.
 * 
 * This bean is used for single-question surveys
 * with the results returned to the user as a
 * graph. 
 */
 
public abstract class FormSurveyBean extends com.covecomm.deweb.forms.FormBean {
	private boolean saveSurvey=false;
	private int surveyNumber=99999;
	private Color[] colors = {
		new Color(164,0,0),
		new Color(0,164,0),
		new Color(0,0,164),
		new Color(0,128,128),
		new Color(128,0,128),
		new Color(128,128,0)};
	private int colorIndex=	0;
	private String handlerName="question";
public FormSurveyBean() {
	super();
	setName("survey");
}
public void addRadioButton(String id, String name, String prompt) {
	FormFieldRadioBean rb = 
		new FormFieldRadioBean(id, handlerName, name); 
	rb.setPrompt(prompt);
	addField(rb);
	addRepeatField(rb);
}
public boolean allowedValue(String value) {
	Iterator iterator = getIterator();
	while (iterator.hasNext()) {
		FormFieldBean field = (FormFieldBean)iterator.next();
		if (value.equals(field.getValue())) return true;
	}
	return false;
}
public void finishSetup() {

	FormFieldRadioHandlerBean frh = 
		new FormFieldRadioHandlerBean(handlerName);
	Iterator iterator = getIterator();
	while (iterator.hasNext()) {
	    FormFieldBean f = (FormFieldBean)iterator.next();
		    frh.addField(f);
	}
	addField(frh);

	FormFieldHiddenBean surveyID = 
		new FormFieldHiddenBean("sid", Integer.toString(getSurveyNumber())); 
	addField(surveyID);

	FormFieldSubmitBean fsb = new FormFieldSubmitBean("Vote");
	fsb.setRequired(true);
	addField(fsb);
}
public Color getNextBarColor(){
	if (colorIndex >= colors.length) colorIndex=0;
	return(colors[colorIndex++]);
}
public int getSurveyNumber() {
	return surveyNumber;
}
public void prepareGraph(GraphBean graph) {
}
public void save(Connection conn) {
	if (!saveSurvey) return;
	String notes = request.getRemoteAddr();
	String value = getFieldValueEscaped(handlerName);
	String sid = getFieldValue("sid");
	colorIndex = 0;
	// dump all invalid survey IDs to 99999
	if ((sid == null) || (sid.equals("")))
		sid = "99999";
	System.out.println("sid: " + sid);
	int surveyId;
	try {
		surveyId = Integer.parseInt(sid);
		System.out.println("survey ID " + surveyId);
	} catch (NumberFormatException e) {
		surveyId = 99999;
		notes = "invalid sid: " + sid + " " + e.toString();
		System.out.println(notes);
	}
	int responses=0;
	GraphBean graph = new GraphBean();

	// prepareGraph can be overridden in the derived
	// class to set up graph properties such as the font
	prepareGraph(graph);
	
	Iterator iterator = getRepeatFields().iterator();
	Statement stmt = null;
	ResultSet rs = null;

	// make sure this is an allowed value
	if (allowedValue(value)) {
		try {
			stmt = conn.createStatement();
			// first delete any record previosly
			// inserted for this host name and survey ID
			
			stmt.executeQuery(
				"DELETE FROM SurveyData WHERE SurveyID="
					+ surveyId
					+ " AND Source='"
					+ notes
					+ "'");
			stmt.executeQuery(
				"INSERT INTO SurveyData (SurveyID,Value,Source) VALUES("
					+ surveyId
					+ ",'"
					+ value
					+ "','"
					+ notes
					+ "')"); 
			System.out.println("inserted survey results");

			// iterate through the survey fields
			int count;
			int idx=-1;
			while (iterator.hasNext()) {
				FormFieldBean field = (FormFieldBean) iterator.next();
				count = 0;
				rs = stmt.executeQuery("SELECT COUNT(*) FROM SurveyData "
	                + "WHERE SurveyID=" + surveyId
	                + " AND Value='" + field.getValueEscaped()
	                + "'");
				if (rs.next()) 
					count = rs.getInt("COUNT(*)");
				graph.addBar(idx, field.getValue(), count, getNextBarColor());
				responses += count;
				idx--;
			}
			graph.setMaxValue(responses);
			graph.setFooter("Votes: " + responses);

		} catch (SQLException e1) {
			System.out.println("SQL Error: " + e1);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e2) {
			};
		}
	}

	// get the survey results
	Frame frame = null;
	Graphics g = null;
	try {
		File tempHtm=null;
		FileWriter out=null;
		String path = context.getRealPath("/");

		// create the graph
		String jpgName = graph.createJpg(path);
		File tempDir = new File(path);
		tempHtm = File.createTempFile("survey-", ".htm", tempDir);
		out = new FileWriter(tempHtm);
		out.write("<html><body>");
		out.write("<img border=\"0\" src=\"");
		out.write(jpgName);
		out.write("\" width=\"" + graph.getWidth()
			+ "\" height=\"" + graph.getHeight() + "\">");
		out.close();
		String contextPath = (String)context.getAttribute(
			"contextPath");
		if (contextPath == null) contextPath="";
		//setFormDonePage(contextPath + "/" + tempHtm.getName());
		setFormDonePage(tempHtm.getName());
		setFormPage(getFormDonePage());
		System.out.println("FormDonePage " + getFormDonePage());
		context.setAttribute(getName()+"-lastgraph",getFormDonePage());
	} catch (Exception e) {
		System.out.println("exception " + e.toString());
	} finally {
		if (g != null)
			g.dispose();
		if (frame != null)
			frame.removeNotify();
	}

	return;
}
public void setServletInfo(
	javax.servlet.http.HttpServletRequest newRequest, 
	javax.servlet.http.HttpServletResponse newResponse, 
	javax.servlet.ServletContext newContext) {

	super.setServletInfo(newRequest, newResponse, newContext);
	
	String newSurvey = String.valueOf(getSurveyNumber()) + "-abc";
	String filledSurvey = String.valueOf(getSurveyNumber()) + "-def";
	String surveyNum = String.valueOf(getSurveyNumber());
	//System.out.println("survey number " + surveyNumber);

	// look for an existing survey cookie in the request
	boolean newSurveyCookie = false;
	boolean filledSurveyCookie = false;
	Cookie[] cookies = request.getCookies();
	if (cookies != null){
		for (int i = 0; i < cookies.length; i++) {
			//System.out.println(
			//	"testing cookie " + cookies[i].getName() + ", " + cookies[i].getValue()); 
			if (cookies[i].getName().equals(getName() + newSurvey)) {
				newSurveyCookie = true;
			} else if (cookies[i].getName().equals(getName() + filledSurvey)) {
				filledSurveyCookie = true;
			}
		}
	}
	/* if this is the first time around
	 	(say after restarting tomcat) then
		there will be no lastGraph to display
		for users who have already voted. So
		let the first person in this situation vote
		again. Vote early, vote often!
		*/

	String lastGraph = (String) context.getAttribute(getName() + "-lastgraph");
	if ((filledSurveyCookie) && (lastGraph == null)) {
		filledSurveyCookie = false;
	}

	if (filledSurveyCookie) {
	    //System.out.println("this person has already voted");
		if (lastGraph != null) {
			setNewForm(false);
			setFormDonePage(lastGraph);
			setFormPage(lastGraph);
		}
		System.out.println("lastGraph " + lastGraph);
	} else {
		if (isNewForm()) {
		    //System.out.println("new form, not previously filled, adding new Survey cookie");
		    // create the new form cookie
			Cookie cookie = new Cookie(getName() + newSurvey, surveyNum);
			cookie.setMaxAge(60 * 60 * 24 * 15);
			response.addCookie(cookie);
		} else {
			// this is a form submission
			if (newSurveyCookie) {
			    //System.out.println("filled form, okay to save");
			    // create the saved form cookie
			    // create the new form cookie
				Cookie cookie = new Cookie(getName() + filledSurvey, surveyNum);
				cookie.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(cookie);
				saveSurvey = true;
			} else {
				//System.out.println("filled form, no cookies");
				setMessage("You must have cookies enabled to fill out this survey");
				setNewForm(true);
			}
		}
	}

}
public void setSurveyNumber(int newSurveyNumber) {
	surveyNumber = newSurveyNumber;
}
public boolean validate() {
	String value = getFieldValue(handlerName);
	if ((value != null) && (allowedValue(value)))
		return true;
	else return false;
}
}