package com.testing.demo.model.exception;

public class WrongDataException extends Exception{
    public WrongDataException() {
    }

    public WrongDataException(String message) {
        super(message);
    }
}
