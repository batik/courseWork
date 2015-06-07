package com.epam.shop.service.captcha;

import com.epam.shop.entity.Captcha;
import com.epam.shop.entity.CaptchaKeeper;
import com.epam.shop.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Implementation for storing captcha in hidden field
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 24-Oct-14.
 */
@Component("hiddenCaptchaService")
public class HiddenCaptchaService implements ICaptchaService, Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(HiddenCaptchaService.class);

    private CaptchaKeeper keeper;

    @Autowired
    public HiddenCaptchaService(CaptchaKeeper keeper) {
        this.keeper = keeper;
        keeper.startClean();
    }

    @Override
    public boolean isRight(HttpServletRequest request) {
        try {
            int userInput = Integer.parseInt(request.getParameter(PARAMETERS_CAPTCHA));
            String hiddenId = request.getParameter(PARAMETERS_CAPTCHA_HIDDEN_FIELD);
            if (hiddenId != null && !hiddenId.isEmpty()) {
                int captchaId = Integer.parseInt(hiddenId);
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
        String generatedHiddenId = request.getParameter(PARAMETERS_CAPTCHA_HIDDEN_FIELD);
        if (generatedHiddenId != null) {
            int generatedCaptchaId = Integer.parseInt(generatedHiddenId);
            captcha.setId(generatedCaptchaId);
        } else {
            LOGGER.error("Can't generate captcha with id in hidden field");
        }
    }
}