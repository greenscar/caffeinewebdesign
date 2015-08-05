<%@page contentType="text/html"%>
<% logging.Secretary.startFxn("subCategoryList.jsp"); %>
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
<!--<div id="centercontent">-->
<h1>Current Categories & SubCategories</h1>
<table class="list">
<tr>
<th width="10%">CAT ID</th>
<th width="30%">CAT NAME</th>
<th width="60%">SUBCATEGORIES</th>
</tr>
<%
    while(admin.categoryListHasMore())
    {%>
<jsp:setProperty name="admin" property="nextCategory" value="true"/>
<tr>
<td>
<jsp:getProperty name="admin" property="categoryNick" />
</td>
<td>
<jsp:getProperty name="admin" property="categoryName" />
</td>
<td>
    <table class="list">
        <tr>
            <th width="60%">Name</th>
        </tr>
<%
        while(admin.subCategoryListHasMore())
        {
        %>
        <jsp:setProperty name="admin" property="nextSubCategory" value="true" />
        <tr>
            <td align="center"><jsp:getProperty name="admin" property="subCategoryName" /></td>
        </tr>
        <%
        }
        %>
    </table>
</td>
</tr>
<%   }
%>
</table>
<!--</div>-->
<%}
else{
%>
<h1>There are currently no categories.</h1>
<%}%>
<input type="button" name="Menu" value="Menu" onclick="location.href='/Shipping/admin'">
</body>
</html>
<% logging.Secretary.endFxn("subCategoryList.jsp"); %>