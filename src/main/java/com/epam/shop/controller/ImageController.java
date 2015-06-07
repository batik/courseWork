package com.epam.shop.controller;

import com.epam.shop.entity.Captcha;
import com.epam.shop.service.captcha.ICaptchaService;
import com.epam.shop.service.image.IImageService;
import com.epam.shop.utils.CaptchaGenerator;
import com.epam.shop.utils.RndNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author Oleh_Osyka on 12.02.2015 for Spring-shop.com.epam.shop.controller.
 */
@Controller
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ICaptchaService captchaService;
    @Autowired
    private IImageService imageService;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showCaptcha(HttpServletRequest request,
                                              HttpServletResponse response) throws IOException {
        int captchaId = RndNumberGenerator.generate();
        int captchaVal = RndNumberGenerator.generate();

        Captcha captcha = new Captcha(captchaId, captchaVal);
        captchaService.addToKeeper(captcha);
        captchaService.saveInAttributes(captcha, request, response);
        return new ResponseEntity<>(imageToByteArray(CaptchaGenerator.generateCaptcha(captchaVal), "jpg"), HttpStatus.OK);
    }

    @RequestMapping(value = "/avatar/*", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showAvatar(HttpServletRequest request) throws IOException {
        String avatarName = request.getRequestURI().substring(request.getContextPath().length()).replace("/avatar/", "");
        File avatar = imageService.getImage("/avatars/", avatarName);
        if (!avatar.exists()) {
            logger.warn("Avatar not found in {}", avatar.getAbsoluteFile());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InputStream stream = new FileInputStream(avatar);
        return new ResponseEntity<>(imageToByteArray(ImageIO.read(stream), "jpg"), HttpStatus.OK);
    }

    @RequestMapping(value = "/product/picture/*", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showProductImage(HttpServletRequest request) throws IOException {
        String pictureName = request.getRequestURI().substring(request.getContextPath().length()).replace("/product/picture/", "");
        File image = imageService.getImage("/products/", pictureName);
        if (!image.exists()) {
            logger.warn("Image not found in {}", image.getAbsoluteFile());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InputStream stream = new FileInputStream(image);
        return new ResponseEntity<>(imageToByteArray(ImageIO.read(stream), "jpg"), HttpStatus.OK);
    }

    private byte[] imageToByteArray(BufferedImage img, String type) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img, type, bao);
        return bao.toByteArray();
    }
}
