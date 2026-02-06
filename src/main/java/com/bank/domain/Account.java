package com.bank.domain;

import com.bank.domain.exception.account.MissingAccountNameException;
import com.bank.domain.exception.account.MissingUserIdException;
import com.bank.domain.exception.account.NotActiveAccountException;
import com.bank.domain.exception.transaction.InsufficientFundsException;
import com.bank.domain.exception.account.NonPositiveAmountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account{
    private final UUID id;
    private final String name;
    private BigDecimal balance;
    private final UUID userId;
    private String iban;
    private String bic;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private Boolean isActive;



    public Account(UUID id, String name, BigDecimal initialBalance, UUID userId, String iban, String bic, Boolean isActive ){
        if(initialBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new NonPositiveAmountException("Initial");
        }
        if(userId == null){
            throw new MissingUserIdException();
        }
        if(name == null){
            throw new MissingAccountNameException();
        }
        this.id = id;
        this.name = name;
        this.balance = initialBalance;
        this.userId = userId;
        this.iban = iban;
        this.bic = bic;
        this.isActive = isActive;
    }

    public UUID getId(){ return id; }
    public String getName(){ return name; }
    public BigDecimal getBalance(){ return balance;}
    public UUID getUserId(){ return userId; }
    public String getIban(){ return iban; }
    public String getBic(){ return bic; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Boolean getIsActive(){ return isActive; }

    public void  setNotActive(){ this.isActive = false; }

    public void debit(BigDecimal amountDebit){
        if( !isActive){
            throw new NotActiveAccountException(id);
        }
        if(amountDebit.compareTo(BigDecimal.ZERO) <= 0){
            throw new NonPositiveAmountException("Debit");
        }
        if(balance.compareTo(amountDebit) < 0){
            throw new InsufficientFundsException(balance, amountDebit);
        }
        balance = balance.subtract(amountDebit);
    }

    public void credit(BigDecimal amountCredit){
        if( !isActive){
            throw new NotActiveAccountException(id);
        }
        if(amountCredit.compareTo(BigDecimal.ZERO) <= 0){
            throw new NonPositiveAmountException("Credit");
        }
        balance = balance.add(amountCredit);
    }

}