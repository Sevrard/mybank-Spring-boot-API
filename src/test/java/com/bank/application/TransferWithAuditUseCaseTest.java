package com.bank.application;
import com.bank.domain.Account;
import com.bank.domain.AccountTestFactory;
import com.bank.infrastructure.repository.FakeAccountRepository;
import com.bank.infrastructure.repository.FakeTransactionRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

class TransferWithAuditUseCaseTest {
    @Test
    void should_transfer_and_record_two_transactions() {

        FakeAccountRepository accountRepo = new FakeAccountRepository();
        FakeTransactionRepository transactionRepo = new FakeTransactionRepository();

        TransferWithAuditUseCase useCase =
                new TransferWithAuditUseCase(accountRepo, transactionRepo);

        Account from = AccountTestFactory.createAccount(new BigDecimal("100.00"));
        Account to = AccountTestFactory.createAccount(new BigDecimal("50.00"));
        accountRepo.add(from);
        accountRepo.add(to);

        useCase.execute(from.getId(), to.getId(), new BigDecimal("30.00"));
        // soldes
        assertEquals(new BigDecimal("70.00"), from.getBalance());
        assertEquals(new BigDecimal("80.00"), to.getBalance());
        // audit
        assertEquals(2, transactionRepo.findAll().size());
    }

}
