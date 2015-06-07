package com.epam.shop.domain;

import com.epam.shop.domain.subdomain.order.LineItems;
import com.epam.shop.domain.subdomain.order.OrderDetailedInformation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.
 */
@Document(collection = Order.COLLECTION_NAME)
public class Order {

    public static final String COLLECTION_NAME = "orders";

    @Id
    private String id;

    private String status;

    private OrderDetailedInformation detailedInformation;

    private String userId;

    private List<LineItems> itemsList;

    private Double total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDetailedInformation getDetailedInformation() {
        return detailedInformation;
    }

    public void setDetailedInformation(OrderDetailedInformation detailedInformation) {
        this.detailedInformation = detailedInformation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<LineItems> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<LineItems> itemsList) {
        this.itemsList = itemsList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        if (!userId.equals(order.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }
}
