package com.epam.shop.filter.locale.holder;


import com.epam.shop.filter.locale.FilterLocalization;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 10-Dec-14.
 */
public class CookieLocaleHolder implements LocaleHolder {
    public static final String COOKIE_NAME_LANG = "lang";
    private int cookieTimeout;
    private FilterLocalization localizationFilter;

    public CookieLocaleHolder(FilterLocalization localizationFilter, int termCookieSeconds) {
        this.localizationFilter = localizationFilter;
        this.cookieTimeout = termCookieSeconds;
    }

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = new Cookie(COOKIE_NAME_LANG, locale.getLanguage());
        cookie.setMaxAge(cookieTimeout);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    public Locale get(HttpServletRequest request, HttpServletResponse response) {
        Cookie langCookie = getCaptchaCookie(COOKIE_NAME_LANG, request.getCookies());
        return langCookie != null ? new Locale(langCookie.getValue()) : null;
    }

    private Cookie getCaptchaCookie(String name, Cookie... cookies) {
        if (cookies != null)
            for (Cookie current : cookies) {
                if (name.equals(current.getName())) {
                    return current;
                }
            }
        return null;
    }


}

