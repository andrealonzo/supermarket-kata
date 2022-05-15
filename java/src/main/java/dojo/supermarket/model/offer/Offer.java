package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.ProductAndAmount;
import dojo.supermarket.model.discount.Discount;

public abstract class Offer {
    private final Product product;

    public Offer(Product product ) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public abstract boolean canBeApplied(ProductAndAmount productAndAmount);

    public abstract Discount getDiscounts(double quantityInWeight, double unitPrice);
}
