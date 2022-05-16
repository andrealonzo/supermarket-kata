package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductAndAmount;

import java.util.*;

public class ShoppingCart {

    private Map<Product, ProductAndAmount> productsAndAmountsMap = new HashMap<>();

    public List<ProductAndAmount> getProductsAndAmounts(){
        return productsAndAmountsMap.values().stream().toList();
    }
    public void addProductAndAmount(Product product, double amount) {
        ProductAndAmount productAndAmount = new ProductAndAmount(product, amount);
        if (productsAndAmountsMap.containsKey(product)) {
            ProductAndAmount found = productsAndAmountsMap.get(product);
            found.increaseAmount(amount);
        } else {
            productsAndAmountsMap.put(product, productAndAmount);
        }
    }

    public boolean productExists(Product product) {
        return productsAndAmountsMap.containsKey(product);
    }
}
