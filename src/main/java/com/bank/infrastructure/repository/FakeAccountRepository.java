package com.bank.infrastructure.repository;

import com.bank.domain.Account;
import com.bank.domain.repository.AccountRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")

public class FakeAccountRepository implements AccountRepository {

    private final Map<UUID, Account> storage = new HashMap<>();

    @Override
    public Optional<Account> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Account> findByUserId(UUID userId) {
        return storage.values().stream()
                .filter(account -> account.getUserId().equals(userId))
                .toList();
    }

    @Override
    public void delete(UUID id) {
        Account account = storage.get(id);
        if (account != null) {
            account.setNotActive();
            storage.put(id, account);
        }
    }

    @Override
    public void save(Account account) {
        storage.put(account.getId(), account);
    }
    public void add(Account account) {
        storage.put(account.getId(), account);
    }
}