package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.Arrays;
import java.util.List;

public abstract class Offer {
    private final List<Product> affectedProducts;

    public Offer(Product affectedProduct) {
        affectedProducts = Arrays.asList(affectedProduct);
    }
    public Offer(List<Product> affectedProducts) {
        this.affectedProducts = affectedProducts;
    }

    public List<Product> getAffectedProducts() {
        return affectedProducts;
    }

    public abstract boolean canBeApplied(ProductAndAmount productAndAmount, ShoppingCart shoppingCart);

    public abstract List<Discount> getDiscounts(ProductAndAmount productAndAmount, double unitPrice);
}
