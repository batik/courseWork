package com.epam.shop.service.product;


import com.epam.shop.dao.category.CategoryDao;
import com.epam.shop.dao.item.ItemDao;
import com.epam.shop.domain.Category;
import com.epam.shop.domain.Item;
import com.epam.shop.entity.FilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 12-Nov-14.
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Item get(String id) {
        return itemDao.getByID(id);
    }

    @Override
    public Item add(Item entity) {
        String objectId = UUID.randomUUID().toString();
        entity.setId(objectId);
        itemDao.add(entity);
        return itemDao.getByID(objectId);
    }

    @Override
    public void delete(Item entity) {
        itemDao.delete(entity);
    }

    @Override
    public void update(Item entity) {
        itemDao.update(entity);
    }

    @Override
    public int getCountProducts(FilterCriteria criteria) {
        return itemDao.getTotalListCount(criteria);
    }

    @Override
    public List<Item> filter(FilterCriteria criteria) {
        return itemDao.getFilterItems(criteria);

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getEntitiesList();
    }
}
