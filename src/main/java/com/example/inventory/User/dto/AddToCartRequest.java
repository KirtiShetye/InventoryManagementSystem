package com.example.inventory.User.dto;

public class AddToCartRequest {
    private int productCategoryId;
    private int count;

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public int getCount() {
        return count;
    }
}