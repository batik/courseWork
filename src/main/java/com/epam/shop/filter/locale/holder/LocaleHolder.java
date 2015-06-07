package com.epam.shop.filter.locale.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 10-Dec-14.
 */
public interface LocaleHolder {

    public void save(HttpServletRequest request, HttpServletResponse response, Locale locale);

    public Locale get(HttpServletRequest request, HttpServletResponse response);

}
