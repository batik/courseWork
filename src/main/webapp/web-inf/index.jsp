<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>Магазин</title>

            <!-- head -->
            <%@ include file="/WEB-INF/jspf/head.jspf" %>

                <script type="text/javascript" src="js/jquery.slidertron-1.3.js"></script>
                <script type="text/javascript" src="js/slider.js"></script>
        </head>

        <body>

            <!-- menu -->
            <%@ include file="/WEB-INF/jspf/menu.jspf" %>

                <!-- ****************************************************************************************************************** -->

                <div id="slider">
                    <a href="#" class="button previous-button"><span class="icon icon-double-angle-left"></span></a>
                    <a href="#" class="button next-button"><span class="icon icon-double-angle-right"></span></a>

                    <div class="viewer">
                        <div class="reel">
                            <div class="slide">
                                <a class="link" href="http://nodethirtythree.com/#slidertron-slide-1">Возможно тут появятся товары
                    ...</a>
                                <img src="images/1.jpg" alt="" />
                            </div>
                            <div class="slide">
                                <a class="link" href="http://nodethirtythree.com/#slidertron-slide-2">Возможно тут появятся товары
                    ...</a>
                                <img src="images/2.jpg" alt="" />
                            </div>
                            <div class="slide">
                                <a class="link" href="http://nodethirtythree.com/#slidertron-slide-3">Возможно тут появятся товары
                    ...</a>
                                <img src="images/1.jpg" alt="" />
                            </div>
                        </div>
                    </div>
                </div>

                <!-- ****************************************************************************************************************** -->


                <div id="wrapper">
                    <div id="page" class="container">
                        <div id="content">
                            <div class="title">
                                <h2>Добро пожаловать на сайт</h2>
                            </div>
                            <p>Это главная страница магазина</p>
                            <a href="<c:url value="/register"/>" class="button">Регистрация</a>
                        </div>
                        <div id="sidebar">
                            <div id="stwo-col">
                                <div class="sbox1">
                                    <h2>Категории</h2>
                                    <ul class="style2">
                                        <li class="icon icon-ok"><a href="#">Категория 1</a>
                                        </li>
                                        <li class="icon icon-ok"><a href="#">Категория 2</a>
                                        </li>
                                        <li class="icon icon-ok"><a href="#">Категория 3</a>
                                        </li>
                                        <li class="icon icon-ok"><a href="#">Категория 4</a>
                                        </li>
                                        <li class="icon icon-ok"><a href="#">Категория 5</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="banner-wrapper">
                        <div id="banner" class="container">
                            <div class="box-left">
                                <h2>Последние поступления</h2>
                                <span>Тут будет самый популярный товар или последние оступления.</span>
                            </div>
                            <div class="box-right"><a href="#" class="button button-big">Посмотреть</a>
                            </div>
                        </div>
                    </div>
                    <div id="featured-wrapper">
                        <div id="featured" class="container">
                            <div class="column1"><span class="icon icon-gift"></span>

                                <div class="title">
                                    <h2>Рубрика 1</h2>
                                </div>
                                <p>Тут идет описание рубрики.</p>
                            </div>
                            <div class="column2"><span class="icon icon-glass"></span>

                                <div class="title">
                                    <h2>Рубрика 2</h2>
                                </div>
                                <p>Тут идет описание рубрики.</p>
                            </div>
                            <div class="column3"><span class="icon icon-music"></span>

                                <div class="title">
                                    <h2>Рубрика 3</h2>
                                </div>
                                <p>Тут идет описание рубрики.</p>
                            </div>
                            <div class="column4"><span class="icon icon-group"></span>

                                <div class="title">
                                    <h2>Рубрика 4</h2>
                                </div>
                                <p>Тут идет описание рубрики.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="copyright" class="container">
                    <p>Copyright (c) 2013 Sitename.com. All rights reserved.</p>
                </div>
                <script type="text/javascript" src="js/shop.js"></script>
        </body>

        </html>
