package com.bank.domain.repository;

import com.bank.domain.BankTransaction;

import java.util.List;
import java.util.UUID;


public interface TransactionRepository {
    List<BankTransaction> findByAccountId(UUID accountId);
    void save(BankTransaction bankTransaction); //
}