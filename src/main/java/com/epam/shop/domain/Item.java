package com.epam.shop.domain;

import com.epam.shop.domain.subdomain.item.ItemDetailedInformation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.
 */
@Document(collection = Item.COLLECTION_NAME)
public class Item {

    public static final String COLLECTION_NAME = "items";

    @Id
    private String id;

    private String name;

    private String description;

    private List<Category> categoryList;

    private ItemDetailedInformation information;

    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ItemDetailedInformation getInformation() {
        return information;
    }

    public void setInformation(ItemDetailedInformation information) {
        this.information = information;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (!name.equals(item.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", categoryList=").append(categoryList);
        sb.append(", information=").append(information);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
