<!-- taglibs -->
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <html>

    <head>
        <title>Ошибка</title>
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
                                 <h2>Залогиньтесь</h2></div>
            <c:if test="${sessionScope.user==null}">
                        <form name="login" method="post" action="<c:url value="/login"/>">
                            <div>
                                <input name="loginBtn" type="submit" value="Войти" onclick="return validateJQLogin()">
                            </div>
                            <div>
                                <input name="first_name_login" placeholder="Имя" type="text">

                                <c:if test="${rfErrors['first_name_login']!=null}">
                                    <label class="error-message-visible">${rfErrors['first_name_login']}</label>
                                </c:if>
                                <span name="first_name_login_span" class="error-message-hidden">Input name!</span>
                            </div>
                            <div>
                                <input name="pass_login" placeholder="Пароль" type="password">

                                 <c:if test="${rfErrors['pass_login']!=null}">
                                      <label class="error-message-visible">${rfErrors['pass_login']}</label>
                                 </c:if>
                                <span name="pass_login_span" class="error-message-hidden">Input password!</span>
                            </div>
                            <input type="hidden" name="viewid" value="${referrer}">
                        </form>
                        </c:if></div></div>
                                                     </div>
                                                     <script type="text/javascript" src="js/shop.js"></script>
    </body>

    </html>