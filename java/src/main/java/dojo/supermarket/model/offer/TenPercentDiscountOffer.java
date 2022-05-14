package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;

public class TenPercentDiscountOffer extends Offer{

    public TenPercentDiscountOffer(Product product, double price) {
        super(OfferType.TenPercentDiscount, product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityAsWeight) {
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isTenPercentDiscountOffer();
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        Discount discount = new Discount(product, this.getPrice() + "% off", -quantityInWeight * unitPrice * this.getPrice() / 100.0);
        return discount;
    }
}
