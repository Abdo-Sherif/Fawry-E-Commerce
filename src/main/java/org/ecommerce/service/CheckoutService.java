package org.ecommerce.service;

import org.ecommerce.checkout.Cart;
import org.ecommerce.customer.Customer;

public interface CheckoutService {

    double processPayment(Customer customer, double totalAmount);

    void checkout(Customer customer, Cart cart);
}
