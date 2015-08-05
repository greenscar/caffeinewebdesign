<!--
    This page submits its contents to loginSubmit.jsp which is responsible for
        processing the login information passed to it.
-->
<%@page contentType="text/html"%>
<html>
<head><title>JSP Page</title></head>
<body bgcolor="#FFFFFF">
    <jsp:useBean id="cal" class="aCalendar.Cal" scope="page" />
    <center>
        <h1>Please login below.</h1>
    </center>
    <br><br>
    <b>Logging in on:</b>
    <font color="green">
        <jsp:getProperty name="cal" property="currentDateNums" />
    </font>
    <br><br><br>
    <TABLE BORDER="4" CELLPADDING="4" ALIGN="CENTER" HEIGHT="115">
        <form method="post" action="loginSubmit.jsp">
            <TR>
                    <TD>				
                    <TABLE BORDER="0" ALIGN="CENTER" HEIGHT="115">
                            <TR>						
                                    <TD ALIGN="right">Name:</TD>
                                    <TD><INPUT TYPE="text" NAME="login" VALUE="jsandlin"></TD>
                            </TR>
                            <TR>
                                    <TD ALIGN="right">Password:</TD>
                                    <TD><INPUT TYPE="password" NAME="password" VALUE="6669"></TD>					
                            </TR>					
                            <TR>
                                    <TD ALIGN = "CENTER" colspan="2">
                                            <INPUT TYPE="submit" NAME="Submit" VALUE="Login">
                                            <INPUT TYPE="reset" NAME="Reset" VALUE="Reset">
                                    </TD>					
                            </TR>				
                    </TABLE>
                    </TD>
            </TR>		
        </form>
    </TABLE>	
</body>
</html>