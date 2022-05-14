package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;

public class Offer {
    private SpecialOfferType offerType;
    private final Product product;
    private double price;

    public Offer(SpecialOfferType offerType, Product product, double price) {
        this.offerType = offerType;
        this.price = price;
        this.product = product;
    }
    public SpecialOfferType getOfferType() {
        return offerType;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }




}
