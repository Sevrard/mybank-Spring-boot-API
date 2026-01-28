package com.bank.domain;

import com.bank.domain.exception.InsufficientFundsException;
import com.bank.domain.exception.NonPositiveAmountException;

import java.math.BigDecimal;
import java.util.UUID;

public class Account{
    private final UUID id;
    private final String owner;
    private BigDecimal balance;

    public Account(UUID id, String owner, BigDecimal initialBalance ){
        if(initialBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new NonPositiveAmountException("Initial");
        }
        this.id = id;
        this.owner = owner;
        this.balance = initialBalance;
    }

    public UUID getId(){ return id; }
    public String getOwner(){ return owner; }
    public BigDecimal getBalance(){ return balance;}

    public void debit(BigDecimal amountDebit){
        if(amountDebit.compareTo(BigDecimal.ZERO) <= 0){
            throw new NonPositiveAmountException("Debit");
        }
        if(balance.compareTo(amountDebit) < 0){
            throw new InsufficientFundsException(balance, amountDebit);
        }
        balance = balance.subtract(amountDebit);
    }

    public void credit(BigDecimal amountCredit){
        if(amountCredit.compareTo(BigDecimal.ZERO) <= 0){
            throw new NonPositiveAmountException("Credit");
        }
        balance = balance.add(amountCredit);
    }
}