package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;

public class TenPercentDiscountOffer extends Offer{

    public TenPercentDiscountOffer(Product product, double price) {
        super(OfferType.TenPercentDiscount, product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, int quantityAsEaches) {
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isTenPercentDiscountOffer();
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, Offer offer, double unitPrice, int quantityAsEaches, int numItemsInDiscount, int numberOfXs) {
        Discount discount = new Discount(product, offer.getPrice() + "% off", -quantityInWeight * unitPrice * offer.getPrice() / 100.0);
        return discount;
    }
}
