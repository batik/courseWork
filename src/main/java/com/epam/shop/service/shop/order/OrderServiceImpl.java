package com.epam.shop.service.shop.order;


import com.epam.shop.dao.order.OrderMongoImpl;
import com.epam.shop.domain.Item;
import com.epam.shop.domain.Order;
import com.epam.shop.domain.subdomain.order.LineItems;
import com.epam.shop.domain.subdomain.order.OrderDetailedInformation;
import com.epam.shop.entity.Cart;
import org.bson.types.BSONTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 07-Dec-14.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final String ACCEPTED = "accepted";
    @Autowired
    private OrderMongoImpl orderMongo;

    @Override
    public Order makeOrder(Cart cart, String address, String comment, String userId) {
        String objectId = UUID.randomUUID().toString();
        Order order = new Order();
        order.setId(objectId);
        order.setStatus(ACCEPTED);
        order.setUserId(userId);
        order.setDetailedInformation(setDetailedParameters(address, comment));
        order.setItemsList(saveOrderItems(cart));
        order.setTotal(cart.totalSum());
        orderMongo.add(order);
        return orderMongo.getByID(objectId);

    }

    private List<LineItems> saveOrderItems(Cart cart) {
        List<LineItems> itemsList = new ArrayList<>();
        Map<Item, Integer> currentCart = cart.getProducts();
        for (Map.Entry<Item, Integer> entry : currentCart.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();
            itemsList.add(new LineItems(item.getId(), entry.getValue(), item.getPrice() * quantity));
        }
        return itemsList;

    }

    private OrderDetailedInformation setDetailedParameters(String address, String comment) {
        OrderDetailedInformation orderDetailedInformation = new OrderDetailedInformation();
        orderDetailedInformation.setOrderDate(new Date(System.currentTimeMillis()));
        orderDetailedInformation.setShippingAdress(address);
        orderDetailedInformation.setShippingComment(comment);
        return orderDetailedInformation;
    }

}
