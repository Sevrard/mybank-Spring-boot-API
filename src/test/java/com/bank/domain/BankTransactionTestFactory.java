package com.bank.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class BankTransactionTestFactory{
    public static BankTransaction createTransaction(
            UUID id,
            UUID AccountId,
            BigDecimal amount,
            BankTransaction.TransactionType type
    ) {
        return new BankTransaction(
                id,
                AccountId,
                amount,
                type
            );
    }
}