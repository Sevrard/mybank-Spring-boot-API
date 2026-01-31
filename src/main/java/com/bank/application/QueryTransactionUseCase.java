package com.bank.application;

import com.bank.api.dto.TransactionDto;
import com.bank.domain.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;


public class QueryTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public QueryTransactionUseCase(TransactionRepository transactionRepository) {
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
