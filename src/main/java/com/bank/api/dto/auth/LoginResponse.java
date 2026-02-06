package com.bank.api.dto.auth;

import java.util.UUID;

public record LoginResponse(
        String token,
        UUID userId
) {}
