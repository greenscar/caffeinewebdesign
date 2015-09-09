<html>
<head><title>JSP Page</title></head>
<body>
<%@ page errorPage="ErrorPage.jsp" %>
<jsp:useBean id="KBFacade" class="knowledgeBase.KnowledgeBaseFacade" scope="page" />
<br>
<font face="Helvetica, Sans-serif" size="+2">
    <center>Messages</center>
</font>
<br><br>
<center>
<font face="Helvetica, Sans-serif" size="+2" color="blue">
<%KBFacade.setFilterSelection(request, session);%>
<!--Build table for output of selected messages -->
  <table border="2" cellpadding="2" bgcolor="white">
    <tr>
        <td bgcolor="#C0D9D9"><b>Message List</b></td>
        <td bgcolor="#C0D9D9">&nbsp;</td>
        <td bgcolor="#C0D9D9">&nbsp;</td>
        <td bgcolor="#C0D9D9">&nbsp;</td>
        <td bgcolor="#C0D9D9">&nbsp;</td>
        <td bgcolor="#C0D9D9">&nbsp;</td>
        <td bgcolor="#C0D9D9">&nbsp;</td>
        <td bgcolor="#C0D9D9">&nbsp;</td>
    </tr>
    <!-- HEADER ROW -->
    <tr>
        <th bgcolor="#E0E0E0"><b>Title</b></th>
        <th bgcolor="#E0E0E0"><b>Type</b></th>
        <th bgcolor="#E0E0E0"><b>Category</b></th>
        <th bgcolor="#E0E0E0"><b>Keywords</b></th>
        <th bgcolor="#E0E0E0"><b>Actions</b></th>
        <th bgcolor="#E0E0E0"><b>&nbsp;</b></th>
        <th bgcolor="#E0E0E0"><b>&nbsp;</b></th>
        <th bgcolor="#E0E0E0"><b>&nbsp;</b></th>
        <th bgcolor="#E0E0E0"><b>&nbsp;</b></th>
    </tr>
    <!--COLUMNS-->
    <!-- Use program logic to indent threaded messages -->
    <%
        //Start of loop
        int currBase_doc_key = 0;
        boolean firstLoop = true;
        while(KBFacade.kbRecsHasMore()){
         %>
            <jsp:setProperty name="KBFacade" property="nextKBVO" value="true" />
            <tr>
                <td>
                    <!--
                        Each entry has a base_doc_key indicating which document is tbe
                        base of it. If someone posts a reply to a message, the reply is
                        given the base_doc_key of the original post.
                    -->
                    <% if(!firstLoop && (currBase_doc_key == KBFacade.getBase_doc_key())){ %>
                        <!--Indent if this is a thread off the base message -->
                        &nbsp;&nbsp;&nbsp;
                        <img src="/JavaWeb/img/quote.gif">&nbsp;
                    <% } %>
                    <jsp:getProperty name="KBFacade" property="doc_name" />
                </td>
                <td>
                    <jsp:getProperty name="KBFacade" property="category" />
                </td>
                <td>
                    <jsp:getProperty name="KBFacade" property="keyword1" />
                    &nbsp;&nbsp;
                    <jsp:getProperty name="KBFacade" property="keyword2" />
                    &nbsp;&nbsp;
                    <jsp:getProperty name="KBFacade" property="keyword3" />
                    &nbsp;&nbsp;
                    <jsp:getProperty name="KBFacade" property="keyword4" />
                </td>
                <!--ACTIONS-->
                <td><a href="inputKB.jsp?doc_key=0&action=insert&base_doc_key=<jsp:getProperty name="KBFacade" property="base_doc_key" />&link_doc=<jsp:getProperty name="KBFacade" property="doc_key" />">Add to Thread</a></td>
                <td><a href="viewKB.jsp?doc_key=<jsp:getProperty name="KBFacade" property="doc_key" />&action=update" >View</a></td>
                <td><a href="inputKB.jsp?doc_key=<jsp:getProperty name="KBFacade" property="doc_key" />&link_doc=0&base_doc_key=<jsp:getProperty name="KBFacade" property="base_doc_key" />&action=update">Update</a></td>
                <td><a href="viewKB.jsp?doc_key=<jsp:getProperty name="KBFacade" property="doc_key" />&base_doc_key=0&link_doc=0&action-delete">Delete</a></td>
            </tr>
         <%
            firstLoop = false;
            if(KBFacade.getBase_doc_key() > 0)
                currBase_doc_key = KBFacade.getBase_doc_key();
            else
                currBase_doc_key = KBFacade.getDoc_key(); //this IS the base
        }
    %>
  </table>
  <br><br>
  <a href="inputKB.jsp?doc_key=0&link_doc=0&base_doc_key=0&action=insert">
  <br><a href="menu.html">Main Menu</a>
  </font>
  </center>
</body>
</html>
