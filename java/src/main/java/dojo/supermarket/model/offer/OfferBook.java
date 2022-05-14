package dojo.supermarket.model.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SupermarketCatalog;

public class OfferBook {

    public Discount getDiscount(double pricePerUnit, Product product, double quantity, Offer offer) {

        Discount discount = null;
        if(offer.canBeApplied(offer, quantity)){
            discount = offer.getDiscountAmount(product, quantity, pricePerUnit);
        }
        return discount;
    }

}
