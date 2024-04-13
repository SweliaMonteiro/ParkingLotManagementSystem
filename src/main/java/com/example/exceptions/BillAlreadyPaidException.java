package com.example.exceptions;

public class BillAlreadyPaidException extends Exception {
    public BillAlreadyPaidException(String message) {
        super(message);
    }
}
