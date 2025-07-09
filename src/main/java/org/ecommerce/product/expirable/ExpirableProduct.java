package org.ecommerce.product.expirable;

import org.ecommerce.product.Product;

import java.time.LocalDate;
import java.util.Optional;

public class ExpirableProduct extends Product {
    private final ExpirableInfo expirableInfo;

    public ExpirableProduct(String name, double price, int availableQuantity, LocalDate expirationDate) {
        super(name, price, availableQuantity);
        expirableInfo = new ExpirableInfo(expirationDate);
    }

    @Override
    public Optional<Expirable> getExpirableInfo() {
        return Optional.of(expirableInfo);
    }
}
