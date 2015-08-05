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

import java.util.*; 

 
/** Models an article, which is usually an HTML document
 * 
 * This class will probably be replaced by WebDocBean.
 * 
 * @author: Dave Harms covecomm@mbnet.mb.ca
 */
 
public class Article {
	protected String title,description,authorName,URL;
	protected Date pubDate,lastModDate;
/**
 * Constructor just calls super()
 */
public Article() {
	super();
}
/**
 * Get the article author name
 * @return java.lang.String
 */
public java.lang.String getAuthorName() {
	return authorName;
}
/**
 * Get the article description
 *
 * @return java.lang.String
 */
public java.lang.String getDescription() {
	return description;
}
/**
 * Get the Article's last modified date
 * @return java.util.Date
 */
public java.util.Date getLastModDate() {
	return lastModDate;
}
/**
 * Get the article's publication date
 * @return java.util.Date
 */
public java.util.Date getPubDate() {
	return pubDate;
}
/**
 * Get the article's title
 * @return java.lang.String
 */
public java.lang.String getTitle() {
	return title;
}
/**
 * Get the article's URL
 * @return java.lang.String
 */
public java.lang.String getURL() {
	return URL;
}
/**
 * Set the name of the article's author
 * @param newAuthorName java.lang.String
 */
public void setAuthorName(java.lang.String newAuthorName) {
	authorName = newAuthorName;
}
/**
 * Set the article's description
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription) {
	description = newDescription;
}
/**
 * set the article's last modified date
 * @param newLastModDate java.util.Date
 */
public void setLastModDate(java.util.Date newLastModDate) {
	lastModDate = newLastModDate;
}
/**
 * Set the article's publication date
 * @param newPubDate java.util.Date
 */
public void setPubDate(java.util.Date newPubDate) {
	pubDate = newPubDate;
}
/**
 * Set the article's title
 * @param newTitle java.lang.String
 */
public void setTitle(java.lang.String newTitle) {
	title = newTitle;
}
/**
 * Set the article's URL
 * @param newURL java.lang.String
 */
public void setURL(java.lang.String newURL) {
	URL = newURL;
}
}