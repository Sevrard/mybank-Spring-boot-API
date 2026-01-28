package com.bank.domain.exception;

public class MissingAccountReferenceException extends RuntimeException{
    public MissingAccountReferenceException(){
        super("Missing account transaction !");
    }
}
