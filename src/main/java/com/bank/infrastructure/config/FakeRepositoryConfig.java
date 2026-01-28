package com.bank.infrastructure.config;

import com.bank.domain.repository.AccountRepository;
import com.bank.domain.repository.TransactionRepository;
import com.bank.infrastructure.repository.FakeAccountRepository;
import com.bank.infrastructure.repository.FakeTransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class FakeRepositoryConfig {

    @Bean
    public AccountRepository accountRepository() {
        return new FakeAccountRepository();
    }
    @Bean
    TransactionRepository transactionRepository() {
        return new FakeTransactionRepository();
    }
}