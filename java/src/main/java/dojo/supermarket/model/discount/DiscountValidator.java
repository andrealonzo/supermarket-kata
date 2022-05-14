package dojo.supermarket.model.discount;

import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.SpecialOfferType;

public class DiscountValidator {

    private Offer offer;

    public DiscountValidator(Offer offer) {
        this.offer = offer;
    }

    public boolean isTenPercentDiscountOffer() {
        return offer.getOfferType() == SpecialOfferType.TenPercentDiscount;
    }

    public boolean isNumForAmountOffer() {
        return offer.getOfferType() == SpecialOfferType.FiveForAmount;
    }
//    public boolean isFiveForAmountOffer() {
//        return offer.getOfferType() == SpecialOfferType.FiveForAmount;
//    }

    public boolean isTwoForAmountOffer() {
        return offer.getOfferType() == SpecialOfferType.TwoForAmount;
    }

    public boolean isThreeForTwoOffer() {
        return offer.getOfferType() == SpecialOfferType.ThreeForTwo;
    }
}
