package com.epam.shop.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Help class for keeping captcha and public id. Supports self cleaning.
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 21-Oct-14.
 */
@Component("keeper")
public class CaptchaKeeper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaKeeper.class);

    private Map<Integer, Captcha> captchaMap = new ConcurrentHashMap<>();
    private Timer timer;

    @Value("${captcha.maxLife}")
    private Integer timeout;


    /**
     * Add generated captcha object to captcha keeper
     *
     * @param captcha for adding
     */
    public void addCaptcha(Captcha captcha) {
        captchaMap.put(captcha.getId(), captcha);
    }

    /**
     * Removes captcha by id and return captcha object.
     *
     * @param id for captcha to remove
     * @return Captcha with was removed or null if map is empty or captcha not found
     */
    public Captcha getAndRemove(int id) {
        try {
            return captchaMap.remove(id);
        } catch (NullPointerException e) {
            LOGGER.error("Captcha map is empty");
            return null;
        }
    }

    /**
     * Method for cleaning keeper. Starts new timer task with keeper timeout. And removes expired captcha.
     */
    public void startClean() {
        timer = new Timer();
        TimerTask schedule = new TimerTask() {
            @Override
            public void run() {
                for (Captcha current : captchaMap.values()) {
                    if (current.isExpired(timeout)) {
                        getAndRemove(current.getId());
                    }
                }
            }
        };
        timer.schedule(schedule, 0, timeout);
    }

    /**
     * Terminate clean task
     */
    public void endClean() {
        timer.cancel();
        LOGGER.debug("Keeper cleaning canceled.");
    }
}
