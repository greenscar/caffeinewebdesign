<%@page contentType="text/html"%>
<% logging.Secretary.startFxn("subCategoryCreate.jsp");%>
<html>
<head><title>Create your Sub Category</title></head>
<body>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
<%
    admin.categoryListReset();
if(admin.categoryListHasMore())
{
%>
<h2>Current Categories & SubCategories</h2>
<table border="1" width="700">
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
    <table width="100%">
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
<%}%>
<form name="category" method="post" action="SubCategoryAdmin?do=create">
<h2>Enter your subcategory info</h2>
SUBCATEGORY TO: 
<select name="catID">
<%
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {%>
<jsp:setProperty name="admin" property="nextCategory" value="true"/>
<option value="<jsp:getProperty name="admin" property="categoryID" />"><jsp:getProperty name="admin" property="categoryName" />
<%   }
admin.categoryListReset();
%>
</select><br>

NAME: <input type="text" name="subCatName"><br>
<input type="submit" name="Submit" value="Create">
<input type="button" name="Cancel" value="Cancel" onclick="location.href='/Shipping/admin'">
</form>
</body>
</html>
<% logging.Secretary.endFxn("subCategoryCreate.jsp");%>