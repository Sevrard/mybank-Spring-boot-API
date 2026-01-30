package com.bank.api.dto.auth;

public record LoginRequest(
        String email,
        String password
) {}
