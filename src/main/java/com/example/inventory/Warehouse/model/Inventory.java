package com.example.inventory.Warehouse.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Inventory {

    List<ProductCategory> productCategoryList;

    Inventory(){
        productCategoryList = new ArrayList<>();
    }

    public void addCategory(int categoryId, String name, double price){
        ProductCategory productCategory = new ProductCategory();
        productCategory.price = price;
        productCategory.categoryName = name;
        productCategory.productCategoryId = categoryId;
        productCategoryList.add(productCategory);
    }

    public void addProductInCategory(UniqueProduct product, int productCategoryId){

        ProductCategory categoryObject = null;
        for(ProductCategory category : productCategoryList)
        {
            if(category.productCategoryId == productCategoryId){
                categoryObject = category;
            }
        }
        if(categoryObject !=null) {
            categoryObject.addProduct(product);
        }else{
            //throw error to add productcategory / productcategory won't exists
        }
    }

    public void removeItemsFromCategory(Map<Integer, Integer> productCategoryAndCountMap){

        for(Map.Entry<Integer, Integer> entry : productCategoryAndCountMap.entrySet())
        {
            ProductCategory category = getProductCategoryFromID(entry.getKey());
            category.removeProduct(entry.getValue());
        }

    }

    private ProductCategory getProductCategoryFromID(int productCategoryId){

        for(ProductCategory productCategory : productCategoryList){

            if(productCategory.productCategoryId == productCategoryId){
                return productCategory;
            }
        }

        return null;
    }

}
