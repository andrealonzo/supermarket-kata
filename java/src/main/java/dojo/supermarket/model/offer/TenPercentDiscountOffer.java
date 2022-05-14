package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class TenPercentDiscountOffer extends Offer{

    public TenPercentDiscountOffer(Product product, double price) {
        super(product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityAsWeight) {
        return true;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        Discount discount = new Discount(product, this.getPrice() + "% off", -quantityInWeight * unitPrice * this.getPrice() / 100.0);
        return discount;
    }
}
