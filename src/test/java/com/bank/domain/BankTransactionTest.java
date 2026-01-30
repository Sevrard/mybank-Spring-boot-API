package com.bank.domain;

import com.bank.domain.exception.transaction.InvalidTransactionAmountException;
import com.bank.domain.exception.transaction.MissingAccountReferenceException;
import com.bank.domain.exception.transaction.MissingTransactionTypeException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;


public class BankTransactionTest {

    ///  HAPOY ///
    @Test
    void should_create_valid_transaction() {
        Transaction transaction =
                BankTransactionTestFactory.createTransaction(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        new BigDecimal("100.00"),
                        Transaction.TransactionType.CREDIT
                );

        assertNotNull(transaction.getId());
        assertEquals(new BigDecimal("100.00"), transaction.getAmount());
        assertEquals(Transaction.TransactionType.CREDIT, transaction.getType());
        assertNotNull(transaction.getCreatedAt());
    }

    /// ERROR ///
    @Test
    void should_throw_when_invalid_transaction_amount(){
        assertThrows(InvalidTransactionAmountException.class, ()-> BankTransactionTestFactory.createTransaction(
                UUID.randomUUID(),
                UUID.randomUUID(),
                new BigDecimal("-100.00"),
                Transaction.TransactionType.DEBIT
        ));
    }
    @Test
    void should_throw_when_transaction_amount_is_zero() {
        assertThrows(InvalidTransactionAmountException.class, () ->
                BankTransactionTestFactory.createTransaction(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        BigDecimal.ZERO,
                        Transaction.TransactionType.DEBIT
                )
        );
    }
    @Test
    void should_throw_when_transaction_type_is_missing() {
        assertThrows(MissingTransactionTypeException.class, () ->
                BankTransactionTestFactory.createTransaction(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        new BigDecimal("50.00"),
                        null
                )
        );
    }
    @Test
    void should_throw_when_account_reference_is_missing() {
        assertThrows(MissingAccountReferenceException.class, () ->
                BankTransactionTestFactory.createTransaction(
                        UUID.randomUUID(),
                        null,
                        new BigDecimal("50.00"),
                        Transaction.TransactionType.TRANSFER
                )
        );
    }
    @Test
    void should_throw_when_transaction_id_is_missing() {
        assertThrows(IllegalArgumentException.class, () ->
                BankTransactionTestFactory.createTransaction(
                        null,
                        UUID.randomUUID(),
                        new BigDecimal("50.00"),
                        Transaction.TransactionType.DEBIT
                )
        );
    }

}
