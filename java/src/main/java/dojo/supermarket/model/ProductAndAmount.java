package dojo.supermarket.model;

public class ProductAndAmount {
    private Product product;
    private double amount;

    public ProductAndAmount(Product product, double amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }

}
