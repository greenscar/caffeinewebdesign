
<jsp:useBean id="admin" scope="session" type="users.User" />
<%
/**********************************************************************
 * USER IS ADMIN - DISPLAY STORE LIST TO SELECT
 *********************************************************************/
// DISPLAY LIST OF ALL STORES FOR ORDERER TO SELECT FROM
%>
<jsp:include page="topMenu.jsp">
    <jsp:param name="dispMenu" value="false"/>
    <jsp:param name="title" value="Enter your order information"/>
</jsp:include>
<h1 class="pageTitle">Enter your order information</h1>
<div id="body" class="mmBody">
<table class="orders">
        <tr>
            <td class="title">
                Order Information
            </td>
        </tr>
        <form name="recipientList" method="post" action="Order" onsubmit="return startOrder()">
        <tr>
            <td class="subTitle">
                Please enter your name
            </td>
        </tr>
        <tr>
            <td>
                <table align="left"><tr><td>
                <input type="text" name="contact" value="Corp" size="50">
                </td></tr></table>
            </td>
        </tr>
        <tr>
            <td class="subTitle">
                Please select your order recipients
            </td>
        </tr>
        <tr>
            <td>
                <table align="left"><tr><td>
                <div class="centerHoriz">
                        <span  class="centerVert">
                            <select class="siteList" multiple name="possibleRecipients">
                            <%
                                while(admin.siteListHasMore())
                                {
                                    %><jsp:setProperty name="admin" property="nextSite" value="true" /><option value="<jsp:getProperty name="admin" property="siteId" />"><jsp:getProperty name="admin" property="siteId" /> => <jsp:getProperty name="admin" property="siteName" /></option>
                                    <%
                                }
                            %>
                            </select>
                        </span>
                        <span class="centerVert">
                            <input type="button" name="<<" value="<<" onclick="delRecipients(this)"></input>
                            <input type="button" name=">>" value=">>" onclick="addRecipients(this)"></input>
                        </span>
                        <span   class="centerVert">
                            <select class="siteList" multiple name="recipients">
                            </select>
                        </span>
                        <br>
                        <input type="hidden" name="do" value="processRecipients">
                        <input type="submit" name="submit" value="Start Order">
                        <input type="button" name="cancel" value="Cancel" onClick="window.close();">
                </div>
                </td></tr></table>
            </td>
        </tr>
        </form>
<%
/**********************************************************************
 * END USER IS ADMIN - DISPLAY STORE LIST TO SELECT
 *********************************************************************/
%>