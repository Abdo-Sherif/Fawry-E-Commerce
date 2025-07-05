package org.ecommerce.customer;

public class Customer {
    private double balance;

    public Customer(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double deductedAmount) {
        if (deductedAmount < 0) {
            throw new IllegalArgumentException("Deducted amount cannot be negative");
        }
        this.balance -= deductedAmount;
    }
}
