package com.epam.shop.service;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 12-Nov-14.
 */
public interface Service<T> {

    /**
     * Getting entity information by id
     *
     * @param id to find user
     * @return entity
     */
    public T get(String id);

    /**
     * Add entity to repo.
     */
    public T add(T entity);

    /**
     * Delete entity from repo.
     */
    public void delete(T entity);

    /**
     * Update entity in repo.
     */
    public void update(T entity);
}
