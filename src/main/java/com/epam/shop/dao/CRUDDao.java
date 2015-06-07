package com.epam.shop.dao;

import java.util.List;

/**
 * Repository interface with CRUD operation
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 21-Oct-14.
 */
public interface CRUDDao<T> {

    /**
     * Get entity by it's unique id
     *
     * @param id of entity
     * @return founded entity or null if nothing found.
     */
    public T getByID(String id);

    /**
     * Get entities list from repository
     *
     * @return list with specified entity type
     */
    public List<T> getEntitiesList();

    /**
     * Add specified type of entity into repository
     *
     * @param entity specified entity
     */
    public void add(T entity);

    /**
     * Delete specified type of entity from repository
     *
     * @param entity to delete
     */
    public void delete(T entity);

    /**
     * Update specified type of entity in repository
     *
     * @param entity to update
     */
    public void update(T entity);
}
