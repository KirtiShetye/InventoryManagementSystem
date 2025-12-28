package com.example.inventory;

public class Payment {
    PaymentType paymentType;

    Payment(PaymentType paymentType){
        this.paymentType = paymentType;
    }

    public boolean makePayment(){
        return paymentType.makePayment();
    }

}
