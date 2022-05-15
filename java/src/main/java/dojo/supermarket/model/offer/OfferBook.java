package dojo.supermarket.model.offer;

import dojo.supermarket.model.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class OfferBook {

    public List<Discount> getDiscounts(double pricePerUnit, double quantity, Offer offer) {
        List<Discount> discounts = new ArrayList<>();

        if(offer.canBeApplied(quantity)){
            Discount discount = offer.getDiscountAmount(quantity, pricePerUnit);
            discounts.add(discount);
        }
        return discounts;
    }

}
