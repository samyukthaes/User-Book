package com.example.Practice.exception;

public class UserAlreadyPresentException extends RuntimeException{
    public UserAlreadyPresentException(String s) {
        super(s);
    }
}
