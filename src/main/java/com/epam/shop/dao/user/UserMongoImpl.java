package com.epam.shop.dao.user;

import com.epam.shop.dao.AbstractCRUDDao;
import com.epam.shop.domain.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.
 */
@Repository
public class UserMongoImpl extends AbstractCRUDDao<User> implements UserDao {

    public UserMongoImpl() {
        super(User.class, User.COLLECTION_NAME);
    }

    @Override
    public User findByLogin(String login) {
        return mongoTemplate.findOne(Query.query(Criteria.where("login").is(login)), User.class);
    }

    @Override
    public User findByLoginAndPass(String login, String password) {
        return mongoTemplate.findOne(Query.query(Criteria.where("login").is(login).and("password").is(password)), User.class);
    }

    @Override
    protected String getId(User entity) {
        return entity.getId();
    }
}
