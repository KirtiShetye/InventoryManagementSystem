package com.example.inventory.Order.controller;

import com.example.inventory.Order.model.Order;
import com.example.inventory.Order.service.OrderService;
import com.example.inventory.User.dto.AddToCartRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

     private final OrderService orderService;

     public OrderController(OrderService orderService){
         this.orderService=orderService;
     }

    //place order for all items in cart
    @PostMapping("/add")
    public ResponseEntity<?> placeOrder(
            Authentication authentication) {

        String email = (String) authentication.getDetails();

        Order order=orderService.createNewOrder(
                email
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("orderId", order.getOrderId()));
    }

    //place order for individual item in cart
    @PostMapping("/buy-now")
    public ResponseEntity<?> placeOrder(
            @RequestBody AddToCartRequest request,
            Authentication authentication) {

        String email = (String) authentication.getDetails();

        Order order = orderService.createNewOrder(
                email,
                request.getProductCategoryId(),
                request.getCount()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Items added in cart successfully");
    }

}
