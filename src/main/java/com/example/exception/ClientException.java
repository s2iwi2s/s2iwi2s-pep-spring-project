package com.example.exception;

public class ClientException extends RuntimeException {
    public ClientException() {
        super();
    }
    public ClientException(String text) {
        super(text);
    }
}
