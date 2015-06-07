package com.epam.shop.utils;

import com.epam.shop.exception.CryptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Util class for encrypting passwords to MD5 hash
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 10-Nov-14.
 */
public class MD5PasswordGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5PasswordGenerator.class);

    public static String encrypt(String passwordToHash) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Can't encrypt password to MD5");
            throw new CryptException("Can't encrypt password to MD5", e);
        }
    }

}
