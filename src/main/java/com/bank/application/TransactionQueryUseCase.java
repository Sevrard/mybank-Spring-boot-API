package com.bank.application;

import com.bank.api.dto.TransactionDto;
import com.bank.domain.BankTransaction;
import com.bank.domain.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;


public class TransactionQueryUseCase {

    private final TransactionRepository transactionRepository;

    public TransactionQueryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDto> findByAccount(UUID accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(tx -> new TransactionDto(
                        tx.getId(),
                        tx.getAccountId(),
                        tx.getAmount(),
                        tx.getType(),
                        tx.getCreatedAt()
                ))
                .toList();
    }
}
