package com.tallyto.sicred.coopdecisionhub.error;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ValidationError {

    private final String field;
    private final String message;

    public ValidationError(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }

}