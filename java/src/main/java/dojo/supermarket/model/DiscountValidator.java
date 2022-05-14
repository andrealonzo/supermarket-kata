package dojo.supermarket.model;

public class DiscountValidator {

    private Offer offer;

    public DiscountValidator(Offer offer) {
        this.offer = offer;
    }

    public boolean isTenPercentDiscountOffer() {
        return offer.getOfferType() == SpecialOfferType.TenPercentDiscount;
    }

    public boolean isFiveForAmountOffer() {
        return offer.getOfferType() == SpecialOfferType.FiveForAmount;
    }

    public boolean isTwoForAmountOffer() {
        return offer.getOfferType() == SpecialOfferType.TwoForAmount;
    }

    public boolean isThreeForTwoOffer() {
        return offer.getOfferType() == SpecialOfferType.ThreeForTwo;
    }
}
