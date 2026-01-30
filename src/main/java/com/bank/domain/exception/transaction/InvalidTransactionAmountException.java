package com.bank.domain.exception.transaction;

public class InvalidTransactionAmountException extends RuntimeException{
    public InvalidTransactionAmountException(){
        super("Invalid transaction Amount !");
    }
}
