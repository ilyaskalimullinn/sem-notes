package org.example.ilyaskalimullinn.notes.exception;

public class DictionaryApiException extends RuntimeException {
    public DictionaryApiException() {
    }

    public DictionaryApiException(String message) {
        super(message);
    }

    public DictionaryApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public DictionaryApiException(Throwable cause) {
        super(cause);
    }

    public DictionaryApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
