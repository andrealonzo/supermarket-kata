package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnitType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductTest {
    @Test
    public void productNotEqual(){
        Product product1 = new Product("name", ProductUnitType.Each);
        assertNotEquals(product1, "notProduct");
    }
    @Test
    public void productsEqual(){
        Product product1 = new Product("name", ProductUnitType.Each);
        Product product2 = new Product("name", ProductUnitType.Each);

        assertEquals(product1, product2);
    }

    @Test
    public void productGetUnit(){
        Product product = new Product("name", ProductUnitType.Each);

        assertEquals(ProductUnitType.Each, product.getUnit());
    }
}
