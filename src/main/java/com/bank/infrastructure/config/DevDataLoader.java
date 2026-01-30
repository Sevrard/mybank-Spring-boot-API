package com.bank.infrastructure.config;

import com.bank.infrastructure.persistence.user.JpaUserRepository;
import com.bank.infrastructure.persistence.user.UserEntity;
import com.bank.infrastructure.security.UserDetails.Role;
import com.bank.infrastructure.security.userCredentials.JpaUserCredentialsRepository;
import com.bank.infrastructure.security.userCredentials.UserCredentialsEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
@Profile({"dev", "test"})
public class DevDataLoader implements CommandLineRunner {

    private final JpaUserRepository userRepository;
    private final JpaUserCredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;

    public DevDataLoader(
            JpaUserRepository userRepository,
            JpaUserCredentialsRepository credentialsRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {

//        if (userRepository.existsByEmail("john.doe@bank.com")) {
//            return;
//        }

        UserEntity user = new UserEntity(
                null,
                "john.doe@bank.com",
                "John",
                "Doe"
        );
        UserEntity savedUser = userRepository.save(user);
        UserCredentialsEntity credentials = new UserCredentialsEntity(
                        savedUser,
                        passwordEncoder.encode("password123"),
                        Role.USER
        );
        credentialsRepository.save(credentials);
        System.out.println("✅ DEV user created: john.doe@bank.com / password123");
    }
}
