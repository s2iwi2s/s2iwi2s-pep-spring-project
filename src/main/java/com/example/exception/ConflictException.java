package com.example.exception;

public class ConflictException extends RuntimeException {
    public ConflictException() {
        super();
    }
    public ConflictException(String text) {
        super(text);
    }
    
}
