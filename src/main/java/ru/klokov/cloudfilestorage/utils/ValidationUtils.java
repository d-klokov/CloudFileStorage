package ru.klokov.cloudfilestorage.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class ValidationUtils {
    public static String getErrorMessageFromBindingResult(BindingResult result) {
        return result.getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Error uploading file!");
    }
}
