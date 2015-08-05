<%@page isErrorPage="true" %>
<html>
<head><title>JSP Page</title></head>
<body bgcolor="#FFFFFF">
<br><br>
<h1 align="center">Error</h1>
<br><br><br><br><br>
<font face="helvetica, sans-serif" size="+3" color="red">
    <p>Error reported: <% exception.getMessage(); %></p>
    <p>Exception: <%= exception %></p>
</font>
<center>
    <a href="menu.html">Return to Main Menu</a>
</center>
<br><br><br>
<%@include file="footer.txt" %>
</body>
</html>
