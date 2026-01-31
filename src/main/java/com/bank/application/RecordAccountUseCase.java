package com.bank.application;

import com.bank.domain.Account;
import com.bank.domain.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class RecordAccountUseCase {

    private final AccountRepository accountRepository;
    public RecordAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(UUID id, String name, BigDecimal initialBalance, UUID userId) {
        Account account = new Account(id, name, initialBalance, userId);
        accountRepository.save(account);
    }
}
