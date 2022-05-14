package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class FiveForAmountOffer extends Offer{

    public FiveForAmountOffer(Product product, double price) {
        super(OfferType.FiveForAmount, product, price);
    }
}
