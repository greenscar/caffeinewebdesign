<%@page contentType="text/html"%>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
<%
    logging.Secretary.write("----------------------itemMod.jsp CATEGORY LOADING----------------------");
/*********************************************************
 * PREPARE CATEGORY AND SUBCATEGORY ARRAYS
 ********************************************************/
    String catName, subCatName;
    Integer catIDInt, subCatIDInt;
    java.util.LinkedHashMap categories = new java.util.LinkedHashMap();
    java.util.LinkedHashMap subCategories;
    java.util.LinkedHashMap catToSubCat = new java.util.LinkedHashMap();
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {
        subCategories = new java.util.LinkedHashMap();
        admin.setNextCategory(true);
        catIDInt = new Integer(admin.getCategoryID());
        catName = admin.getCategoryName();
        categories.put(catIDInt, catName);
        while(admin.subCategoryListHasMore())
        {
            admin.setNextSubCategory(true);
            subCatIDInt = new Integer(admin.getSubCategoryID());
            subCatName = admin.getSubCategoryName();
            subCategories.put(subCatIDInt, subCatName);
        }
        catToSubCat.put(catIDInt, subCategories);
    }
    java.util.Iterator keySet = categories.keySet().iterator();
    String catNames = new String();
    String subCatNames = new String();
    String catIDs = new String();
    String subCatIDs = new String();
    java.util.Vector subCatIDStringVector = new java.util.Vector();
    java.util.Vector subCatNameStringVector = new java.util.Vector();
    while(keySet.hasNext())
    {
        catIDInt = (Integer)keySet.next();
        if(catIDs.length() == 0) catIDs = "\"" + catIDInt.toString() + "\"";
        else catIDs = catIDs.concat(", \"" +catIDInt + "\"");
        %>
        <%--catIDs = <%=catIDs%><br>--%>
        <%
        catName = (String)categories.get(catIDInt);
        if(catNames.length() == 0) catNames = "\"" + catName + "\"";
        else catNames = catNames.concat(", \"" + catName + "\"");
        %>
        <%--catNames = <%=catNames%><br>--%>
        <%
        subCategories = (java.util.LinkedHashMap)(catToSubCat.get(catIDInt));
        java.util.Iterator subKeySet = subCategories.keySet().iterator();

        subCatIDs = new String();
        subCatNames = new String();
        while(subKeySet.hasNext())
        {
            subCatIDInt = (Integer)subKeySet.next();
            if(subCatIDs.length() == 0) subCatIDs = "\"" + subCatIDInt.toString() + "\"";
            else subCatIDs = subCatIDs.concat(", \"" + subCatIDInt + "\"");
            %>
            <%--subCatIDs = <%=subCatIDs.toString()%><br>--%>
            <%
            subCatName = (String)subCategories.get(subCatIDInt);
            if(subCatNames.length() == 0) subCatNames = "\"" + subCatName + "\"";
            else subCatNames = subCatNames.concat(", \"" + subCatName + "\"");
            %>
            <%--subCatNames = <%=subCatName.toString()%><br>--%>
            <%
        }
        subCatIDStringVector.add(subCatIDs);
        subCatNameStringVector.add(subCatNames);
    }
    admin.categoryListReset(); 
    logging.Secretary.write("----------------------itemMod.jsp CATEGORY LOADING COMPLETE----------------------");
/*********************************************************
 * END PREPARE CATEGORY AND SUBCATEGORY ARRAYS
 ********************************************************/

%>
<%
/*****************************************************************************************
 * SELECT WHO TO MODIFY
 ****************************************************************************************/
if((request.getParameter("who") == null) || (request.getParameter("SUBMIT") != null))
{
    logging.Secretary.write("who == null");
    %>
    <html>
    <head><title>Select an item to Modify</title>
    <style type="text/css">
    <!--
    @import url(templates.css);
    -->
    </style>
    </head>
    <%
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {
        boolean catDisplayed = false;
        %>
        <jsp:setProperty name="admin" property="nextCategory" value="true"/>
        <%
        while(admin.subCategoryListHasMore())
        {
            boolean subCatDisplayed = false;
            %>
            <jsp:setProperty name="admin" property="nextSubCategory" value="true" />
            <%
            if(admin.itemListHasMore())
            {
                //Display Category
                if(catDisplayed == false)
                {
                    catDisplayed = true;
                    %>
                    <h1 align="center">
                        <jsp:getProperty name="admin" property="categoryName" />
                    </h1>
                    <%
                } // END if(catDisplayed == false)
                if(subCatDisplayed == false)
                {
                    subCatDisplayed = true;
                    %>
                    <h3 align="center">
                        <jsp:getProperty name="admin" property="subCategoryName" />
                    </h3>
                    <%
                } // END if(subCatDisplayed == false)
                %>
                <table align="center" width="95%" border="1">
                <tr>
                    <th width="50"><!--<a href="ItemAdmin?do=mod&order=item_id">-->ID<!--</a>--></th>
                    <th width="300"><!--<a href="ItemAdmin?do=mod&order=name">-->NAME<!--</a>--></th>
                    <th width="100"><!--<a href="ItemAdmin?do=mod&order=cost">-->COST<!--</a>--></th>
                    <th width="75"><!--<a href="ItemAdmin?do=mod&order=gl_acct">-->GL ACCT NUM<!--</a>--></th>
                    <th width="75"><!--<a href="ItemAdmin?do=mod&order=active">-->ACTIVE<!--</a>--></th>     
                    <th width="100">CATEGORY</th>
                    <th width="100">SUBCATEGORY</th>
                    <th width="100">ORDER SIZE</th>
                </tr>
                <%
                java.text.NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();
                while(admin.itemListHasMore())
                {
                    %>
                    <jsp:setProperty name="admin" property="nextItem" value="true" />
                    <%int itemID = admin.getItemID();%>
                    <tr align="center">
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <%=itemID%>
                            </a>
                        </td>
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <jsp:getProperty name="admin" property="itemName" />
                            </a>
                        </td>
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <%=nf.format(admin.getItemCost())%>
                            </a>
                        </td>
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <jsp:getProperty name="admin" property="itemGlNum" />
                            </a>
                        </td>
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <jsp:getProperty name="admin" property="itemActive" />
                            </a>
                        </td>
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <jsp:getProperty name="admin" property="categoryName"/>
                            </a>
                        </td>
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <jsp:getProperty name="admin" property="subCategoryName"/>
                            </a>
                        </td>
                        <td>
                            <a href="ItemAdmin?do=mod&step=display&who=<%=itemID%>&subCat=<jsp:getProperty name="admin" property="subCategoryID"/>&cat=<jsp:getProperty name="admin" property="categoryID"/>">
                                <jsp:getProperty name="admin" property="itemOrderQuantityName"/>
                            </a>
                        </td>
                    <tr>
                    <%
                } // while(admin.itemListHasMore())
                %>
                </table>
                <%
            } //if(admin.itemListHasMore())
            } // while(admin.subCategoryListHasMore())
    } // while(admin.categoryListHasMore()) 
    %>
    <input type="button" name="Cancel" value="Cancel" onclick="location.href='admin.jsp'">
    <%
    //DISPLAY LIST OF ITEMS TO SELECT WHO TO MOD
} // END if(request.getParameter("who") == null)
else // (request.getParameter("who") != null)
{
    logging.Secretary.write("who != null");
    logging.Secretary.write("who = " + request.getParameter("who"));
    int who = new Integer(request.getParameter("who")).intValue();
    int subCatID = new Integer(request.getParameter("subCat")).intValue();
    int catID = new Integer(request.getParameter("cat")).intValue();
    admin.setItemViaID(who, subCatID, catID);
    int itemID = admin.getItemID();
    //logging.Secretary.write("who == " + request.getParameter("who"));
    //logging.Secretary.write("itemID == " + itemID);
    /***********************************************************************
     * DISPLAY ONE ITEM TO MODIFY
     **********************************************************************/
    %>
<html>
<head>
<title>Do your mods then press submit</title>
<script language="JavaScript" src="itemModFxns.js"></script>
<script language="JavaScript">
    var ids = new Array(<%=catIDs%>);
    var names = new Array(<%=catNames%>);
    var subCats = new Array();
    function init()
    {
        <%
            for(int k=0; k < subCatIDStringVector.size(); k++)
            {
                %>
                var tempIDs = new Array(<%=(String)(subCatIDStringVector.get(k))%>);
                var tempNames = new Array(<%=(String)(subCatNameStringVector.get(k))%>);
                subCats[<%=k%>] = new subCategory(tempIDs, tempNames);
                <%
            }
        %>
        optionTest = true;
        lgth = document.modOne.subCategoryNew.options.length - 1;
        document.modOne.subCategoryNew.options[lgth] = null;
        if (document.modOne.subCategoryNew.options[lgth]) optionTest = false;
        populateCategory(ids, names, <%=admin.getCategoryID()%>);
        populateSubCategory(<%=admin.getSubCategoryID()%>);
    }
</script>
</head>
<body onLoad="init()">
    <form name="modOne" method="post" action="ItemAdmin?do=mod&step=process">
         <table align="center" width="95%" border="1">
            <%
                //java.text.NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();
                java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance();
                nf.setMinimumFractionDigits(2);
                nf.setMaximumFractionDigits(2);
                Boolean isActive = new Boolean(admin.getItemActive());
            %>
            <tr>
                <td width="20%">ID: </td>
                <td width="80%">
                    <%=admin.getItemID()%>
                </td>
            </tr>
            <tr>
                <td>NAME:</td>
                <td>
                    <input type="text" size="75" name="name" value="<jsp:getProperty name="admin" property="itemName" />">
                </td>
            </tr>
            <tr>
                <td>COST:</td>
                <td>
                    <input type="text" size="5" name="cost" value="<%=nf.format(admin.getItemCost())%>">
                </td>
            </tr>
            <tr>
            <tr>
                <td>GL ACCT NUM:</td>
                <td>
                    <input type="text" size="5" name="glNum" value="<jsp:getProperty name="admin" property="itemGlNum" />">
                </td>
            </tr>
            <tr>
                <td>ACTIVE:</td>
                <td>
                    <select name="active">
                        <option value="true"<%
                            if(isActive.booleanValue())
                            {%> selected<%}%>>YES
                        <option value="false"<%
                            if(!isActive.booleanValue())
                            {%> selected<%}%>>NO
                   </select>
                </td>
            </tr>
            <tr>
                <td>CATEGORY:</td>
                <td>
                    <SELECT NAME="categoryNew" WIDTH=400px onChange="populateSubCategory(subCats, <%=admin.getSubCategoryID()%>)">
                    </SELECT>
                </td>
            </tr>
            <tr>
                <td>SUBCATEGORY:</td>
                <td>
                    <SELECT NAME="subCategoryNew" WIDTH=400px>
                       <OPTION>No subCategory level yet</OPTION>
                       <OPTION>Your browser can't handle this script</OPTION>
                    </SELECT>
                </td>
            </tr>
            <tr>
                <td>ORDER SIZE:</td>
                <td>
                    <select name="orderQuantityID">
        
        <%
        admin.orderQuantityIteratorReset();
        while(admin.orderQuantityListHasMore())
        {
            logging.Secretary.write("admin.getTempOrderQuantityID() = " + admin.getTempOrderQuantityID());
        %>
            <jsp:setProperty name="admin" property="nextOrderQuantity" value="true" />
        <%
            int id = admin.getTempOrderQuantityID();
        %>
            <option value="<%=id%>"<%
            logging.Secretary.write("admin.getItemOrderQuantityID() = " + admin.getItemOrderQuantityID());
            if(admin.getItemOrderQuantityID() == id)
            {%> selected <%}%>> <jsp:getProperty name="admin" property="tempOrderQuantityName" />
        <%
        }
        %>
                    </select>
                </td>
            </tr>
        </table>
        <input type="hidden" name="do" value="process">
        <input type="hidden" name="who" value="<%=admin.getItemID()%>">
        <input type="hidden" name="categoryOrig" value="<%=admin.getCategoryID()%>">
        <input type="hidden" name="subCategoryOrig" value="<%=admin.getSubCategoryID()%>">
        <p align="center">
        <input type="submit" name="SUBMIT" value="SUBMIT">
        <input type="button" name="Cancel" value="Cancel" onclick="location.href='admin'">
        </p>
    </form>
<%
admin.categoryListReset();
%>
<%-- <jsp:useBean id="beanInstanceName" scope="session" class="package.class" /> --%>
<%-- <jsp:getProperty name="beanInstanceName"  property="propertyName" /> --%>
</body>
</html>
<%
}// END (request.getParameter("who") != null)
logging.Secretary.write("*****************************************************");
logging.Secretary.logAllRequestVars(request);
logging.Secretary.write("*****************************************************");    
%>