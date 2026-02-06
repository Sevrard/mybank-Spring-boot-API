package com.bank.infrastructure.persistence.account;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

@Profile("!test")
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID> {

    @Query("SELECT a FROM AccountEntity a WHERE a.userId = :userId AND a.isActive = true")
    List<AccountEntity> findByUserId(UUID userId);

}

