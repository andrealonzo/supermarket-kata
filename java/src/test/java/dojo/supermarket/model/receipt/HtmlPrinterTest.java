package dojo.supermarket.model.receipt;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnitType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HtmlPrinterTest {

    @Test
    public void printBlankReceipt() {

        Receipt receipt = new Receipt();
        TraditionalReceiptPrinter printer = new TraditionalReceiptPrinter();
        String actualPrintout = printer.printReceipt(receipt);

        String expectedPrintout = """
                
                Total:                              0.00
                """;
        assertEquals(expectedPrintout, actualPrintout);
    }

    @Test
    public void printReceiptWithItemsButNoDiscounts() {
        //GIVEN
        Receipt receipt = new Receipt();
        TraditionalReceiptPrinter printer = new TraditionalReceiptPrinter();

        Product product = new Product("bananas", ProductUnitType.Each);
        receipt.addPurchase(product, 5, 7.00);

        //WHEN
        String actualPrintout = printer.printReceipt(receipt);

        System.out.println(actualPrintout);
        String expectedPrintout = """
                
                Total:                              0.00
                """;
        assertEquals(expectedPrintout, actualPrintout);
    }
    //receipt with items but no discounts
    //receipt w discounts but no items
    //receipt w items and discounts
}
