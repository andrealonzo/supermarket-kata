package dojo.supermarket.model;

import dojo.supermarket.model.offer.BundleOffer;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.receipt.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BundleTest {

    private SupermarketCatalog catalog;

    private Cashier cashier;
    private ShoppingCart cart;

    @BeforeEach
    public void setupTest() {
        catalog = new FakeCatalog();
        cashier = new Cashier(catalog);
        cart = new ShoppingCart();
    }

  //  @Disabled
    @Test
    public void productsInBundleGet10PercentOff() {
        Product product1 = new Product("toothbrush", ProductUnitType.Each);
        Product product2 = new Product("floss", ProductUnitType.Each);
        catalog.addProduct(product1, 0.99);
        catalog.addProduct(product2, 1.99);
        cart.addProductAndAmount(product1, 1);
        cart.addProductAndAmount(product2, 1);
        List<Product> productsInBundle = Arrays.asList(new Product[]{product1, product2});
        double bundleDiscount = .10;
        Offer offer = new BundleOffer(productsInBundle, bundleDiscount);
        cashier.addOffer(offer);

        Receipt receipt = cashier.checkOutShoppingCart(cart);

        assertEquals(2.68, receipt.getTotalPrice(), 0.01);
        assertEquals(2, receipt.getDiscounts().size());

    }
    @Test
    public void productsNotInBundleDontGet10PercentOff() {
        Product product1 = new Product("toothbrush", ProductUnitType.Each);
        Product product2 = new Product("floss", ProductUnitType.Each);
        Product product3 = new Product("cups", ProductUnitType.Each);
        catalog.addProduct(product1, 0.99);
        catalog.addProduct(product2, 1.99);
        catalog.addProduct(product3, 2.99);
        cart.addProductAndAmount(product1, 1);
        cart.addProductAndAmount(product2, 1);
        cart.addProductAndAmount(product3, 1);
        List<Product> productsInBundle = Arrays.asList(new Product[]{product1, product2});
        double bundleDiscount = .20;
        Offer offer = new BundleOffer(productsInBundle, bundleDiscount);
        cashier.addOffer(offer);

        Receipt receipt = cashier.checkOutShoppingCart(cart);

        assertEquals(2, receipt.getDiscounts().size());
        assertEquals(5.37, receipt.getTotalPrice(), 0.01);

    }


    @Test
    public void oneProductsNotInBundleWithTwoProductsDontGetDiscount() {

        Product product1 = new Product("toothbrush", ProductUnitType.Each);
        Product product2 = new Product("floss", ProductUnitType.Each);
        catalog.addProduct(product1, 0.99);
        catalog.addProduct(product2, 1.99);
        cart.addProductAndAmount(product1, 1);
        List<Product> productsInBundleOffer = Arrays.asList(new Product[]{product1, product2});
        double bundleDiscount = .10;
        Offer offer = new BundleOffer(productsInBundleOffer, bundleDiscount);
        cashier.addOffer(offer);

        Receipt receipt = cashier.checkOutShoppingCart(cart);

        assertEquals(0, receipt.getDiscounts().size());
        assertEquals(.99, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void allProductsThatMeetMinBundleReqGet10PercentOff() {
        //GIVEN
        Product product1 = new Product("toothbrush", ProductUnitType.Each);
        Product product2 = new Product("floss", ProductUnitType.Each);
        catalog.addProduct(product1, 0.99);
        catalog.addProduct(product2, 1.99);
        cart.addProductAndAmount(product1, 1);
        cart.addProductAndAmount(product2, 2);
        List<Product> productsInBundle = Arrays.asList(new Product[]{product1, product2});
        double bundleDiscount = .10;
        Offer offer = new BundleOffer(productsInBundle, bundleDiscount);
        cashier.addOffer(offer);

        //WHEN
        Receipt receipt = cashier.checkOutShoppingCart(cart);

        //RESULT
        assertEquals(2, receipt.getDiscounts().size());
        assertEquals(4.47, receipt.getTotalPrice(), 0.01);
    }

}
