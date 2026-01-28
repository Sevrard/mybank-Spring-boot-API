package com.bank.domain.repository;

import com.bank.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findById(UUID id); // Manage Null return
    void save(Account account); //
}