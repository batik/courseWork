<!-- taglibs -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>

    <html>

    <head>
        <title>Регистрация</title>

        <!-- head -->
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        <script type="text/javascript" src="js/validation-jquery.js"></script>

    </head>
    <body>

        <!-- menu -->
        <%@ include file="/WEB-INF/jspf/menu.jspf" %>

            <div id="wrapper">
                <div id="page" class="container">
                    <div id="content">
                        <div class="title">
                            <h2>Регистрация</h2>
                        </div>
                        <form name="register" method="post" action="<c:url value='/register'/>" enctype="multipart/form-data">
                            <div>
                                <input id="form-login" name="login" placeholder="Имя" value="${userBean.login}" type="text">
                                <label id="login-exist" class="error-message-hidden">User already exist!</label>
                                <c:if test="${rfErrors['first_name']!=null}">
                                    <label class="error-message-visible">${rfErrors['first_name']}</label>
                                </c:if>
                                <span name="first_name_span" class="error-message-hidden">Input name!</span>
                            </div>
                            <div>
                                <input name="name" placeholder="Фамилия" value="${userBean.name}" type="text">

                                <c:if test="${rfErrors['last_name']!=null}">
                                    <label class="error-message-visible">${rfErrors['last_name']}</label>
                                </c:if>
                                <span name="last_name_span" class="error-message-hidden">Input last name!</span>
                            </div>
                            <div>
                                <input name="pass" placeholder="Пароль" value="${userBean.pass}" type="password">

                                <c:if test="${rfErrors['pass']!=null}">
                                    <label class="error-message-visible">${rfErrors['pass']}</label>
                                </c:if>
                                <span name="pass_span" class="error-message-hidden">Input password!</span>
                            </div>
                            <div>
                                <input name="email" placeholder="Email" value="${userBean.email}" type="text">

                                <c:if test="${rfErrors['email']!=null}">
                                    <label class="error-message-visible">${rfErrors['email']}</label>
                                </c:if>
                                <span name="email_span" class="error-message-hidden">Input proper email!</span>
                            </div>
                            <div>
                                <label id="gender">Пол:</label>
                                <custom:gender/>
                            </div>

                            <div>
                                <custom:captcha/>
                            </div>
                            <div>
                                <input name="captcha" placeholder="Captcha" type="text">

                                <c:if test="${rfErrors['captcha']!=null}">
                                    <label class="error-message-visible">${rfErrors['captcha']}</label>
                                </c:if>
                                <span name="captcha_span" class="error-message-hidden">Wrong symbols quantity!</span>
                            </div>
                            <div>
                                <label id="feeds">Рассылки:</label>

                                <c:choose>
                                    <c:when test="${fn:length(userBean.feeds) < 1}">
                                        <br>
                                        <input name="feeds" type="checkbox" value="News" />Новости
                                        <br>
                                        <input name="feeds" type="checkbox" value="Updates" />Обновления
                                    </c:when>
                                    <c:otherwise>

                                        <c:set var="contains" value="false" />
                                        <c:forEach var="feed" items="${userBean.feeds}">
                                            <c:if test="${feed eq 'News'}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <br>
                                        <input name="feeds" type="checkbox" value="1" ${contains=='true' ? 'checked' : ''}/>Новости
                                        <c:set var="contains" value="false" />
                                        <c:forEach var="feed" items="${userBean.feeds}">
                                            <c:if test="${feed eq 'Updates'}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <br>
                                        <input name="feeds" type="checkbox" value="2" ${contains=='true' ? 'checked' : ''}/>Обновления
                                    </c:otherwise>
                                </c:choose>

                                <c:if test="${rfErrors['feeds']!=null}">
                                    <label class="error-message-visible">${rfErrors['feeds']}</label>
                                </c:if>
                            </div>

                            <div>
                                Аватарка:
                                <input type="file" name="file" id="file" />
                                <c:if test="${rfErrors['file']!=null}">
                                    <label class="error-message-visible">${rfErrors['file']}</label>
                                </c:if>
                            </div>

                            <div>
                                <input name="submitJQ" type="submit" value="Регистарция" onclick="return validateJQForm()">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/shop.js"></script>
    </body>

    </html>