package com.bank.api.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        String firstname,
        String lastname,
        String email
) {}
