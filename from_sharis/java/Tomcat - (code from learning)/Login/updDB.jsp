<%@page contentType="text/html"%>
<html>
<head><title>JSP Page</title></head>
<body bgcolor="#FFFFFF">
    <%@ page errorPage="ErrorPage.jsp" %>
    <jsp:useBean id="KBFacade" class="knowledgeBase.KnowledgeBaseFacade" scope="page" />
    <br>
    <font face="Helvetica, Sans-serif" size="+2">
    <h1 align="center">Update Completed</h1>
    </font>
    <br><br>
    <% KBFacade.doUpdate(request, session); %>
    <center>
        <font face="Helvetica, Sans-serif" size="+2" color="blue">
            <br>Updated <jsp:getProperty name="KBFacade" property="rowsUpdated" /> Rows.
            <br><br><br>
            <a href="pickKB.jsp?type=all">Return to Message List </a>
            <br>
            <a href="menu.html">Return to Main Menu</a>
        </font>
    </center>
</body>
</html>
