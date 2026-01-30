package com.bank.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank String firstname,
        @NotBlank String lastname,
        @Email @NotBlank String email
) {}
