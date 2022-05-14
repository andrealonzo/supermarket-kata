package dojo.supermarket.model.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.discount.DiscountValidator;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SupermarketCatalog;

public class OfferBook {


    public Discount getDiscount(SupermarketCatalog catalog, Product product, double quantityInWeight, Offer offer) {

        double unitPrice = catalog.getUnitPrice(product);
        int numItemsInDiscount = getNumItemsInOffer(offer);
        int quantityAsEaches = (int) quantityInWeight;
        Discount discount = null;

        int numberOfXs = quantityAsEaches / numItemsInDiscount;
        if(offer.canBeApplied(offer, quantityAsEaches)){
            discount = offer.getDiscountAmount(product, quantityInWeight, unitPrice, quantityAsEaches, numItemsInDiscount,numberOfXs);
        }
        return discount;
    }

    private int getNumItemsInOffer(Offer offer){

        DiscountValidator discountValidator = new DiscountValidator(offer);
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
}
