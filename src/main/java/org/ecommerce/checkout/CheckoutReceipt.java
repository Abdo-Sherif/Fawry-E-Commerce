package org.ecommerce.checkout;

public class CheckoutReceipt {

    private final boolean success;
    private final String message;
    private final double subtotal;
    private final double shippingFee;
    private final double totalAmount;
    private final double remainingBalance;

    public CheckoutReceipt(boolean success, String message, double subtotal, double shippingFee, double totalAmount, double remainingBalance) {
        this.success = success;
        this.message = message;
        this.subtotal = subtotal;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.remainingBalance = remainingBalance;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }
}
