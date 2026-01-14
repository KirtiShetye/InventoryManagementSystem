package com.example.inventory.Order.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class Payment {
    PaymentType paymentType;

    Payment(PaymentType paymentType){
        this.paymentType = paymentType;
    }

    public boolean makePayment(){
        return paymentType.makePayment();
    }

}
