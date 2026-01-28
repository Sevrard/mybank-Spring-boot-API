package com.bank.infrastructure.config;
import com.bank.domain.repository.TransactionRepository;
import com.bank.infrastructure.persistence.account.JpaAccountRepository;
import com.bank.infrastructure.persistence.account.JpaAccountRepositoryAdapter;
import com.bank.infrastructure.persistence.transaction.JpaTransactionRepository;
import com.bank.infrastructure.persistence.transaction.JpaTransactionRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.domain.repository.AccountRepository;
import com.bank.infrastructure.repository.FakeTransactionRepository;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("!test")
public class RepositoryConfig {


    @Bean
    public AccountRepository accountRepository(JpaAccountRepository jpaRepo) {
        return new JpaAccountRepositoryAdapter(jpaRepo);
    }
    @Bean
    public TransactionRepository transactionRepository(JpaTransactionRepository jpaRepo) {
        return new JpaTransactionRepositoryAdapter(jpaRepo);
    }
}


