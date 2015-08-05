<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/deweb.tld" prefix="deweb" %>
<deweb:menu menuName="leftsidebar">
   <deweb:iterate collection="leftsidebar" iteratedItemName="menuItem" iteratedItemClass="com.covecomm.deweb.doc.HttpLinkBean">
 <jsp:setProperty name="menuItem" property="style" value="color: #21007b"/>
 <jsp:getProperty name="menuItem" property="formattedLink"/><br>
   </deweb:iterate>
   <p>&nbsp;</p>
</deweb:menu>  
