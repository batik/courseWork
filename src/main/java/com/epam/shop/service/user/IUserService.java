package com.epam.shop.service.user;

import com.epam.shop.domain.User;
import com.epam.shop.service.Service;

/**
 * User service interface for using and validating captcha
 * Class for Task9
 * Created by Oleh_Osyka on 22-Oct-14.
 */
public interface IUserService extends Service<User> {

    /**
     * Checks inputted name for existing in repo.
     *
     * @return true or false.
     */
    public boolean isLoginExists(String login);

    /**
     * Get User by login and password.
     *
     * @return Founded user or null.
     */
    public User getByLoginAndPass(String login, String password);
}
