package com.example.inventory.model;

import com.example.inventory.Cart;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CartStore {

    private final Map<Long, Cart> userCartMap = new ConcurrentHashMap<>();

    public Cart getCart(Long userId) {
        return userCartMap.computeIfAbsent(userId, id -> new Cart());
    }

    public void removeCart(Long userId) {
        userCartMap.remove(userId);
    }

    public void clear() {
        userCartMap.clear();
    }
}