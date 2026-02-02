package com.bank.infrastructure.security.userCredentials;

import com.bank.domain.Role;
import com.bank.domain.repository.UserCredentialsRepository;
import com.bank.infrastructure.persistence.user.JpaUserRepository;
import com.bank.infrastructure.persistence.user.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public class JpaUserCredentialsRepositoryAdapter
        implements UserCredentialsRepository {

    private final JpaUserCredentialsRepository jpaCredentialsRepository;
    private final JpaUserRepository userJpaRepository;

    public JpaUserCredentialsRepositoryAdapter(
            JpaUserCredentialsRepository jpaCredentialsRepository,
            JpaUserRepository userJpaRepository
    ) {
        this.jpaCredentialsRepository = jpaCredentialsRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void save(UUID userId, String encodedPassword, Role role) {

        UserEntity userEntity = userJpaRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalStateException("User not found: " + userId)
                );

        UserCredentialsEntity entity =
                new UserCredentialsEntity(
                        userEntity,
                        encodedPassword,
                        role
                );

        jpaCredentialsRepository.save(entity);
    }
}
