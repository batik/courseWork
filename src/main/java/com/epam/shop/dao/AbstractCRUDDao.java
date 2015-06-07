package com.epam.shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.
 */
@Repository
public abstract class AbstractCRUDDao<T> implements CRUDDao<T> {

    private final String collectionName;
    private final Class<T> type;
    @Autowired
    protected MongoTemplate mongoTemplate;

    public AbstractCRUDDao(Class<T> type, String collectionName) {
        this.collectionName = collectionName;
        this.type = type;
    }

    @Override
    public T getByID(String id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), type);
    }

    @Override
    public List<T> getEntitiesList() {
        return mongoTemplate.findAll(type, collectionName);
    }

    @Override
    public void add(T entity) {
        if (!mongoTemplate.collectionExists(type)) {
            mongoTemplate.createCollection(type);
        }
        mongoTemplate.insert(entity, collectionName);
    }

    @Override
    public void delete(T entity) {
        //here we could use getQuery instead of getId and flexible override
        String id = getId(entity);
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), collectionName);
    }

    @Override
    public void update(T entity) {
        mongoTemplate.save(entity, collectionName);
    }

    protected abstract String getId(T entity);
}
