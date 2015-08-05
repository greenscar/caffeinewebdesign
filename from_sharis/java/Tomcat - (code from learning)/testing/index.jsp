<%@page contentType="text/html"%>
<html>
<head><title>JSP Page</title></head>

<body bgcolor="#777777" onLoad="document.loginForm.username.focus()">
  <form name="loginForm" method="post" action="DateDemo">
   <TABLE WIDTH="250" BORDER="0" CELLPADDING="0" ALIGN="CENTER">
   <TR VALIGN="TOP"><TD>
    <table WIDTH="250" border="1" cellspacing="0" cellpadding="0" align="center">
     <tr>
      <td><div align="right">User Name: </div></td>
      <td><input type="text" name="userName"></td>
     </tr>
     <tr>
      <td><div align="right">Password: </div></td>
      <td><input type="password" name="password"></td>
     </tr>
     <tr>
      <td colspan="2" align="center"><br><input type="Submit" name="Submit" Value="Log In"></td>
     </tr>
    </table>
   </TD></TR>
  </TABLE> 
  </form>
</body>
</html>


