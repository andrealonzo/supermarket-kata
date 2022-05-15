package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.Arrays;
import java.util.List;

public class PercentDiscountOffer extends Offer{
    private double discountPercentage;
    public PercentDiscountOffer(Product product, double discountPercentage) {
        super(product);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean canBeApplied(ProductAndAmount productAndAmount, ShoppingCart shoppingCart) {
        return true;
    }

    @Override
    public List<Discount> getDiscounts(ProductAndAmount productAndAmount, double unitPrice) {
        Discount discount = new Discount(this.getProduct(), discountPercentage + "% off", -productAndAmount.getAmount() * unitPrice * discountPercentage / 100.0);
        return Arrays.asList(discount);
    }
}
