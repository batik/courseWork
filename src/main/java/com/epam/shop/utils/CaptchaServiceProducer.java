package com.epam.shop.utils;
//TODO

import com.epam.shop.service.captcha.ICaptchaService;

/**
 * Util class for producing different captcha service using service name
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 24-Oct-14.
 */
public class CaptchaServiceProducer {

    private static final String CAPTCHA_SERVICE_HIDDEN = "csHidden";
    private static final String CAPTCHA_SERVICE_COOKIE = "csCookie";
    private static final String CAPTCHA_SERVICE_SESSION = "csSession";

    /**
     * Get captcha service
     *
     * @param serviceName - string with captcha type
     * @return specified CaptchaService or null
     */
    public static ICaptchaService getCaptchaService(String serviceName) {
//        if (CAPTCHA_SERVICE_SESSION.equals(serviceName)) {
//            return new SessionCaptchaService();
//        }
//        if (CAPTCHA_SERVICE_COOKIE.equals(serviceName)) {
//            return new CookieCaptchaService();
//        }
//        if (CAPTCHA_SERVICE_HIDDEN.equals(serviceName)) {
//            return new HiddenCaptchaService();
//        }
        throw new IllegalArgumentException("No valid startup input found.");
    }
}
