package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;

public class ThreeForTwoOffer extends Offer{

    public ThreeForTwoOffer(Product product, double price) {
        super(OfferType.ThreeForTwo, product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, int quantity) {

        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isThreeForTwoOffer() && quantity > 2;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice, int quantityAsEaches, int numItemsInDiscount, int numberOfXs) {
        double discountAmount = quantityInWeight * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsEaches % 3 * unitPrice);
        Discount discount = new Discount(product, "3 for 2", -discountAmount);
        return discount;

    }
}
