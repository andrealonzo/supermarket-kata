package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

public abstract class Offer {
    //private OfferType offerType;
    private final Product product;
    private double price;

    public Offer(Product product, double price) {
    //    this.offerType = offerType;
        this.price = price;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }


    public abstract boolean canBeApplied(Offer offer, double quantityInWeight);

    public abstract Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice);
}
