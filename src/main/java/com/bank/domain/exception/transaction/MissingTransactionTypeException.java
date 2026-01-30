package com.bank.domain.exception.transaction;

public class MissingTransactionTypeException extends RuntimeException{
    public MissingTransactionTypeException(){
        super("No transaction type set !");
    }
}
