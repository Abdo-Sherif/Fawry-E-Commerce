package org.ecommerce.CustomExceptions;

public class IllegalQuantityException extends RuntimeException {
    public IllegalQuantityException(String message) {
        super(message);
    }
}
