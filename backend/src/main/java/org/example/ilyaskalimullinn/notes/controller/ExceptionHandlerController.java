package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.response.GenericErrorResponse;
import org.example.ilyaskalimullinn.notes.data.response.ValidationErrorResponse;
import org.example.ilyaskalimullinn.notes.exception.FieldsValidationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(FieldsValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(FieldsValidationException e) {
        return ValidationErrorResponse.builder()
                .detail(e.getMessage())
                .errors(e.getErrors())
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GenericErrorResponse handleDuplicateKeyException(DuplicateKeyException e) {
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GenericErrorResponse handleBadCredentialsException(BadCredentialsException e) {
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }
}
