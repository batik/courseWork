package com.epam.shop.service.shop.order;


import com.epam.shop.domain.Order;
import com.epam.shop.entity.Cart;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 07-Dec-14.
 */
public interface OrderService {

    public Order makeOrder(Cart cart, String address, String comment, String userId);

}
