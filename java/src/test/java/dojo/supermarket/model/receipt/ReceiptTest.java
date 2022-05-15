package dojo.supermarket.model.receipt;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnitType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    @Test
    public void discountAddedMatches(){
        Product product = new Product("name", ProductUnitType.Each);
        Discount discount = new Discount(product, "name", 1.0);
        Receipt receipt = new Receipt();
        receipt.addDiscountsApplied(discount);
        assertFalse(receipt.getDiscountsApplied().isEmpty());
        assertEquals(discount, receipt.getDiscountsApplied().get(0));
    }
    @Test
    public void totalPriceWithDiscounts(){
        Product product = new Product("name", ProductUnitType.Each);
        Discount discount = new Discount(product, "name", 1.0);
        Receipt receipt = new Receipt();
        receipt.addDiscountsApplied(discount);

        assertEquals(1.0, receipt.getTotalPrice(), 0.01);
    }
}
