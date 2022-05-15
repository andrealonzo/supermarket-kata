package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.List;

public abstract class Offer {
    private final Product product;

    public Offer(Product product ) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public abstract boolean canBeApplied(ProductAndAmount productAndAmount, ShoppingCart shoppingCart);

    public abstract List<Discount> getDiscounts(ProductAndAmount productAndAmount, double unitPrice);
}
