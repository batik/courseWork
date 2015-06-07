package com.epam.shop.filter.locale.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 10-Dec-14.
 */
public class SessionLocaleHolder implements LocaleHolder {
    public static final String SESSION_ATTRIBUTE_LOCALE = "locale";

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        request.getSession().setAttribute(SESSION_ATTRIBUTE_LOCALE, locale);
    }

    @Override
    public Locale get(HttpServletRequest request, HttpServletResponse response) {
        return (Locale) request.getSession().getAttribute(SESSION_ATTRIBUTE_LOCALE);
    }
}
