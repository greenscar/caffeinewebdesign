<%@page contentType="text/html"%>
<html>
<head><title>Message</title></head>
<body>
<center>
<!--
    Load the current message document.
-->
<%@page import="java.util.*" %>
<%@page errorPage="ErrorPage.jsp" %>
<jsp:useBean id="KBFacade" class="knowledgebase.KnowledgeBaseFacade" scope="page" />
<!-- Set the doc_key first, before setting 'action' -->
<% KBFacade.setDoc_key(Integer.parseInt(request.getParameter("doc_key").trim())); %>
<% KBFacade.setAction(request.getParameter("action")); %>
<br><br>
<H1>Message Display</H1>
</center>
<!--
    Describe the output of an HTML table using jsp:getProperty to
        retrieve the appropriate values for the document being viewed.
-->
<table border="0" cellpadding="2">
    <tr>
        <td width="5%"><br></td>
        <td width="5%" bgcolor="#E0E0E0"> Document Key: </td>
        <td width="30%" bgcolor="#C0C0C0"><jsp:getProperty name="KBFacade" property="doc_key" /> &nbsp; </td>
        <td width="5%" bgcolor="#E0E0E0"> Category: </td>
        <td width="30%" bgcolor="#C0C0C0"><jsp:getProperty name="KBFacade" property="category" /> &nbsp; </td>
    </tr>
    <tr>
        <td width="5%"><br></td>
        <td width="5%" bgcolor="#E0E0E0"> Name: </td>
        <td width="30%" bgcolor="#C0C0C0"><jsp:getProperty name="KBFacade" property="doc_name" /> &nbsp; </td>
        <td width="5%" bgcolor="#E0E0E0"> Posted by: </td>
        <td width="30%" bgcolor="#C0C0C0"><jsp:getProperty name="KBFacade" property="post_user" /> &nbsp; </td>
    </tr>
</table>
<!--
    Display the menu of options. 
    NOTE: The permissions of a user are not checked until the user trys to load
            one of these pages. If the user does not have permission to access a 
            page he tried to load, an exception is thrown and the page is not loaded.
-->
<font color="white" family="times roman">
    <p>
    <a href="inputKB.jsp?doc_key=0&action=insert&base_doc_key=<jsp:getProperty name="KBFacade" property="base_doc_key"/>&link_doc=<jsp:getProperty name="KBFacade" property="doc_key" />">Add to Thread</a> &nbsp;&nbsp;
    <a href="updDB.jsp?doc_key=<jsp:getProperty name="KBFacade" property="doc_key" />&action=delete">Delete</a> &nsbp;&nbsp;
    <a href="inputKB.jsp?doc_key=<jsp:getProperty name="KBFacade" property="doc_key" />&base_doc_key=<jsp:getProperty name="KBFacade" property="base_doc_key" />&link_doc=0&action=update">Update</a>&nbsp;&nbsp;
    <a href="pickKB.jsp?type=all">Message List</a>&nbsp;&nbsp;
    <a href="menu.html">Main Menu</a></p>
</font>
</body>
</html>
