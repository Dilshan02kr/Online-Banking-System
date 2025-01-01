package com.mybanking.bankingapp.exception;

public class TransactionException extends RuntimeException{

    public TransactionException(String message) {
        super(message);
    }
}
