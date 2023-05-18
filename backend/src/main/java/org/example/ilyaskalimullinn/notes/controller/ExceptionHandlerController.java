package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.response.GenericErrorResponse;
import org.example.ilyaskalimullinn.notes.data.response.ValidationErrorResponse;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ValidationErrorResponse.builder().errors(errors).build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GenericErrorResponse handleDuplicateKeyException(DuplicateKeyException e) {
        return GenericErrorResponse.builder().errors(List.of(e.getLocalizedMessage())).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GenericErrorResponse handleBadCredentialsException(BadCredentialsException e) {
        return GenericErrorResponse.builder().errors(List.of(e.getLocalizedMessage())).build();
    }
}
