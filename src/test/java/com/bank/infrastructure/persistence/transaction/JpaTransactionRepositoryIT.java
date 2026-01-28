package com.bank.infrastructure.persistence.transaction;
import com.bank.domain.BankTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@DataJpaTest
//@ActiveProfile("test")

class JpaTransactionRepositoryIT{

    @Autowired
    private JpaTransactionRepository jpaTransactionRepository;

    @Test
    void should_persist_and_load_transaction(){
        UUID accountId = UUID.randomUUID();
        TransactionEntity entity = new TransactionEntity(
                UUID.randomUUID(),
                accountId,
                new BigDecimal("100.00"),
                BankTransaction.TransactionType.DEBIT
        );

        jpaTransactionRepository.save(entity);
        List<TransactionEntity> result = jpaTransactionRepository.findByAccountId(accountId);
        assertEquals(1, result.size());
        assertEquals(new BigDecimal("100.00"), result.getFirst().getAmount());
    }
}