package com.epam.shop.dao.item;

import com.epam.shop.dao.CRUDDao;
import com.epam.shop.domain.Category;
import com.epam.shop.domain.Item;
import com.epam.shop.entity.FilterCriteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.item.
 */
public interface ItemDao extends CRUDDao<Item> {

    /**
     * Find item by name
     *
     * @param name of item
     * @return founded item or null if nothing found.
     */
    public Item findItem(String name);

    public List<Item> findItemsByCategory(Category category);

    /**
     * Find total items count for using filter
     *
     * @param preparedQuery - filter params
     * @return count items, or 0 if nothing found
     */
    public int getTotalListCount(FilterCriteria criteria);

    /**
     * Filter and get items
     *
     * @param preparedQuery - filtering params
     * @return item list for shown.
     */
    public List<Item> getFilterItems(FilterCriteria criteria);
}
