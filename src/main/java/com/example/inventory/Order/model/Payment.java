package com.example.inventory.Order.model;

public class Payment {
    PaymentType paymentType;

    Payment(PaymentType paymentType){
        this.paymentType = paymentType;
    }

    public boolean makePayment(){
        return paymentType.makePayment();
    }

}
