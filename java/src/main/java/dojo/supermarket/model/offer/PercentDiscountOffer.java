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
    public List<Discount> getDiscounts(ProductAndAmount productAndAmount, double unitPrice) {

        List<Discount> discounts = new ArrayList<>();
        for (Product affectedProduct : this.getAffectedProducts()) {
            Discount discount = new Discount(affectedProduct, discountPercentage + "% off", -productAndAmount.getAmount() * unitPrice * discountPercentage / 100.0);

            discounts.add(discount);
        }
        return discounts;
    }
}
