package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
/*
    TODO:  Combine FiveForAmount and TwoForAmount into NumForAmount.  Make test to have 3 for amount.
    TODO:  Make TenPercentDiscount into PercentDiscount.  Make test to have 20% discount.
    TODO:  Make ThreeForTwo into NumForNumDiscount.  Make test to have FourForThree.
    */
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
        for (Product product: getProductQuantitiesMap().keySet()) {
            double quantityInWeight = productQuantitiesMap.get(product);
            if (productOfferMap.containsKey(product)) {
                Offer offer = productOfferMap.get(product);
                DiscountBook discountBook = new DiscountBook();
                Discount discount = discountBook.getDiscount(catalog, product, quantityInWeight, offer);
               if (discount != null)
                    receipt.addDiscount(discount);
            }

        }
    }


}
