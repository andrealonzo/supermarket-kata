package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class TenPercentDiscountOffer extends Offer{

    public TenPercentDiscountOffer(Product product, double price) {
        super(OfferType.TenPercentDiscount, product, price);
    }
}
