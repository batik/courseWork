package com.epam.shop.service.captcha;

import com.epam.shop.entity.Captcha;
import com.epam.shop.entity.CaptchaKeeper;
import com.epam.shop.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Implementation for storing captcha in cookies
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 21-Oct-14.
 */
@Component("cookieCaptchaService")
public class CookieCaptchaService implements ICaptchaService, Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieCaptchaService.class);

    private CaptchaKeeper keeper;

    @Autowired
    public CookieCaptchaService(CaptchaKeeper keeper) {
        this.keeper = keeper;
        keeper.startClean();
    }

    @Override
    public boolean isRight(HttpServletRequest request) {
        try {
            int userInput = Integer.parseInt(request.getParameter(PARAMETERS_CAPTCHA));
            Cookie cookie = getCaptchaCookie(CAPTCHA_ATTRIBUTE_NAME, request.getCookies());
            if (cookie != null) {
                int captchaId = Integer.parseInt(cookie.getValue());
                Captcha existingCaptcha = keeper.getAndRemove(captchaId);
                return existingCaptcha.getValue() == userInput;
            }
            return false;
        } catch (NumberFormatException e) {
            LOGGER.error("Input string in captcha field expect number!");
            return false;
        }
    }

    @Override
    public void addToKeeper(Captcha captcha) {
        keeper.addCaptcha(captcha);
    }

    @Override
    public void saveInAttributes(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCaptchaCookie(CAPTCHA_ATTRIBUTE_NAME, request.getCookies());
        if (cookie == null) {
            cookie = new Cookie(CAPTCHA_ATTRIBUTE_NAME, String.valueOf(captcha.getId()));
        } else {
            cookie.setValue(String.valueOf(captcha.getId()));
        }
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(MAX_COOKIE_AGE);
        response.addCookie(cookie);
    }

    private Cookie getCaptchaCookie(String name, Cookie... cookies) {
        for (Cookie current : cookies) {
            if (name.equals(current.getName())) {
                return current;
            }
        }
        return null;
    }

}
