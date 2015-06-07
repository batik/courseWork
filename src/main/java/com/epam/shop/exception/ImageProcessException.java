package com.epam.shop.exception;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 10-Nov-14.
 */
public class ImageProcessException extends RuntimeException {

    public ImageProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageProcessException(String message) {
        super(message);
    }
}