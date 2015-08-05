<%@ page errorPage="errorpage.jsp" %>
<%@ page import="java.util.*" %>

<!-- Instantiate the Counter bean with an id of "counter" -->
<jsp:useBean id="cart" scope="session" class="ShoppingCart" />

<html>
  <head>
    <title>Shopping Cart Contents</title>
  </head>

<body bgcolor="#FFFFFF">
<table width="100%" border="0">
  <tr> 
    <td colspan="2"><%@ include file="titlebar.jsp" %></td>
  </tr>
  <tr> 
    <td width="75" valign="top"><%@ include file="navigation.jsp" %></td>
    <td valign="top">
    <table align="top" width="600" cellspacing="0" cellpadding="2" border="0">
      <caption><b>Shopping Cart Contents</b></caption>
      <tr>
        <th align="left">Description</th>
        <th>Price</th>
        <th>Quantity</th>
      </tr>
      <%
          Enumeration enum = cart.getEnumeration();
          String[] tmpItem;
          // Iterate over the cart
          while (enum.hasMoreElements()) {
      
            tmpItem = (String[])enum.nextElement();
      %>
      <tr>
          <td><%=tmpItem[1] %></td>
          <td align="center">$<%=tmpItem[2] %></td>
          <td align="center"><%=tmpItem[3] %></td>
      </tr>
      <%
      }
      %>
    </table>
</body>
</html>