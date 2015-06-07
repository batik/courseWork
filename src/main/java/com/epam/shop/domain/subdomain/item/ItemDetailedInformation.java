package com.epam.shop.domain.subdomain.item;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.subdomain.item.
 */
@Document
public class ItemDetailedInformation {

    private String author;
    private Integer year;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemDetailedInformation{");
        sb.append("author='").append(author).append('\'');
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}
