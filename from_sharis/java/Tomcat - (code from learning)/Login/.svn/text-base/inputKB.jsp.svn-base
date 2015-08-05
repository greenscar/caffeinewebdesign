<%@page contentType="text/html"%>
<html>
<head><title>JSP Page</title></head>
<body>
<!-- java.util.* contains Collections interface used in scriplets on this page. -->
<%@ page import="java.util.*" %>
<!-- The error page to be used if an exception is caught -->
<%@ page errorPage="ErrorPage.jsp" %>
<!-- Associate KBFacade with an instance of KnowledgeBaseFacade JavaBean -->
<jsp:useBean id="KBFacade" class="knowledgebase.KnowledgeBaseFacade" scope="page" />
<!-- 
    Call KnowledgeBaseFacade.processParameters to perform the work
        necessary for the page to be output with the HTML form.
    The 'action' parameter indicates whether or not this is an insert, update, 
        or delete operation
-->
<% KBFacade.processParameters(request, session); %>
<title>Knowledge Base</title>
<br><br>
<H1 align="center">Message Update</H1>
<table border="0" width="100%">
    <form method="post" action="updDB.jsp">
        <tr>
            <td width="10%"><br></td>
            <input name="action" type="hidden" value="<jsp:getProperty name="KBFacade" property="action" />">
            <input name="link_doc" type="hidden" value="<jsp:getProperty name="KBFacade" property="link_doc" />">
            <td width="10%">Category:</td>
            <!--Display a list of Categories, which must be created dynamically-->
            <td width="20%">
                <!-- CREATE A CATEGORY LIST BOX -->
                <select name="category"
                    <%
                        //Iterate through category list to create a listbox of categories.
                        String category = null
                        //Retrieve the contents of the message_categories table
                        Iterator i = KBFacade.getCategoryList();
                        //Loop through the Iterator converting each to a String and printing them.
                        while(i.hasNext()){
                            category = KBFacade.makeCategoryString(i.next());
                            if(KBFacade.isDefaultCategory(category)){
                    %>
                                <option selected> <%= category %>
                    <% 
                            }
                            else{
                    %>
                                <option> <%= category %>
                    <%
                            }
                        }
                    %>
                </select>
                <!-- END CREATE A CATEGORY LIST BOX -->
            </td>
            <!--END Display a list of Categories, which must be created dynamically-->
            <!--Show a series of input fields-->
            <td width="5%"> Entry Date: </td>
            <td width="20%">
                <font face="helvetica, sans-serif" color="green">
                    <jsp:getProperty name="KBFacade" property="entry_date" />
                </font>
            </td>
        </tr>
        <tr>
            <td width="10%"><br></td>
            <td width="15%"> Short Description: </td>
            <td width="20%"> 
                <input name="doc_name" type="text" value="<jsp:getProperty name="KBFacade" property="doc_name" />">
            </td>
            <td width="5%"> Posted by: </td>
            <td width="20%">
                <input name="post_user" type="text" value="<jsp:getProperty name="KBFacade" property="post_user" />">
            </td>
        </tr>
        <tr>
            <td width="10%"><br></td>
            <td width="15%"> Location: </td>
            <td width="20%"> 
                <input name="doc_location" type="text" value="<jsp:getProperty name="KBFacade" property="doc_location" />">
            </td>
            <td width="5%"> Submitted: </td>
            <td width="20%">
                <font face="helvetica, sans-serif" color="green">
                    <input name="post_user" type="text" value="<jsp:getProperty name="KBFacade" property="post_user" />">
                </font>
            </td>
        </tr>
        <tr>
            <td>
                <!--CREATE MESSAGE TYPE BOX-->
                <select name="message_type">
                    <%
                        //Iterate through message types list to create listbox of message types.
                        String message_type = null;
                        i = KBFacade.getMessageTypesList();
                        while(i.hasNext()){
                            message_type = KBFacade.makeMessageTypesString(i.next());
                            if((KBFacade.getMessage_type().equals(message_type))){
                    %>
                                <option selected><%= message_type %>
                    <%      }
                            else{ %>
                                <option><%= message_type %>
                    <%      }
                        }
                    %>
                </select>
                <!--END CREATE MESSAGE TYPE BOX-->
            </td>
        </tr>
        <tr>
            <td width="10%"><br></td>
            <td width="15%"> Message Text: </td>
            <td width="20%"> 
                <textarea name="message_text" cols="40" rows="5" wrap><jsp:getProperty name="KBFacade" property="message_text" /></textarea>
            </td>
        </tr>
        <tr>
            <td width="5%"><br></td>
            <td width="2%"> Key Words:</td>
            <td width="2%">
                <input name="keyword1" type="text" value="<jsp:getProperty name="KBFacade" property="keyword1" />">
            </td>
            <td width="5%" align="left"></td>
            <td width="5%">
                <input name="keyword2" type="text" value="<jsp:getProperty name="KBFacade" property="keyword2" />">
            </td>
        </tr>
        <tr>
            <td width="5%"><br></td>
            <td width="2%"> Key Words:</td>
            <td width="2%">
                <input name="keyword3" type="text" value="<jsp:getProperty name="KBFacade" property="keyword3" />">
            </td>
            <td width="5%" align="left"></td>
            <td width="5%">
                <input name="keyword4" type="text" value="<jsp:getProperty name="KBFacade" property="keyword4" />">
            </td>
        </tr>
        <tr>
            <td width="10%"><br></td>
            <td width="10%"><br></td>
            <td width="5%">
                <input name="submit" type="submit" value="<jsp:getProperty name="KBFacade" property="submitTitle" />">
            </td>
        </tr>
    </form>
</table>    
</body>
</html>
