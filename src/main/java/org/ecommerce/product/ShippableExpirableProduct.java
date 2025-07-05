package org.ecommerce.product;

import org.ecommerce.product.expirable.Expirable;
import org.ecommerce.product.shippable.Shippable;

import java.time.LocalDate;
import java.util.Optional;

public class ShippableExpirableProduct extends Product implements Shippable, Expirable {
    private final LocalDate expirationDate;
    private final double weight;

    public ShippableExpirableProduct(String name, double price, int availableQuantity, LocalDate expirationDate, double weight) {
        super(name, price, availableQuantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public Optional<Expirable> getExpirableInfo() {
        return Optional.of(this);
    }

    @Override
    public Optional<Shippable> getShippableInfo() {
        return Optional.of(this);
    }
}
