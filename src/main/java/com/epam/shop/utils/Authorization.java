package com.epam.shop.utils;


import com.epam.shop.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Util class for authorization.
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 27-Oct-14.
 */
public class Authorization {

    private static final String USER_SESSION = "user";

    /**
     * Get user and request and set user id to session.
     *
     * @param user    entity with id
     * @param request from servlet
     */
    public static void login(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_SESSION, user);
    }

    /**
     * Get session from request and invalidate it.
     *
     * @param request from servlet
     */
    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
