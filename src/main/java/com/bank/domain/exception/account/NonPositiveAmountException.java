package com.bank.domain.exception.account;

public class NonPositiveAmountException extends RuntimeException {
    public NonPositiveAmountException(String operation){
        super(operation + " amount must be strictly positive");
    }
}