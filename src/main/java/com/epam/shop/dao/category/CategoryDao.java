package com.epam.shop.dao.category;

import com.epam.shop.dao.CRUDDao;
import com.epam.shop.domain.Category;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.category.
 */
public interface CategoryDao extends CRUDDao<Category> {

    /**
     * Find category by name
     *
     * @param name of item
     * @return founded item or null if nothing found.
     */
    public Category findCategory(String name);
}
