package com.epam.shop.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Util Captcha generation class. Used java.awt.* to generate BufferedImage and return to servlet.
 * <p/>
 * Class for Task8
 * Created by Oleh_Osyka on 20-Oct-14.
 */
public class CaptchaGenerator {

    private static final int MAX_LINES = 5;
    private static final int WIDTH = 85;
    private static final int HEIGHT = 35;
    private static final String FONT_NAME = "TimesRoman";

    /**
     * Captcha generator from numbers.
     *
     * @param number to draw on image
     * @return BufferedImage with numbers and 5 random lines
     */
    public static BufferedImage generateCaptcha(int number) {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();

        //set font
        Font font = new Font(FONT_NAME, Font.BOLD, 20);
        graphics.setFont(font);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(Integer.toString(number));
        int stringHeight = fontMetrics.getAscent();

        //paint background
        graphics.setPaint(Color.black);

        //draw numbers
        graphics.setColor(Color.white);
        graphics.drawString(String.valueOf(number), (WIDTH - stringWidth) / 2, HEIGHT / 2 + stringHeight / 4);

        //loop for drawing lines
        for (int i = 0; i < MAX_LINES; i++) {
            Random r = new Random();
            int x = Math.abs(r.nextInt()) % WIDTH;
            int y = Math.abs(r.nextInt()) % HEIGHT;
            graphics.setStroke(new BasicStroke(new Random().nextInt(4)));
            graphics.drawLine(x, y, (10 + x + new Random().nextInt(20)), (y + new Random().nextInt(3)));
        }
        return img;
    }
}
