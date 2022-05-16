package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class NumForNumOffer extends Offer{

    public  int num1Items = 3;
    public  int num2Items = 2;

    public NumForNumOffer(Product product, int num1Items, int num2Items) {
        super(product);
        this.num1Items = num1Items;
        this.num2Items = num2Items;
    }

    @Override
    public boolean canBeApplied(ProductAndAmount productAndAmount, ShoppingCart shoppingCart) {
        return productAndAmount.getAmount() > num2Items;
    }

    @Override
    public Discount getDiscount(ProductAndAmount productAndAmount, double unitPrice) {
        int quantityAsEaches = (int) productAndAmount.getAmount();
        int numberOfXs = quantityAsEaches / num1Items;
        double discountAmount = productAndAmount.getAmount() * unitPrice - ((numberOfXs * num2Items * unitPrice) + quantityAsEaches % num1Items * unitPrice);

            Discount discount = new Discount(productAndAmount.getProduct(), num1Items + " for " + num2Items, -discountAmount);
        return discount;

    }
}
