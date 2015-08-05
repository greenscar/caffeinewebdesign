package com.covecomm.deweb.control;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;


import com.covecomm.deweb.util.*;
import sun.misc.BASE64Decoder;
import com.covecomm.deweb.forms.*;
import com.covecomm.deweb.doc.*;

/** The controller of the model/view/controller application.
 *
 * This servlet handles all incoming requests for pages 
 * mapped in web.xml, manages security, and creates any beans
 * needed for page processing. It then hands off to whatever
 * view JSP is appropriate.
 */


public class ControllerServlet extends HttpServlet {
/** Handles all incoming page requests as mapped in web.xml */
public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        System.err.println("ControllerServlet.doGet()");
	System.err.println("******* start of request processing *****");
	java.util.Date date = new java.util.Date();
	response.setDateHeader("Date", date.getTime());

	Cookie[] cookies = request.getCookies();
	if (cookies != null){
		for (int i = 0; i < cookies.length; i++) {
			System.err.println("recieved cookie " + cookies[i].getName() + ", " + cookies[i].getValue());
		}
	}

	UserBean user = null;
	String forwardTo;
	int status = 0;
	String jsp = messageJsp;
	count++;

	String pageTitle="";

	// get the user's session
	HttpSession session = request.getSession();

	// get the request URI
	String uri = request.getServletPath();
	//uri = request.getContextPath() + request.getServletPath(); //
	//System.err.println("uri " + uri);
	String origUri = uri;
	//System.err.println("uri " + uri);


	// try to get the user from the session -
	// if not found, create a new user
	user = (UserBean) session.getAttribute("user");
	if (user == null) {
                System.err.println("No user found. Creating a new one.");
		user = new UserBean();
		session.setAttribute("user", user);
	}
        else{
           System.err.println("User found in session.");
        }
	request.setAttribute("user", user);
	user.setHostname(request.getRemoteAddr());
	String username = null;
	String password = null;
	String decoded;
	String authHeader = (String) request.getHeader("Authorization");
        System.err.println("authHeader = " + authHeader);
	if (authHeader != null) {
		authHeader = authHeader.substring(6).trim();
		synchronized (this) {
			decoded = new String(decoder.decodeBuffer(authHeader));
		}
		int colon = decoded.indexOf(":");
		if (colon > 0) {
			username = decoded.substring(0, colon);
			password = decoded.substring(colon + 1);
		}
		user.setPassword(password);
		user.setUsername(username);
		System.err.println("Controller set username to " + username);
	}

	//user = new UserBean(username, password, request.getRemoteAddr());

	if (findLogger())
		//logger.write(request.getRemoteAddr(), username, uri, "", "uri requested");
		logger.write(username, request, status, "uri requested");

	WebDocBean webdoc = null;
	// get a document object corresponding to the uri
	SecurityBean security = new SecurityBean();
	java.sql.Connection connection = null;

	// check for various errors. Default jsp is messageJsp.
	do {
		if (!findDbPool()) {
			uri = systemErrorHtml;
			status = SecurityBean.NO_DBPOOL;
			break;
		}
		connection = dbPool.getConnection();
		if (connection == null) {
			uri = systemErrorHtml;
			status = SecurityBean.NO_CONN;
			break;
		}
		webdoc = security.getWebDocBean(connection, uri);
		if (!webdoc.isInDatabase()) {
			uri = notFoundHtml;
			status = SecurityBean.NO_FILE;
			break;
		}

		// set the page title
		pageTitle = webdoc.getTitle();

		// If public access then forward to its JSP
		if (webdoc.getRequireRights().equals("N")) {
			jsp = webdoc.getJspName();
			System.err.println("using jsp " + jsp);
			status = SecurityBean.AUTHORIZED;
			break;
		}

		status = security.getAccess(connection, user, webdoc);
		switch (status) {
			case SecurityBean.INVALID_USER :
				response.setContentType("text/html");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setHeader("WWW-Authenticate", "BASIC realm=\"Subscribers\"");
				uri = invalidUserHtml;
				break;
			case SecurityBean.NOT_AUTHORIZED :
				uri = notAuthorizedHtml;
				break;
			case SecurityBean.PENDING :
				uri = pendingAuthHtml;
				break;
			case SecurityBean.CONFIGURATION_ERROR :
				uri = systemErrorHtml;
				break;
			case SecurityBean.AUTHORIZED :
				jsp = webdoc.getJspName();
				break;
			default :
				uri = systemErrorHtml;
				break;
		}
	} while (false);

	if (findLogger())
		logger.write(username, request, status, "forwarding " + uri + " to " + jsp);

	// If this page request has a form associated with it,
	// create the form bean. 
	do {
		if ((status == SecurityBean.AUTHORIZED) && (webdoc.isFormPage())) {
			FormBean form = null;
			FormFieldBean b = null;
			try {
				Class c = Class.forName(webdoc.getExtraInfo());
				form = (FormBean) c.newInstance();
			} catch (ClassNotFoundException e) {
				System.err.println(e);
				uri = systemErrorHtml;
				break;
			} catch (InstantiationException e) {
				System.err.println(e);
				uri = systemErrorHtml;
				break;
			} catch (IllegalAccessException e) {
				System.err.println(e);
				uri = systemErrorHtml;
				break;
			} catch (NullPointerException e) {
				System.err.println(e);
				uri = systemErrorHtml;
				break;
			}

			form.setServletInfo(request, response, getServletContext());

			
			String contextPath = (String)getServletContext().
				getAttribute("contextPath");
			if (contextPath == null) contextPath = "";
	
			form.setAction(contextPath + webdoc.getUri());
			request.setAttribute(form.getName(), form);


			if ((!form.isNewForm()) && (form.validate(connection))) {
				form.save(connection);
				uri = form.getFormDonePage();
			} else
				uri = form.getFormPage();
		}

	} while (false);

	request.setAttribute("pageTitle",pageTitle);
	System.err.println((String)request.getAttribute("pageTitle"));

		
	if ((connection != null) && (dbPool != null))
		dbPool.freeConnection(connection);
	if (jsp == null)
		throw new ServletException("Cannot forward to this page");

	if (uri != null) {
		if ((webdoc.getIncludePage() != null) 
			&& (webdoc.getIncludePage().trim().length() > 0))
			request.setAttribute("includePage", webdoc.getIncludePage());
		else 					
			request.setAttribute("includePage", uri);
	}

	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
	if (dispatcher != null)
		dispatcher.forward(request, response);
	return;
}

/** Maps PUT requests to doGet() */

public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		doGet(request, response);
	}
	private int count = 0;		private ConnectionPoolAdapter dbPool;		private BASE64Decoder decoder;		private String homeJsp = null;		private String invalidUserHtml = null;		private LoggerBean logger;		private String messageJsp = null;		private String notAuthorizedHtml = null;		private String notFoundHtml = null;		private String pendingAuthHtml = null;		private String systemErrorHtml = null;/** Locate the database connection pool */
protected boolean findDbPool() {
	if (dbPool == null) 
		dbPool = (ConnectionPoolAdapter)getServletContext().getAttribute("dbPool");
	return (dbPool != null);
 }                    /** Locate the logger bean */ 
protected boolean findLogger() {
	if (logger == null)
		logger = (LoggerBean)getServletContext().getAttribute("logger");
	return (logger != null);
 }                        
	
/** Initialize the servlet with values from web.xml */
public void init(ServletConfig config) throws ServletException {
		super.init(config);
		decoder = new BASE64Decoder();
		findDbPool();
		setHomeJsp(getInitParameter(""));
		setMessageJsp(getInitParameter("MessageJsp"));
		setNotFoundHtml(getInitParameter("NotFoundHtml"));
		setNotAuthorizedHtml(getInitParameter("NotAuthorizedHtml"));
		setInvalidUserHtml(getInitParameter("InvalidUserHtml"));
		setSystemErrorHtml(getInitParameter("SystemErrorHtml"));
		setPendingAuthHtml(getInitParameter("PendingAuthHtml"));
		setHomeJsp(getInitParameter("HomeJsp"));
} 
/** get the home page JSP */
public java.lang.String getHomeJsp() {
	return homeJsp;
}
 
/** Get the page with the "Invalid User" message */
public java.lang.String getInvalidUserHtml() {
	return invalidUserHtml;
}

 
/** Get the name of the JSP used to display messages to the user */
public java.lang.String getMessageJsp() {
	return messageJsp;
}
 
/** Get the page used to display a "not authorized" message to the user */
public java.lang.String getNotAuthorizedHtml() {
	return notAuthorizedHtml;
}
  
/** Get the page used to display a "page not found" message */
public java.lang.String getNotFoundHtml() {
	return notFoundHtml;
} 
/** Get the page used to tell the user that
 *  authorization is not yet complete 
 */
public java.lang.String getPendingAuthHtml() {
	return pendingAuthHtml;
} 
/** Get the page used to display a 
  * system error message to the user */
public java.lang.String getSystemErrorHtml() {
	return systemErrorHtml;
}
/** Set the JSP used to display the home page */
public void setHomeJsp(java.lang.String newHomeJsp) {
	homeJsp = newHomeJsp;
}
 
/** Set the page with the "Invalid User" message */
public void setInvalidUserHtml(java.lang.String newInvalidUserHtml) {
	invalidUserHtml = newInvalidUserHtml;
} 
/** Set the name of the JSP used to display messages to the user */
public void setMessageJsp(java.lang.String newMessageJsp) {
	messageJsp = newMessageJsp;
} 
/** Set the page used to display a "not authorized" message to the user */
public void setNotAuthorizedHtml(java.lang.String newNotAuthorizedHtml) {
	notAuthorizedHtml = newNotAuthorizedHtml;
}/**
 * Insert the method's description here.
 * Creation date: (12/6/00 2:47:31 PM)
 * @param newNotFoundHtml java.lang.String
 */      
/** Set the page used to display a "page not found" message */
public void setNotFoundHtml(java.lang.String newNotFoundHtml) {
	notFoundHtml = newNotFoundHtml;
}
 
/** Get the page used to tell the user that
 *  authorization is not yet complete 
 */
public void setPendingAuthHtml(java.lang.String newPendingAuthHtml) {
	pendingAuthHtml = newPendingAuthHtml;
}
  
/** Set the page used to display a 
  * system error message to the user */
public void setSystemErrorHtml(java.lang.String newSystemErrorHtml) {
	systemErrorHtml = newSystemErrorHtml;
}
}