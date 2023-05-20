package org.example.ilyaskalimullinn.notes.exception;


import java.util.Map;

public class FieldsValidationException extends RuntimeException {
    private Map<String, String> errors;

    public FieldsValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public FieldsValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public FieldsValidationException(String message, Throwable cause, Map<String, String> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public FieldsValidationException(Throwable cause, Map<String, String> errors) {
        super(cause);
        this.errors = errors;
    }

    public FieldsValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Map<String, String> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
