package org.ecommerce.product.shippable;

import org.ecommerce.product.Product;

import java.util.Optional;

public class ShippableProduct extends Product implements Shippable {

    private final double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
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
    public Optional<Shippable> getShippableInfo() {
        return Optional.of(this);
    }
}
