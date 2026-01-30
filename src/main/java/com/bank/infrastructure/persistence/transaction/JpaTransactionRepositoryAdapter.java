package com.bank.infrastructure.persistence.transaction;

import com.bank.domain.Transaction;
import com.bank.domain.repository.TransactionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Profile({"dev", "prod"})
@Transactional

public class JpaTransactionRepositoryAdapter implements TransactionRepository{

    private final JpaTransactionRepository jpaTransactionRepository;

    public JpaTransactionRepositoryAdapter(JpaTransactionRepository jpaTransactionRepository){
        this.jpaTransactionRepository = jpaTransactionRepository;
    }

    @Override
    public void save(Transaction bankTransaction) {
        Objects.requireNonNull(bankTransaction, "Transaction cannot be null");
        jpaTransactionRepository.save(TransactionMapper.toEntity(bankTransaction));
    }

    @Override
    public List<Transaction> findByAccountId(UUID accountId) {
        return jpaTransactionRepository.findByAccountId(accountId)
                .stream()
                .map(TransactionMapper::toDomain)
                .toList();
    }
}
