package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReceiptItemTest {

    @Test
    public void testReceiptItemEqualsExactItem() {
        Product p = new Product("toothbrush", ProductUnitType.Each);
        ReceiptItem item = new ReceiptItem(p, 1.0, 1.0);
        assertEquals(item, item);
    }

    @Test
    public void testReceiptItemEqualsSameItem() {
        Product p = new Product("toothbrush", ProductUnitType.Each);
        ReceiptItem item1 = new ReceiptItem(p, 1.0, 1.0);
        ReceiptItem item2 = new ReceiptItem(p, 1.0, 1.0);
        assertEquals(item1, item2);
    }
    @Test
    public void testReceiptItemNotEqualsDifferenttItem() {
        Product p = new Product("toothbrush", ProductUnitType.Each);
        ReceiptItem item1 = new ReceiptItem(p, 2.0, 1.0);
        ReceiptItem item2 = new ReceiptItem(p, 1.0, 1.0);
        assertNotEquals(item1, item2);
    }

    @Test
    public void twoReceiptItemsGenerateDifferentHashCodes() {
        Product p = new Product("toothbrush", ProductUnitType.Each);
        ReceiptItem item1 = new ReceiptItem(p, 2.0, 1.0);
        ReceiptItem item2 = new ReceiptItem(p, 1.0, 1.0);
        assertNotEquals(item1.hashCode(), item2.hashCode());
    }
}
