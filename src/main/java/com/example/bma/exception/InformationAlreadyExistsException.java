package com.example.bma.exception;

public class InformationAlreadyExistsException extends RuntimeException {

    public InformationAlreadyExistsException(String message) {
        super(message);
    }
}
