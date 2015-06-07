package com.epam.shop.domain.subdomain.user;

import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.subdomain.
 */
@Document
public class UserBan {

    private Boolean isBanned;
    private Timestamp banDate;

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

    public Timestamp getBanDate() {
        return banDate;
    }

    public void setBanDate(Timestamp banDate) {
        this.banDate = banDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserBan{");
        sb.append("isBanned=").append(isBanned);
        sb.append(", banDate=").append(banDate);
        sb.append('}');
        return sb.toString();
    }
}
