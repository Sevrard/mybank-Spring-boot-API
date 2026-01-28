package com.bank.application ;

import com.bank.infrastructure.repository.FakeTransactionRepository;
import org.junit.jupiter.api.Test;
import com.bank.domain.BankTransaction;
import static org.junit.jupiter.api.Assertions.*;
import com.bank.domain.BankTransactionTestFactory;
import java.math.BigDecimal;
import java.util.UUID;

public class RecordTransactionUseCaseTest  {
    @Test
    void should_record_transaction() {
        FakeTransactionRepository fakeRepo = new FakeTransactionRepository();
        RecordTransactionUseCase useCase = new RecordTransactionUseCase(fakeRepo);

        BankTransaction transaction =
                BankTransactionTestFactory.createTransaction(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        new BigDecimal("100.00"),
                        BankTransaction.TransactionType.CREDIT
                );

        useCase.record(transaction);
        assertEquals(1, fakeRepo.findAll().size());
    }
}
