package com.bank.domain.exception.transaction;

public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException(){
        super ("Cannot transfer money to the same account");
    }
}