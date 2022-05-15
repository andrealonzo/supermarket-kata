package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addOffer(Offer offer) {
        this.offers.put(offer.getProduct(), offer);
    }

    public Receipt checkOutShoppingCart(ShoppingCart shoppingCart) {
        Receipt receipt = new Receipt();

        Set<Product> products = shoppingCart.getProductQuantitiesMap().keySet();
        addProductsToReceipt(shoppingCart, receipt, products);
        addDiscountsToReceipt(shoppingCart,receipt, offers, catalog);
        //shoppingCart.addDiscountsToReceipt(receipt, offers, catalog);

        return receipt;
    }

    private void addProductsToReceipt(ShoppingCart shoppingCart, Receipt receipt, Set<Product> products) {
        for (Product product: products) {
            double amount = getAmountOfProductInCart(product, shoppingCart);
            double unitPrice = this.catalog.getUnitPrice(product);
            receipt.addProduct(product, amount, unitPrice);
        }
    }

    private void addDiscountsToReceipt(ShoppingCart shoppingCart, Receipt receipt, Map<Product, Offer> productOfferMap, SupermarketCatalog catalog) {
        Map<Product,Double> productQuantitiesMap = shoppingCart.getProductQuantitiesMap();
        for (Product product : productQuantitiesMap.keySet()) {
            double quantity = productQuantitiesMap.get(product);
            if (productOfferMap.containsKey(product)) {
                Offer offer = productOfferMap.get(product);
                double pricePerUnit = catalog.getUnitPrice(product);
                if(offer.canBeApplied(quantity)){
                    Discount discount = offer.getDiscounts(quantity, pricePerUnit);
                    receipt.addDiscount(discount);
                }
            }

        }
    }

    private Double getAmountOfProductInCart(Product product, ShoppingCart shoppingCart) {
        return shoppingCart.getProductQuantitiesMap().get(product);
    }

}
