package ru.klokov.cloudfilestorage.validaion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FileIsNotEmptyValidator.class)
public @interface FileIsNotEmpty {
    String message() default "File required!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
