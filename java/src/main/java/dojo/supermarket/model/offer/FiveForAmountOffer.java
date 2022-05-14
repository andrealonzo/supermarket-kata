package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class FiveForAmountOffer extends Offer{

    public FiveForAmountOffer(Product product, double price) {
        super(OfferType.FiveForAmount, product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityInWeight) {
        int quantity = (int)quantityInWeight;
        return quantity >= 5;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        int numberOfXs = quantityAsEaches / getNumItemsInOffer();
        double discountTotal = unitPrice * quantityInWeight - (this.getPrice() * numberOfXs + quantityAsEaches % 5 * unitPrice);
        Discount discount = new Discount(product, getNumItemsInOffer() + " for " + this.getPrice(), -discountTotal);
        return discount;
    }
}
