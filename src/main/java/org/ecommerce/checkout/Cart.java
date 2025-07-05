package org.ecommerce.checkout;

import org.ecommerce.CustomExceptions.InSufficientQuantityException;
import org.ecommerce.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (product.getAvailableQuantity() < quantity) {
            throw new InSufficientQuantityException("Insufficient quantity available for " + product.getName());
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public boolean isCartEmpty(){
        return cartItems.isEmpty();
    }

    public List<CartItem> collectShippableProducts() {
        List<CartItem> shippableProducts = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getShippableInfo().isPresent()) {
                shippableProducts.add(cartItem);
            }
        }
        return shippableProducts;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getSubTotal(){
        double subTotal = 0.0;
        for(CartItem cartItem : cartItems){
            subTotal += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return subTotal;
    }
}
