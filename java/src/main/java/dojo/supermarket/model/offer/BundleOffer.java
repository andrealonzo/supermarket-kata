package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.discount.Discount;

import java.util.List;

public class BundleOffer extends Offer {
    private List<Product> products;
    private double discount;
    public BundleOffer(List<Product> products, double discount) {
        super(null);
        this.products = products;
        this.discount = discount;
    }

    @Override
    public boolean canBeApplied(double quantityInWeight) {
        return true;
    }

    @Override
    public Discount getDiscountAmount( double quantityInWeight, double unitPrice) {
        return null;
    }
}
