package com.bank.api.dto;
import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        UUID id,
        String name,
        BigDecimal balance,
        UUID userId
) {}
