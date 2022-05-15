package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.ProductAndAmount;
import dojo.supermarket.model.ShoppingCart;
import dojo.supermarket.model.discount.Discount;

import java.util.Arrays;
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
    public List<Discount> getDiscounts(ProductAndAmount productAndAmount, double unitPrice) {
        int quantityAsEaches = (int) productAndAmount.getAmount();
        int numberOfXs = quantityAsEaches / num1Items;
        double discountAmount = productAndAmount.getAmount() * unitPrice - ((numberOfXs * num2Items * unitPrice) + quantityAsEaches % num1Items * unitPrice);
        Discount discount = new Discount(this.getProduct(), num1Items + " for " + num2Items, -discountAmount);
        return Arrays.asList(discount);

    }
}
