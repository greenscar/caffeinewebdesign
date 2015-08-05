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
 
package com.covecomm.deweb.control;

/**
 * Insert the type's description here.
 * Creation date: (12/27/00 12:19:36 PM)
 * @author: 
 */

import javax.servlet.http.HttpSession;

 
/** This JavaBean models a user on the web site. 
 * Based on UserBean, it also stores session data.
 * 
 */
 
public class HttpUserBean extends UserBean {
	private HttpSession session = null;
/**
 * HttpUserBean constructor.
 */
public HttpUserBean() {
	super();
}
/**
 * HttpUserBean constructor comment.
 * @param newUsername java.lang.String
 * @param newPassword java.lang.String
 * @param newHostname java.lang.String
 */
public HttpUserBean(String newUsername, String newPassword, String newHostname) {
	super(newUsername, newPassword, newHostname);
}
/**
 * Get the user's session.
 */
public javax.servlet.http.HttpSession getSession() {
	return session;
}
/**
 * Set the user's session
 */
public void setSession(javax.servlet.http.HttpSession newSession) {
	session = newSession;
}
}