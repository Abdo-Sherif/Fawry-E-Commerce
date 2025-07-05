package org.ecommerce.product;

import org.ecommerce.CustomExceptions.IllegalQuantityException;
import org.ecommerce.product.expirable.Expirable;
import org.ecommerce.product.shippable.Shippable;

import java.util.Optional;

public class Product {
    private final String name;
    private final double price;
    private int availableQuantity;

    public Product(String name, double price, int availableQuantity) {
        if (price < 0 || availableQuantity < 0)
            throw new IllegalArgumentException("Price or Available quantity can't be less than zero");
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public Optional<Shippable> getShippableInfo() {
        return Optional.empty();
    }

    public Optional<Expirable> getExpirableInfo() {
        return Optional.empty();
    }

    public boolean isAvailable() {
        return availableQuantity > 0;
    }

    public void reduceAvailableQuantity(int reductionAmount) {
        if (availableQuantity - reductionAmount < 0) {
            throw new IllegalQuantityException("Product's available quantity can't be less than zero");
        } else {
            this.availableQuantity -= reductionAmount;
        }
    }
}
