<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/deweb.tld" prefix="deweb" %>
<deweb:form formName="survey">
<deweb:formField formName="survey" fieldName="formID"/>
<table border="0" cellpadding="2" cellspacing="0">
  <tr>
    <td width="100%" valign="top" colspan="2" align="center">
    </td>
  </tr>
<deweb:iterate collection="survey" iteratedItemName="formField" iteratedItemClass="com.covecomm.deweb.forms.FormFieldBean" scope="request">
  <tr>
     <td valign="top">
     <jsp:getProperty name="formField" property="html"/>   
     </td>
     <td valign="top">
     <jsp:getProperty name="formField" property="prompt"/>   
     </td>
  </tr>
</deweb:iterate>
  <tr>
    <td width="100%" valign="top" colspan="2">
      <br>
      <p align="center">      
      <deweb:formField formName="survey" fieldName="sid"/>
      <deweb:formField formName="survey" fieldName="submit"/>
      </p></td>
  </tr>
</table>
</deweb:form>








