package com.example.demo.exception;

public class DateUnavailableException extends RuntimeException{
    public DateUnavailableException(String message) {
        super(message);
    }
}
