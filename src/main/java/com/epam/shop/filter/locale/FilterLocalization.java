package com.epam.shop.filter.locale;


import com.epam.shop.filter.locale.holder.LocaleHolder;
import com.epam.shop.filter.locale.holder.LocaleHolderFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 09-Dec-14.
 */
@WebFilter(filterName = "localization", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "store", value = "COOKIE"),
                @WebInitParam(name = "supportedLanguages", value = "en ru"),
                @WebInitParam(name = "defaultLocale", value = "en")
        })
public class FilterLocalization implements Filter {
    public static final String INIT_PARAMETER_NAME_LOCALES = "supportedLanguages";
    public static final String INIT_PARAMETER_NAME_DEFAULT_LOCALE = "defaultLocale";
    public static final String INIT_PARAMETER_STORE = "store";
    public static final String PARAMETER_NAME_LANG = "locale";
    public static final String SEPARATOR_SPACE = " ";
    private Map<String, Locale> supportedLocales = new HashMap<>();
    private Locale defaultLocale;
    private LocaleHolder localeHolder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String languages = filterConfig.getInitParameter(INIT_PARAMETER_NAME_LOCALES);
        for (String lang : Arrays.asList(languages.split(SEPARATOR_SPACE))) {
            supportedLocales.put(lang, new Locale(lang));
        }

        defaultLocale = supportedLocales.get(filterConfig.getInitParameter(INIT_PARAMETER_NAME_DEFAULT_LOCALE));
        if (defaultLocale == null)
            throw new RuntimeException("Incorrect default locale");
        localeHolder = LocaleHolderFactory.getLanguageHolder(filterConfig.getInitParameter(INIT_PARAMETER_STORE), filterConfig
                .getServletContext(), this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        final String language = req.getParameter(PARAMETER_NAME_LANG);
        Locale locale;
        if (language != null && checkSupportLanguage(language)) {
            locale = supportedLocales.get(language);
        } else {
            locale = defineUserLocale(req, resp);
        }
        LocaleRequestWrapper requestWrapper = new LocaleRequestWrapper(locale, req);
        localeHolder.save(req, resp, locale);
        chain.doFilter(requestWrapper, resp);
    }

    public boolean checkSupportLanguage(String language) {
        return supportedLocales.containsKey(language);
    }

    private Locale defineUserLocale(HttpServletRequest req, HttpServletResponse resp) {
        Locale locale = localeHolder.get(req, resp);
        if (locale != null)
            return locale;
        else
            locale = getLocaleFromBrowser(req);

        return locale != null ? locale : defaultLocale;
    }

    @Override
    public void destroy() {

    }

    private Locale getLocaleFromBrowser(HttpServletRequest request) {
        final Enumeration<Locale> locales = request.getLocales();
        while (locales != null && locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (supportedLocales.containsValue(locale)) {
                return locale;
            }
        }
        return null;
    }
}