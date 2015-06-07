package com.epam.shop.beans;

import java.io.Serializable;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 08-Dec-14.
 */
public class OrderInfoBean implements Serializable {

    private String cardNumber;
    private String address;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
