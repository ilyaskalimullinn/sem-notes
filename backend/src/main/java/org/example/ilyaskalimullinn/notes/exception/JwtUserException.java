package org.example.ilyaskalimullinn.notes.exception;

import org.springframework.security.access.AccessDeniedException;

public class JwtUserException extends AccessDeniedException {
    public JwtUserException(String msg) {
        super(msg);
    }

    public JwtUserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
