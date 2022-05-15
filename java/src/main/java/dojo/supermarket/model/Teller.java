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

    public Receipt checkOutShoppingCart(ShoppingCart shoppingCart) {
        Receipt receipt = new Receipt();

        Set<Product> products = shoppingCart.getProductQuantitiesMap().keySet();
        for (Product product: products) {
            double amount = getAmountOfProductInCart(product, shoppingCart);
            double unitPrice = this.catalog.getUnitPrice(product);
            double price = amount * unitPrice;
            receipt.addProduct(product, amount, unitPrice, price);
        }
        shoppingCart.addDiscountsToReceipt(receipt, this.offers, this.catalog);

        return receipt;
    }

    private Double getAmountOfProductInCart(Product product, ShoppingCart shoppingCart) {
        return shoppingCart.getProductQuantitiesMap().get(product);
    }

}
