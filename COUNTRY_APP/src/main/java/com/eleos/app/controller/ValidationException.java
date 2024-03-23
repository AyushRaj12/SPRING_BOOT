package com.eleos.app.controller;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
