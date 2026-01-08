package com.example.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {

    private Map<Integer, Integer> productCategoryIdVsCountMap;

    public Cart(){
        productCategoryIdVsCountMap = new ConcurrentHashMap<>();
    }

    public void addItemInCart(int productCategoryId, int count){

        if(getItemCount(productCategoryId)>0){
            int noOfItemsInCart = getItemCount(productCategoryId);
            productCategoryIdVsCountMap.put(productCategoryId, noOfItemsInCart + count);
        } else{
            productCategoryIdVsCountMap.put(productCategoryId, count);
        }
    }

    public void removeItemFromCart(int productCategoryId, int count) {
        int currentCount = getItemCount(productCategoryId);

        if (currentCount == 0) {
            System.out.println("Product not found in cart");
            return;
        }

        if (count > currentCount) {
            System.out.println("Items in cart are less than requested");
            return;
        }

        setItemCount(productCategoryId, currentCount - count);
    }

    private void setItemCount(int productCategoryId, int count) {
        if(count==0)
            productCategoryIdVsCountMap.remove(productCategoryId);
        productCategoryIdVsCountMap.put(productCategoryId,count);
    }

    private int getItemCount(int productCategoryId) {
        return productCategoryIdVsCountMap.getOrDefault(productCategoryId,0);
    }

    public void emptyCart(){
        productCategoryIdVsCountMap = new HashMap<>();
    }

    public  Map<Integer, Integer> getCartItems(){
        return productCategoryIdVsCountMap;
    }
}

