package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {


    @Test
    public void newShoppingCartIsEmpty(){
        ShoppingCart cart = new ShoppingCart();
        assertTrue(cart.getProducts().isEmpty());
    }

}
