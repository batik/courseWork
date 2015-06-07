package com.epam.shop.beans;

import java.io.Serializable;
import java.util.List;

/**
 * JavaBean object for keeping user information till validation won't complete.
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 22-Oct-14.
 */
public class UserBean implements Serializable {

    private String login;
    private String name;
    private String pass;
    private String email;
    private String gender;
    private List<String> feeds;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<String> feeds) {
        this.feeds = feeds;
    }

}
