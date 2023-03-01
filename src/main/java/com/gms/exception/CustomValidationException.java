package com.gms.exception;

/*
    Exception thrown if a user is trying to introduce not permitted value for a field.
*/
public class CustomValidationException extends RuntimeException {
    private final String message;

    public CustomValidationException(String message) {
        super(message);
        this.message = message;
    }
}
