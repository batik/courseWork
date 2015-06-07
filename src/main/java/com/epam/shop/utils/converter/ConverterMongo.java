package com.epam.shop.utils.converter;


import com.epam.shop.entity.FilterCriteria;
import com.epam.shop.entity.PreparedPair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * SQL implementation of converter
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 01-Dec-14.
 */
public class ConverterMongo implements Converter {

    private final String startSelectStatement;
    private StringBuilder builder;
    private List<Object> params;

    public ConverterMongo(String select) {
        startSelectStatement = select;
        builder = new StringBuilder(select);
        params = new ArrayList<>();
    }

    @Override
    public PreparedPair convert(FilterCriteria criteria) {
        if (criteria.getProductName() != null) {
            appendStatement(builder);
            builder.append("items.name LIKE ?");
            params.add("%" + criteria.getProductName() + "%");
        }
        if (criteria.getProductAuthor() != null) {
            appendStatement(builder);
            builder.append("items.author LIKE ?");
            params.add("%" + criteria.getProductAuthor() + "%");
        }
        if (criteria.getMinYear() != null) {
            appendStatement(builder);
            builder.append("items.year >=?");
            params.add(criteria.getMinYear());
        }
        if (criteria.getMaxYear() != null) {
            appendStatement(builder);
            builder.append("items.year <=?");
            params.add(criteria.getMaxYear());
        }
        if (criteria.getMinPrice() != null) {
            appendStatement(builder);
            builder.append("items.price >=?");
            params.add(criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            appendStatement(builder);
            builder.append("items.price <=?");
            params.add(criteria.getMaxPrice());
        }
        List<Integer> categories = criteria.getCategories();
        if (categories != null && !categories.isEmpty()) {
            appendStatement(builder);
            Iterator itr = categories.iterator();
            for (; itr.hasNext(); ) {
                builder.append("Category_ID =?");
                params.add(itr.next());
                if (itr.hasNext()) {
                    builder.append(" OR ");
                }
            }
        }
        if (criteria.getSortType() != null && (criteria.getSortType().equals("ASC") || criteria.getSortType().equals("DESC"))) {
            builder.append(" order by items.name ").append(criteria.getSortType());
        }
        return new PreparedPair(builder.toString(), params);
    }

    /**
     * Method for checking words to append statements
     */
    private void appendStatement(StringBuilder builder) {
        if (!builder.toString().endsWith(startSelectStatement)) {
            builder.append(" AND ");
        } else {
            builder.append("WHERE ");
        }
    }

}
