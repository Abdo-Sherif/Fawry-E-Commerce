package org.ecommerce.product;

import org.ecommerce.product.expirable.Expirable;
import org.ecommerce.product.expirable.ExpirableInfo;
import org.ecommerce.product.shippable.Shippable;
import org.ecommerce.product.shippable.ShippableInfo;

import java.time.LocalDate;
import java.util.Optional;

public class ShippableExpirableProduct extends Product {
    private final ShippableInfo shippableInfo;
    private final ExpirableInfo expirableInfo;

    public ShippableExpirableProduct(String name, double price, int availableQuantity, LocalDate expirationDate, double weight) {
        super(name, price, availableQuantity);
        shippableInfo = new ShippableInfo(weight, name);
        expirableInfo = new ExpirableInfo(expirationDate);
    }

    @Override
    public Optional<Expirable> getExpirableInfo() {
        return Optional.of(expirableInfo);
    }

    @Override
    public Optional<Shippable> getShippableInfo() {
        return Optional.of(shippableInfo);
    }
}
