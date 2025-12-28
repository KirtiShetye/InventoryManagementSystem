package com.example.inventory;

public class CardPayment implements PaymentType {

    public boolean makePayment() {
        System.out.println("Payment done using Card");
        return true;
    }

}
