package com.example.inventory.Order.model;

public class Invoice {
    int totalItemPrice;
    int totalTax;
    int totalFinalPrice;

    public void generateInvoice(Order order){

        totalItemPrice = 50;
        totalTax = 10;
        totalFinalPrice = 60;
    }

}
