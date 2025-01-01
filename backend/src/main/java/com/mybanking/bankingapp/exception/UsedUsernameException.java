package com.mybanking.bankingapp.exception;

public class UsedUsernameException extends RuntimeException{

    public UsedUsernameException(String message) {
        super(message);
    }
}
