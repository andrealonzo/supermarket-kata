package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;

public class FiveForAmountOffer extends Offer{

    public FiveForAmountOffer(Product product, double price) {
        super(OfferType.FiveForAmount, product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, int quantity) {

        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isNumForAmountOffer() && quantity >= 5;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, Offer offer, double unitPrice, int quantityAsEaches, int numItemsInDiscount, int numberOfXs) {
        double discountTotal = unitPrice * quantityInWeight - (offer.getPrice() * numberOfXs + quantityAsEaches % 5 * unitPrice);
        Discount discount = new Discount(product, numItemsInDiscount + " for " + offer.getPrice(), -discountTotal);
        return discount;
    }
}
