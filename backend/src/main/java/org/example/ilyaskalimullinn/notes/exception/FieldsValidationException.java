package org.example.ilyaskalimullinn.notes.exception;


import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class FieldsValidationException extends RuntimeException {
    private Map<String, Set<String>> errors;

    public FieldsValidationException(String message, List<FieldError> fieldErrors) {
        super(message);
        this.errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            Set<String> errorMessages;
            if (this.errors.containsKey(fieldError.getField())) {
                errorMessages = this.errors.get(fieldError.getField());
            } else {
                errorMessages = new HashSet<>();
                this.errors.put(fieldError.getField(), errorMessages);
            }

            String errorMesage = fieldError.getDefaultMessage();
            if (errorMesage == null) {
                errorMesage = "Validation error";
            }

            errorMessages.add(errorMesage);
        }
    }
}
