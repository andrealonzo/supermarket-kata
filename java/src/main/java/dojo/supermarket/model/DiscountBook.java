package dojo.supermarket.model;

public class DiscountBook {

    public  Discount getDiscount(Product p, double quantityInWeight, Offer offer, double unitPrice) {
        int numItemsInDiscount = getNumItemsInOffer(offer);
        int quantityAsEaches = (int) quantityInWeight;
        Discount discount = null;

        if (canTwoForAmountOfferBeApplied(offer, quantityAsEaches)){
            discount = getTwoForAmountDiscount(p, quantityInWeight, offer, unitPrice, quantityAsEaches, numItemsInDiscount);
        }

        int numberOfXs = quantityAsEaches / numItemsInDiscount;
        if (canThreeForTwoOfferBeApplied(offer, quantityAsEaches)) {
            discount = getThreeForTwoDiscount(p, quantityInWeight, unitPrice, quantityAsEaches, numberOfXs);
        }
        DiscountValidator discountValidator = new DiscountValidator(offer);
        if (discountValidator.isTenPercentDiscountOffer()) {
            discount = getTenPercentDiscount(p, quantityInWeight, offer, unitPrice);
        }
        if (canFiveForAmountOfferBeApplied(offer, quantityAsEaches)) {
            discount = getFiveForAmountDiscount(p, quantityInWeight, offer, unitPrice, quantityAsEaches, numItemsInDiscount, numberOfXs);
        }
        return discount;
    }

    private boolean canFiveForAmountOfferBeApplied(Offer offer, int quantityAsInt) {
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isFiveForAmountOffer() && quantityAsInt >= 5;
    }

    private boolean canThreeForTwoOfferBeApplied(Offer offer, int quantityAsInt) {

        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isThreeForTwoOffer() && quantityAsInt > 2;
    }

    private boolean canTwoForAmountOfferBeApplied(Offer offer, int quantityAsInt) {
        DiscountValidator discountValidator = new DiscountValidator(offer);
        return discountValidator.isTwoForAmountOffer() && quantityAsInt >= 2;
    }


    private Discount getFiveForAmountDiscount(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numItemsInDiscount, int numberOfXs) {

        double discountTotal = unitPrice * quantity - (offer.price * numberOfXs + quantityAsInt % 5 * unitPrice);
        Discount discount = new Discount(p, numItemsInDiscount + " for " + offer.price, -discountTotal);
        return discount;
    }

    private Discount getTenPercentDiscount(Product p, double quantity, Offer offer, double unitPrice) {

        Discount discount = new Discount(p, offer.price + "% off", -quantity * unitPrice * offer.price / 100.0);
        return discount;
    }

    private Discount getThreeForTwoDiscount(Product p, double quantity, double unitPrice, int quantityAsInt, int numberOfXs) {
        double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        Discount discount = new Discount(p, "3 for 2", -discountAmount);
        return discount;
    }

    private Discount getTwoForAmountDiscount(Product p, double quantity, Offer offer, double unitPrice, int quantityAsInt, int numItemsInDiscount) {
        double total = offer.price * (quantityAsInt / numItemsInDiscount) + quantityAsInt % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        Discount discount = new Discount(p, "2 for " + offer.price, -discountN);
        return discount;
    }

    private int getNumItemsInOffer(Offer offer){

        DiscountValidator discountValidator = new DiscountValidator(offer);
        int numItemsInDiscount = 1;
        if (discountValidator.isThreeForTwoOffer()) {
            numItemsInDiscount = 3;
        } else if (discountValidator.isTwoForAmountOffer()) {
            numItemsInDiscount = 2;

        } if (discountValidator.isFiveForAmountOffer()) {
            numItemsInDiscount = 5;
        }
        return numItemsInDiscount;
    }
}
