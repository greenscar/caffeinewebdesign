<%@page contentType="text/html"%>
<% logging.Secretary.startFxn("categoryMod.jsp"); %>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
<html>
<head><title>JSP Page</title></head>
<body>
    <%
    if(request.getParameter("catID") == null)
    {
        admin.categoryListReset();
        if(admin.categoryListHasMore())
        {
%>
<h2>Current Categories</h2>
<table border="1" width="200">
<tr>
<th>NICK</th>
<th>NAME</th>
</tr>
<%
            while(admin.categoryListHasMore())
            {%>
<jsp:setProperty name="admin" property="nextCategory" value="true"/>
<tr>
<td width="20%">
<jsp:getProperty name="admin" property="categoryNick" />
</td>
<td width="80%">
<a href="CategoryAdmin?do=mod&catID=<jsp:getProperty name="admin" property="categoryID" />">
<jsp:getProperty name="admin" property="categoryName" />
</a>
</td>
<%          } //END while(admin.categoryListHasMore())
            admin.categoryListReset();
%>
</table>
<input type="button" name="Cancel" value="Cancel" onclick="location.href='admin.jsp'">
<%
        } // END if(admin.categoryListHasMore())
    } // END if(request.getParameter("catID") == null)
    else
    {
%>
<jsp:setProperty name="admin" property="categoryViaID" value="<%=request.getParameter("catID")%>" />
<form name="category" method="post" action="CategoryAdmin?do=mod">
<h2>Modify your category info</h2>
<input type="hidden" name="catID" value="<jsp:getProperty name="admin" property="categoryID"/>">
NICK: <input type="text" name="catNick" value="<jsp:getProperty name="admin" property="categoryNick"/>"><br>
NAME: <input type="text" name="catName" value="<jsp:getProperty name="admin" property="categoryName"/>">
<br>
<input type="submit" name="Submit" value="Submit">&nbsp;
<input type="button" name="Cancel" value="Cancel" onclick="location.href='admin'">
</form>
<%
    } // END else
%>
</body>
</html>
<% logging.Secretary.endFxn("categoryMod.jsp"); %>