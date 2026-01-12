package com.example.inventory.Order.service;

import com.example.inventory.Order.model.Order;
import com.example.inventory.User.entity.Users;
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
    UserRepository userRepository;

    public OrderService(UserRepository userRepository){
        orderList=new ArrayList<>();
        userIDVsOrders=new ConcurrentHashMap<>();
        this.userRepository=userRepository;
    }

    public Order createNewOrder(String email){
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        Order order = new Order(user);
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
        return order;
    }

    public int getUserOrderCount(Long userId){
        List<Order> order = userIDVsOrders.getOrDefault(userId,null);
        if(order==null)
            return 0;
        return order.size();

    }
}
