package org.ecommerce.product.shippable;

import org.ecommerce.product.Product;

import java.util.Optional;

public class ShippableProduct extends Product {

    private final ShippableInfo shippableInfo;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        shippableInfo = new ShippableInfo(weight, name);
    }

    @Override
    public Optional<Shippable> getShippableInfo() {
        return Optional.of(shippableInfo);
    }
}
