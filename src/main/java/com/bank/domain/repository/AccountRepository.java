package com.bank.domain.repository;

import com.bank.domain.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findById(UUID id); // Manage Null return
    List<Account> findByUserId(UUID id); // Manage Null return

    void delete(UUID id);
    void save(Account account); //
}