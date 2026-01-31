package com.bank.application.test;

import com.bank.domain.Account;
import com.bank.domain.exception.account.AccountNotFoundException;
import com.bank.domain.exception.transaction.SameAccountTransferException;
import com.bank.domain.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.UUID;

public class TransferMoneyUseCase {

    private final AccountRepository accountRepository;
    public TransferMoneyUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void execute(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {

        if (fromAccountId.equals(toAccountId)) {
            throw new SameAccountTransferException();
        }

        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountId));

        Account to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(toAccountId));

        from.debit(amount);
        to.credit(amount);
        accountRepository.save(from);
        accountRepository.save(to);
    }
}
