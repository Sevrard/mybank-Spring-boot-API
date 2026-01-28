package com.bank.domain.exception;

public class InvalidTransactionAmountException extends RuntimeException{
    public InvalidTransactionAmountException(){
        super("Invalid transaction Amount !");
    }
}
