package com.example.exceptions;

public class InvalidBillException extends Exception {
    public InvalidBillException(String message) {
        super(message);
    }
}
