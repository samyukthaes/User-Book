package com.example.Practice.exception;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException(String id) {
        super(id);

    }
}
