package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.OfferBook;
import dojo.supermarket.model.offer.Offer;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
/*
    TODO:  Make DiscountValidator not check for types.  Remove offerType
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
                OfferBook offerBook = new OfferBook();
                double pricePerUnit = catalog.getUnitPrice(product);
                Discount discount = offerBook.getDiscount(pricePerUnit, product, quantityInWeight, offer);
               if (discount != null)
                    receipt.addDiscount(discount);
            }

        }
    }


}
