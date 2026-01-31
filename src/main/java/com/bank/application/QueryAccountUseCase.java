package com.bank.application;

import com.bank.domain.Account;
import com.bank.domain.exception.account.AccountNotFoundException;
import com.bank.domain.repository.AccountRepository;
import java.util.UUID;


public class QueryAccountUseCase {

    private final AccountRepository accountRepository;

    public QueryAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
