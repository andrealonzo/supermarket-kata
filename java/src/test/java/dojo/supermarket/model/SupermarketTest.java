package dojo.supermarket.model;

import dojo.supermarket.model.offer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void noDiscountOnKiloItem(){

        Product product = new Product("apple", ProductUnitType.Kilo);
        catalog.addProduct(product, 1.99);

        cart.addProductQuantity(product, 1);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(1.99, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void percentDiscountOnKiloItem(){

        Product product = new Product("apple", ProductUnitType.Kilo);
        catalog.addProduct(product, 1.99);

        cart.addProductQuantity(product, 1);

        Offer offer = new PercentDiscountOffer(product, 10.0);
   //     Offer offer = new Offer(OfferType.TenPercentDiscount, product, 10.0);
        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(1.79, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void percentDiscountOnEachItem(){

        Product product = new Product("rice", ProductUnitType.Kilo);
        catalog.addProduct(product, 2.49);

        cart.addProductQuantity(product, 1);

        Offer offer = new PercentDiscountOffer(product, 10.0);

        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(2.24, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void noDiscountIfNumItemsIsUnderLimit(){

        Product product = new Product("rice", ProductUnitType.Kilo);
        catalog.addProduct(product, 2.49);
        cart.addProductQuantity(product, 1);


        Offer offer = new NumForAmountOffer(product,5, 10.0);
        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(2.49, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void fiveToothpasteNoDiscount(){

        Product product = new Product("toothpaste", ProductUnitType.Each);
        catalog.addProduct(product, 1.79);
        cart.addProductQuantity(product, 5);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(8.95, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void fiveToothpasteHasDiscount(){

        Product product = new Product("toothpaste", ProductUnitType.Each);
        catalog.addProduct(product, 1.79);
        cart.addProductQuantity(product, 4);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(7.16, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void twoCherryTomatoesNoDiscount(){

        Product product = new Product("tomato", ProductUnitType.Each);
        catalog.addProduct(product, .69);
        cart.addProductQuantity(product, 2);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(1.38, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void twoCherryTomatoesWithDiscount(){

        Product product = new Product("tomato", ProductUnitType.Each);
        catalog.addProduct(product, .69);
        cart.addProductQuantity(product, 2);

        Offer offer = new NumForAmountOffer(product,2, .99);
        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(.99, receipt.getTotalPrice(), 0.01);
    }
    @Test
    public void tenPercentDiscount() {
        Product toothbrush = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnitType.Kilo);
        catalog.addProduct(apples, 1.99);
         Offer offer = new PercentDiscountOffer(toothbrush, 10.0);

        teller.addOffer(offer);

        cart.addProductQuantity(apples, 2.5);

        // ACT
        Receipt receipt = teller.checkOutShoppingCart(cart);

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
    public void tenPercentDiscountOnToothbrush() {

        Product toothbrush = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(toothbrush, 0.99);
        Offer offer = new PercentDiscountOffer(toothbrush, 10.0);
        teller.addOffer(offer);

        cart.addProductQuantity(toothbrush, 2);

        // ACT
        Receipt receipt = teller.checkOutShoppingCart(cart);

        // ASSERT
        assertEquals(1.78, receipt.getTotalPrice(), 0.01);
        assertFalse( receipt.getDiscounts().isEmpty());

    }
    @Test
    public void twentyPercentDiscountOnToothbrush() {

        Product toothbrush = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(toothbrush, 0.99);
        Offer offer = new PercentDiscountOffer(toothbrush, 20.0);
        teller.addOffer(offer);

        cart.addProductQuantity(toothbrush, 2);

        // ACT
        Receipt receipt = teller.checkOutShoppingCart(cart);

        // ASSERT
        assertEquals(1.58, receipt.getTotalPrice(), 0.01);
        assertFalse( receipt.getDiscounts().isEmpty());

    }

    @Test
    public void addItemsMultipleTimes() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 1);
        cart.addProductQuantity(product, 2);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(2.97, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void threeForTwoDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 1);
        cart.addProductQuantity(product, 2);
        Offer offer = new NumForNumOffer(product, 3,2);

        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(1.98, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void fiveForThreeDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 5);
        Offer offer = new NumForNumOffer(product, 5,3);

        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(2.97, receipt.getTotalPrice(), 0.01);

    }
    @Test
    public void fiveForAmountDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 5);
        Offer offer = new NumForAmountOffer(product, 5,2.99);
        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(2.99, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void fiveForAmountDiscountWithLessThanFiveDoesntGetDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 4);
        Offer offer = new NumForAmountOffer(product, 5, 2.99);
        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(3.96, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void fourForAmountDiscount() {
        Product product = new Product("toothbrush", ProductUnitType.Each);
        catalog.addProduct(product, 0.99);
        cart.addProductQuantity(product, 4);
        Offer offer = new NumForAmountOffer(product, 4,2.99);
        teller.addOffer(offer);

        Receipt receipt = teller.checkOutShoppingCart(cart);

        assertEquals(2.99, receipt.getTotalPrice(), 0.01);

    }

}
