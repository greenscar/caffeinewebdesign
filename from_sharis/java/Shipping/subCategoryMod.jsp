<%@page contentType="text/html"%>
<% logging.Secretary.startFxn("subCategoryMod.jsp"); %>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
<html>
<head><title>Subcategory Modification</title></head>
<body>
<h2>Current Categories & SubCategories</h2>
<table border="1" width="700">
<tr>
<th width="5%">ID</th>
<th width="30%">NAME</th>
<th width="65%">SUBCATEGORIES</th>
</tr>
<%
    admin.categoryListReset();
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
<%
        admin.subCategoryListReset();
        while(admin.subCategoryListHasMore())
        {
            boolean subCatNull = true;
            if(request.getParameter("subCatID") != null)
                subCatNull = false;
        %>
        <jsp:setProperty name="admin" property="nextSubCategory" value="true" />
        <tr>
            <td align="center">
            <% if(subCatNull){%>
                <a href="SubCategoryAdmin?do=mod&catID=<jsp:getProperty name="admin" property="categoryID"/>&subCatID=<jsp:getProperty name="admin" property="subCategoryID" />">
            <%}%>
                    <jsp:getProperty name="admin" property="subCategoryName" />
            <%if(subCatNull){%></a><%}%>
            </td>
        </tr>
        <%} //END while(admin.subCategoryListHasMore()) %>
    </table>
</td>
</tr>
<%   }// END while(admin.categoryListHasMore())
admin.categoryListReset();
%>
<tr><td align="center" colspan="3">
<input type="button" name="Cancel" value="Cancel" onclick="location.href='admin'">
</td></tr>
</table>

<%
    if(request.getParameter("subCatID") != null){
%>
<form name="category" method="post" action="SubCategoryAdmin?do=mod">
<h2>Enter your subcategory info</h2>
SUBCATEGORY TO: 
<select name="newCatID">
<%
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {%>
<jsp:setProperty name="admin" property="nextCategory" value="true"/>

<option value="<jsp:getProperty name="admin" property="categoryID" />"<%
if(request.getParameter("catID").equalsIgnoreCase(admin.getCategoryID()))
{%> selected<%}%>><jsp:getProperty name="admin" property="categoryName" />
<%   }
admin.categoryListReset();
// Load this subCat into the Admin's memory.
admin.setCategoryViaID(request.getParameter("catID"));
admin.setSubCategoryViaID(request.getParameter("subCatID"));
%>
</select><br>
<input type="hidden" name="oldCatID" value="<%=admin.getCategoryID()%>">
<input type="hidden" name="subCatID" value="<%=admin.getSubCategoryID()%>">
NAME: <input type="text" name="subCatName" value="<%=admin.getSubCategoryName()%>"><br>
<input type="submit" name="Submit" value="Process">
<input type="button" name="Cancel" value="Cancel" onclick="location.href='admin'">
</form>
<%
}
%>
</body>
</html>
<% logging.Secretary.endFxn("subCategoryMod.jsp"); %>