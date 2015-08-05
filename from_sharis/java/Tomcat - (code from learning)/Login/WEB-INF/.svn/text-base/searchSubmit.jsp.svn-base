<!--
    This page is responsible for aggregating or collecting the search criteria
        entered on the searchKB.jsp page then forwarding it to another page
        for processing.
    This demonstrates:
        1) Use of the session object for storing session-specific info
        2) Use of the JSP forwarding to forward request processing to another page.
    Request parameters are loaded into a Collection object.
    Processing is forwarded to the pickKB.jsp page
    pickKB.jsp executes and displayes results of the query.
-->
<%@page contentType="text/html"%>
<html>
<head><title>searchSubmit.jsp</title></head>
<body>
<%@page errorPage="ErrorPage.jsp" %>
<jsp:useBean id="searchFacade" class="knowledgebase.searchFacade" scope="page" />
<h1 align="center">Login to Message List</h1>
<!-- Load the search criteria into a collection which is stored in the session object -->
<% searchFacade.handleSubmit(request, session); %>
<!-- Forward to pickKB.jsp which will execute the query and display the result -->
<jsp:forward page="/jsp/pickKB.jsp?type=keyword" />
<br><br><br>
</body>
</html>
