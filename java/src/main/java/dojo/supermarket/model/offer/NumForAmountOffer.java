package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class NumForAmountOffer extends Offer{

    private int numItems;
    private double price;

    public NumForAmountOffer(Product product, int numItems, double price) {
        super(product);
        this.price = price;
        this.numItems = numItems;
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityInWeight) {
        int quantity = (int)quantityInWeight;
        return quantity >= numItems;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        int numberOfXs = quantityAsEaches / numItems;
        double discountTotal = unitPrice * quantityInWeight - (price  * numberOfXs + quantityAsEaches % numItems * unitPrice);
        Discount discount = new Discount(product, numItems + " for " + price, -discountTotal);
        return discount;
    }
}
