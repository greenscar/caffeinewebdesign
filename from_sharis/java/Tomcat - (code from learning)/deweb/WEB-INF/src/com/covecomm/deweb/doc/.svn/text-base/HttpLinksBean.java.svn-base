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

/** Stores a list of HttpLinkBean objects.
 * 
 * This bean is used on web pages when you need
 * to iterate through a list of links to
 * documents. Not to be confused with
 * HttpLinkBean which is the object that models
 * one link. I should probably rename this one. 
 */
 
public class HttpLinksBean {
	protected TreeSet links;
	private String linksName = "default";
/**
 * On creation, make a new links list. 
 */
public HttpLinksBean() {
	super();
	links = new TreeSet();
}
/**
 * Set the links list name and call the default constructor.
 */
public HttpLinksBean(String linksName) {
	this();
	setLinksName(linksName);
}
/** Add a link bean to the list */
public void add(HttpLinkBean link) {
	links.add(link);
}
/** Get an iterator of the list of links. 
 *
 * This method is called by the Iterate tag.
 */
public java.util.Iterator getIterator() {
	if (links == null) links = new TreeSet();
	return links.iterator();
}
/**
 * Get the name of the list of links.
 */
public java.lang.String getLinksName() {
	return linksName;
}
/**
 * Set the name of the list of links.
 */
public void setLinksName(java.lang.String newLinksName) {
	linksName = newLinksName;
}
}