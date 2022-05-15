package dojo.supermarket.model.receipt;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnitType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraditionalReceiptPrinterTest {

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
        receipt.addReceiptItem(product, 5, .50);

        //WHEN
        String actualPrintout = printer.printReceipt(receipt);

        String expectedPrintout = """
                bananas                             2.50
                  0.50 * 5
                              
                Total:                              2.50
                """;
        assertEquals(expectedPrintout, actualPrintout);
    }
    @Test
    public void printReceiptWithDiscountsButNoProductsPurchased() {

        Receipt receipt = new Receipt();

        Product product = new Product("bananas", ProductUnitType.Each);
        Discount discount = new Discount( product, "Free Money For All", -.99);
        receipt.addDiscountsApplied(discount);
        TraditionalReceiptPrinter printer = new TraditionalReceiptPrinter();
        String actualPrintout = printer.printReceipt(receipt);


        String expectedPrintout = """
                Free Money For All(bananas)        -0.99
                                
                Total:                             -0.99
                """;
        assertEquals(expectedPrintout, actualPrintout);
    }

}
