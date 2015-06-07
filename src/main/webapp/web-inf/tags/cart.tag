	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div>
        <div>
            <div>Cart</div>
            <div><span id="countCart">${sessionScope.cart eq null ? 0: sessionScope.cart.size()} items</span>
            </div>
        </div>
        <div>
            <div>
                Total: $<span id="totalCart">${sessionScope.cart eq null ? 0: sessionScope.cart.totalSum()}</span>
            </div>
            <c:choose>
                <c:when test="${sessionScope.user!=null}">
                <a href="<c:url value="/checkout/create"/>">Make order</a>
                </c:when>
                <c:otherwise>
                <a href="<c:url value="/register"/>">Make order</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>