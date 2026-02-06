package com.bank.infrastructure.persistence.account;

import com.bank.domain.Account;
import com.bank.domain.repository.AccountRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Profile({"dev", "prod"})
@Transactional
public class JpaAccountRepositoryAdapter implements AccountRepository {

    private final JpaAccountRepository jpaRepository;

    public JpaAccountRepositoryAdapter(JpaAccountRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(AccountMapper::toDomain);
    }

    @Override
    public List<Account> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId)
                .stream()
                .map(AccountMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Account account) {
        jpaRepository.save(AccountMapper.toEntity(account));
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.findById(id).ifPresent(entity -> {
            entity.setNotActive();
            jpaRepository.save(entity);
        });
    }
}
