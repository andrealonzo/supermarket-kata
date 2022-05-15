package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.ProductAndAmount;
import dojo.supermarket.model.discount.Discount;

public class PercentDiscountOffer extends Offer{
    private double discountPercentage;
    public PercentDiscountOffer(Product product, double discountPercentage) {
        super(product);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean canBeApplied(ProductAndAmount productAndAmount) {
        return true;
    }

    @Override
    public Discount getDiscounts(ProductAndAmount productAndAmount, double unitPrice) {
        Discount discount = new Discount(this.getProduct(), discountPercentage + "% off", -productAndAmount.getAmount() * unitPrice * discountPercentage / 100.0);
        return discount;
    }
}
