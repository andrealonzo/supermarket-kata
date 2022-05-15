package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.ProductAndAmount;
import dojo.supermarket.model.discount.Discount;

public class NumForAmountOffer extends Offer{

    private int numItems;
    private double amount;

    public NumForAmountOffer(Product product, int numItems, double amount) {
        super(product);
        this.amount = amount;
        this.numItems = numItems;
    }

    @Override
    public boolean canBeApplied(ProductAndAmount productAndAmount) {
        return productAndAmount.getAmount() >= numItems;
    }

    @Override
    public Discount getDiscounts(double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        int numberOfXs = quantityAsEaches / numItems;
        double discountTotal = unitPrice * quantityInWeight - (amount * numberOfXs + quantityAsEaches % numItems * unitPrice);
        Discount discount = new Discount(this.getProduct(), numItems + " for " + amount, -discountTotal);
        return discount;
    }
}
