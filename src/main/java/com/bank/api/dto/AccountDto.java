package com.bank.api.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountDto(
        UUID id,
        String name,
        BigDecimal balance,
        UUID userId,
        String iban,
        String bic,
        LocalDateTime createdAt,
        Boolean isActive
) {}
