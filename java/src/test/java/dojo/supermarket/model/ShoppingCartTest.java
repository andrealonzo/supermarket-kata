package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    @Test
    public void addedItemIsInShoppingCart(){
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("apple", ProductUnit.Kilo);
        cart.addItem(product);
        assertFalse(cart.getItems().isEmpty());
        assertEquals(product,cart.getItems().get(0).getProduct());
    }

    @Test
    public void newShoppingCartIsEmpty(){
        ShoppingCart cart = new ShoppingCart();
        assertTrue(cart.getItems().isEmpty());
    }

}
