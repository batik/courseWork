package com.epam.shop.dao.category;

import com.epam.shop.dao.AbstractCRUDDao;
import com.epam.shop.domain.Category;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.category.
 */
@Repository
public class CategoryMongoImpl extends AbstractCRUDDao<Category> implements CategoryDao {


    public CategoryMongoImpl() {
        super(Category.class, Category.COLLECTION_NAME);
    }

    @Override
    public Category findCategory(String name) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)), Category.class);
    }

    @Override
    protected String getId(Category entity) {
        return entity.getId();
    }
}
