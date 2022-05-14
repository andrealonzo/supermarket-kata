package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    @Test
    public void discountAddedMatches(){
        Product product = new Product("name", ProductUnit.Each);
        Discount discount = new Discount(product, "name", 1.0);
        Receipt receipt = new Receipt();
        receipt.addDiscount(discount);
        assertFalse(receipt.getDiscounts().isEmpty());
        assertEquals(discount, receipt.getDiscounts().get(0));
    }
    @Test
    public void totalPriceWithDiscounts(){
        Product product = new Product("name", ProductUnit.Each);
        Discount discount = new Discount(product, "name", 1.0);
        Receipt receipt = new Receipt();
        receipt.addDiscount(discount);

        assertEquals(1.0, receipt.getTotalPrice(), 0.01);
    }
}
