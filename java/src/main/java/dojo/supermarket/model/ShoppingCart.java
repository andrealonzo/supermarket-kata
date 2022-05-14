package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

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

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product product: productQuantities().keySet()) {
            double quantityInWeight = productQuantities.get(product);
            if (offers.containsKey(product)) {
                Offer offer = offers.get(product);
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
        if (isTenPercentDiscountOffer(offer)) {
            discount = getTenPercentDiscount(p, quantity, offer, unitPrice);
        }
        if (canFiveForAmountOfferBeApplied(offer, quantityAsInt)) {
            discount = getFiveForAmountDiscount(p, quantity, offer, unitPrice, quantityAsInt, numItemsInDiscount, numberOfXs);
        }
        return discount;
    }

    private Discount getFiveForAmountDiscount(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numItemsInDiscount, int numberOfXs) {
        Discount discount;
        double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
        discount = new Discount(p, numItemsInDiscount + " for " + offer.argument, -discountTotal);
        return discount;
    }

    private Discount getTenPercentDiscount(Product p, double quantity, Offer offer, double unitPrice) {
        Discount discount;
        discount = new Discount(p, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
        return discount;
    }

    private Discount getThreeForTwoDiscount(Product p, double quantity, double unitPrice, int quantityAsInt, int numberOfXs) {
        Discount discount;
        double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        discount = new Discount(p, "3 for 2", -discountAmount);
        return discount;
    }

    private Discount getTwoForAmountDiscount(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numItemsInDiscount) {
        Discount discount;
        double total = offer.argument * (quantityAsInt / numItemsInDiscount) + quantityAsInt % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        discount = new Discount(p, "2 for " + offer.argument, -discountN);
        return discount;
    }

    private boolean canFiveForAmountOfferBeApplied(Offer offer, int quantityAsInt) {
        return isFiveForAmountOffer(offer) && quantityAsInt >= 5;
    }

    private boolean canThreeForTwoOfferBeApplied(Offer offer, int quantityAsInt) {
        return isThreeForTwoOffer(offer) && quantityAsInt > 2;
    }

    private boolean canTwoForAmountOfferBeApplied(Offer offer, int quantityAsInt) {
        return isTwoForAmountOffer(offer) && quantityAsInt >= 2;
    }

    private int getNumItemsInOffer(Offer offer){

        int numItemsInDiscount = 1;
        if (isThreeForTwoOffer(offer)) {
            numItemsInDiscount = 3;
        } else if (isTwoForAmountOffer(offer)) {
            numItemsInDiscount = 2;

        } if (isFiveForAmountOffer(offer)) {
            numItemsInDiscount = 5;
        }
        return numItemsInDiscount;
    }

    private boolean isNoDiscountOffer(Discount discount) {
        return discount != null;
    }

    private boolean isTenPercentDiscountOffer(Offer offer) {
        return offer.offerType == SpecialOfferType.TenPercentDiscount;
    }

    private boolean isFiveForAmountOffer(Offer offer) {
        return offer.offerType == SpecialOfferType.FiveForAmount;
    }

    private boolean isTwoForAmountOffer(Offer offer) {
        return offer.offerType == SpecialOfferType.TwoForAmount;
    }

    private boolean isThreeForTwoOffer(Offer offer) {
        return offer.offerType == SpecialOfferType.ThreeForTwo;
    }
}
