package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class TwoForAmountOffer extends Offer{

    public static final int NUM_ITEMS_IN_OFFER = 2;

    public TwoForAmountOffer(Product product, double price) {
        super(product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityAsWeight) {
        int quantity = (int)quantityAsWeight;
        return quantity >= 2;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        double total = this.getPrice() * (quantityAsEaches / NUM_ITEMS_IN_OFFER) + quantityAsEaches % 2 * unitPrice;
        double discountN = unitPrice * quantityAsEaches - total;
        Discount discount = new Discount(product, NUM_ITEMS_IN_OFFER + " for " + this.getPrice(), -discountN);
        return discount;
    }
}
