package dojo.supermarket.model.receipt.printer;

import dojo.supermarket.model.receipt.Receipt;

public interface ReceiptPrinter {
    String printReceipt(Receipt receipt);
}
