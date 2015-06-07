package com.epam.shop.utils;

/**
 * Class for Task8
 * Created by Oleh_Osyka on 20-Oct-14.
 */
public interface Constants {

    String MAIN_PAGE = "/WEB-INF/index.jsp";
    String REGISTRATION_PAGE = "/WEB-INF/register.jsp";
    String LOGIN_PAGE = "/WEB-INF/login.jsp";
    String CHECKOUT_PAGE = "/WEB-INF/checkout.jsp";
    String PAYMENT_PAGE = "/WEB-INF/checkoutRequisite.jsp";
    String PAYMENT_RESULT_PAGE = "/WEB-INF/checkoutResult.jsp";
    String SHOP_PAGE = "/WEB-INF/shop.jsp";
    int MAX_COOKIE_AGE = 300;

    String CAPTCHA_ATTRIBUTE_NAME = "captcha";

    String PARAMETERS_LOGIN = "first_name";
    String PARAMETERS_NAME = "last_name";
    String PARAMETERS_PASS = "pass";
    String PARAMETERS_EMAIL = "email";
    String PARAMETERS_GENDER = "sex";
    String PARAMETERS_FEEDS = "feeds";
    String PARAMETERS_CAPTCHA = "captcha";
    String PARAMETERS_CAPTCHA_HIDDEN_FIELD = "captchaHidden";
    String PARAMETERS_USER_BEAN = "userBean";
    String PARAMETERS_REGISTER_FORM_ERRORS = "rfErrors";
    String PARAMETERS_LAST_PAGE = "viewid";
    String PARAMETERS_AVATAR = "file";

    String LISTENERS_USER_SERVICE = "userService";
    String LISTENERS_CAPTCHA_SERVICE = "captchaService";
    String LISTENERS_AVATAR_SERVICE = "avatarService";
    String LISTENERS_FEED_SERVICE = "feedService";
    String LISTENERS_PRODUCT_SERVICE = "productService";
    String LISTENERS_ORDER_SERVICE = "orderService";
    String LISTENERS_ORDERED_ITEM_SERVICE = "orderedItemService";


    String CAPTCHA_SERVICE_TYPE = "csType";

    String DATABASE_CONFIG_FILE = "src/main/resources/db.properties";
    String SQL_CONFIG_FILE = "src/main/resources/sql.properties";

    String SESSION_USER_CART = "userCart";
    String SESSION_USER_CART_INFO = "userOrderInfo";

}
