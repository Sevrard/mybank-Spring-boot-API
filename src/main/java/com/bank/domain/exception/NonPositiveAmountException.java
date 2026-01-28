package com.bank.domain.exception;

public class NonPositiveAmountException extends RuntimeException {
    public NonPositiveAmountException(String operation){
        super(operation + " amount must be strictly positive");
    }
}