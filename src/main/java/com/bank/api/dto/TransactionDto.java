package com.bank.api.dto;

import com.bank.domain.BankTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        UUID accountId,
        BigDecimal amount,
        BankTransaction.TransactionType type,
        LocalDateTime createdAt
) {}
