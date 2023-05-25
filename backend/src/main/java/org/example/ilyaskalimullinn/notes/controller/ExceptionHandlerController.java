package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.response.FieldsValidationErrorResponse;
import org.example.ilyaskalimullinn.notes.data.response.GenericErrorResponse;
import org.example.ilyaskalimullinn.notes.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({FieldsValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FieldsValidationErrorResponse handleFieldsValidationException(FieldsValidationException e) {
        logger.error("Exception occurred", e);
        return FieldsValidationErrorResponse.builder()
                .detail(e.getMessage())
                .fieldErrors(e.getErrors())
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GenericErrorResponse handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error("Exception occurred", e);
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GenericErrorResponse handleBadCredentialsException(BadCredentialsException e) {
        logger.error("Exception occurred", e);
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorResponse handleInvalidRequestException(InvalidRequestException e) {
        logger.error("Exception occurred", e);
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }

    @ExceptionHandler(EntityPersistenceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericErrorResponse handleNotePersistenceException(EntityPersistenceException e) {
        logger.error("Exception occurred", e);
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericErrorResponse handleNotFoundException(NotFoundException e) {
        logger.error("Exception occurred", e);
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorResponse handleHttpMessageConversionException(HttpMessageConversionException e) {
        logger.error("Exception occurred", e);
        return GenericErrorResponse.builder().detail("Invalid request, please try again later").build();
    }

    @ExceptionHandler(DictionaryApiException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public GenericErrorResponse handleDictionaryApiException(DictionaryApiException e) {
        logger.error("Exception occurred", e);
        return GenericErrorResponse.builder().detail(e.getLocalizedMessage()).build();
    }
}
