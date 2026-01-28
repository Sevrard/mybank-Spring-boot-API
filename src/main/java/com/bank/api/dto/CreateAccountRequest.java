package com.bank.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountRequest(
        UUID id,
        String owner,
        BigDecimal initialBalance
) {}
