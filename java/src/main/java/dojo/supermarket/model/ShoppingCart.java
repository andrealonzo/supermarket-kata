package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;

import java.util.*;

public class ShoppingCart {
    private Map<Product, Double> productQuantitiesMap = new HashMap<>();

    public Map<Product, Double> getProductQuantitiesMap() {
        return productQuantitiesMap;
    }

    public void addProductQuantity(Product product, double quantity) {
        if (productQuantitiesMap.containsKey(product)) {
            productQuantitiesMap.put(product, productQuantitiesMap.get(product) + quantity);
        } else {
            productQuantitiesMap.put(product, quantity);
        }
    }

    void addReceiptDiscounts(Receipt receipt, Map<Product, Offer> productOfferMap, SupermarketCatalog catalog) {
        for (Product product : getProductQuantitiesMap().keySet()) {
            double quantity = productQuantitiesMap.get(product);
            if (productOfferMap.containsKey(product)) {
                Offer offer = productOfferMap.get(product);
                double pricePerUnit = catalog.getUnitPrice(product);
                if(offer.canBeApplied(quantity)){
                    Discount discount = offer.getDiscounts(quantity, pricePerUnit);
                    receipt.addDiscount(discount);
                }
            }

        }
    }


}
