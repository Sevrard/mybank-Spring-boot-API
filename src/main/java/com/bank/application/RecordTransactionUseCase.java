package com.bank.application;

import com.bank.domain.Transaction;
import com.bank.domain.repository.TransactionRepository;

public class RecordTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public RecordTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public void record(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
