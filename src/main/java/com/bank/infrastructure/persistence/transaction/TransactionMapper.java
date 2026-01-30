package com.bank.infrastructure.persistence.transaction;

import com.bank.domain.Transaction;

public class TransactionMapper {

    private TransactionMapper(){}

    public static Transaction toDomain(TransactionEntity entity){
        if (entity == null) {
            return null;
        }
        return new Transaction(
                entity.getId(),
                entity.getAccountId(),
                entity.getAmount(),
                entity.getType()
        );
    }
    public static TransactionEntity toEntity(Transaction transaction){
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
