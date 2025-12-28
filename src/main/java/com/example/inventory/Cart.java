package com.example.inventory;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    Map<Integer, Integer> productCategoryIdVsCountMap;

    public Cart(){
        productCategoryIdVsCountMap = new HashMap<>();
    }

    public void addItemInCart(int productCategoryId, int count){

        if(productCategoryIdVsCountMap.containsKey(productCategoryId)){
            int noOfItemsInCart = productCategoryIdVsCountMap.get(productCategoryId);
            productCategoryIdVsCountMap.put(productCategoryId, noOfItemsInCart + count);
        } else{
            productCategoryIdVsCountMap.put(productCategoryId, count);
        }
    }

    public void removeItemFromCart(int productCategoryId, int count) {

        if (productCategoryIdVsCountMap.containsKey(productCategoryId))
        {
            int noOfItemsInCart = productCategoryIdVsCountMap.get(productCategoryId);
            if (count-noOfItemsInCart==0) {
                productCategoryIdVsCountMap.remove(productCategoryId);
            }
            else if(count-noOfItemsInCart>0){
                productCategoryIdVsCountMap.put(productCategoryId, noOfItemsInCart - count);
            }
            else{
                System.out.println("Items in cart are less than requested");
            }
        }
    }

    public void emptyCart(){
        productCategoryIdVsCountMap = new HashMap<>();
    }

    public  Map<Integer, Integer> getCartItems(){
        return productCategoryIdVsCountMap;
    }
}

