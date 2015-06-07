                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@tag pageEncoding="UTF-8" %>
                    <c:choose>
                        <c:when test="${userBean.gender == 'MALE'}">
                            <br><input type="radio" name="gender" value="Male" checked/>Мужской
                            <br><input type="radio" name="gender" value="Female"/>Женский
                        </c:when>
                        <c:when test="${userBean.gender == 'FEMALE'}">
                            <br><input type="radio" name="gender" value="Male"/>Мужской
                            <br><input type="radio" name="gender" value="Female" checked/>Женский
                        </c:when>
                        <c:otherwise>
                            <br><input type="radio" name="gender" value="Male"/>Мужской
                            <br><input type="radio" name="gender" value="Female"/>Женский
                        </c:otherwise>
                    </c:choose>
                    <br>
                        <c:if test="${rfErrors['sex']!=null}">
                        <label class="error-message-visible">${rfErrors['sex']}</label>
                        </c:if>
                        <span name="sex_span" class="error-message-hidden">Check radio!</span>