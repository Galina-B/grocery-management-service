package com.gms.exception;

/*
    Exception thrown if a user is trying to delete or update a product which doesn't exist.
*/
public class NoSuchProductException extends RuntimeException {

    private final String message;

    public NoSuchProductException(String message) {
        super(message);
        this.message = message;
    }
}
