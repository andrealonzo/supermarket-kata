package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class ThreeForTwoOffer extends Offer{

    public ThreeForTwoOffer(Product product, double price) {
        super(OfferType.ThreeForTwo, product, price);
    }
}
