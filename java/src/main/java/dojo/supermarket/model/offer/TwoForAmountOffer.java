package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;

public class TwoForAmountOffer extends Offer{

    public TwoForAmountOffer(Product product, double price) {
        super(OfferType.TwoForAmount, product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, int quantity) {
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isTwoForAmountOffer() && quantity >= 2;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice, int quantity, int numberOfXs) {
        double total = this.getPrice() * (quantity / getNumItemsInOffer()) + quantity % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        Discount discount = new Discount(product, "2 for " + this.getPrice(), -discountN);
        return discount;
    }
}
