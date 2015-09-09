<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>MightyWords Demo Application</title>
</head>

<body bgcolor="#FFFFFF">
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/mightywords.tld" prefix="mw" %>
<table border="1" cellpadding="4" cellspacing="0" width="500" bordercolor="#FFFFFF">
  <tr>
    <td bgcolor="#C0C0C0">First Name</td>
    <td bgcolor="#C0C0C0">Last Name</td>
  </tr>
 <mw:iterate collection="names" iteratedItemName="name" iteratedItemClass="mightywords.NameBean">
  <tr>
    <td><jsp:getProperty name="name" property="firstName"/></td>
    <td><jsp:getProperty name="name" property="lastName"/></td>
  </tr>
 </mw:iterate>
</table>
</body>
