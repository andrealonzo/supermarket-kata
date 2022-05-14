package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class ThreeForTwoOffer extends Offer{

    public static final int NUM1_ITEMS_IN_OFFER = 3;
    public static final int NUM2_ITEMS_IN_OFFER = 2;

    public ThreeForTwoOffer(Product product, double price) {
        super(product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityInWeight) {
        int quantity = (int) quantityInWeight;
        return quantity > NUM2_ITEMS_IN_OFFER;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        int numberOfXs = quantityAsEaches / NUM1_ITEMS_IN_OFFER;
        double discountAmount = quantityInWeight * unitPrice - ((numberOfXs * NUM2_ITEMS_IN_OFFER * unitPrice) + quantityAsEaches % NUM1_ITEMS_IN_OFFER * unitPrice);
        Discount discount = new Discount(product, NUM1_ITEMS_IN_OFFER+ " for " + NUM2_ITEMS_IN_OFFER, -discountAmount);
        return discount;

    }
}
