package com.bank.domain.exception;

public class MissingTransactionTypeException extends RuntimeException{
    public MissingTransactionTypeException(){
        super("No transaction type set !");
    }
}
