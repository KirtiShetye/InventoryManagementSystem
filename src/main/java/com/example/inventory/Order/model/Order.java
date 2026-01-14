package com.example.inventory.Order.model;

import com.example.inventory.Order.enumeration.OrderStatus;
import com.example.inventory.User.entity.Users;
import com.example.inventory.User.model.Address;
import com.example.inventory.User.model.CardPayment;
import com.example.inventory.Warehouse.model.Warehouse;
import com.example.inventory.User.model.CartStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class Order {
    String orderId;
    Users user;
    Address deliveryAddress;
    Map<Integer, Integer> productCategoryAndCountMap;
    Warehouse warehouse;
    Invoice invoice;
    Payment payment;
    OrderStatus orderStatus;

    public Order(Users user, Map<Integer, Integer> productCategoryAndCountMap,
                 Address deliveryAddress){
        this.user = user;
        //this.cartStore = cartStore;
        this.productCategoryAndCountMap = productCategoryAndCountMap;
        this.deliveryAddress = user.getAddress(); //from where I can get delivery address
        this.invoice = new Invoice();
        this.orderId=OrderIdGenerator.generate();
    }

    public void placeOrder(){
        boolean isPaymentSuccess = makePayment(new CardPayment());
        if(isPaymentSuccess) {
            this.orderStatus=OrderStatus.INPROGRESS;
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
