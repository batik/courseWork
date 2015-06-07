package com.epam.shop.entity;

/**
 * Entity for storing captcha information.
 * <p/>
 * Class for Task9.
 * Created by Oleh_Osyka on 21-Oct-14.
 */
public class Captcha {

    private int id;
    private int value;
    private long creationTime;

    /**
     * @param id    - public id for captcha
     * @param value - expected input for captcha
     */
    public Captcha(int id, int value) {
        setId(id);
        setValue(value);
        this.creationTime = System.currentTimeMillis();
    }

    /**
     * Checking if captcha lifetime expired.
     *
     * @param timeout for captcha lifetime
     * @return true if captcha still alive
     */
    public boolean isExpired(int timeout) {
        return timeout <= System.currentTimeMillis() - creationTime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
