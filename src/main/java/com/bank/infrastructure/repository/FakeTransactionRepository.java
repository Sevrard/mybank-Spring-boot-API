package com.bank.infrastructure.repository;

import com.bank.domain.BankTransaction;
import com.bank.domain.repository.TransactionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")

public class FakeTransactionRepository implements TransactionRepository {

    private final List<BankTransaction> storage = new ArrayList<>();

    @Override
    public List<BankTransaction> findByAccountId(UUID accountId) {
        return storage;
    }

    @Override
    public void save(BankTransaction transaction) {
        storage.add(transaction);
    }
    public List<BankTransaction> findAll() {
        return storage;
    }
}
