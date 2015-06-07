package com.epam.shop.dao.converter;


import com.epam.shop.entity.FilterCriteria;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * Statements converter for DB
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 01-Dec-14.
 */
public interface Converter {

    /**
     * Cenvertion method
     *
     * @param criteria - filter object
     * @return prepared pair of SQL query string and params for PreparedStatement
     */
    public Criteria convert(FilterCriteria criteria);
}
