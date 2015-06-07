<!-- taglibs -->
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <html>

    <head>
        <title>Оформление заказа</title>
        <!-- head -->
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
    </head>

    <body>
        <!-- menu -->
        <%@ include file="/WEB-INF/jspf/menu.jspf" %>
            <div id="wrapper">
                <div id="page" class="container">
                    <div id="content">
                        <div class="title">
                            <h2>Оформление заказа</h2>
                        </div>
                        <div>
                        <table>
                        <thead>
                        <tr>
                            <th class="first">Quantity</th>
                            <th class="second">Item name</th>
                            <th class="third">Price</th>
                            <th class="fourth">Total cost</th>
                            <th class="fifth">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${sessionScope.cart.getProducts()}">
                            <tr id="line${product.key.id}" class="productitm">
                            <td>${product.value}</td>
                        	<td>${product.key.name}</td>
                        	<td>${product.key.price}</td>
                        	<td>${product.key.price*product.value}</td>
                        	</tr>
                        </c:forEach>

                        <tr class="totalprice">
                        <td class="light">Total:</td>
                        <td colspan="2">&nbsp;</td>
                        <td colspan="2"><span class="thick">$<span id="total">${sessionScope.cart.totalSum()}</span></span></td>
                        </tr>

                        <form id="payment" method="POST" action="<c:url value="/checkout/checkoutInfo"/>">
                        <tr class="extracosts">
                            <td class="light">Card number:</td>
                            <td colspan="4"><input type="text" name="cardNumber" id="cardNumber" value="${sessionScope.userOrderInfo.cardNumber}"/></td>
                        </tr>
                        <tr class="extracosts">
                            <td class="light">Adress:</td>
                            <td colspan="4"><input type="text" name="address" id="address" value="${sessionScope.userOrderInfo.address}"/></td>
                        </tr>

                        	<tr class="checkoutrow">
                        	<td colspan="5" class="checkout">
                        		<a class="submitbtn" href="<c:url value="/checkout/create"/>">Back</a>
                        		<input id="nextInfoButton" type="submit" ${sessionScope.userOrderInfo eq null ? 'class="Disabled" disabled': 'class="submitbtn"'} value="Next"/>
                            </td>
                            </tr>
                            </form>
                            </tbody>
                        </table>
                        </div>


                    </div>
                </div>
            </div>
            <script type="text/javascript" src="js/shop.js"></script>
            <script type="text/javascript" src="<c:url value="/js/checkout.js"/>"></script>
    </body>
    </html>