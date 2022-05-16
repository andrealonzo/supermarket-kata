package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class PercentDiscountOffer extends Offer {
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
    public Discount getDiscount(ProductAndAmount productAndAmount, double unitPrice) {
            Discount discount = new Discount(productAndAmount.getProduct(), getDescription(), getDiscountAmount(productAndAmount, unitPrice));
            return discount;
    }

    private double getDiscountAmount(ProductAndAmount productAndAmount, double unitPrice) {
        return -productAndAmount.getAmount() * unitPrice * discountPercentage / 100.0;
    }

    private String getDescription() {
        return discountPercentage + "% off";
    }
}
