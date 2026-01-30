package com.bank.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class BankTransactionTestFactory{
    public static Transaction createTransaction(
            UUID id,
            UUID AccountId,
            BigDecimal amount,
            Transaction.TransactionType type
    ) {
        return new Transaction(
                id,
                AccountId,
                amount,
                type
            );
    }
}