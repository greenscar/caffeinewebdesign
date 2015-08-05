<html>
<head><title>JSP Page</title></head>
<body>
<h1 align="center">Enter Search Criterian</h1>
<br><br><br><br>
<table border="0" width="100%">
<form method="post" action="searchSubmit.jsp">
<tr>
    <td width="10%"> &nsbp; </td>
    <td width="10%">Keyword 1: </td>
    <td><input name="keyword1" type="text" value=""></td>
</tr>
<tr>
    <td width="10%"> &nsbp; </td>
    <td width="10%">Keyword 2: </td>
    <td><input name="keyword2" type="text" value=""></td>
</tr>
<tr>
    <td width="10%"> &nsbp; </td>
    <td width="10%">Keyword 3: </td>
    <td><input name="keyword3" type="text" value=""></td>
</tr>
<tr>
    <td width="10%"> &nsbp; </td>
    <td width="10%">Keyword 4: </td>
    <td><input name="keyword4" type="text" value=""></td>
</tr>
<tr>
    <td width="10%">&nbsp; </td>
    <td width="10%"><input name="submit" type="submit" value="submit Search"></td>
</tr>
</form>
</table>
<br><br><br><br><br>
<%@ include file="footer.txt" %>
</body>
</html>
