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
    private final List<ProductQuantity> items = new ArrayList<>();
    private Map<Product, Double> productQuantities = new HashMap<>();


    public List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    public Map<Product, Double> productQuantities() {
        return productQuantities;
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> productOfferMap, SupermarketCatalog catalog) {
        for (Product product: productQuantities().keySet()) {
            double quantityInWeight = productQuantities.get(product);
            if (productOfferMap.containsKey(product)) {
                Offer offer = productOfferMap.get(product);
                double unitPrice = catalog.getUnitPrice(product);
                int quantityAsEaches = (int) quantityInWeight;
                int numItemsInDiscount = getNumItemsInOffer(offer);
                DiscountBook discountBook = new DiscountBook();
                Discount discount = discountBook.getDiscount(product, quantityInWeight, offer, unitPrice, quantityAsEaches, numItemsInDiscount);

               if (isNoDiscountOffer(discount))
                    receipt.addDiscount(discount);
            }

        }
    }


    private int getNumItemsInOffer(Offer offer){

        DiscountValidator discountValidator = new DiscountValidator(offer);
        int numItemsInDiscount = 1;
        if (discountValidator.isThreeForTwoOffer()) {
            numItemsInDiscount = 3;
        } else if (discountValidator.isTwoForAmountOffer()) {
            numItemsInDiscount = 2;

        } if (discountValidator.isFiveForAmountOffer()) {
            numItemsInDiscount = 5;
        }
        return numItemsInDiscount;
    }

    private boolean isNoDiscountOffer(Discount discount) {
        return discount != null;
    }


}
