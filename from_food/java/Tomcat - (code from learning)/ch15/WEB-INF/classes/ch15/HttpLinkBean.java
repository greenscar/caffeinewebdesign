package ch15;

import java.sql.*;
import java.text.SimpleDateFormat;

/** A Bean which can display a document link with summary. 
 *
 * Like DocumentBean, this bean doesn't hold the entire contents
 * of a document, just everything else (title, summary, author(s),
 * etc). HttpLinkBean adds all the HTTP code to this information. 
 */

 
public class HttpLinkBean extends DocumentBean implements Comparable{
	private String link;
	private long sortValue;
	private int sequence;
	private boolean descending = true;
	private String bookmark=null;
	private String style=""; // "color: 21007b";
/**
 * Default constructor.
 */
public HttpLinkBean() {
	super();
}
/** Create an instance with a link, uri, name, text, pub date.
 *
 * The descending flag indicates whether this bean will be sorted
 * in descending or ascending date/time order.
 */
public HttpLinkBean(String link,String uri,
	String name,String text, Date pubdate, 
	Time time,boolean descending) {
	super(uri,name,text,pubdate);
	System.out.println("HttpLinkBean link: " + link);
	setLink(link);
	setSortValue(pubdate);
	setDescending(descending);
}
/** This method is required by the Comparable interface.
 *
 * When you add HttpLinkBean instances to HttpLinksBean
 * they are sorted according to the results of this method.
 */	
public int compareTo(java.lang.Object o) {
	if (o instanceof HttpLinkBean) {
		HttpLinkBean otherLink = (HttpLinkBean) o;
		long thisValue = getSortValue();
		long otherValue = otherLink.getSortValue();
			if (descending)
				return (thisValue < otherValue ? 1 : -1);
			else
				return (thisValue < otherValue ? -1 : 1);
	} else 
		System.out.println("compare failed - no instance of object or Values");
	return 1;
}
/**
 * Get the article's HTML bookmark.
 */
public java.lang.String getBookmark() {
	return bookmark;
}
/** Get a formatted date string */
public String getFormattedDate() {
	SimpleDateFormat df = new SimpleDateFormat("EEEEEEE, MMMMMMMM dd, yyyy");
	return df.format(getDate());
}
/**
 * Get the formatted HTML link
 */
public java.lang.String getFormattedLink() {
	StringBuffer s = new StringBuffer("<a ");
	if ((style != null) && (!style.equals("")))
		s.append("style=\"" + style.trim() + "\"");
	s.append("href=\"" + link + "\"");
	if ((bookmark != null) && (!bookmark.equals("")))
		s.append("name=\"" + bookmark.trim() + "\"");
	s.append(">" + getTitle() + "</a>");
	return s.toString();
	
	
}
/**
 * Get the unformatted link.
 */
public java.lang.String getLink() {
	return link;
}
/**
 * Get the value compareTo will use when sorting.
 */
public long getSortValue() {
	return sortValue;
}
/**
 * Get the link's HTML style.
 */
public java.lang.String getStyle() {
	return style;
}
/**
 * Get the descending flag value.
 */
boolean isDescending() {
	return descending;
}
/**
 * Set the bookmark to use for this link.
 */
public void setBookmark(java.lang.String newBookmark) {
	bookmark = newBookmark;
}
/**
 * Set the descending flag value. This 
 * indicates whether this bean will be sorted
 * in descending or ascending date/time order.
 */
public void setDescending(boolean newDescending) {
	descending = newDescending;
}
/**
 * Set the link
 */
public void setLink(java.lang.String newLink) {
	link = newLink;
}
/**
 * Set the sort value.
 */
public void setSortValue(java.util.Date newSortValue) {
	sortValue = newSortValue.getTime();
}
/**
 * Set the HTML style.
 */
public void setStyle(java.lang.String newStyle) {
	style = newStyle;
}
/** Display a string representation */
	public String toString(){
		return (getLink() + "," + getSortValue() + ","+ super.toString());
		}
}