package com.jrinehuls.rpgapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

    private final String[] validExtensions = {"image/jpeg", "image/png"};

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null) return false;
        for (String extension : this.validExtensions) {
            if (extension.equals(file.getContentType())) return true;
        }
        return false;
    }
}
