package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class NumForNumOffer extends Offer{

    public  int num1Items = 3;
    public  int num2Items = 2;

    public NumForNumOffer(Product product, int num1Items, int num2Items) {
        super(product);
        this.num1Items = num1Items;
        this.num2Items = num2Items;
    }

    @Override
    public boolean canBeApplied(double quantity) {
        return quantity > num2Items;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        int numberOfXs = quantityAsEaches / num1Items;
        double discountAmount = quantityInWeight * unitPrice - ((numberOfXs * num2Items * unitPrice) + quantityAsEaches % num1Items * unitPrice);
        Discount discount = new Discount(product, num1Items + " for " + num2Items, -discountAmount);
        return discount;

    }
}
