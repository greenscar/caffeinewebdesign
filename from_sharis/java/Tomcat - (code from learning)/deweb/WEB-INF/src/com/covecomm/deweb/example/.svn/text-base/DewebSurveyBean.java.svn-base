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

/**
 * Insert the type's description here.
 * Creation date: (12/15/00 11:55:06 AM)
 * @author: 
 */

import com.covecomm.deweb.graph.GraphBean;
 
public class DewebSurveyBean extends com.covecomm.deweb.forms.FormSurveyBean {
public DewebSurveyBean() {
	super();
	setFormPage("/survey.jsp");
	setAction("/deweb/index.html");
	setFormDonePage("");
	setSurveyNumber(1);
	setMessage("Are databases an important part of your web development efforts?");
	addRadioButton("a","Yes","Yes"); 
	addRadioButton("b","No","No"); 
	addRadioButton("c","Not sure","Not sure"); 

	// create the radio button handler object
	finishSetup();


}
public void prepareGraph(GraphBean graph) {
	/* Uncomment the following to get a list of fonts
		available to you. You can then create a suitable
		font object and pass it to graph with the setFont()
		method. 
	System.out.println("----------- fonts ----------------");
	GraphicsEnvironment ge =
	GraphicsEnvironment.getLocalGraphicsEnvironment();
	Font[] installedFonts = ge.getAllFonts();
	for (int i = 0; i < installedFonts.length; i++) {
		System.out.println("Font: " + installedFonts[i]);
	}
	System.out.println("----------------------------------");
	*/
	graph.setWidth(108);
	graph.setLegendHeight(15);
	
}
}