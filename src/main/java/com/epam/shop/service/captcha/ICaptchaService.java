package com.epam.shop.service.captcha;


import com.epam.shop.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Captcha service interface for using and validating captcha
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 21-Oct-14.
 */
public interface ICaptchaService {

    /**
     * Checks if inputted captcha is right.
     *
     * @param request - for getting captcha public id
     * @return true if inputted value is on captcha.
     */
    public boolean isRight(HttpServletRequest request);

    /**
     * Adds captcha to specified container
     */
    public void addToKeeper(Captcha captcha);

    /**
     * Save captcha to attribute
     */
    public void saveInAttributes(Captcha captcha, HttpServletRequest request, HttpServletResponse response);

}
