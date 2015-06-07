package com.epam.shop.entity;

import java.util.List;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 01-Dec-14.
 */
public class PreparedPair {

    private final String sqlStatement;
    private final List<Object> preparedParams;

    public PreparedPair(String sqlStatement, List<Object> preparedParams) {
        this.sqlStatement = sqlStatement;
        this.preparedParams = preparedParams;
    }

    public String getSqlStatement() {
        return sqlStatement;
    }

    public List<Object> getPreparedParams() {
        return preparedParams;
    }
}
