package com.bank.domain ;

import com.bank.domain.exception.transaction.InvalidTransactionAmountException;
import com.bank.domain.exception.transaction.MissingTransactionTypeException;
import com.bank.domain.exception.transaction.MissingAccountReferenceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final BigDecimal amount;
    private final TransactionType type;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final UUID accountId;


    public enum TransactionType {
        IN,
        OUT
    }

    public Transaction(UUID id, UUID accountId, BigDecimal amount, TransactionType type){
        if(id == null){
            throw new IllegalArgumentException("Transaction id required");
        }
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidTransactionAmountException();
        }
        if(accountId == null){
            throw new MissingAccountReferenceException();
        }
        if (type == null) {
            throw new MissingTransactionTypeException();
        }
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
    }

    public UUID getId(){ return id; }
    public BigDecimal getAmount(){ return amount; }
    public TransactionType getType(){ return type; }
    public LocalDateTime getCreatedAt(){ return createdAt; };
    public UUID getAccountId(){ return accountId; }

    public static Transaction debit(UUID accountId, BigDecimal amount) {
        return new Transaction(
                UUID.randomUUID(),
                accountId,
                amount,
                TransactionType.OUT
        );
    }
    public static Transaction credit(UUID accountId, BigDecimal amount) {
        return new Transaction(
                UUID.randomUUID(),
                accountId,
                amount,
                TransactionType.IN
        );
    }

}