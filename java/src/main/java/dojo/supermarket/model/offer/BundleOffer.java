package dojo.supermarket.model.offer;

import dojo.supermarket.model.Product;
import dojo.supermarket.model.ProductAndAmount;
import dojo.supermarket.model.ProductUnitType;
import dojo.supermarket.model.discount.Discount;

import java.util.ArrayList;
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
    public boolean canBeApplied(ProductAndAmount productAndAmount) {
        return true;
    }

    @Override
    public Discount getDiscounts(double quantityInWeight, double unitPrice) {
        return null;
//        Product product1 = new Product("toothbrush", ProductUnitType.Each);
//        Product product2 = new Product("floss", ProductUnitType.Each);
//        Discount discount1 = new Discount(product1,"Bundle Discount", .09);
//        Discount discount2 = new Discount(product2,"Bundle Discount", .19);
//
//        List<Discount> discounts = new ArrayList<>();
//        discounts.add(discount1);
//        discounts.add(discount2);
//
//        return discounts;
    }
}
