package com.epam.shop.domain.subdomain.user;

import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.subdomain.
 */
@Document
public class UserDetails {

    private String gender;
    private String role;
    private Timestamp lastVisit;
    private UserBan userBan;
    private List<String> feeds;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Timestamp lastVisit) {
        this.lastVisit = lastVisit;
    }

    public UserBan getUserBan() {
        return userBan;
    }

    public void setUserBan(UserBan userBan) {
        this.userBan = userBan;
    }

    public List<String> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<String> feeds) {
        this.feeds = feeds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserDetails{");
        sb.append("gender='").append(gender).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", lastVisit=").append(lastVisit);
        sb.append(", userBan=").append(userBan);
        sb.append(", feeds=").append(feeds);
        sb.append('}');
        return sb.toString();
    }
}
