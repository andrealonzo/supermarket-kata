package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;

public abstract class Offer {
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

    protected int getNumItemsInOffer(){

        DiscountValidator discountValidator = new DiscountValidator(this);
        int numItemsInDiscount = 1;
        if (discountValidator.isThreeForTwoOffer()) {
            numItemsInDiscount = 3;
        } else if (discountValidator.isTwoForAmountOffer()) {
            numItemsInDiscount = 2;

        } if (discountValidator.isNumForAmountOffer()) {
            numItemsInDiscount = 5;
        }
        return numItemsInDiscount;
    }

    public abstract boolean canBeApplied(Offer offer, double quantityInWeight);

    public abstract Discount getDiscountAmount(Product product, double quantityInWeight, double unitPrice);
}
