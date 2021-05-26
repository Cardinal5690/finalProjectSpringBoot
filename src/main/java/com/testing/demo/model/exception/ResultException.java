package com.testing.demo.model.exception;

public class ResultException extends RuntimeException{
    public ResultException() {
    }

    public ResultException(String message) {
        super(message);
    }
}
