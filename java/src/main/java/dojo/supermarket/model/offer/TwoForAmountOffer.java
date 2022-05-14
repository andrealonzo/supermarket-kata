package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;

public class TwoForAmountOffer extends Offer{

    public TwoForAmountOffer(Product product, double price) {
        super(OfferType.TwoForAmount, product, price);
    }

    @Override
    public boolean canBeApplied(Offer offer, double quantityAsWeight) {
        int quantity = (int)quantityAsWeight;
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isTwoForAmountOffer() && quantity >= 2;
    }

    @Override
    public Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice) {
        int quantityAsEaches = (int) quantityInWeight;
        double total = this.getPrice() * (quantityAsEaches / getNumItemsInOffer()) + quantityAsEaches % 2 * unitPrice;
        double discountN = unitPrice * quantityAsEaches - total;
        Discount discount = new Discount(product, "2 for " + this.getPrice(), -discountN);
        return discount;
    }
}
