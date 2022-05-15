package dojo.supermarket.model;

import dojo.supermarket.model.offer.BundleOffer;
import dojo.supermarket.model.offer.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BundleTest {

    private SupermarketCatalog catalog;

    private Teller teller;
    private ShoppingCart cart;

    @BeforeEach
    public void setupTest() {
        catalog = new FakeCatalog();
        teller = new Teller(catalog);
        cart = new ShoppingCart();
    }

    @Disabled
    @Test
    public void productsInBundleGet10PercentOff() {
        Product product1 = new Product("toothbrush", ProductUnitType.Each);
        Product product2 = new Product("floss", ProductUnitType.Each);
        catalog.addProduct(product1, 0.99);
        catalog.addProduct(product2, 1.99);
        cart.addProductQuantity(product1, 1);
        cart.addProductQuantity(product2, 1);
        List<Product> productsInBundle = Arrays.asList(new Product[]{product1, product2});
        double bundleDiscount = .10;
        Offer offer = new BundleOffer(productsInBundle, bundleDiscount);
        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.68, receipt.getTotalPrice(), 0.01);

    }
}
