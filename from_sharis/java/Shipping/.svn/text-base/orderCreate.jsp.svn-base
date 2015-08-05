<% logging.Secretary.startFxn("orderCreate.jsp");%>
<jsp:useBean id="admin" scope="session" type="users.User" />
<%
if(request.getAttribute("catID") == null)
{
/**********************************************************************
 * FIRST VISIT TO SITE. DISPLAY START ORDER FORM
 *********************************************************************/
    if(admin instanceof users.Admin)
    {
    %>
        <jsp:include page="orderSelectRecipients.jsp"/>
    <%}
    else
    {
        /**********************************************************************
         * USER IS SITE - DISPLAY NAME FIELD ONLY
         *********************************************************************/
        // DISPLAY ONLY USERS NAME FORM.
        // STORE NUM IS SELECTED.
        %>
<jsp:include page="topMenu.jsp">
    <jsp:param name="dispMenu" value="false"/>
    <jsp:param name="title" value="Enter your order information"/>
</jsp:include>

<!--<h1 class="pageTitle">Enter your order information</h1>-->
<div id="body" class="mmBody">
<table class="orders">
        <tr>
            <td class="title">
                Order Information
            </td>
        </tr>
        <tr>
            <td>
            </td>
        </tr>
        <%
        /**********************************************************************
         * END USER IS SITE - DISPLAY NAME FIELD ONLY
         *********************************************************************/
    }
    /**********************************************************************
     * END FIRST VISIT TO SITE. DISPLAY START ORDER FORM
     *********************************************************************/
}
else //if(request.getAttribute("nextCat") == null)
{
    /**********************************************************************
     * ORDER HAS BEEN STARTED. DISPLAY CATEGORY FOR ORDERING
     *********************************************************************/
int currentCatID = (new Integer((String)(request.getAttribute("catID")))).intValue();
System.out.println("currentCatID = " + currentCatID);
java.text.NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();
%>
<jsp:include page="topMenu.jsp">
    <jsp:param name="dispMenu" value="false"/>
    <jsp:param name="title" value="Enter your order information"/>
</jsp:include>
<form name="order" method="post" action="Order">
<input type="hidden" name="do" value="cont">
<div id="body" class="mmBody">
<table class="orders">
    <tr><td class="subtitle">Order Information</td></tr>
    <tr><td>
        <span class="orderInfo"><b>Order Date:</b> <%=admin.getOrderDate()%></span>
        <span class="orderInfo"><b>Customer:</b> <%=admin.getOrderContact()%></span>
        <span class="orderInfo"><b>Pieces:</b> <%=admin.getOrderNumPieces()%></span>
        <span class="orderInfo"><b>Total Cost:</b> <%=nf.format(admin.getOrderTotalCost())%></span>
    </td></tr>
        <tr>
            <td class="title">Enter your order information</td>
        </tr>
<tr>
<td class="subtitle">
Goto: 
<%
    if(admin.getCategoryID() != admin.categoryListGetFirstKey())
    {%>
<input class="goTo" type="submit" value="<< First" name="goTo">
<input class="goTo" type="submit" value="< Prev" name="goTo">
    <%}
    if(admin.getCategoryID() != admin.categoryListGetLastKey())
    {%>
<input class="goTo" type="submit" value="Next >" name="goTo">
<input class="goTo" type="submit" value="Last >>" name="goTo">
    <%}%>
<select name="nextCat" onchange="order.submit()" class="nextCat">
<option value="Select Category" selected>Select Category</option>
<%
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {
%>
    <jsp:setProperty name="admin" property="nextCategory" value="true" />
<%
//System.out.println("admin.getCategoryID() = " + admin.getCategoryID());
//System.out.println("currentCatID = " + currentCatID);
if(admin.getCategoryID() != currentCatID){%>
    <option value="<jsp:getProperty name="admin" property="categoryID"/>"><jsp:getProperty name="admin" property="categoryName"/></option>
<%}
    }
    admin.categoryListReset();
    admin.setCategoryViaID(currentCatID);
%>                
</select>
<input class="submitOrder" type="submit" name="goTo" value="Submit Order">
            </td>
        </tr>
        <tr>
            <td>
    <h1 class="category"><jsp:getProperty name="admin" property="categoryName" /></h1>
<%
    // Display Items of the selected category.
    //logging.Secretary.write("categoryName = " + admin.getCategoryName());
    while(admin.subCategoryListHasMore())
    {
        int rowNum = 0;
        boolean subCatDisplayed = false;
        %>
        <jsp:setProperty name="admin" property="nextSubCategory" value="true" />
        <%
        if(admin.itemListHasMore())
        {
            //logging.Secretary.write("itemListHasMore = TRUE");
            //Display Category
            if(subCatDisplayed == false)
            {
                subCatDisplayed = true;
                %>
                <h3 class="subCategory">
                    <jsp:getProperty name="admin" property="subCategoryName" />
                </h3>
                <%
            } // END if(subCatDisplayed == false)
            %>
            <table class="itemList">
            <tr>
                <th align="left" width="10%">ITEM #</th>
                <th align="left" width="40%">NAME</th>
                <th align="left" width="5%">GL #</th>
                <th align="center" width="10%">COST</th>
                <th align="left" width="10%">UNIT</th>     
                <th align="left" width="5%">QTY</th>
                <th align="left" width="10%">ITEM COST</th>
            </tr>
            <%
            //logging.Secretary.write("Item Headers Done");
            //logging.Secretary.write("admin.itemListHasMore() = " + admin.itemListHasMore());
            /*
            if(admin.itemListHasMore())
            {
                logging.Secretary.write("name = ???????");
                admin.setNextItem(true);
                logging.Secretary.write("name = " + admin.getItemName());
            }
            */
            while(admin.itemListHasMore())
            {
                %>
                <jsp:setProperty name="admin" property="nextItem" value="true" />
                <% 
                    int itemID = admin.getItemID();
                    %>
                    <tr>
                    <%
                    if(rowNum++ % 2 == 0){%>

                    <td class="even">
                        <%=itemID%>
                    </td>
                    <td class="even">
                        <jsp:getProperty name="admin" property="itemName" />
                    </td>
                    <td class="even">
                        <jsp:getProperty name="admin" property="itemGlNum" />
                    </td>
                    <td class="even">
                        <input type="text" class="itemCostEven" id="itemCost" name="cost_<%=itemID%>" value="<%=nf.format(admin.getItemCost())%>" readonly>
                    </td>
                    <td class="even">
                        <jsp:getProperty name="admin" property="itemOrderQuantityName"/>
                    </td>
                    <td class="even">
                        <input type="text" class="qty" id="qty" name="quantity_<%=itemID%>" onblur='itemCostCompute(<%=itemID%>);'></input>
                    </td>
                    <td class="even">
                        <input type="text" class="itemCostEven" id="itemCost" name="sum_<%=itemID%>" readonly value="$0.00">
                    </td>
                    <%}
                    else{%>
                    <td class="odd">
                        <%=itemID%>
                    </td>
                    <td class="odd">
                        <jsp:getProperty name="admin" property="itemName" />
                    </td>
                    <td class="odd">
                        <jsp:getProperty name="admin" property="itemGlNum" />
                    </td>
                    <td class="odd">
                        <input type="text" class="itemCostOdd" id="itemCost" name="cost_<%=itemID%>" value="<%=nf.format(admin.getItemCost())%>" readonly>
                    </td>
                    <td class="odd">
                        <jsp:getProperty name="admin" property="itemOrderQuantityName"/>
                    </td>
                    <td class="odd">
                        <input type="text" class="qty" id="qty" name="quantity_<%=itemID%>" onblur='itemCostCompute(<%=itemID%>);'></input>
                    </td>
                    <td class="odd">
                        <input type="text" class="itemCostOdd" id="itemCost" name="sum_<%=itemID%>" readonly value="$0.00">
                    </td>
                    <%}%>
                <tr>
                <%
            } // while(admin.itemListHasMore())
            %>
            </table>
            <%
        } //if(admin.itemListHasMore())
    } // while(admin.subCategoryListHasMore())
%>
        </td>
    </tr>
<tr>
<td class="subtitle">
Goto: 
<%
    if(admin.getCategoryID() != admin.categoryListGetFirstKey())
    {%>
<input class="goTo" type="submit" value="<< First" name="goTo">
<input class="goTo" type="submit" value="< Prev" name="goTo">
    <%}
    if(admin.getCategoryID() != admin.categoryListGetLastKey())
    {%>
<input class="goTo" type="submit" value="Next >" name="goTo">
<input class="goTo" type="submit" value="Last >>" name="goTo">
    <%}%>
<select name="nextCat" onchange="order.submit()" class="nextCat">
<option value="Select Category" selected>Select Category</option>
<%
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {
%>
    <jsp:setProperty name="admin" property="nextCategory" value="true" />
<%
System.out.println("admin.getCategoryID() = " + admin.getCategoryID());
System.out.println("currentCatID = " + currentCatID);
if(admin.getCategoryID() != currentCatID){%>
    <option value="<jsp:getProperty name="admin" property="categoryID"/>"><jsp:getProperty name="admin" property="categoryName"/></option>
<%}
    }
    admin.categoryListReset();
    admin.setCategoryViaID(currentCatID);
%>                
</select>
<input class="submitOrder" type="submit" name="goTo" value="Submit Order">
            </td>
        </tr>
<%
    /**********************************************************************
     * END ORDER HAS BEEN STARTED. DISPLAY CATEGORY FOR ORDERING
     *********************************************************************/
} // END else if(request.getAttribute("nextCat") == null)
%>
</table>
</form>
</body>
</html>
