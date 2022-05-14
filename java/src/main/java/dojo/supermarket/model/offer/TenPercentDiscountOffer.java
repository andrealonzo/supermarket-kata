package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class TenPercentDiscountOffer extends Offer{

    public TenPercentDiscountOffer(SpecialOfferType offerType, Product product, double price) {
        super(offerType, product, price);
    }
}
