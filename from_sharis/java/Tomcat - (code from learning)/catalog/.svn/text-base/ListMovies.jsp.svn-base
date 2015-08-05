<%@ page import="java.util.*" %>
<html>
<head>
<title>Movie List</title>
</head>

<body bgcolor="#FFFFFF">
<table width="100%" border="0">
  <tr> 
    <td colspan="2"><%@ include file="titlebar.jsp" %></td>
  </tr>
  <tr> 
    <td width="75" valign="top"><%@ include file="navigation.jsp" %></td>
    <td valign="top">
	<table cellspacing="0" cellpadding="2" border="0" width="600">
      <%
	Object[] movies = (Object[])request.getAttribute("movies");

	
	for ( int x = 0; x < movies.length; x++ ) {

		HashMap movie = (HashMap)movies[x];

		out.println("<tr><td>" + movie.get("title_name") + "</td>" + 
		    "<td>" + movie.get("price") + "</td>" +
		    "<td>" + movie.get("quantity") + 
		    "<td><a href=/catalog/servlet/Controller?service=AddToCart" +
		    "&title_id=" + movie.get("title_id") +
		    "&target=/ListShoppingCart.jsp>Buy</a></td></tr>");
	}
	%>
	</table>
    </td>
  </tr>
</table>
</body>
</html>