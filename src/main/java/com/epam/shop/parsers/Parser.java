package com.epam.shop.parsers;


import com.epam.shop.beans.UserBean;

import javax.servlet.http.HttpServletRequest;

/**
 * Parser interface for creating userBeans from request.
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 29-Oct-14.
 */
public interface Parser {

    /**
     * Parsing inputted date to UserBean
     *
     * @param request for parsing
     * @return UserProfileBean with parsed information.
     */
    public UserBean parse(HttpServletRequest request);
}
