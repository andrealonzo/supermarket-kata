package dojo.supermarket.model.receipt;

public class HtmlReceiptPrinter implements ReceiptPrinter {
    private ReceiptPrinter receiptPrinter;

    public HtmlReceiptPrinter(ReceiptPrinter receiptPrinter) {
        this.receiptPrinter = receiptPrinter;
    }

    @Override
    public String printReceipt(Receipt receipt) {
        String basicReceipt = receiptPrinter.printReceipt(receipt);
        String htmlReceipt = """
                    <html>
                        <body>
                        %s
                        </body>
                    </html>
                """.formatted(basicReceipt);
        return htmlReceipt;
    }
}
