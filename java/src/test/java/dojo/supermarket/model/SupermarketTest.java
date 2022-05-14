package dojo.supermarket.model;

import dojo.supermarket.model.offer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupermarketTest {

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
        Product toothbrush = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(toothbrush, 0.99);

        cart.addProductQuantity(toothbrush, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void noDiscountOnKiloItem(){

        Product product = new Product("apple", ProductUnitType.Kilo);
        catalog.addProduct(product, 1.99);

        cart.addProductQuantity(product, 1);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.99, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void percentDiscountOnKiloItem(){

        Product product = new Product("apple", ProductUnitType.Kilo);
        catalog.addProduct(product, 1.99);

        cart.addProductQuantity(product, 1);

        Offer offer = new TenPercentDiscountOffer(product, 10.0);
   //     Offer offer = new Offer(OfferType.TenPercentDiscount, product, 10.0);
        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.79, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void percentDiscountOnEachItem(){

        Product product = new Product("rice", ProductUnitType.Kilo);
        catalog.addProduct(product, 2.49);

        cart.addProductQuantity(product, 1);

        Offer offer = new TenPercentDiscountOffer(product, 10.0);

        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.24, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void noDiscountIfNumItemsIsUnderLimit(){

        Product product = new Product("rice", ProductUnitType.Kilo);
        catalog.addProduct(product, 2.49);
        cart.addProductQuantity(product, 1);


        Offer offer = new FiveForAmountOffer(product, 10.0);
        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.49, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void fiveToothpasteNoDiscount(){

        Product product = new Product("toothpaste", ProductUnitType.Each);
        catalog.addProduct(product, 1.79);
        cart.addProductQuantity(product, 5);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(8.95, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void fiveToothpasteHasDiscount(){

        Product product = new Product("toothpaste", ProductUnitType.Each);
        catalog.addProduct(product, 1.79);
        cart.addProductQuantity(product, 4);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(7.16, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void twoCherryTomatoesNoDiscount(){

        Product product = new Product("tomato", ProductUnitType.Each);
        catalog.addProduct(product, .69);
        cart.addProductQuantity(product, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.38, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void twoCherryTomatoesWithDiscount(){

        Product product = new Product("tomato", ProductUnitType.Each);
        catalog.addProduct(product, .69);
        cart.addProductQuantity(product, 2);

        Offer offer = new TwoForAmountOffer(product, .99);
        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(.99, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void tenPercentDiscount() {
        Product toothbrush = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnitType.Kilo);
        catalog.addProduct(apples, 1.99);
         Offer offer = new TenPercentDiscountOffer(toothbrush, 10.0);

        teller.addOffer(offer);

        cart.addProductQuantity(apples, 2.5);

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

    @Test
    public void addItemsMultipleTimes() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 1);
        cart.addProductQuantity(product, 2);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.97, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void threeForTwoDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 1);
        cart.addProductQuantity(product, 2);
        Offer offer = new ThreeForTwoOffer(product, 0);

        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.98, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void fiveForAmountDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 5);
        Offer offer = new FiveForAmountOffer(product, 2.99);
        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(2.99, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void fiveForAmountDiscountWithLessThanFiveDoesntGetDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 4);
        Offer offer = new FiveForAmountOffer(product, 2.99);
        teller.addOffer(offer);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(3.96, receipt.getTotalPrice(), 0.01);

    }


}
