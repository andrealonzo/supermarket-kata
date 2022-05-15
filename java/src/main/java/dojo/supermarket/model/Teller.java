package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> productsAndOffers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addOffer(Offer offer) {
        this.productsAndOffers.put(offer.getProduct(), offer);
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
            receipt.addProduct(productAndAmount.getProduct(), productAndAmount.getAmount(), unitPrice);
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
                            .forEach(discount -> receipt.addDiscount(discount));

                }
            }

        }
    }


}
