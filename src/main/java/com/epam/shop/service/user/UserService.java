package com.epam.shop.service.user;

import com.epam.shop.dao.user.UserDao;
import com.epam.shop.domain.User;
import com.epam.shop.utils.MD5PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.service.user.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isLoginExists(String login) {
        return userDao.findByLogin(login) != null;
    }

    @Override
    public User getByLoginAndPass(String login, String password) {
        return userDao.findByLoginAndPass(login, password);
    }

    @Override
    public User get(String id) {
        return userDao.getByID(id);
    }

    @Override
    public User add(User entity) {
        String objectId = UUID.randomUUID().toString();
        entity.setId(objectId);
        entity.setPassword(MD5PasswordGenerator.encrypt(entity.getPassword()));
        userDao.add(entity);
        return userDao.getByID(objectId);
    }

    @Override
    public void delete(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }
}
