package com.epam.shop.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 01-Dec-14.
 */
public class FilterCriteria {

    private int pageOffset;
    private int listLength;
    private String productName;
    private String productAuthor;
    private Integer minYear;
    private Integer maxYear;
    private Integer minPrice;
    private Integer maxPrice;
    private String sortType;
    private List<Integer> categories = new ArrayList<>();

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAuthor() {
        return productAuthor;
    }

    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public void setMinYear(Integer minYear) {
        this.minYear = minYear;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void addCategorie(Integer categoryId) {
        categories.add(categoryId);
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public int getListLength() {
        return listLength;
    }

    public void setListLength(int listLength) {
        this.listLength = listLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterCriteria)) return false;

        FilterCriteria that = (FilterCriteria) o;

        if (listLength != that.listLength) return false;
        if (pageOffset != that.pageOffset) return false;
        if (categories != null ? !categories.equals(that.categories) : that.categories != null) return false;
        if (maxPrice != null ? !maxPrice.equals(that.maxPrice) : that.maxPrice != null) return false;
        if (maxYear != null ? !maxYear.equals(that.maxYear) : that.maxYear != null) return false;
        if (minPrice != null ? !minPrice.equals(that.minPrice) : that.minPrice != null) return false;
        if (minYear != null ? !minYear.equals(that.minYear) : that.minYear != null) return false;
        if (productAuthor != null ? !productAuthor.equals(that.productAuthor) : that.productAuthor != null)
            return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (sortType != null ? !sortType.equals(that.sortType) : that.sortType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageOffset;
        result = 31 * result + listLength;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productAuthor != null ? productAuthor.hashCode() : 0);
        result = 31 * result + (minYear != null ? minYear.hashCode() : 0);
        result = 31 * result + (maxYear != null ? maxYear.hashCode() : 0);
        result = 31 * result + (minPrice != null ? minPrice.hashCode() : 0);
        result = 31 * result + (maxPrice != null ? maxPrice.hashCode() : 0);
        return result;
    }
}
