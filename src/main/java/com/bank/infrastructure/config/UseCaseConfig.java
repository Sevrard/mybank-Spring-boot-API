package com.bank.infrastructure.config;

import com.bank.application.*;
import com.bank.domain.repository.AccountRepository;
import com.bank.domain.repository.TransactionRepository;
import com.bank.domain.repository.UserCredentialsRepository;
import com.bank.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseConfig{
    @Bean
    public RecordTransferWithAuditUseCase transferWithAuditUseCase(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository
    ) {
        return new RecordTransferWithAuditUseCase(
                accountRepository,
                transactionRepository
        );
    }

    /// TRANSACTION ///
    @Bean
    public QueryTransactionUseCase transactionQueryUseCase(TransactionRepository transactionRepository) {
        return new QueryTransactionUseCase(
                transactionRepository
        );
    }

    ///  ACCOUNT //
    @Bean
    public RecordAccountUseCase createAccountUseCase(AccountRepository accountRepository) {
        return new RecordAccountUseCase(
               accountRepository
        );
    }
    @Bean
    public QueryAccountUseCase accountQueryUseCase(AccountRepository accountRepository) {
        return new QueryAccountUseCase(
                accountRepository
        );
    }

    ///  USER ////
    @Bean
    public RecordUserUseCase createUserUseCase(
            UserRepository userRepository,
            UserCredentialsRepository credentialsRepository,
            PasswordEncoder passwordEncoder
    ) {
        return new RecordUserUseCase(
                userRepository,
                passwordEncoder,
                credentialsRepository
        );
    }

    @Bean
    public QueryUserUseCase userQueryUseCase(UserRepository userRepository) {
        return new QueryUserUseCase(
                userRepository
        );
    }

}