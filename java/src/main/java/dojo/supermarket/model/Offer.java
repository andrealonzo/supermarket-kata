package dojo.supermarket.model;

public class Offer {
    SpecialOfferType offerType;
    private final Product product;
    double price;

    public Offer(SpecialOfferType offerType, Product product, double price) {
        this.offerType = offerType;
        this.price = price;
        this.product = product;
    }

//    Product getProduct() {
//        return this.product;
//    }

}
