package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class ThreeForTwoOffer extends Offer{

    public static final int NUM_ITEMS_IN_OFFER = 3;

    public ThreeForTwoOffer(Product product, double price) {
        super(product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityInWeight) {
        int quantity = (int) quantityInWeight;
        return quantity > 2;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        int numberOfXs = quantityAsEaches / NUM_ITEMS_IN_OFFER;
        double discountAmount = quantityInWeight * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsEaches % 3 * unitPrice);
        Discount discount = new Discount(product, "3 for 2", -discountAmount);
        return discount;

    }
}
