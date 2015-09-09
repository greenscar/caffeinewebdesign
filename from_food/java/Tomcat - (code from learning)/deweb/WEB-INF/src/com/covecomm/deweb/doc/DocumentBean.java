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
 
package com.covecomm.deweb.doc;


import java.sql.*;
 
/**
 * Models a document, including author-related information
 */

public class DocumentBean {
	private Date date;
	private String uri;
	private String title;
	private String text;
	private String summary;
	private String author;
	private int ID;

public DocumentBean() {
	super();
}
/**
 * This constructor takes a document uri, name (title), summary,
 * and publication date.
 */
public DocumentBean(String uri,String name,String summary, Date pubdate) {
	// comment
	this();
	setUri(uri);
	setTitle(name);
	setSummary(summary);
	setDate(pubdate);
}
/**
 * Get the article's publication date.
 * @return java.sql.Date
 */
public java.sql.Date getDate() {
	return date;
}
/**
 * Get the document's unique ID
 * @return int
 */
public int getID() {
	return ID;
}
/**
 * Get the document's summary.
 */
public java.lang.String getSummary() {
	return summary;
}
/**
 * Get the document's title.
 */
public java.lang.String getTitle() {
	return title;
}
/**
 * Get the document's URI
 */
public java.lang.String getUri() {
	return uri;
}
/**
 * Set the document's publication date.
 */
public void setDate(java.sql.Date newDate) {
	date = newDate;
}
/**
 * Set the document's unique ID
 */
public void setID(int newID) {
	ID = newID;
}
/**
 * Set the document's summary.
 */
public void setSummary(java.lang.String newSummary) {
	summary = newSummary;
}
/**
 * Set the document's title.
 */
public void setTitle(java.lang.String newTitle) {
	title = newTitle;
}
/**
 * Set the document's URI.
 */
public void setUri(java.lang.String newUri) {
	uri = newUri;
}
/** Return a string representation of the document */
	public String toString(){
		return getTitle()
			+ ","
			+ getUri()
			+ ","
			+ getDate()
			+ ","
			+ getSummary();
		}
}