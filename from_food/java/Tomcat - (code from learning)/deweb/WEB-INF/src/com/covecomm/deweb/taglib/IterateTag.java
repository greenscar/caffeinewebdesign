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

package com.covecomm.deweb.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.io.IOException;
import java.lang.reflect.*;

/** A tag to iterate through a collection of objects.
 *
 */

public class IterateTag extends BodyTagSupport {
	private Object collectionBean;
	private Iterator iterator;

	private String iteratedItemClass;
	private String collection;
	public int doAfterBody() throws JspException {

		if (iterate() == SKIP_BODY) {

			try {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
				return SKIP_BODY;
			} catch (IOException e) {
				throw new JspTagException(e.toString());
			}
		}
		return EVAL_BODY_INCLUDE; //EVAL_BODY_TAG;
	}

	public int doStartTag() throws JspException {
		try {
			Object object = null;
			object = pageContext.getAttribute(collection, scope);
			if (object == null)
				object = pageContext.findAttribute(collection);
			if (object == null) {
				System.out.println(
					"IterateTag could not find "
						+ "the "
						+ collection
						+ " object to iterate");
				return SKIP_BODY;
			}
			Class[] params = new Class[0];
			Method method;
			if (object instanceof java.util.TreeSet)
				method = object.getClass().getMethod("iterator", params);
			else
				method = object.getClass().getMethod("getIterator", params);
			Object[] args = new Object[0];
			iterator = (Iterator) method.invoke(object, args);
		} catch (NoSuchMethodException e1) {
			System.out.println("Exception " + e1);
			return SKIP_BODY;
		} catch (IllegalAccessException e2) {
			System.out.println("Exception " + e2);
			return SKIP_BODY;
		} catch (InvocationTargetException e3) {
			System.out.println("Exception " + e3);
			return SKIP_BODY;
		} catch (ClassCastException e4) {
			System.out.println("Exception " + e4);
			return SKIP_BODY;
		}
		return iterate();
	}
	public java.lang.String getCollection() {
		return collection;
	}

	public java.lang.String getIteratedItemClass() {
		return iteratedItemClass;
	}
	protected int iterate() {
		if ((iteratedItemName != null)
			&& (iterator != null)
			&& (iterator.hasNext())) {
			Object element = iterator.next();
			pageContext.setAttribute(iteratedItemName, element);
			Class c = element.getClass();
			return EVAL_BODY_INCLUDE; //EVAL_BODY_TAG;
		} else {
			return SKIP_BODY;
		}

	}
	public void setCollection(java.lang.String newCollection) {
		collection = newCollection;
	}

	public void setIteratedItemClass(java.lang.String newIteratedItemClass) {
		iteratedItemClass = newIteratedItemClass;
		//
	}
	private String iteratedItemName;
	private int scope = PageContext.PAGE_SCOPE;
	public java.lang.String getIteratedItemName() {
		return iteratedItemName;
	}
	public int getScope() {
		return scope;
	}
	public void setIteratedItemName(java.lang.String newIteratedItemName) {
		iteratedItemName = newIteratedItemName;
	}
	public void setScope(int newScope) {
		scope = newScope;
	}
	public void setScope(java.lang.String newScope) {
		if (newScope.equalsIgnoreCase("REQUEST"))
			setScope(PageContext.REQUEST_SCOPE);
		if (newScope.equalsIgnoreCase("SESSION"))
			setScope(PageContext.SESSION_SCOPE);
		if (newScope.equalsIgnoreCase("APPLICATION"))
			setScope(PageContext.APPLICATION_SCOPE);
		if (newScope.equalsIgnoreCase("PAGE"))
			setScope(PageContext.PAGE_SCOPE);
	}
}