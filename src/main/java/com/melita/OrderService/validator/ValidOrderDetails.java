package com.melita.OrderService.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderDetailsValidator.class)
public @interface ValidOrderDetails {
    String message() default "Invalid order details";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
