package com.gms.exception;
/*
    Exception thrown if a user is trying to add same product multiple times.
*/
public class ProductExistsException extends RuntimeException{

    private final String message;

    public ProductExistsException(String message) {
        super(message);
        this.message = message;
    }
}
