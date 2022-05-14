package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DiscountTest {
    @Test
    public void getDescriptionWithWrongDescriptionIsFalse(){
        Product product = new Product("name", ProductUnit.Each);
        Discount discount = new Discount(product, "name", 1.0);
        assertNotEquals("name1", discount.getDescription());
    }
    @Test
    public void getDescriptionWithMatchingDescriptionIsTrue(){
        Product product = new Product("name", ProductUnit.Each);
        Discount discount = new Discount(product, "name", 1.0);
        assertEquals("name", discount.getDescription());
    }
    @Test
    public void getProductWithWrongProductIsFalse(){
        Product product1 = new Product("name", ProductUnit.Each);
        Product product2 = new Product("name1", ProductUnit.Each);
        Discount discount = new Discount(product1, "name", 1.0);
        assertNotEquals(product2, discount.getProduct());
    }

    @Test
    public void getProductWithMatchingProductIsTrue(){
        Product product1 = new Product("name", ProductUnit.Each);
        Product product2 = new Product("name1", ProductUnit.Each);
        Discount discount = new Discount(product1, "name", 1.0);
        assertEquals(product1, discount.getProduct());
    }
}
