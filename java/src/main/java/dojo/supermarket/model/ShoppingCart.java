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

                Discount discount = getDiscount(product, quantityInWeight, offer, unitPrice, quantityAsEaches, numItemsInDiscount);
                if (isNoDiscountOffer(discount))
                    receipt.addDiscount(discount);
            }

        }
    }

    private Discount getDiscount(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numItemsInDiscount) {
        Discount discount = null;

        if (canTwoForAmountOfferBeApplied(offer, quantityAsInt)){
            discount = getTwoForAmountDiscount(p, quantity, offer, unitPrice, quantityAsInt, numItemsInDiscount);
        }

        int numberOfXs = quantityAsInt / numItemsInDiscount;
        if (canThreeForTwoOfferBeApplied(offer, quantityAsInt)) {
            discount = getThreeForTwoDiscount(p, quantity, unitPrice, quantityAsInt, numberOfXs);
        }
        DiscountValidator discountValidator = new DiscountValidator(offer);
        if (discountValidator.isTenPercentDiscountOffer()) {
            discount = getTenPercentDiscount(p, quantity, offer, unitPrice);
        }
        if (canFiveForAmountOfferBeApplied(offer, quantityAsInt)) {
            discount = getFiveForAmountDiscount(p, quantity, offer, unitPrice, quantityAsInt, numItemsInDiscount, numberOfXs);
        }
        return discount;
    }

    private Discount getFiveForAmountDiscount(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numItemsInDiscount, int numberOfXs) {

        double discountTotal = unitPrice * quantity - (offer.price * numberOfXs + quantityAsInt % 5 * unitPrice);
        Discount discount = new Discount(p, numItemsInDiscount + " for " + offer.price, -discountTotal);
        return discount;
    }

    private Discount getTenPercentDiscount(Product p, double quantity, Offer offer, double unitPrice) {

        Discount discount = new Discount(p, offer.price + "% off", -quantity * unitPrice * offer.price / 100.0);
        return discount;
    }

    private Discount getThreeForTwoDiscount(Product p, double quantity, double unitPrice, int quantityAsInt, int numberOfXs) {
        double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        Discount discount = new Discount(p, "3 for 2", -discountAmount);
        return discount;
    }

    private Discount getTwoForAmountDiscount(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numItemsInDiscount) {
        double total = offer.price * (quantityAsInt / numItemsInDiscount) + quantityAsInt % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        Discount discount = new Discount(p, "2 for " + offer.price, -discountN);
        return discount;
    }

    private boolean canFiveForAmountOfferBeApplied(Offer offer, int quantityAsInt) {
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isFiveForAmountOffer() && quantityAsInt >= 5;
    }

    private boolean canThreeForTwoOfferBeApplied(Offer offer, int quantityAsInt) {

        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isThreeForTwoOffer() && quantityAsInt > 2;
    }

    private boolean canTwoForAmountOfferBeApplied(Offer offer, int quantityAsInt) {
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isTwoForAmountOffer() && quantityAsInt >= 2;
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
