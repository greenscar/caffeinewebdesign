<%@page contentType="text/html"%>
<% boolean dispMenu = true; String title="Main Order Screen"; %>
<jsp:include page="topMenu.jsp">
    <jsp:param name="dispMenu" value="true"/>
    <jsp:param name="title" value="Main Order Screen"/>
</jsp:include>
<div id="body" class="mmBody">
    <table class="orders">
        <tr>
            <td class="title">
                Orders
            </td>
        </tr>
        <tr>
            <td class="subtitle">
                New Order
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" name="startNew" value="Start New Order" onClick="window.open('Order?do=getPossibleRecipients','test');"><!--, 'fullscreen,scrollbars'-->
                <br><br>
            </td>
        </tr>
        <tr>
            <td class="column_name_bar">
                <table width="100%">
                    <tr>
                        <td class="column_name">Date</td>
                        <td class="column_name">Period</td>
                        <td class="column_name">Order #</td>
                        <td class="column_name">Placed by</td>
                        <td class="column_name">Pieces</td>
                        <td class="column_name">Total $</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="subtitle">Existing Orders (Click to Modify)</td>
        </tr>
        <tr>
            <td class="dataWhite">There are no existing orders</td>
        </tr>
        <tr>
            <td class="subtitle">Order History (Click to View)</td>
        </tr>
        <tr>
            <td class="dataWhite">There are no orders in your history.</td>
        </tr>
    </table>
</div>
</body>
</html>
