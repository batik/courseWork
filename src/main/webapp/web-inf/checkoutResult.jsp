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
                        <c:if test="${successOrder ne 'true'}">
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

                        <tr class="extracosts">
                            <td class="light">Card number:</td>
                            <td colspan="4">${sessionScope.userOrderInfo.cardNumber}</td>
                        </tr>
                        <tr class="extracosts">
                            <td class="light">Address:</td>
                            <td colspan="4">${sessionScope.userOrderInfo.address}</td>
                        </tr>
                        <form method="post" action="<c:url value="/checkout/buy"/>">
                        	<tr class="checkoutrow">
                        	<td colspan="5" class="checkout">
                        		<a class="submitbtn" href="<c:url value="/checkout/checkoutPayment/"/>">Back</a>
                        		<input type="submit" value="Buy"></>
                            </td>
                            </tr>
                            </form>
                            </tbody>
                        </table>
                        </div>
                        </c:if>
                        <c:if test="${successOrder eq 'true'}">
                           <span name="successOrder" data-url="<c:url value="/index"/>" class="error-message-visible">Order was successfully made! You will be redirect automatically on main page.</span>
                           <script>window.setTimeout(function() {
                                   location.href = $('span[name=successOrder]').attr('data-url');
                                   }, 5000);
                           </script>
                        </c:if>

                    </div>
                </div>
            </div>
            <script type="text/javascript" src="js/shop.js"></script>
            <script type="text/javascript" src="<c:url value="/js/checkout.js"/>"></script>
    </body>
    </html>