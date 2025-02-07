package com.melita.OrderService.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CustomerDetailsValidator implements ConstraintValidator<ValidCustomerDetails, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^?[0-9. ()-]{7,}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (EMAIL_PATTERN.matcher(value).matches() || PHONE_PATTERN.matcher(value).matches());
    }
}