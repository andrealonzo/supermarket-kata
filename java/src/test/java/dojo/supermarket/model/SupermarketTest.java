package dojo.supermarket.model;

import dojo.supermarket.ReceiptPrinter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly
    private SupermarketCatalog catalog;

    private Teller teller;
    private ShoppingCart cart;
    @BeforeEach
    public void setupTest(){
        catalog = new FakeCatalog();
        teller = new Teller(catalog);
        cart = new ShoppingCart();


    }
    @Test
    public void testNoDiscount() {
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        cart.addItemQuantity(toothbrush, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void noDiscountOnKiloItem(){

        Product product = new Product("apple", ProductUnit.Kilo);
        catalog.addProduct(product, 1.99);

        cart.addItemQuantity(product, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.99, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void percentDiscountOnKiloItem(){

        Product product = new Product("apple", ProductUnit.Kilo);
        catalog.addProduct(product, 1.99);

        cart.addItemQuantity(product, 1);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, product, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.79, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void percentDiscountOnEachItem(){

        Product product = new Product("rice", ProductUnit.Kilo);
        catalog.addProduct(product, 2.49);

        cart.addItemQuantity(product, 1);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, product, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.24, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void noDiscountOnEachItem(){

        Product product = new Product("rice", ProductUnit.Kilo);
        catalog.addProduct(product, 2.49);
        cart.addItemQuantity(product, 1);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, product, 7.49);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.49, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void fiveToothpasteNoDiscount(){

        Product product = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(product, 1.79);
        cart.addItemQuantity(product, 5);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(8.95, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void fiveToothpasteHasDiscount(){

        Product product = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(product, 1.79);
        cart.addItemQuantity(product, 4);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(7.16, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void twoCherryTomatoesNoDiscount(){

        Product product = new Product("tomato", ProductUnit.Each);
        catalog.addProduct(product, .69);
        cart.addItemQuantity(product, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.38, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void twoCherryTomatoesWithDiscount(){

        Product product = new Product("tomato", ProductUnit.Each);
        catalog.addProduct(product, .69);
        cart.addItemQuantity(product, 2);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, product, .99);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(.99, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void tenPercentDiscount() {
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        cart.addItemQuantity(apples, 2.5);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(4.975, receipt.getTotalPrice(), 0.01);
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(2.5 * 1.99, receiptItem.getTotalPrice());
        assertEquals(2.5, receiptItem.getQuantity());

    }


}
