package com.epam.shop.dao.user;

import com.epam.shop.dao.CRUDDao;
import com.epam.shop.domain.User;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.
 */
public interface UserDao extends CRUDDao<User>{

    /**
     * Find user by login
     *
     * @param login to find
     * @return founded user or null if nothing found.
     */
    public User findByLogin(String login);

    /**
     * Find user by login and password
     *
     * @param login    to find
     * @param password to find
     * @return founded user or null if nothing found.
     */
    public User findByLoginAndPass(String login, String password);

}
