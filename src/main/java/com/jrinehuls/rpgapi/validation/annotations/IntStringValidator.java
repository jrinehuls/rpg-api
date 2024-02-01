package com.jrinehuls.rpgapi.validation.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntStringValidator implements ConstraintValidator<Image, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            int number = Integer.parseInt(value);
            if (number >= 1 && number <= 255) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }
}
