package dojo.supermarket.model;

public interface SupermarketCatalog {
    void addProduct(Product product, double price);

    double getPricePerUnit(Product product);

}
