package com.epam.shop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author Oleh_Osyka on 09.02.2015 for Spring-shop.com.epam.shop.domain.subdomain.item.
 */
@Document(collection = Category.COLLECTION_NAME)
public class Category {

    public static final String COLLECTION_NAME = "categories";

    @Id
    private String id;

    private String name;

    private List<Category> parent;

    private List<Category> children;

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

    public List<Category> getParent() {
        return parent;
    }

    public void setParent(List<Category> parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!id.equals(category.id)) return false;
        if (!name.equals(category.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Category{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", parent=").append(parent);
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}
