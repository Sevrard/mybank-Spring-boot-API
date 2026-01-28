package com.bank.infrastructure.persistence.account;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Profile("!test")
public interface JpaAccountRepository
        extends JpaRepository<AccountEntity, UUID> {}

