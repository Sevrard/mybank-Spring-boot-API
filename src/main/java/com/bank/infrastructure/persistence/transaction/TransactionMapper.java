package com.bank.infrastructure.persistence.transaction;

import com.bank.domain.BankTransaction;

public class TransactionMapper {

    private TransactionMapper(){}

    public static BankTransaction toDomain(TransactionEntity entity){
        if (entity == null) {
            return null;
        }
        return new BankTransaction(
                entity.getId(),
                entity.getAccountId(),
                entity.getAmount(),
                entity.getType()
        );
    }
    public static TransactionEntity toEntity(BankTransaction transaction){
        if (transaction == null) {
            return null;
        }
        return new TransactionEntity(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getType()
        );
    }
}
