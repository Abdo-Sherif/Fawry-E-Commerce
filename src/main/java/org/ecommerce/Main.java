package org.ecommerce;

import org.ecommerce.checkout.Cart;
import org.ecommerce.customer.Customer;
import org.ecommerce.product.Product;
import org.ecommerce.product.ShippableExpirableProduct;
import org.ecommerce.product.expirable.ExpirableProduct;
import org.ecommerce.product.shippable.ShippableProduct;
import org.ecommerce.service.CheckoutService;
import org.ecommerce.service.impl.CheckoutServiceImpl;

import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Customer customer = customerLogin();

        Product cheese = createShippableExpirableProduct();
        Product tv = createShippableProduct();
        Product biscuits = createExpirableProduct();

        Cart cart = fillShoppingCart(Map.of(
                cheese, 3,
                tv, 1,
                biscuits, 2
        ));

        checkout(customer, cart);
    }

    private static Customer customerLogin() {
        return new Customer(9_000);
    }

    private static Product createShippableExpirableProduct() {
        return new ShippableExpirableProduct("cheese", 100, 5, LocalDate.now(), 200);
    }

    private static Product createShippableProduct() {
        return new ShippableProduct("TV", 7000, 5, 10000);
    }

    private static Product createExpirableProduct() {
        return new ExpirableProduct("biscuits", 150, 5, LocalDate.now());
    }

    private static Cart fillShoppingCart(Map<Product, Integer> cartItems) {
        Cart cart = new Cart();
        for (Map.Entry<Product, Integer> cartItem : cartItems.entrySet()) {
            cart.add(cartItem.getKey(), cartItem.getValue());
        }
        return cart;
    }

    private static void checkout(Customer customer, Cart cart) {
        CheckoutService checkoutService = new CheckoutServiceImpl();
        checkoutService.checkout(customer, cart);
    }

}