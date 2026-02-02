package com.bank.application;

import com.bank.domain.Role;
import com.bank.domain.User;
import com.bank.domain.repository.UserCredentialsRepository;
import com.bank.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Transactional
public class RecordUserUseCase {

    private static final Logger log = LoggerFactory.getLogger(RecordUserUseCase.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialsRepository credentialsRepository;

    public RecordUserUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserCredentialsRepository credentialsRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.credentialsRepository = credentialsRepository;
    }

    public void execute(String firstname, String lastname,String email, String password) {
        log.info("Try to create : name={} t={} email={} / password={}",firstname,lastname, email, password);
        User user = new User(UUID.randomUUID(), email, firstname, lastname);
        User savedUser = userRepository.save(user);
        String encodedPassword = passwordEncoder.encode(password);
        credentialsRepository.save(
                savedUser.getId(),
                encodedPassword,
                Role.USER
        );
        log.info("✅ User + credentials created for {}", email);
    }
}
