package com.bank.infrastructure.persistence.account;

import com.bank.domain.Account;

public class AccountMapper {
    private AccountMapper(){}

    public static Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getName(),
                entity.getBalance(),
                entity.getUserId()
        );
    }

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.getId(),
                account.getName(),
                account.getBalance(),
                account.getUserId()
        );
    }
}
