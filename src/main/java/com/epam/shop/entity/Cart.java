package com.epam.shop.entity;

import com.epam.shop.domain.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 06-Dec-14.
 */
public class Cart {
    final Map<Item, Integer> cart;

    public Cart() {
        this.cart = new ConcurrentHashMap<>();
    }

    public void add(Item item) {
        int total = 0;
        if (cart.containsKey(item)) {
            total = cart.get(item);
        }
        cart.put(item, ++total);
    }

    public void set(Item item, int amount) {
        cart.put(item, amount);
    }

    /**
     * Empty cart
     */
    public void clear() {
        cart.clear();
    }

    /**
     * Removing product from cart
     *
     * @param item to remove
     */
    public void remove(Item item) {
        cart.remove(item);
    }

    public Map<Item, Integer> getProducts() {
        return new HashMap<>(cart);
    }

    public int size() {
        int size = 0;
        for (Integer count : cart.values()) {
            size += count;
        }
        return size;
    }

    public double totalSum() {
        int sum = 0;
        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        return cart.equals(o);
    }

    @Override
    public int hashCode() {
        return cart.hashCode();
    }
}
