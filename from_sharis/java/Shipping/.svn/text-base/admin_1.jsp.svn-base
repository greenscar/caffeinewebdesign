<%@page contentType="text/html"%>
<% 
logging.Secretary.clearBlanks();
logging.Secretary.startFxn("admin.jsp START");
%>
<html>
<head><title>JSP Page</title></head>
<body>
<%@ page import="users.Admin" %>
<%
    if(session.getAttribute("admin") == null)
    {
        logging.Secretary.write("ADMIN SESSION DOES NOT EXIST!!!! ERROR!!!!!");
        //Shipping/admin admin = new Admin();
        //session.setAttribute("admin", admin);
    }
%>
<h3>Category Admin</h3>
<a href="CategoryAdmin?do=create">Create a Category</a><br>
<a href="CategoryAdmin?do=mod">Modify a Category</a><br>
<a href="CategoryAdmin?do=disp">Display Categories</a><br>
<!--<a href="CategoryAdmin?do=del">Delete a Category</a><br>-->

<h3>SubCategory Admin</h3>
<a href="SubCategoryAdmin?do=create">Create a SubCategory</a><br>
<a href="SubCategoryAdmin?do=mod">Modify a SubCategory</a><br>
<a href="SubCategoryAdmin?do=disp">Display SubCategories</a>
<!--<a href="SubCategoryAdmin?do=del">Delete a SubCategory</a><br>-->

<h3>Item Admin</h3>
<%--<a href="ItemAdmin?do=create">Create an Item</a><br>--%>
<a href="ItemAdmin?do=mod">Modify an Item</a><br>
<%--<a href="ItemAdmin?do=mod&step=display&who=2&subCat=15&cat=5">Modify Item</a><br>--%>
<a href="ItemAdmin?do=disp">Display Items</a><br>

<h3>Order Admin</h3>
<a href="Order">Goto Order Page</a>
</body>
</html>
<% logging.Secretary.endFxn("admin.jsp END");%>