package com.bank.application;

import com.bank.domain.Account;
import com.bank.domain.exception.transaction.InsufficientFundsException;
import com.bank.domain.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TransferWithAuditUseCaseIT {

    @Autowired
    private RecordTransferWithAuditUseCase useCase;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void should_transfer_money_and_record_transactions() {
        Account from = new Account(UUID.randomUUID(), "Alice", new BigDecimal("100.00"));
        Account to   = new Account(UUID.randomUUID(), "Bob",   new BigDecimal("50.00"));

        accountRepository.save(from);
        accountRepository.save(to);

        useCase.execute(from.getId(), to.getId(), new BigDecimal("30.00"));

        Account updatedFrom = accountRepository.findById(from.getId()).orElseThrow();
        Account updatedTo   = accountRepository.findById(to.getId()).orElseThrow();

        assertEquals(new BigDecimal("70.00"), updatedFrom.getBalance());
        assertEquals(new BigDecimal("80.00"), updatedTo.getBalance());
    }

    @Test
    void should_rollback_when_insufficient_funds() {
        Account from = new Account(UUID.randomUUID(), "Alice", new BigDecimal("10.00"));
        Account to   = new Account(UUID.randomUUID(), "Bob",   new BigDecimal("50.00"));

        accountRepository.save(from);
        accountRepository.save(to);

        assertThrows(InsufficientFundsException.class, () ->
                useCase.execute(from.getId(), to.getId(), new BigDecimal("100.00"))
        );

        Account reloaded = accountRepository.findById(from.getId()).orElseThrow();
        assertEquals(new BigDecimal("10.00"), reloaded.getBalance());
    }

}
