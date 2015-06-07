<!-- taglibs -->
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Товары</title>
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
                            <h2>Список товаров</h2>
                        </div>
                        <ul class="shopcat">
                            <c:if test="${empty itemList}">
                            <h3>Товаров не найдено.</h3>
                            </c:if>
                            <c:if test="${not empty itemList}">
                                <c:forEach var="item" items="${itemList}">
                                    <li>
                                        <img src="<c:url value='/product/picture/${item.id}'/>">
                                        <h3>${item.name}</h3>
                                        <p>Автор: ${item.information.author}</p>
                                        <p>Год: ${item.information.year}</p>
                                        <!--<p>Категория: </p>-->
                                        <p>Цена: ${item.price}</p>
                                        <p>${item.description}</p>
                                        <button onclick="buy('${item.id}');">Купить</button>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                        <custom:paging></custom:paging>
                    </div>
                    <div id="sidebar">

                                <custom:cart/>

                            <select id="listPerPage" name="listPerPage">
                                <option value="2" ${listPerPage==2? 'selected="selected"':''}>2</option>
                                <option value="5" ${listPerPage==5? 'selected="selected"':''}>5</option>
                                <option value="10" ${listPerPage==10? 'selected="selected"':''}>10</option>
                            </select>
                        <div class="filter-widget">
                            <form id="filter" name="product-filter" method="post" action="<c:url value="/product"/>">
                                <div>
                                    <h2>Поиск</h2>
                                </div>
                                <div>
                                    <input name="name" placeholder="Input name" value="${filter.productName}" type="text">
                                    <c:choose>
                                    <c:when test="${filter.sortType == 'ASC'}">
                                    <input name="sortType" value="ASC" type="radio" checked>ASC
                                    <input name="sortType" value="DESC" type="radio">DESC
                                    </c:when>
                                    <c:when test="${filter.sortType == 'DESC'}">
                                    <input name="sortType" value="ASC" type="radio">ASC
                                    <input name="sortType" value="DESC" type="radio"checked>DESC
                                    </c:when>
                                    <c:otherwise>
                                    <input name="sortType" value="ASC" type="radio">ASC
                                    <input name="sortType" value="DESC" type="radio">DESC
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <input name="author" placeholder="Input author" value="${filter.productAuthor}" type="text">
                                </div>
                                <div>
                                    <input name="startYear" placeholder="Input year from:" value="${filter.minYear}" type="text">
                                    <input name="endYear" placeholder="Input year to:" value="${filter.maxYear}" type="text">
                                </div>
                                <div>
                                    <input name="startPrice" placeholder="Input price from" value="${filter.minPrice}" type="text">
                                    <input name="endPrice" placeholder="Input price to" value="${filter.maxPrice}" type="text">
                                </div>
                                <div>
                                    <label id="categories">Категории:</label>

                                    <c:choose>
                                        <c:when test="${fn:length(filter.categories) < 1}">
                                            <br>
                                            <input name="categories" type="checkbox" value="1" />Музыка
                                            <br>
                                            <input name="categories" type="checkbox" value="2" />Фильмы
                                        </c:when>
                                        <c:otherwise>

                                            <c:set var="contains" value="false" />
                                            <c:forEach var="id" items="${filter.categories}">
                                                <c:if test="${id eq '1'}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <br>
                                            <input name="categories" type="checkbox" value="1" ${contains=='true' ? 'checked' : ''}/>Музыка
                                            <c:set var="contains" value="false" />
                                            <c:forEach var="id" items="${filter.categories}">
                                                <c:if test="${id eq '2'}">
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <br>
                                            <input name="categories" type="checkbox" value="2" ${contains=='true' ? 'checked' : ''}/>Фильмы
                                        </c:otherwise>
                                    </c:choose>
                                    <br/>
                                         <input name="filterButton" type="button" value="Отфильтровать"/>
                                         <input name="cancelFilter" type="button" value="Сбросить всё"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
             <script type="text/javascript" src="<c:url value='js/shop.js'/>"></script>
    </body>
    </html>