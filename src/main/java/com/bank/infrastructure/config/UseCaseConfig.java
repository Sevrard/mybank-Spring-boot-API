package com.bank.infrastructure.config;

import com.bank.application.AccountQueryUseCase;
import com.bank.application.CreateAccountUseCase;
import com.bank.application.TransactionQueryUseCase;
import com.bank.application.TransferWithAuditUseCase;
import com.bank.domain.repository.AccountRepository;
import com.bank.domain.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig{
    @Bean
    public TransferWithAuditUseCase transferWithAuditUseCase(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository
    ) {
        return new TransferWithAuditUseCase(
                accountRepository,
                transactionRepository
        );
    }

    @Bean
    public TransactionQueryUseCase transactionQueryUseCase(
            TransactionRepository transactionRepository
    ) {
        return new TransactionQueryUseCase(
                transactionRepository
        );
    }

    @Bean
    public CreateAccountUseCase createAccountUseCase(
            AccountRepository accountRepository
    ) {
        return new CreateAccountUseCase(
               accountRepository
        );
    }

    @Bean
    public AccountQueryUseCase accountQueryUseCase(
            AccountRepository accountRepository
    ) {
        return new AccountQueryUseCase(
                accountRepository
        );
    }

}