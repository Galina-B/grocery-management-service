package com.gms.service;

import com.gms.exception.CustomValidationException;
import com.gms.jpa.entity.Product;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ValidationService {

    public void typeNotNull(String type) {
        if (StringUtils.isWhitespaceOrEmpty(type))
            throw new CustomValidationException("Product type must be present.");

    }

    public void priceNotNullAndPositive(BigDecimal price) {
        if (Objects.isNull(price))
            throw new CustomValidationException("Price can not be null.");
        if (price.doubleValue() < 0.0)
            throw new CustomValidationException("Price can not be negative.");
    }

    public void validatePriceAndType(Product input) {
        typeNotNull(input.getType());
        priceNotNullAndPositive(input.getPrice());
    }
}
