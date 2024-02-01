package com.jrinehuls.rpgapi.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntStringValidator.class)
public @interface IntString {

    String message() default "Field must be between 1 and 255";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
