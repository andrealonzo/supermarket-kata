package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class Offer {
    private OfferType offerType;
    private final Product product;
    private double price;

    public Offer(OfferType offerType, Product product, double price) {
        this.offerType = offerType;
        this.price = price;
        this.product = product;
    }
    public OfferType getOfferType() {
        return offerType;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }




}
