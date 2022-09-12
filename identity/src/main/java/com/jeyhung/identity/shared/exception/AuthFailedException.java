package com.jeyhung.identity.shared.exception;

public class AuthFailedException extends RuntimeException {
    public AuthFailedException(String message) {
        super(message);
    }
}
