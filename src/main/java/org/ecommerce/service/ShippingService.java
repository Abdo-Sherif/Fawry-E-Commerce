package org.ecommerce.service;

import org.ecommerce.checkout.CartItem;

import java.util.List;

public interface ShippingService {
    void ship(List<CartItem> shippableItems);

    double calculateShipping(List<CartItem> shippableItems);

}
