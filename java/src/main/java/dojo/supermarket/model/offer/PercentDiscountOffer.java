package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class PercentDiscountOffer extends Offer{
    private double price;
    public PercentDiscountOffer(Product product, double price) {
        super(product);
        this.price = price;
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityAsWeight) {
        return true;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        Discount discount = new Discount(product, price + "% off", -quantityInWeight * unitPrice * price  / 100.0);
        return discount;
    }
}
