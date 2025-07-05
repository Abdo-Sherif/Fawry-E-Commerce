package org.ecommerce.CustomExceptions;

public class InSufficientQuantityException extends RuntimeException {
    public InSufficientQuantityException(String message) {
        super(message);
    }
}
