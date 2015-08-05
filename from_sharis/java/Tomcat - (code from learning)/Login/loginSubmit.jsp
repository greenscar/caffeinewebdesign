<!--
    This page takes post info from login.jsp and uses the loginFacade class
        to process the input information. 
    The handleSubmit method is passed both the request object and the session
        object.
-->
<%@page contentType="text/html"%>
<html>
<head><title>JSP Page</title></head>
<body bgcolor="#FFFFFF">
    <%@ page errorPage="ErrorPage.jsp" %>
    <jsp:useBean id="loginFacade" class="facades.LoginFacade" scope="page" />
    <center>
        <h1>Login to Message List</h1>
    </center>
    <% loginFacade.processLogin(request, session); %>
    <br><br>
    <b>Login Completed for user: </b>
    <%= session.getAttribute("firstName") %>
    &nbsp;&nbsp;
    <jsp:getProperty name="loginFacade" property="lastName" />
    <br>
    <b>Last logged in:</b>
    <jsp:getProperty name="loginFacade" property="lastLogin" />
    <br><br><br>

    <a href="menu.jsp">To Menu</a>
</body>
</html>
