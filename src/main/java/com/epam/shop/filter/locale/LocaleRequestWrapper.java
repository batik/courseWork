package com.epam.shop.filter.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

/**
 * Class for Task9
 * Created by Oleh_Osyka on -Dec-.
 */
public class LocaleRequestWrapper extends HttpServletRequestWrapper {
        
    private final Locale lang;	
            
    public LocaleRequestWrapper(Locale lang, HttpServletRequest request) {
        super(request);	
        this.lang = lang;	
    }	
            
    @Override	
    public Locale getLocale() {	
        return lang;	
    }	
            
    @Override	
    public Enumeration<Locale> getLocales() {
        Vector<Locale> locales = new Vector<>();
        locales.add(lang);
        return locales.elements();
    }	
}
