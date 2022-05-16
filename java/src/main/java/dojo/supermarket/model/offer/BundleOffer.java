package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.Arrays;
import java.util.List;

public class BundleOffer extends Offer {

    private double discount;

    public BundleOffer(List<Product> affectedProducts, double discount) {
        super(affectedProducts);
        this.discount = discount;
    }

    @Override
    public boolean canBeApplied(ProductAndAmount productAndAmount, ShoppingCart shoppingCart) {
        for (Product product : this.getAffectedProducts()) {
            if (!shoppingCart.productExists(product)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Discount getDiscount(ProductAndAmount productAndAmount, double unitPrice) {

        double discountAmount = (productAndAmount.getAmount() * unitPrice) * discount;
        Discount discount = new Discount(productAndAmount.getProduct(), "Bundle Discount", -discountAmount);
        return discount;

    }
}
