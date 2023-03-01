package com.gms.service;

import com.gms.exception.CustomValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationServiceTest {

    private static final ValidationService SERVICE = new ValidationService();

    @ParameterizedTest
    @ValueSource(strings = {"", " ",})
    void typeNotNullThrowsCustomValidationException(String type){
        assertThrows(CustomValidationException.class, () -> SERVICE.typeNotNull(type));
    }


    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"-1.0", "-89.65678989"})
    void priceNotNullAndPositiveThrowsCustomValidationException(BigDecimal price){
        assertThrows(CustomValidationException.class, () -> SERVICE.priceNotNullAndPositive(price));
    }

}