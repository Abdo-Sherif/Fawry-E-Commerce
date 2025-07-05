package org.ecommerce.product.expirable;

import org.ecommerce.product.Product;

import java.time.LocalDate;
import java.util.Optional;

public class ExpirableProduct extends Product implements Expirable {
    private final LocalDate expirationDate;

    public ExpirableProduct(String name, double price, int availableQuantity, LocalDate expirationDate) {
        super(name, price, availableQuantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public Optional<Expirable> getExpirableInfo() {
        return Optional.of(this);
    }
}
