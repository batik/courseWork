package com.epam.shop.domain.subdomain.order;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.subdomain.order.
 */
@Document
public class LineItems {

    private String itemId;
    private Integer quantity;
    private Double totalPriceForItems;

    public LineItems(String itemId, Integer quantity, Double totalPriceForItems) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalPriceForItems = totalPriceForItems;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPriceForItems() {
        return totalPriceForItems;
    }

    public void setTotalPriceForItems(Double totalPriceForItems) {
        this.totalPriceForItems = totalPriceForItems;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LineItems{");
        sb.append("itemId='").append(itemId).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", totalPriceForItems=").append(totalPriceForItems);
        sb.append('}');
        return sb.toString();
    }
}
