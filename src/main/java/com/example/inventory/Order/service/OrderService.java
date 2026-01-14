package com.example.inventory.Order.service;

import com.example.inventory.Order.model.Order;
import com.example.inventory.User.entity.Users;
import com.example.inventory.User.model.CartStore;
import com.example.inventory.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
    List<Order> orderList;
    Map<Long, List<Order>> userIDVsOrders;
    private final UserRepository userRepository;
    private final CartStore cartStore;

    public OrderService(UserRepository userRepository, CartStore cartStore){
        orderList=new ArrayList<>();
        userIDVsOrders=new ConcurrentHashMap<>();
        this.userRepository=userRepository;
        this.cartStore=cartStore;
    }

    public Order createNewOrder(String email){
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        Map<Integer, Integer> cartItems =
                cartStore.getCart(user.getId()).getCartItems();
        Order order = new Order(user, cartItems, user.getAddress());
        orderList.add(order);

        if(getUserOrderCount(user.getId())!=0){
            List<Order> userOrders = userIDVsOrders.get(user.getId());
            userOrders.add(order);
            userIDVsOrders.put(user.getId(), userOrders);
        }
        else{
            List<Order> userOrders = new ArrayList<>();
            userOrders.add(order);
            userIDVsOrders.put(user.getId(), userOrders);
        }
        order.placeOrder();
        order.generateOrderInvoice();
        cartStore.removeCart(user.getId());
        return order;
    }

    public Order createNewOrder(String email, int productCategoryId, int count){
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        Map<Integer, Integer> cartItems =
                cartStore.getCart(user.getId()).getCartItems();
        Order order = new Order(user, cartItems, user.getAddress());
        orderList.add(order);

        if(getUserOrderCount(user.getId())!=0){
            List<Order> userOrders = userIDVsOrders.get(user.getId());
            userOrders.add(order);
            userIDVsOrders.put(user.getId(), userOrders);
        }
        else{
            List<Order> userOrders = new ArrayList<>();
            userOrders.add(order);
            userIDVsOrders.put(user.getId(), userOrders);
        }
        order.placeOrder();
        order.generateOrderInvoice();
        cartStore.removeCart(user.getId());
        return order;
    }

    public int getUserOrderCount(Long userId){
        List<Order> order = userIDVsOrders.getOrDefault(userId,null);
        if(order==null)
            return 0;
        return order.size();

    }
}
