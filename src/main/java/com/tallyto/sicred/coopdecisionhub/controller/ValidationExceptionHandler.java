package com.tallyto.sicred.coopdecisionhub.controller;

import com.tallyto.sicred.coopdecisionhub.error.ApiError;
import com.tallyto.sicred.coopdecisionhub.error.ValidationErrorResponse;
import com.tallyto.sicred.coopdecisionhub.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class ValidationExceptionHandler {

    public static final String OPERACAO_NAO_PERMITIDA = "Operação não permitida";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        var errors = new ValidationErrorResponse(bindingResult);
        var apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), OPERACAO_NAO_PERMITIDA, "", errors.getErrors());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException ex) {
        var apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), OPERACAO_NAO_PERMITIDA, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
