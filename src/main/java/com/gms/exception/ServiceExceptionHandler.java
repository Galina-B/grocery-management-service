package com.gms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(value = ProductExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody
    ErrorResponse handleProductExistsException(ProductExistsException ex) {
        logger.error(ex.getMessage(),ex);
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(value = NoSuchProductException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorResponse handleNoSuchProductException(NoSuchProductException ex) {
        logger.error(ex.getMessage(),ex);
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse handleValidationException(CustomValidationException ex) {
        logger.error(ex.getMessage(),ex);
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorResponse handleUnexpectedException(Throwable ex) {
        logger.error("Unexpected exception occurred:", ex);
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong, please check input values or contact app admin.");
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        logger.error("Not logged.", ex);
        return new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(), "Unauthorized.");
    }
}
