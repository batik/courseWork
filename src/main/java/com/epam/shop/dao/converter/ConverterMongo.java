package com.epam.shop.dao.converter;


import com.epam.shop.entity.FilterCriteria;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * SQL implementation of converter
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 01-Dec-14.
 */
public class ConverterMongo implements Converter {

    private Criteria criteriak = new Criteria();

    @Override
    public Criteria convert(FilterCriteria criteria) {
        if (criteria.getProductName() != null) {
            criteriak.and("name").is(criteria.getProductName());
        }
        if (criteria.getProductAuthor() != null) {
            criteriak.and("information.author").is(criteria.getProductAuthor());
        }
        if (criteria.getMinYear() != null) {
            criteriak.and("information.year").gte(criteria.getMinYear());
        }
        if (criteria.getMaxYear() != null) {
            criteriak.and("information.year").lte(criteria.getMaxYear());
        }
        if (criteria.getMinPrice() != null) {
            criteriak.and("price").gte(criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            criteriak.and("price").lte(criteria.getMaxPrice());
        }
        List<Integer> categories = criteria.getCategories();
        if (categories != null && !categories.isEmpty()) {
            criteriak.and("categoryList.id").in(categories);
        }
        return criteriak;
    }

}
