package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class NumForAmountOffer extends Offer {

    private int numItems;
    private double amount;

    public NumForAmountOffer(Product product, int numItems, double amount) {
        super(product);
        this.amount = amount;
        this.numItems = numItems;
    }

    @Override
    public boolean canBeApplied(ProductAndAmount productAndAmount, ShoppingCart shoppingCart) {
        return productAndAmount.getAmount() >= numItems;
    }

    @Override
    public Discount getDiscount(ProductAndAmount productAndAmount, double unitPrice) {
        int quantityAsEaches = (int) productAndAmount.getAmount();
        int numberOfXs = quantityAsEaches / numItems;
        double discountTotal = unitPrice * productAndAmount.getAmount() - (amount * numberOfXs + quantityAsEaches % numItems * unitPrice);

        Discount discount = new Discount(productAndAmount.getProduct(), numItems + " for " + amount, -discountTotal);

        return discount;
    }
}
