package com.example.inventory.Order.model;

import com.example.inventory.Order.enumeration.OrderStatus;
import com.example.inventory.User.entity.Users;
import com.example.inventory.User.model.Address;
import com.example.inventory.User.model.CardPayment;
import com.example.inventory.Warehouse.model.Warehouse;
import com.example.inventory.User.model.CartStore;

import java.util.Map;

public class Order {
    String orderId;
    Users user;
    Address deliveryAddress;
    Map<Integer, Integer> productCategoryAndCountMap;
    Warehouse warehouse;
    com.example.inventory.Order.model.Invoice invoice;
    Payment payment;
    OrderStatus orderStatus;
    CartStore cartStore;

    public Order(Users user){
        this.user = user;
        this.productCategoryAndCountMap = cartStore.getCart(user.getId()).getCartItems();
        this.deliveryAddress = user.getAddress(); //from where I can get delivery address
        invoice = new com.example.inventory.Order.model.Invoice();
        this.orderId=OrderIdGenerator.generate();
    }

    public void placeOrder(){
        boolean isPaymentSuccess = makePayment(new CardPayment());
        if(isPaymentSuccess) {
            this.orderStatus=OrderStatus.INPROGRESS;
            user.getUserCart().emptyCart();
            //to add - remove item from inventory
        }else{
            this.orderStatus=OrderStatus.PAYMENTFAILED;
        }
    }

    public boolean makePayment(PaymentType paymentType){
        payment = new Payment(paymentType);
        return payment.makePayment();
    }

    public void generateOrderInvoice(){
        invoice.generateInvoice(this);
    }

    public String getOrderId(){
        return orderId;
    }


}
