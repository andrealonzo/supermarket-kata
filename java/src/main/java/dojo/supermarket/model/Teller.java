package dojo.supermarket.model;

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

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();

        Set<Product> productQuantities = theCart.getProductQuantitiesMap().keySet();
        for (Product product: productQuantities) {
            double quantity = theCart.getProductQuantitiesMap().get(product);
            double unitPrice = this.catalog.getUnitPrice(product);
            double price = quantity * unitPrice;
            receipt.addProduct(product, quantity, unitPrice, price);
        }
        theCart.addReceiptDiscounts(receipt, this.offers, this.catalog);

        return receipt;
    }

}
