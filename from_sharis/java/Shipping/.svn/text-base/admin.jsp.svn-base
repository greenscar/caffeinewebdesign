<%@page contentType="text/html"%>
<%@ page import="users.Admin" %>
<% 
    logging.Secretary.clearBlanks();
    logging.Secretary.startFxn("admin.jsp START");
    if(session.getAttribute("admin") == null)
    {
        logging.Secretary.write("ADMIN SESSION DOES NOT EXIST!!!!");
    }
%>
<jsp:include page="topMenu.jsp">
    <jsp:param name="dispMenu" value="true"/>
    <jsp:param name="title" value="Admin Main Menu"/>
</jsp:include>
</body>
</html>
<% logging.Secretary.endFxn("admin.jsp END");%>