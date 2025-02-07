package com.melita.OrderService.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class OrderDetailsValidator implements ConstraintValidator<ValidOrderDetails, List<String>> {
    private static final List<String> VALID_PRODUCTS = List.of("Internet", "TV", "Telephony", "Mobile");

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        return value != null && value.stream().allMatch(VALID_PRODUCTS::contains);
    }
}
