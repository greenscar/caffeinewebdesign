<%@page contentType="text/html"%>
<% logging.Secretary.startFxn("itemList.jsp"); %>
<jsp:useBean id="admin" scope="session" type="users.Admin" />
    <html>
    <head>
    <title>Your Items</title>
        <style type="text/css">
        <!--
        @import url(templates.css);
        -->
        </style>
    </head>
    <%
    //admin.catalogReset();
    admin.categoryListReset();
    while(admin.categoryListHasMore())
    {
        boolean catDisplayed = false;
        %>
        <jsp:setProperty name="admin" property="nextCategory" value="true"/>
        <%
        logging.Secretary.write("categoryName = " + admin.getCategoryName());
        while(admin.subCategoryListHasMore())
        {
            boolean subCatDisplayed = false;
            %>
            <jsp:setProperty name="admin" property="nextSubCategory" value="true" />
            <%
            //logging.Secretary.write("subCategoryName = " + admin.getSubCategoryName());
            //logging.Secretary.write("list has more = " + admin.itemListHasMore());
            if(admin.itemListHasMore())
            {
                //logging.Secretary.write("itemListHasMore = TRUE");
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
                <table class="list">
                <tr>
                    <th><!--<a href="ItemAdmin?do=disp&order=item_id">-->ID<!--</a>--></th>
                    <th><!--<a href="ItemAdmin?do=disp&order=name">-->NAME<!--</a>--></th>
                    <th><!--<a href="ItemAdmin?do=disp&order=cost">-->COST<!--</a>--></th>
                    <th><!--<a href="ItemAdmin?do=disp&order=gl_acct">-->GL ACCT NUM<!--</a>--></th>
                    <th><!--<a href="ItemAdmin?do=disp&order=active">-->ACTIVE<!--</a>--></th>     
                    <th>CATEGORY</th>
                    <th>SUBCATEGORY</th>
                    <th>ORDER SIZE</th>
                </tr>
                <%
                //logging.Secretary.write("Item Headers Done");
                java.text.NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();
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
                    <tr align="center">
                        <td>
                            <%=itemID%>
                        </td>
                        <td>
                            <jsp:getProperty name="admin" property="itemName" />
                        </td>
                        <td>
                            <%=nf.format(admin.getItemCost())%>
                        </td>
                        <td>
                            <jsp:getProperty name="admin" property="itemGlNum" />
                        </td>
                        <td>
                            <jsp:getProperty name="admin" property="itemActive" />
                        </td>
                        <td>
                            <jsp:getProperty name="admin" property="subCategoryName"/>
                        </td>
                        <td>
                            <jsp:getProperty name="admin" property="categoryName"/>
                        </td>
                        <td>
                            <jsp:getProperty name="admin" property="itemOrderQuantityName"/>
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
<input type="button" name="Menu" value="Menu" onclick="location.href='/Shipping/admin'">
</body>
</html>
<% logging.Secretary.endFxn("itemList.jsp"); %>