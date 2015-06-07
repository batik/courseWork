package com.epam.shop.utils;

import java.util.Random;

/**
 * Util class for generation random captcha id and value
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 24-Oct-14.
 */
public class RndNumberGenerator {

    private static final int CAPTCHA_SEED = 10000;

    public static int generate() {
        Random r = new Random();
        return CAPTCHA_SEED + r.nextInt(CAPTCHA_SEED * 10 - CAPTCHA_SEED);
    }
}
