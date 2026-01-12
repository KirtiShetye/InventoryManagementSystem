package com.example.inventory.User.dto;

import java.util.Map;

public class CartResponseDTO {

    private Map<Integer, Integer> items;

    public CartResponseDTO(Map<Integer, Integer> items) {
        this.items = items;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }
}