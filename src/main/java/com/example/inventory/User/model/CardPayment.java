package com.example.inventory.User.model;

import com.example.inventory.Order.model.PaymentType;

public class CardPayment implements PaymentType {

    public boolean makePayment() {
        System.out.println("Payment done using Card");
        return true;
    }

}
