<%@page contentType="text/html"%>
<% logging.Secretary.startFxn("categoryList.jsp"); %>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
<html>
<head>
    <title>Your Categories</title>
    <style type="text/css">
        <!--
            @import url(templates.css);
        -->
    </style>
    </head>
<body>
<%
    admin.categoryListReset();
if(admin.categoryListHasMore())
{
%>
<h2>Current Categories</h2>
<table class="list">
<tr>
<th>ID</th>
<th>NICK</th>
<th>NAME</th>
</tr>
<%
    while(admin.categoryListHasMore())
    {%>
<jsp:setProperty name="admin" property="nextCategory" value="true"/>
<tr>
<td width="10%">
<jsp:getProperty name="admin" property="categoryID" />
</td>
<td width="10%">
<jsp:getProperty name="admin" property="categoryNick" />
</td>
<td width="80%">
<jsp:getProperty name="admin" property="categoryName" />
</td>
</tr>
<%   }%>
</table>
<%}
else{
%>
<h1>There are currently no categories.</h1>
<%}%>
<input type="button" name="Menu" value="Menu" onclick="location.href='/Shipping/admin'">
</body>
</html>
<% logging.Secretary.endFxn("categoryList.jsp"); %>