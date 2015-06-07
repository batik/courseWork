package com.epam.shop.service.product;


import com.epam.shop.domain.Category;
import com.epam.shop.domain.Item;
import com.epam.shop.entity.FilterCriteria;
import com.epam.shop.service.Service;

import java.util.List;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 12-Nov-14.
 */
public interface IProductService extends Service<Item> {

    public int getCountProducts(FilterCriteria criteria);

    public List<Item> filter(FilterCriteria criteria);

    public List<Category> getAllCategories();

}
