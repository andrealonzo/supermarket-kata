package dojo.supermarket.model;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addOffer(Offer offer) {
        this.offers.put(offer.getProduct(), offer);
    }

    public Receipt checkOutShoppingCart(ShoppingCart shoppingCart) {
        Receipt receipt = new Receipt();
        Collection<ProductAndAmount> productsAndAmounts = shoppingCart.getProductsAndAmountsMap().values();
        addProductsAndAmountsToReceipt(productsAndAmounts, receipt);
        addDiscountsToReceipt(shoppingCart,receipt, offers);

        return receipt;
    }

    private void addProductsAndAmountsToReceipt(Collection<ProductAndAmount> productsAndAmounts, Receipt receipt) {
        for (ProductAndAmount productAndAmount: productsAndAmounts) {
            double unitPrice = catalog.getPricePerUnit(productAndAmount.getProduct());
            receipt.addProduct(productAndAmount.getProduct(), productAndAmount.getAmount(), unitPrice);
        }
    }

    private void addDiscountsToReceipt(ShoppingCart shoppingCart, Receipt receipt, Map<Product, Offer> productOfferMap) {
        Map<Product,ProductAndAmount> productsAndAmountsMap = shoppingCart.getProductsAndAmountsMap();
        for (Product product : productsAndAmountsMap.keySet()) {
            ProductAndAmount productAndAmount = productsAndAmountsMap.get(product);
            double amount = productsAndAmountsMap.get(product).getAmount();
            if (productOfferMap.containsKey(product)) {
                Offer offer = productOfferMap.get(product);
                double pricePerUnit = catalog.getPricePerUnit(product);
                if(offer.canBeApplied(productAndAmount)){
                    Discount discount = offer.getDiscounts(amount, pricePerUnit);
                    receipt.addDiscount(discount);
                }
            }

        }
    }


}
