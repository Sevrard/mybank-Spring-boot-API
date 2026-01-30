package com.bank.infrastructure.repository;

import com.bank.domain.Transaction;
import com.bank.domain.repository.TransactionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")

public class FakeTransactionRepository implements TransactionRepository {

    private final List<Transaction> storage = new ArrayList<>();

    @Override
    public List<Transaction> findByAccountId(UUID accountId) {
        return storage;
    }

    @Override
    public void save(Transaction transaction) {
        storage.add(transaction);
    }
    public List<Transaction> findAll() {
        return storage;
    }
}
