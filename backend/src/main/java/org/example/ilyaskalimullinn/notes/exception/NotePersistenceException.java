package org.example.ilyaskalimullinn.notes.exception;

public class NotePersistenceException extends RuntimeException {
    public NotePersistenceException() {
    }

    public NotePersistenceException(String message) {
        super(message);
    }

    public NotePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotePersistenceException(Throwable cause) {
        super(cause);
    }

    public NotePersistenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
