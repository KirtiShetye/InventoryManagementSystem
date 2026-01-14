package com.example.inventory.Warehouse.dto;

import com.example.inventory.Warehouse.model.UniqueProduct;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductCategoryRequest {
    private int productCategoryId;
    private String categoryName;
    private double price;

}
