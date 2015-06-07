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
                            <td>
                            <input type="number" id="count${product.key.id}" onchange="changeQuantity('${product.key.id}')" value="${product.value}" min="1" max="99" class="qtyinput">
                        	</td>
                        	<td><span id="name${product.key.id}">${product.key.name}</span></td>
                        	<td>$<span id="price${product.key.id}">${product.key.price}</span></td>
                        	<td>$<span id="totalLine${product.key.id}">${product.key.price*product.value}</span></td>
                        	<td><span class="remove" onclick="removeLine('${product.key.id}')"><img src="<c:url value="/images/glyphicons-17-bin.png"/>" alt="X"></span></td>
                        	</tr>
                        </c:forEach>

                        <tr class="totalprice">
                            <td class="light">Total:</td>
                            <td colspan="2">&nbsp;</td>
                            <td colspan="2"><span class="thick">$<span id="total">${sessionScope.cart.totalSum()}</span></span></td>
                        	</tr>
                        	<tr class="checkoutrow">
                        	<td colspan="5" class="checkout">
                        		<a class="submitbtn" href="<c:url value="/product"/>">toShop</a>
                        		<c:if test="${sessionScope.cart!=null and (sessionScope.cart.size() ne 0)}">
                        		<a id="nextButt" class="submitbtn" href="<c:url value="/checkout/checkoutPayment/"/>">next</a>
                                    </c:if>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        </div>


                    </div>
                </div>
            </div>
            <script type="text/javascript" src="<c:url value="/js/shop.js"/>"></script>
            <script type="text/javascript" src="<c:url value="/js/checkout.js"/>"></script>
    </body>
    </html>