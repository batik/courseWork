package com.epam.shop.domain.subdomain.order;

import org.bson.types.BSONTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.subdomain.order.
 */
@Document
public class OrderDetailedInformation {

    private String shippingComment;
    private String shippingAdress;
    private Date orderDate;

    public String getShippingComment() {
        return shippingComment;
    }

    public void setShippingComment(String shippingComment) {
        this.shippingComment = shippingComment;
    }

    public String getShippingAdress() {
        return shippingAdress;
    }

    public void setShippingAdress(String shippingAdress) {
        this.shippingAdress = shippingAdress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDetailedInformation{");
        sb.append("shippingComment='").append(shippingComment).append('\'');
        sb.append(", shippingAdress='").append(shippingAdress).append('\'');
        sb.append(", orderDate=").append(orderDate);
        sb.append('}');
        return sb.toString();
    }
}
