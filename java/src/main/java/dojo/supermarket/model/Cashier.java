package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductAndAmount;
import dojo.supermarket.model.receipt.Receipt;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cashier {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> productsAndOffers = new HashMap<>();

    public Cashier(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addOffer(Offer offer) {
        offer.getAffectedProducts().stream().forEach(
                product->this.productsAndOffers.put(product, offer)
        );
    }

    public Receipt checkOutShoppingCart(ShoppingCart shoppingCart) {
        Receipt receipt = new Receipt();
        Collection<ProductAndAmount> productsAndAmounts = shoppingCart.getProductsAndAmounts();
        addProductsAndAmountsToReceipt(productsAndAmounts, receipt);
        addDiscountsToReceipt(shoppingCart,receipt, productsAndOffers);

        return receipt;
    }

    private void addProductsAndAmountsToReceipt(Collection<ProductAndAmount> productsAndAmounts, Receipt receipt) {
        for (ProductAndAmount productAndAmount: productsAndAmounts) {
            double unitPrice = catalog.getPricePerUnit(productAndAmount.getProduct());
            receipt.addPurchase(productAndAmount.getProduct(), productAndAmount.getAmount(), unitPrice);
        }
    }

    private void addDiscountsToReceipt(ShoppingCart shoppingCart, Receipt receipt, Map<Product, Offer> productOfferMap) {
        Collection<ProductAndAmount> productsAndAmounts = shoppingCart.getProductsAndAmounts();
        for (ProductAndAmount productAndAmount : productsAndAmounts) {
            Product product = productAndAmount.getProduct();
            if (productOfferMap.containsKey(product)) {
                Offer offer = productOfferMap.get(product);
                double pricePerUnit = catalog.getPricePerUnit(product);
                if(offer.canBeApplied(productAndAmount, shoppingCart)){
                    List<Discount> discounts = offer.getDiscounts(productAndAmount, pricePerUnit);
                    discounts.stream()
                            .forEach(discount -> receipt.addDiscountsApplied(discount));

                }
            }

        }
    }


}
