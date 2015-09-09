<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>DEWEB Demo Application</title>
</head>

<body bgcolor="#FFFFFF">
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/deweb.tld" prefix="deweb" %>
<table border="0" cellpadding="6" cellspacing="0" width="650">
  <tr>
    <td width="100%">
      <p align="center"><img border="0" src="images/title.gif"></td>
  </tr>
</table>
<table border="0" cellpadding="6" cellspacing="0" width="650">
  <tr>
    <td width="142">
    <jsp:include page="sidebar.jsp" flush="true">
      </td>
    <td width="480">
    <!--
      <deweb:currentArticles articlesBean="current">
       <deweb:iterate collection="current" iteratedItemName="menuItem" iteratedItemClass="com.covecomm.deweb.doc.HttpLinkBean">
     <p align="left"><b><jsp:getProperty name="menuItem" property="formattedLink"/></b><br><jsp:getProperty name="menuItem" property="summary"/>
     <br><font size="1"><i>Posted <jsp:getProperty name="menuItem" property="formattedDate"/></i></font>
       </deweb:iterate>
      </deweb:currentArticles>          
      -->
    
    </td>
  </tr>
</table>
<table border="0" cellpadding="6" cellspacing="0" width="650">
  <tr>
    <td width="100%"><jsp:include page="footer.htm" flush="true"/></td>
  </tr>
</table>
</body>
