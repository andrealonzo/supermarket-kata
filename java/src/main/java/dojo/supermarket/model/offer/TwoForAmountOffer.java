package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class TwoForAmountOffer extends Offer{

    public TwoForAmountOffer(Product product, double price) {
        super(OfferType.TwoForAmount, product, price);
    }
}
