package com.gms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Custom response in case of exception
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
}
