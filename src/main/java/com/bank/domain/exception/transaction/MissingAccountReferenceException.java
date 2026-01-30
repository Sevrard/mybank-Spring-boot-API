package com.bank.domain.exception.transaction;

public class MissingAccountReferenceException extends RuntimeException{
    public MissingAccountReferenceException(){
        super("Missing account transaction !");
    }
}
