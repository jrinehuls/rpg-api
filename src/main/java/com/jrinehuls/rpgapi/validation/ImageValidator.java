package com.jrinehuls.rpgapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

    private final String[] validExtensions = {"image/jpeg", "image/png"};

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        String fileExtension = file.getContentType();
        if (fileExtension == null) return false;
        for (String extension : this.validExtensions) {
            if (extension.equals(fileExtension)) return true;
        }
        return false;
    }
}
