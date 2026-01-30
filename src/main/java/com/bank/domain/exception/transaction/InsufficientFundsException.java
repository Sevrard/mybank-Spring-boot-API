package com.bank.domain.exception.transaction;


import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(BigDecimal balance, BigDecimal amount){
        super("Insufficient funds in account ! Balance:" + balance + " , amount:"+amount );
    }
}