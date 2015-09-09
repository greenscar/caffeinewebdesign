<HTML>
  <HEAD>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <TITLE>An Online Magazine</TITLE>
  </HEAD>
  <BODY bgcolor="FFFFFF">
  <%@page language="java" %>
  <%@taglib uri="/WEB-INF/ch15.tld" prefix="ch15" %>

    <table border="0" width="525">
        <tr>
            <td width="525" align="center" colspan="2">
                <h2>Books, Free or Cheaper!</h2>
            </td>
        </tr>
        <tr>
            <td width="120" align="center" valign="middle" height="22" bgcolor="#CCCCCC" colspan="2">
                <!--Menu goes here-->
                <ch15:menu menuName="leftsidemenu">
                    <ch15:iterate collection="leftsidemenu" 
                    iteratedItemName="menuItem" 
                    iteratedItemClass="ch15.HttpLinkBean">
                        <jsp:getProperty name="menuItem" property="link"/>&nbsp;&nbsp;&nbsp;
                    </ch15:iterate>
                </ch15:menu>
            </td>
        </tr>
        <tr>
            <td width="120" valign="top" align="left" height="32" bgcolor="#CCCCCC">
                <table border="0" cellpadding="0" width="100%" cellspacing="8">
                    <tr>
                        <td width="100">
                            <ch15:menu menuName="leftsidemenu"
                            iteratedItemName="menuItem"
                            iteratedItemClass="ch15.HttpLinkBean">
                                <p align="left"><font size="2">
                                    <jsp:getProperty name="menuItem" property="link" />
                                </font></p>
                            </ch15:menu>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="405" height="32" valign="top" align="left">
                <table border="0" cellpadding="0" width="100%" cellspacing="8">
                    <tr> 
                        <td width="100%">
                            <h3 align="center">Featured this week</H3>h3>
                            <ch15:menu menuName="current">
                                <ch15:iterate collection="current"
                                iteratedItemName="menuItem"
                                iteratedItemClass="ch15:HttpLinkBean">
                                    <p align="left"><b>
                                        <jsp:getProperty name="menuItem" property="link" />
                                    </b><br>
                                    <jsp:getProperty name="menuItem" property="text" />
                                </ch15:iterate>
                            </ch15:menu>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td width="525" valign="middle" align="center" height="32" colspan="2">
            </td>
        </tr>
  </BODY>
</HTML>
