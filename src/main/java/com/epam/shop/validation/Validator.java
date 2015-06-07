package com.epam.shop.validation;

import java.util.Map;

/**
 * Validator interface for beans
 * <p/>
 * Created by Oleh_Osyka on 22-Oct-14.
 */
public interface Validator<T> {

    /**
     * If parameter validation fails, put error message to specified parameter.
     *
     * @param formBean for validation
     * @return map with error messages or empty map if validation success
     */
    public Map<String, String> validate(T formBean);
}
