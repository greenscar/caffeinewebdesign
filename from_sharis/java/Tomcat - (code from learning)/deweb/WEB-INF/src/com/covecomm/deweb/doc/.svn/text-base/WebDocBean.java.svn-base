package com.covecomm.deweb.doc;
import java.util.*;
import java.sql.*;

/** A class to model information about a web document.
 *
 * Beyond the usual author/title type info kept in the
 * parent DocumentBean, this class includes information
 * like which JSP to use for display, all related
 * article groups, pages to include or forward to, etc.
 */


public class WebDocBean extends DocumentBean implements Comparable {
	private int authorCount;
	private String jspName;
	private int jspId;
	private String realPath;
	private String requireRights="Y";
	private boolean onDisk=false;
	private boolean inDatabase=false;
	private Set articleGroups;
	private String extraInfo;
	private String includePage;
	private String forwardPage;
	private boolean formPage;
	private String dateString;
	private boolean anyValidUser=false;
	private int requiredCredits = 0;
	private java.util.Date startDate = null;
	private java.util.Date endDate = null;
	private boolean groupDoc=false;
	private String author;
	private long sortValue;
/**
 * A do-nothing constructor
 */
public WebDocBean() {
	super();
	articleGroups = new HashSet();
}
/** Add a group to which this article belongs */
public void addArticleGroup(int group) {
	articleGroups.add((new Integer(group)));
}
/** add an author name */
protected void addAuthor(String firstName,String lastName){
	if ((firstName == null) && (lastName == null)) return;
	String s;
	if (firstName == null)
		s = lastName;
	else
		s = firstName + " " + lastName;
	authorCount ++;
	if (authorCount == 1)
		author = s;
	else if (authorCount == 2)
		author = author + " and " + s;
	else
		author = s + ", " + author;
	System.out.println("* AuthorBean.addAuthor: set authors to " + author);
	//

}
/** Function used to sort this document in a list. */
public int compareTo(java.lang.Object o) {
	if (o instanceof WebDocBean) {
		WebDocBean otherLink = (WebDocBean) o;
		long thisValue = getSortValue();
		long otherValue = otherLink.getSortValue();
		return (thisValue < otherValue ? -1 : 1);
	} else {
		System.out.println("compare failed - no instance of object or Values");
		return 1;
	}
}
/** Get the array of article groups */
public Object[] getArticleGroups(){
	return articleGroups.toArray();
}
/** Get the author(s) */
public java.lang.String getAuthor() {
	return author;
}
/**
 * Get a formatted date
 */
public java.lang.String getDateString() {
	return dateString;
}
/**
 * Get the document expiry date.
 */
public java.util.Date getEndDate() {
	return endDate;
}
/** Get the ExtraInfo database field associated with this article.
 *
 * This field is usually used to store the name of a bean
 * that should be created and accompany this document.
 */
public java.lang.String getExtraInfo() {
	return extraInfo;
}
/** Get the page to forward to when this page is requested */
public java.lang.String getForwardPage() {
	return forwardPage;
}
/** Get the page to include with this page. */
public java.lang.String getIncludePage() {
	return includePage;
}
/** Get the unique ID of the JSP used to display this page */
public int getJspId() {
	return jspId;
}
/**
 * Get the name of the JSP this article should
 * use for display
 */
public java.lang.String getJspName() {
	return jspName;
}
/** Get the actual file path of this document */
public java.lang.String getRealPath() {
	return realPath;
}
/** Get the minimum credits this document requires for authorization */

public int getRequiredCredits() {
	return requiredCredits;
}
/** Get the RequireRights (Y/N) value */
public java.lang.String getRequireRights() {
	return requireRights;
}
/** Get the value used for sorting comparisons */
public long getSortValue() {
	return sortValue;
}
/** Get the document's start date */
public java.util.Date getStartDate() {
	return startDate;
}
/** Initialize with values from the database.
 *
 *
 */
public void init(Connection conn, String uri) {
	setInDatabase(false);
	setUri(uri);
	Statement stmt = null;
	try {
		stmt = conn.createStatement();

		// get the article info from the database
		String s = 	"select a.Title,a.JspID,a.RequireRights,a.FileName"
					+ ",a.FilePath,a.ArticleID,a.PublicationDate "
					+ ",a.IncludePage,a.ExtraInfo,a.HasForm,a.AuthorName"
					+ ",n.LastName,n.FirstName,n.AuthorBio"
					+ ",a.Summary "
					+ ",date_format(a.PublicationDate,'%Y-%m-%d') as DateString "
					+ "from Articles a left join "
					+ "AuthorArticle l using (ArticleID) "
					+ "left join Names n on l.AuthorID=n.NameID "
					+ "where a.URI= '"
					+ uri
					+ "'";
		//System.out.println(s);
		ResultSet rs = 	stmt.executeQuery(s);
		if (rs.next()) {
			System.out.println(rs.getString("Title"));
			setTitle(rs.getString("Title"));
			setID(rs.getInt("ArticleID"));
			setExtraInfo(rs.getString("ExtraInfo"));
			setDate(rs.getDate("PublicationDate"));
			setIncludePage(rs.getString("IncludePage"));
			//System.out.println("set include page to " + rs.getString("IncludePage"));
			setSummary(rs.getString("Summary"));
			setDateString(rs.getString("DateString"));
			setJspId(rs.getInt("JspID"));
			setInDatabase(true);
			setRequireRights(rs.getString("RequireRights"));
			String hasForm = rs.getString("HasForm");
			if ((hasForm != null) && hasForm.equals("Y"))
				setFormPage(true);
			setAuthorCount(0);
			// this is a fix for the COL articles, which have an author name
			// stored in a separate field, at least at present
			setAuthor(rs.getString("AuthorName"));
			// add this author, if any, and any additional authors
			addAuthor(rs.getString("FirstName"),rs.getString("LastName"));
			addAuthorBio(rs.getString("AuthorBio"));
			while (rs.next()) {
				addAuthor(rs.getString("FirstName"),rs.getString("LastName"));
				addAuthorBio(rs.getString("AuthorBio"));
			}
		}

	} catch (SQLException e1) {
		System.out.println("getWebDocBean: " + e1);
	} finally {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e2) {
		}

	}
}
/** Get the flag indicating any valid user can read this document.
 */
public boolean isAnyValidUser() {
	return anyValidUser;
}
/** Is this page used to display a form? */
public boolean isFormPage() {
	return formPage;
}
/** Is the document in the database? */
public boolean isInDatabase() {
	return inDatabase;
}
/** Is the document text physically present on disk? */
public boolean isOnDisk() {
	return onDisk;
}
/** Allow any valid user to read this document */
public void setAnyValidUser(boolean newAnyValidUser) {
	anyValidUser = newAnyValidUser;
}
/** Set the author name.*/
public void setAuthor(java.lang.String newAuthor) {
	author = newAuthor;
}
/** Set the publication date string */
public void setDateString(java.lang.String newDateString) {
	dateString = newDateString;
}
public void setEndDate(java.util.Date newEndDate) {
	endDate = newEndDate;
}
public void setExtraInfo(java.lang.String newExtraInfo) {
	extraInfo = newExtraInfo;
}
public void setFormPage(boolean newFormPage) {
	formPage = newFormPage;
}
public void setForwardPage(java.lang.String newForwardPage) {
	forwardPage = newForwardPage;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/01 4:42:49 PM)
 * @param newGroupDoc boolean
 */
public void setGroupDoc(boolean newGroupDoc) {
	groupDoc = newGroupDoc;
}
public void setIncludePage(java.lang.String newIncludePage) {
	includePage = newIncludePage;
}
public void setInDatabase(boolean newInDatabase) {
	inDatabase = newInDatabase;
}
public void setJspId(int newJspId) {
	jspId = newJspId;
}
public void setJspName(java.lang.String newJspName) {
	jspName = newJspName;
}
public void setOnDisk(boolean newOnDisk) {
	onDisk = newOnDisk;
}
public void setRealPath(java.lang.String newRealPath) {
	realPath = newRealPath;
}
public void setRealPath(java.lang.String newRealName,java.lang.String newRealPath) {
	realPath = newRealPath + newRealName;
}
public void setRequiredCredits(int newRequiredCredits) {
	requiredCredits = newRequiredCredits;
}
public void setRequireRights(java.lang.String newRequireRights) {
	requireRights = newRequireRights;
}
public void setSortValue(long newSortValue) {
	sortValue = newSortValue;
}
public void setStartDate(java.util.Date newStartDate) {
	startDate = newStartDate;
}

	private String authorBio;

protected void addAuthorBio(String bio) {
	if (bio != null) {
		if (authorBio == null)
			authorBio = bio;
		else
			authorBio = authorBio.trim() + "<p>" + bio;
	}
}

public java.lang.String getAuthorBio() {
	return authorBio;
}

protected int getAuthorCount() {
	return authorCount;
}

public void setAuthorBio(java.lang.String newAuthorBio) {
	authorBio = newAuthorBio;
}

public void setAuthorCount(int newAuthorCount) {
	authorCount = newAuthorCount;
}

/**
 * Insert the method's description here.
 * Creation date: (4/13/01 4:42:49 PM)
 * @return boolean
 */
public boolean isGroupDoc() {
	return groupDoc;
}
}