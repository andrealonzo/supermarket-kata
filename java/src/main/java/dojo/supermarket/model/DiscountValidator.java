package dojo.supermarket.model;

public class DiscountValidator {

    private Offer offer;

    public DiscountValidator(Offer offer) {
        this.offer = offer;
    }

    public boolean isTenPercentDiscountOffer() {
        return offer.offerType == SpecialOfferType.TenPercentDiscount;
    }

    public boolean isFiveForAmountOffer() {
        return offer.offerType == SpecialOfferType.FiveForAmount;
    }

    public boolean isTwoForAmountOffer() {
        return offer.offerType == SpecialOfferType.TwoForAmount;
    }

    public boolean isThreeForTwoOffer() {
        return offer.offerType == SpecialOfferType.ThreeForTwo;
    }
}
