package dojo.supermarket.model;

import java.util.*;

public class ShoppingCart {
    private Map<Product, Double> productQuantitiesMap = new HashMap<>();

    public Map<Product, Double> getProductQuantitiesMap() {
        return productQuantitiesMap;
    }

    public void addProductAndAmount(Product product, double amount) {
        if (productQuantitiesMap.containsKey(product)) {
            productQuantitiesMap.put(product, productQuantitiesMap.get(product) + amount);
        } else {
            productQuantitiesMap.put(product, amount);
        }
    }



}
