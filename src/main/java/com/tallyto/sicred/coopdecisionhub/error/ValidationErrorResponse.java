package com.tallyto.sicred.coopdecisionhub.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;

@Getter
public class ValidationErrorResponse {

    private final List<ValidationError> errors;

    public ValidationErrorResponse(BindingResult bindingResult) {
        this.errors = bindingResult.getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();
    }

}