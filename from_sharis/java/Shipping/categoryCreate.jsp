<%@page contentType="text/html"%>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
<% 
    logging.Secretary.startFxn("categoryCreate.jsp");
%>
<html>
<head>
<title>Create your Catalog</title>
<script language="javascript" type="text/javascript">
<%
    /*
     * GENERATE ARRAY OF EXISTING KEYS AND EXISTING NAMES SO WHEN USER ENTERES 
     * A NEW CATEGORY, WE CAN ENSURE IT DOESN'T ALREADY EXIST BEFORE SUBMITTING.
     */
    String catKeyList = "";
    String catNameList = "";
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {
        admin.setNextCategory(true);
        catKeyList = catKeyList.concat("\"" + admin.getCategoryNick() + "\", ");
        catNameList = catNameList.concat("\"" + admin.getCategoryName() + "\", ");
    }
    catKeyList = catKeyList.substring(0, (catKeyList.length() - 2));
    catNameList = catNameList.substring(0, (catNameList.length() - 2));
    admin.categoryListReset();
%>
function checkCatForm(form) 
{
    var keys = new Array(<%=catKeyList%>);
    var names = new Array(<%=catNameList%>);
    for(i=0; i<keys.length;i++)
    {
        key_entered = form.catNick.value;
        name_entered = form.catName.value;
        if(key_entered == "")
        {
            alert("You must enter a nick.");
            form.catNick.focus();
            return(false);
        }
        if(key_entered == keys[i])
        {
            alert(key_entered + " is already used as a nick.\nPlease enter a different nick.");
            form.catNick.focus();
            return(false);
        }
        if(name_entered == "")
        {
            alert("You must enter a name.");
            form.catName.focus();
            return(false);
        }
        if(name_entered == names[i])
        {
            alert(name_entered + " is already used as a name.\nPlease enter a different name.");
            form.catName.focus();
            return(false);
        }
    }
    return(true);
}
</script>
</head>
<body>
<%
if(admin.categoryListHasMore())
{
%>
<h2>Current Categories</h2>
<table border="1" width="250">
<tr>
<th>ID</th>
<th>NICK</th>
<th>NAME</th>
</tr>
<%
    while(admin.categoryListHasMore())
    {%>
<jsp:setProperty name="admin" property="nextCategory" value="true"/>
<tr>
<td width="10%">
<jsp:getProperty name="admin" property="categoryID" />
</td>
<td width="10%">
<jsp:getProperty name="admin" property="categoryNick" />
</td>
<td width="80%">
<jsp:getProperty name="admin" property="categoryName" />
</td>
<%   }%>
</table>
<%}%>


<form name="category" method="post" action="CategoryAdmin?do=create" onsubmit="return checkCatForm(this)">
<h2>Enter your new category</h2>
NICK: <input type="text" name="catNick">
<br>
NAME: <input type="text" name="catName">
<br>
<input type="submit" name="Submit" value="Create">
<input type="button" name="Cancel" value="Cancel" onclick="location.href='/Shipping/admin'">
</form>
</body>
</html>
<%
logging.Secretary.endFxn("categoryCreate.jsp");
%>