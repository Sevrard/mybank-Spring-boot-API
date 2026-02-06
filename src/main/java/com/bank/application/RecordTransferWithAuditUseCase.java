package com.bank.application;
import com.bank.domain.Account;
import com.bank.domain.Transaction;
import com.bank.domain.exception.account.AccountNotFoundException;
import com.bank.domain.exception.transaction.InvalidTransactionAmountException;
import com.bank.domain.exception.transaction.SameAccountTransferException;
import com.bank.domain.repository.AccountRepository;
import com.bank.domain.repository.TransactionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordTransferWithAuditUseCase {

    private static final Logger log = LoggerFactory.getLogger(RecordTransferWithAuditUseCase.class);
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public RecordTransferWithAuditUseCase(AccountRepository accountRepository,TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void execute(UUID fromAccountId, UUID toAccountId, BigDecimal amount, String label) {

        log.info("🚨TRANSFER from={}, to={}, amount={}, LABEL:{}", fromAccountId, toAccountId, amount, label);

        if (fromAccountId.equals(toAccountId)) { throw new SameAccountTransferException();}
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) { throw new InvalidTransactionAmountException(); }

        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountId));
        Account to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(toAccountId));

        from.debit(amount);
        to.credit(amount);

        accountRepository.save(from);
        accountRepository.save(to);

        transactionRepository.save(Transaction.debit(fromAccountId, amount, label));
        transactionRepository.save(Transaction.credit(toAccountId, amount, label));
    }

}
