package dojo.supermarket.model.receipt;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> receiptItems = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();

    public Double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : this.receiptItems) {
            total += item.getTotalPrice();
        }
        for (Discount discount : this.discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public void addReceiptItem(Product p, double quantity, double pricePerUnit) {
        this.receiptItems.add(new ReceiptItem(p, quantity, pricePerUnit));
    }

    public List<ReceiptItem> getReceiptItems() {
        return this.receiptItems;
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }
}
