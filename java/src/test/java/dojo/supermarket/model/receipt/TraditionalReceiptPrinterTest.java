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
        receipt.addPurchase(product, 5, .50);

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


    @Test
    public void printReceiptWithDiscountsAndProductsPurchased() {

        Receipt receipt = new Receipt();

        Product product = new Product("bananas", ProductUnitType.Each);
        Discount discount = new Discount( product, "Bananas 2 for 1", -.99);
        receipt.addPurchase(product, 2, .99);
        receipt.addDiscountsApplied(discount);
        TraditionalReceiptPrinter printer = new TraditionalReceiptPrinter();
        String actualPrintout = printer.printReceipt(receipt);


        String expectedPrintout = """
                bananas                             1.98
                  0.99 * 2
                Bananas 2 for 1(bananas)           -0.99
                                
                Total:                              0.99
                """;
        assertEquals(expectedPrintout, actualPrintout);
    }


    @Test
    public void printReceiptWithDiscountsAndKiloProductsPurchased() {

        Receipt receipt = new Receipt();

        Product product = new Product("bananas", ProductUnitType.Kilo);
        Discount discount = new Discount( product, "Bananas 2 for 1", -.99);
        receipt.addPurchase(product, 2, .99);
        receipt.addDiscountsApplied(discount);
        TraditionalReceiptPrinter printer = new TraditionalReceiptPrinter();
        String actualPrintout = printer.printReceipt(receipt);


        String expectedPrintout = """
                bananas                             1.98
                  0.99 * 2.000
                Bananas 2 for 1(bananas)           -0.99
                                
                Total:                              0.99
                """;
        assertEquals(expectedPrintout, actualPrintout);
    }

}
