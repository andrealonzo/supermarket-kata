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
        for (Product p: productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                int quantityAsInt = (int) quantity;
                Discount discount = null;
                int x = 1;
                if (isThreeForTwoOffer(offer)) {
                    x = 3;

                } else if (isTwoForAmountOffer(offer)) {
                    x = 2;
                    if (quantityAsInt >= 2) {
                        double total = offer.argument * (quantityAsInt / x) + quantityAsInt % 2 * unitPrice;
                        double discountN = unitPrice * quantity - total;
                        discount = new Discount(p, "2 for " + offer.argument, -discountN);
                    }

                } if (isFiveForAmountOffer(offer)) {
                    x = 5;
                }
                int numberOfXs = quantityAsInt / x;
                if (isThreeForTwoOffer(offer) && quantityAsInt > 2) {
                    double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
                    discount = new Discount(p, "3 for 2", -discountAmount);
                }
                if (isTenPercentDiscountOffer(offer)) {
                    discount = new Discount(p, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
                }
                if (isFiveForAmountOffer(offer) && quantityAsInt >= 5) {
                    double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
                    discount = new Discount(p, x + " for " + offer.argument, -discountTotal);
                }
                if (isNoDiscountOffer(discount))
                    receipt.addDiscount(discount);
            }

        }
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
