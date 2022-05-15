package dojo.supermarket.model;

import java.util.*;

public class ShoppingCart {

    private Map<Product, ProductAndAmount> productQuantitiesMap = new HashMap<>();

    public Map<Product, ProductAndAmount> getProductsAndAmountsMap() {
        return productQuantitiesMap;
    }

    public void addProductAndAmount(Product product, double amount) {
        ProductAndAmount productAndAmount = new ProductAndAmount(product, amount);
        if (productQuantitiesMap.containsKey(product)) {
            ProductAndAmount found = productQuantitiesMap.get(product);
            found.increaseAmount(amount);
        } else {
            productQuantitiesMap.put(product, productAndAmount);
        }
    }



}
