package com.example.inventory.Warehouse.model;

import com.example.inventory.User.model.Address;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Warehouse {

    Inventory inventory;
    Address address;

    public Warehouse(Inventory inventory){
        this.inventory=inventory;
    }

    public void removeItemFromInventory(Map<Integer, Integer> productCategoryAndCountMap){

    }

    public void addItemToInventory(Map<Integer, Integer> productCategoryAndCountMap){

    }

}
