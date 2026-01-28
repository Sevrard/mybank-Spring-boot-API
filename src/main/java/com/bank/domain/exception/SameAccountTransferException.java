package com.bank.domain.exception;

import java.util.UUID;

public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException(){
        super ("Cannot transfer money to the same account");
    }
}