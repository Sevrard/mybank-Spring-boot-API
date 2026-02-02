package com.bank.domain.repository;

import com.bank.domain.Role;

import java.util.UUID;

public interface UserCredentialsRepository {
    void save(UUID userId, String encodedPassword, Role role);
}
