package com.bank.domain.repository;

import com.bank.domain.Transaction;

import java.util.List;
import java.util.UUID;


public interface TransactionRepository {
    List<Transaction> findByAccountId(UUID accountId);
    void save(Transaction bankTransaction); //
}