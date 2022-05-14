package dojo.supermarket.model.discount;

import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.OfferType;

public class DiscountValidator {

    private Offer offer;

    public DiscountValidator(Offer offer) {
        this.offer = offer;
    }

    public boolean isTenPercentDiscountOffer() {
        return offer.getOfferType() == OfferType.TenPercentDiscount;
    }

    public boolean isNumForAmountOffer() {
        return offer.getOfferType() == OfferType.FiveForAmount;
    }


    public boolean isTwoForAmountOffer() {
        return offer.getOfferType() == OfferType.TwoForAmount;
    }

    public boolean isThreeForTwoOffer() {
        return offer.getOfferType() == OfferType.ThreeForTwo;
    }
}
