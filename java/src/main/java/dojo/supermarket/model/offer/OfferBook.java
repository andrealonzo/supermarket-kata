package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public class OfferBook {

    public Discount getDiscount(double pricePerUnit, Product product, double quantity, Offer offer) {

        Discount discount = null;
        if(offer.canBeApplied(offer, quantity)){
            discount = offer.getDiscountAmount(product, quantity, pricePerUnit);
        }
        return discount;
    }

}
