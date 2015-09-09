<%@page contentType="text/html"%>
<% logging.Secretary.write("categoryMod.jsp"); %>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
<html>
<head><title>Delete A Category</title></head>
<body>
<%
    admin.categoryListLoad();
if(admin.categoryListHasMore())
{
%>
<h2>Current Categories</h2>
<table border="1" width="200">
<tr>
<th>ID</th>
<th>NAME</th>
</tr>
<%
    while(admin.categoryListHasMore())
    {%>
<jsp:setProperty name="admin" property="nextCategory" value="true"/>
<tr>
<td width="20%">
<jsp:getProperty name="admin" property="categoryID" />
</td>
<td width="80%">
<a href="CategoryAdmin?do=del&id=<jsp:getProperty name="admin" property="categoryID" />">
<jsp:getProperty name="admin" property="categoryName" />
</a>
</td>
<%   }%>
</table>
<%}
else{
%>
<h1>There are currently no categories.</h1>
<%}%>
<input type="button" name="Cancel" value="Cancel" onclick="location.href='/Shipping/admin'">
</body>
</html>
