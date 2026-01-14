package com.example.inventory.Warehouse.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    int productCategoryId;
    String categoryName;
    List<UniqueProduct> products = new ArrayList<>();
    double price;


    public void addProduct(UniqueProduct product){
        products.add(product);
    }

    //remove products
    public void removeProduct(int count){
        for(int i=1;i<=count;i++){
            products.remove(0); //removing 0th element so that old stock gets cleared
        }
    }

}
