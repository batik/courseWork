            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@tag pageEncoding="UTF-8" %>
            <c:if test="${sessionScope.user==null}">
            <form name="login" method="post" action="<c:url value="/login"/>">
                <div id="first">
                    <input name="loginBtn" type="submit" value="Войти" onclick="return validateJQLogin()">
                </div>
                <div id="login">
                    <input name="first_name_login" placeholder="Имя" type="text">
                    <c:if test="${rfErrors['first_name_login']!=null}">
                        <label class="error-message-visible">${rfErrors['first_name_login']}</label>
                    </c:if>
                    <span name="first_name_login_span" class="error-message-hidden">Input name!</span>
                </div>
                <div id="login">
                    <input name="pass_login" placeholder="Пароль" type="password">
                     <c:if test="${rfErrors['pass_login']!=null}">
                          <label class="error-message-visible">${rfErrors['pass_login']}</label>
                     </c:if>
                    <span name="pass_login_span" class="error-message-hidden">Input password!</span>
                </div>
                <input type="hidden" name="viewid" value="${pageContext.request.requestURI}">
            </form>
            </c:if>