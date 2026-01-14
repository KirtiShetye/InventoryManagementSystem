package com.example.inventory.Warehouse.controller;

import com.example.inventory.Warehouse.dto.ProductCategoryRequest;
import com.example.inventory.Warehouse.dto.UniqueProductRequest;
import com.example.inventory.Warehouse.model.Inventory;
import com.example.inventory.Warehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/inventory")
public class WarehouseController {

    WarehouseService warehouseService;
    Inventory inventory;

    public WarehouseController(WarehouseService warehouseService, Inventory inventory){
        this.warehouseService=warehouseService;
        this.inventory=inventory;
    }

    //add productcategory
    @PostMapping("/addProductCategory")
    public ResponseEntity<?> addProductCategory(
            @RequestBody ProductCategoryRequest request) {

            inventory.addCategory(request.getProductCategoryId(),
                    request.getCategoryName(), request.getPrice());
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Product category added in inventory successfully");
    }

    //remove product from category

    //add product to category
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(
            @RequestBody UniqueProductRequest request) {


        inventory.addProductInCategory();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Product category added in inventory successfully");
    }
}
