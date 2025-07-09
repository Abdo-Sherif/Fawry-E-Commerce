package org.ecommerce.service.impl;

import org.ecommerce.checkout.CartItem;
import org.ecommerce.product.shippable.Shippable;
import org.ecommerce.service.ShippingService;

import java.util.List;

public class ShippingServiceImpl implements ShippingService {
    private static final double SHIPPING_RATE = 30.0;

    @Override
    public double calculateShipping(List<CartItem> shippableItems) {
        double totalWeight = calculateTotalWeight(shippableItems);
        return (totalWeight * SHIPPING_RATE) / 1000.0;
    }

    @Override
    public void ship(List<CartItem> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        for (CartItem item : shippableItems) {
            Shippable shippableProduct = item.getProduct().getShippableInfo().get();
            System.out.println(item.getQuantity() + "x " + shippableProduct.getName() + "\t" + shippableProduct.getWeight() * item.getQuantity() + "g");
        }

        double totalWeight = calculateTotalWeight(shippableItems);
        System.out.println("Total package weight " + (totalWeight / 1000.0) + "kg\n");
    }

    private double calculateTotalWeight(List<CartItem> shippableItems) {
        double totalWeight = 0.0;
        for (CartItem item : shippableItems) {
            Shippable shippableProduct = item.getProduct().getShippableInfo().get();
            totalWeight += shippableProduct.getWeight() * item.getQuantity();
        }
        return totalWeight;
    }
}
