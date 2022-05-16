package dojo.supermarket.model.receipt;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> purchases = new ArrayList<>();
    private List<Discount> discountsApplied = new ArrayList<>();

    public Double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : this.purchases) {
            total += item.getTotalPrice();
        }
        for (Discount discount : this.discountsApplied) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public void addPurchase(Product p, double quantity, double pricePerUnit) {
        this.purchases.add(new ReceiptItem(p, quantity, pricePerUnit));
    }

    public List<ReceiptItem> getPurchases() {
        return this.purchases;
    }

    public void addDiscountsApplied(Discount discount) {
        this.discountsApplied.add(discount);
    }

    public List<Discount> getDiscountsApplied() {
        return discountsApplied;
    }
}
