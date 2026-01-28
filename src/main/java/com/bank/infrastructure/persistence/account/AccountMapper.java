package com.bank.infrastructure.persistence.account;

import com.bank.domain.Account;

public class AccountMapper {
    private AccountMapper(){}

    public static Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getOwner(),
                entity.getBalance()
        );
    }

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.getId(),
                account.getOwner(),
                account.getBalance()
        );
    }
}
