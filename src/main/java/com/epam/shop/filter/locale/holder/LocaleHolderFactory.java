package com.epam.shop.filter.locale.holder;


import com.epam.shop.filter.locale.FilterLocalization;

import javax.servlet.ServletContext;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 10-Dec-14.
 */
public class LocaleHolderFactory {

    public static final String CONTEXT_PARAM_NAME_TERM_COOKIE = "cookie-timeout";

    public static LocaleHolder getLanguageHolder(String storeParameter, ServletContext ctx, FilterLocalization localizationFilter) {
        switch (storeParameter) {
            case "SESSION":
                return new SessionLocaleHolder();
            case "COOKIE":
                return new CookieLocaleHolder(localizationFilter, Integer.valueOf(ctx.getInitParameter(CONTEXT_PARAM_NAME_TERM_COOKIE)));
            default:
                throw new RuntimeException("Incorrect store parameter");
        }
    }

}
