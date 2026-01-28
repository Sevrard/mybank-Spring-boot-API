package com.bank.infrastructure.persistence.transaction;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Profile("!test")
public interface JpaTransactionRepository
    extends JpaRepository<TransactionEntity, UUID>{
    List<TransactionEntity> findByAccountId(UUID accountId);
    List<TransactionEntity> findByAccountIdOrderByCreatedAtDesc(UUID accountId);
}