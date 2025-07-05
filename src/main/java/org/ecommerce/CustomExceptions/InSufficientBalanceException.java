package org.ecommerce.CustomExceptions;

public class InSufficientBalanceException extends RuntimeException {
    public InSufficientBalanceException(String message) {
        super(message);
    }
}
