package com.bank.application;
import com.bank.domain.Account;
import com.bank.domain.BankTransaction;
import com.bank.domain.exception.AccountNotFoundException;
import com.bank.domain.exception.InvalidTransactionAmountException;
import com.bank.domain.exception.SameAccountTransferException;
import com.bank.domain.repository.AccountRepository;
import com.bank.domain.repository.TransactionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferWithAuditUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateAccountUseCase.class);
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransferWithAuditUseCase(AccountRepository accountRepository,
                                    TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void execute(UUID fromAccountId,
                        UUID toAccountId,
                        BigDecimal amount) {


        if (fromAccountId.equals(toAccountId)) {
            throw new SameAccountTransferException();
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionAmountException();
        }
        log.info("TRANSFERTTTTTTT !!!!!!!!!! id={}, owner={}, balance={}",
                fromAccountId, toAccountId, amount);
//        log.info("TX active = {}",
//                TransactionSynchronizationManager.isActualTransactionActive()
//        );
//        log.info("TransactionRepository impl = {}",
//                transactionRepository.getClass().getName()
//        );
        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountId));

        Account to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(toAccountId));

        from.debit(amount);
        to.credit(amount);

        accountRepository.save(from);
        accountRepository.save(to);

        transactionRepository.save(
                BankTransaction.debit(fromAccountId, amount)
        );
        transactionRepository.save(
                BankTransaction.credit(toAccountId, amount)
        );
    }

}
