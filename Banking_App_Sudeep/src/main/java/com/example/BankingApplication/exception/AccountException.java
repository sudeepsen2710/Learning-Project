package com.example.BankingApplication.exception;

public class AccountException extends RuntimeException{
    public AccountException (String message){
        super(message);
    }
}