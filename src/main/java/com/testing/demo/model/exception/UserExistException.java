package com.testing.demo.model.exception;

public class UserExistException extends Exception{
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }
}
