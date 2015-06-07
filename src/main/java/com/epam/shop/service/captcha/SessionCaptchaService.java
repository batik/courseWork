package com.epam.shop.service.captcha;

import com.epam.shop.entity.Captcha;
import com.epam.shop.entity.CaptchaKeeper;
import com.epam.shop.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 24-Oct-14.
 */
@Primary
@Component("sessionCaptchaService")
public class SessionCaptchaService implements ICaptchaService, Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCaptchaService.class);

    private CaptchaKeeper keeper;

    @Autowired
    public SessionCaptchaService(CaptchaKeeper keeper) {
        this.keeper = keeper;
        keeper.startClean();
    }

    @Override
    public boolean isRight(HttpServletRequest request) {
        try {
            int userInput = Integer.parseInt(request.getParameter(PARAMETERS_CAPTCHA));
            int captchaId = (Integer) request.getSession().getAttribute(CAPTCHA_ATTRIBUTE_NAME);
            if (captchaId != 0) {
                Captcha existingCaptcha = keeper.getAndRemove(captchaId);
                request.getSession().removeAttribute(CAPTCHA_ATTRIBUTE_NAME);
                return existingCaptcha.getValue() == userInput;
            }
            return false;
        } catch (NumberFormatException e) {
            LOGGER.error("Input string in captcha field expect number!");
            return false;
        } catch (NullPointerException e) {
            LOGGER.error("Captcha expired!");
            return false;
        }
    }

    @Override
    public void addToKeeper(Captcha captcha) {
        keeper.addCaptcha(captcha);
    }

    @Override
    public void saveInAttributes(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(CAPTCHA_ATTRIBUTE_NAME, captcha.getId());

    }

}
