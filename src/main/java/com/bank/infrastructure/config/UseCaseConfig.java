package com.bank.infrastructure.config;

import com.bank.application.*;
import com.bank.domain.repository.AccountRepository;
import com.bank.domain.repository.TransactionRepository;
import com.bank.domain.repository.UserRepository;
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

    /// TRANSACTION ///
    @Bean
    public TransactionQueryUseCase transactionQueryUseCase(TransactionRepository transactionRepository) {
        return new TransactionQueryUseCase(
                transactionRepository
        );
    }

    ///  ACCOUNT //
    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository) {
        return new CreateAccountUseCase(
               accountRepository
        );
    }
    @Bean
    public AccountQueryUseCase accountQueryUseCase(AccountRepository accountRepository) {
        return new AccountQueryUseCase(
                accountRepository
        );
    }

    ///  USER ////
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(
                userRepository
        );
    }
    @Bean
    public UserQueryUseCase userQueryUseCase( UserRepository userRepository) {
        return new UserQueryUseCase(
                userRepository
        );
    }

}