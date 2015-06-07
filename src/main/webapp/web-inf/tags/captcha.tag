            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <c:choose>
                    <c:when test="${csType eq 'csHidden'}">
                        <jsp:useBean id="generator" scope="application" class="com.epam.shop.utils.RndNumberGenerator"/>
                        <c:set scope="page" var="captchaGeneratedID" value="${generator.generate()}"/>
                        <img class="image-centered-div" src="<c:url value='/captcha?captchaHidden=${captchaGeneratedID}'/>">
                        <input name="captchaHidden" value="${captchaGeneratedID}" type="hidden">
                    </c:when>
                    <c:otherwise>
                        <img class="image-centered-div" src="<c:url value='/captcha'/>">
                    </c:otherwise>
                </c:choose>
