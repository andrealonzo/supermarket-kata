package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.OfferBook;
import dojo.supermarket.model.offer.Offer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
                OfferBook offerBook = new OfferBook();
                double pricePerUnit = catalog.getUnitPrice(product);
                List<Discount> discounts = offerBook.getDiscounts(pricePerUnit, quantity, offer);
                discounts.forEach(discount -> receipt.addDiscount(discount));
            }

        }
    }


}
