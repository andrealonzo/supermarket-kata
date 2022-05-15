package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class PercentDiscountOffer extends Offer{
    private double discountPercentage;
    public PercentDiscountOffer(Product product, double discountPercentage) {
        super(product);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean canBeApplied(double quantity) {
        return true;
    }

    @Override
    public Discount getDiscounts(double quantityInWeight, double unitPrice) {
        Discount discount = new Discount(this.getProduct(), discountPercentage + "% off", -quantityInWeight * unitPrice * discountPercentage / 100.0);
        return discount;
    }
}
