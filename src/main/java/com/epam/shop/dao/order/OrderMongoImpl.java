package com.epam.shop.dao.order;

import com.epam.shop.dao.AbstractCRUDDao;
import com.epam.shop.domain.Order;
import org.springframework.stereotype.Repository;

/**
 * @Author Oleh_Osyka on 10.02.2015 for Spring-shop.com.epam.shop.dao.order.
 */
@Repository
public class OrderMongoImpl extends AbstractCRUDDao<Order> {

    public OrderMongoImpl() {
        super(Order.class, Order.COLLECTION_NAME);
    }

    @Override
    protected String getId(Order entity) {
        return entity.getId();
    }
}
