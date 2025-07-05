package org.ecommerce.service.impl;

import org.ecommerce.checkout.Cart;
import org.ecommerce.checkout.CartItem;
import org.ecommerce.checkout.CheckoutReceipt;
import org.ecommerce.customer.Customer;
import org.ecommerce.CustomExceptions.InSufficientBalanceException;
import org.ecommerce.product.Product;
import org.ecommerce.service.CheckoutService;
import org.ecommerce.service.ShippingService;

import java.util.List;

public class CheckoutServiceImpl implements CheckoutService {
    private final ShippingService shippingService;

    public CheckoutServiceImpl() {
        this.shippingService = new ShippingServiceImpl();
    }

    @Override
    public double processPayment(Customer customer, double totalAmount) {
        if (customer.getBalance() < totalAmount) {
            throw new InSufficientBalanceException("Your balance is insufficient. Required: " + totalAmount + ", Available: " + customer.getBalance());
        } else {
            customer.deductBalance(totalAmount);
        }
        return customer.getBalance();
    }

    @Override
    public void checkout(Customer customer, Cart cart) {
        boolean success = true;
        String message = "** Checkout receipt **";
        double cartSubTotal = cart.getSubTotal();
        double shipping = shippingService.calculateShipping(cart.collectShippableProducts());
        double totalAmount = cartSubTotal + shipping;
        double customerBalance = customer.getBalance();

        try {
            validateCart(cart);
            customerBalance = processPayment(customer, totalAmount);
        } catch (IllegalStateException | InSufficientBalanceException e) {
            success = false;
            message = "Checkout failed due to: " + e.getMessage();
        } finally {
            printReceipt(new CheckoutReceipt(success, message, cartSubTotal, shipping, totalAmount, customerBalance), cart);
            if (success) updateInventory(cart);
        }
    }

    void validateCart(Cart cart) {

        if (cart.isCartEmpty()) {
            throw new IllegalStateException("Can't checkout an empty cart");
        }

        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            if (!product.isAvailable()) {
                throw new IllegalStateException("Product out of stock: " + product.getName());
            }

            product.getExpirableInfo().ifPresent(expirable -> {
                if (expirable.isExpired()) {
                    throw new IllegalStateException("Product expired: " + product.getName());
                }
            });
        }
    }

    void updateInventory(Cart cart) {
        List<CartItem> items = cart.getCartItems();
        for (CartItem item : items) {
            item.getProduct().reduceAvailableQuantity(item.getQuantity());
        }
    }

    void printReceipt(CheckoutReceipt receipt, Cart cart) {
        shippingService.ship(cart.collectShippableProducts());

        System.out.println(receipt.getMessage());

        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "\t" + item.getProduct().getPrice() * item.getQuantity());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + receipt.getSubtotal());
        System.out.println("Shipping\t" + receipt.getShippingFee());
        System.out.println("Amount\t" + receipt.getTotalAmount());
        System.out.println("Remaining Balance\t" + receipt.getRemainingBalance());
    }

}