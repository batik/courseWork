package com.epam.shop.dao.item;

import com.epam.shop.dao.AbstractCRUDDao;
import com.epam.shop.dao.converter.Converter;
import com.epam.shop.dao.converter.ConverterMongo;
import com.epam.shop.domain.Category;
import com.epam.shop.domain.Item;
import com.epam.shop.entity.FilterCriteria;
import com.epam.shop.entity.PreparedPair;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.item.
 */
@Repository
public class ItemMongoImpl extends AbstractCRUDDao<Item> implements ItemDao {


    public ItemMongoImpl() {
        super(Item.class, Item.COLLECTION_NAME);
    }

    @Override
    public Item findItem(String name) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)), Item.class);
    }

    @Override
    public List<Item> findItemsByCategory(Category category) {
        String st = Query.query(Criteria.where("categoryList.name").is(category.getName())).toString();
        return mongoTemplate.find(Query.query(Criteria.where("categoryList.name").is(category.getName())), Item.class, Item.COLLECTION_NAME);
    }

    @Override
    public int getTotalListCount(FilterCriteria criteria) {
        Converter conv = new ConverterMongo();
        Criteria criteriak = conv.convert(criteria);
        Query query = new Query(criteriak);
        return (int) mongoTemplate.count(query, Item.COLLECTION_NAME);
    }

    @Override
    public List<Item> getFilterItems(FilterCriteria criteria) {
        Converter conv = new ConverterMongo();
        Criteria criteriak = conv.convert(criteria);
        Query query = new Query(criteriak);
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        if (criteria.getSortType() != null && (criteria.getSortType().equals("DESC"))) {
            sort = new Sort(Sort.Direction.DESC, "name");
        }
        query.with(sort);
        query.skip(criteria.getPageOffset());
        query.limit(criteria.getListLength());
        return mongoTemplate.find(query, Item.class, Item.COLLECTION_NAME);
           }

    @Override
    protected String getId(Item entity) {
        return entity.getId();
    }
}
