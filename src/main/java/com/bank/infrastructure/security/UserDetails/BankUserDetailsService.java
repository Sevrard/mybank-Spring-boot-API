package com.bank.infrastructure.security.UserDetails;

import com.bank.infrastructure.security.userCredentials.JpaUserCredentialsRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class BankUserDetailsService implements UserDetailsService {

    private final JpaUserCredentialsRepository repository;

    public BankUserDetailsService(JpaUserCredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return repository.findByUser_Email(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email)
                );
    }
}
